package com.sample.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used on fields only. Used to providing the HATEOAS support.
 *
 * @author puspender
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestResource {

    /**
     * Entity class under which this annotation is used.
     * Algo: The Resource Assembler will look for the path based on the {@link org.springframework.hateoas.ExposesResourceFor} on controllers. This would help in
     * identifying the correct path
     * @return Class type of provided entity
     */
    Class<?> entity();

    /**
     * The path segment under which this resource is to be exported.
     *
     * @return A valid path segment.
     */
     String path();

    /**
     * The rel value to use when generating links to this resource.
     *
     * @return A valid rel value.
     */
    String rel();

    String description();

}
