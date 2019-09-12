package com.sample.rest.web.hateoas;

import com.sample.common.AnnotationsHelper;
import com.sample.common.ParentEntity;
import com.sample.common.RestResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestResourceAssembler<T extends ParentEntity> implements ResourceAssembler<T, Resource<T>> {

    private EntityLinks entityLinks;

    public RestResourceAssembler(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public  Resource<T>  toResource(T entity) {
        Resource<T> resource = new Resource<>(entity);
        resource.add(entityLinks.linkToSingleResource(entity.getClass(), entity.getId()).withSelfRel());

        List<RestResource> restResources = AnnotationsHelper.getRestResource(entity);
        for (RestResource restResource :restResources ) {
            resource.add(entityLinks.linkFor(entity.getClass()).slash(entity.getId()).slash(restResource.path()).withRel(restResource.rel()).withTitle(restResource.description()));
        }

        return resource;
    }

    public Resources<Object> toResources(List<T> entities, Class clazz) {

        if (entities.isEmpty()) {
            EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(clazz);
            Resources<Object> resources = new Resources<>(Arrays.asList(wrapper));
            return resources;
        }

        List<Object> resources = entities.stream().map(company -> toResource(company)).collect(Collectors.toList());
        Resources<Object> collectionResources = new Resources<>(resources);

        return collectionResources;
    }
}
