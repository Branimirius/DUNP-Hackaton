package com.hackathon.server.repository.predicate;

import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntity.enums.GeoEntityCategory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GeoEntitySpecification {

    public static Specification<GeoEntity> byEntityStatus(EntityStatus entityStatus) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("entityStatus"), entityStatus);
    }

    public static Specification<GeoEntity> byCategories(List<GeoEntityCategory> categories) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("category").in(categories));
    }

}
