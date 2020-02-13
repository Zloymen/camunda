package org.camunda.bpm.spring.boot.example.simple;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.*;

@Slf4j
public class OtherTest {

    @Test
    public void runListSplit(){

        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

    }

    @Test
    public void testExternalTask(){
        RestTemplate template = new RestTemplate();

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory("http://localhost:9080/rest");

        template.setUriTemplateHandler(defaultUriBuilderFactory);

        Map<String, Object> body = new HashMap<>();

        Map<String, Object> topic = new HashMap<>();

        String auth = "root:root";
        String basicCredentials = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headerForCamunda = new HttpHeaders();
        headerForCamunda.set("Authorization", "Basic " + basicCredentials);
        headerForCamunda.setContentType(MediaType.APPLICATION_JSON_UTF8);

        body.put("workerId", "root");
        body.put("maxTasks", 10);
        body.put("usePriority", true);
        body.put("topics", Arrays.asList(topic));

        topic.put("topicName", "external_topic");
        topic.put("lockDuration", 20000);
        topic.put("variables", Arrays.asList("applicationId"));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headerForCamunda);

        try {
            List<CamundaExternalTask> response = template.exchange("/external-task/fetchAndLock",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<CamundaExternalTask>>() {
                    }).getBody();
            log.info("{}", response);

            if(response == null) return;

            for(CamundaExternalTask task: response){

                Map<String, Object> data = new HashMap<>();
                Map<String, Object> variables = new HashMap<>();

                Value<String> value = new Value<>();
                value.setValue("status" + task.id);

                variables.put("status", value);

                data.put("workerId", "root");
                data.put("variables", variables);

                HttpEntity<Map<String, Object>> requestData = new HttpEntity<>(data, headerForCamunda);

                template.exchange("/external-task/{id}/complete", HttpMethod.POST, requestData,  Void.class, task.id);
            }
        } catch (NestedRuntimeException e) {
            throw new RuntimeException("Сетевая ошибка в test", e);
        }
    }

    @Data
    public static class CamundaExternalTask{
        private String activityId;
        private String anActivityInstanceId;
        private String errorMessage;
        private String executionId;
        private String id;
        private Map<String, Variable> variables = new HashMap<>();
    }

    @Data
    public static class Variable{
        private Object value;
        private String type;
    }


    @Data
    public static  class Value <T>{
        private T value;

    }

}
