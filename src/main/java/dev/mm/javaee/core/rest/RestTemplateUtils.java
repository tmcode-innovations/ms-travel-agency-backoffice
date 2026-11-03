package dev.mm.javaee.core.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mm.javaee.core.utils.jackson.JACKSON;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdez960717@Github
 */
public class RestTemplateUtils {

    //----------------------------------- LIST -----------------------------------\\
    public static <T> List<T> getForList(RestOperations template, String url, Class<T> clazz) throws RuntimeException {
        return getForList(template, url, new HashMap<>(), clazz);
    }

    public static <T> List<T> getForList(RestOperations template, String url, Map<String, Object> uriVariables, Class<T> clazz) throws RuntimeException {
        return objectForList(template, url, HttpMethod.GET, uriVariables, clazz);
    }

    public static <T> List<T> postForList(RestOperations template, String url, Class<T> clazz) throws RuntimeException {
        return postForList(template, url, new HashMap<>(), clazz);
    }

    public static <T> List<T> postForList(RestOperations template, String url, Map<String, Object> uriVariables, Class<T> clazz) throws RuntimeException {
        return objectForList(template, url, HttpMethod.POST, uriVariables, clazz);
    }

    public static <T> List<T> objectForList(RestOperations template, String url, HttpMethod method, Map<String, Object> uriVariables, Class<T> clazz) throws RuntimeException {
        ParameterizedTypeReference<List<T>> type = new ParameterizedTypeReference<List<T>>() {
        };
        ResponseEntity<List<T>> response = template.exchange(url, method, null, type, uriVariables);

        //como es tipo <T> lo que lee es linkedHashMap, hay que castearlo de nuevo al objeto como tal
        try {
            return JACKSON.convert(response.getBody(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------- MAP -----------------------------------\\
    public static <V, K> Map<V, K> postForMap(RestOperations template, String url, Class<V> clazzV, Class<K> clazzK) throws RuntimeException {
        return postForMap(template, url, new HashMap<>(), clazzV, clazzK);
    }

    public static <V, K> Map<V, K> postForMap(RestOperations template, String url, Map<String, Object> uriVariables, Class<V> clazzV, Class<K> clazzK) throws RuntimeException {
        return objectForMap(template, url, HttpMethod.POST, uriVariables, clazzV, clazzK);
    }

    public static <V, K> Map<V, K> getForMap(RestOperations template, String url, Class<V> clazzV, Class<K> clazzK) throws RuntimeException {
        return getForMap(template, url, new HashMap<>(), clazzV, clazzK);
    }

    public static <V, K> Map<V, K> getForMap(RestOperations template, String url, Map<String, Object> uriVariables, Class<V> clazzV, Class<K> clazzK) throws RuntimeException {
        return objectForMap(template, url, HttpMethod.GET, uriVariables, clazzV, clazzK);
    }

    public static <V, K> Map<V, K> objectForMap(RestOperations template, String url, HttpMethod method, Map<String, Object> uriVariables, Class<V> clazzV, Class<K> clazzK) throws RuntimeException {
        ParameterizedTypeReference<Map<V, K>> type = new ParameterizedTypeReference<Map<V, K>>() {
        };
        ResponseEntity<Map<V, K>> response = template.exchange(url, method, null, type, uriVariables);

        //como es tipo <T> lo que lee es linkedHashMap, hay que castearlo de nuevo al objeto como tal
        //return ConverterHandler.convert(response.getBody(), clazz);
        return response.getBody();
    }
}
