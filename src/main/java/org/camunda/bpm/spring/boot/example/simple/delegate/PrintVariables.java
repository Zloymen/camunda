package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.slf4j.Logger;

import java.util.Map;

public interface PrintVariables {

    default <T, K> void printAll(Logger log, Map<T,K> map){
        for(T key : map.keySet()){
            log.info("key:{} value:{}", key, map.get(key));
        }
    }
}
