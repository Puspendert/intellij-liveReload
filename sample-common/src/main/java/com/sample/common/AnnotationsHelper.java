package com.sample.common;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationsHelper {

    public static List<RestResource> getRestResource(Object entity) {

        Stream<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields());

        List<RestResource> restResources  = fields
                .filter(field -> field.isAnnotationPresent(RestResource.class))
                .map(field -> field.getAnnotation(RestResource.class)).collect(Collectors.toList());

        return restResources;

    }

}
