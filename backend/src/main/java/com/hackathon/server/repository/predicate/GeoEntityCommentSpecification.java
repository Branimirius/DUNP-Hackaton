package com.hackathon.server.repository.predicate;

import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import org.springframework.data.jpa.domain.Specification;

public class GeoEntityCommentSpecification {

    public static Specification<GeoEntityComment> byEntityStatus(EntityStatus entityStatus) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("entityStatus"), entityStatus);
    }

    public static Specification<GeoEntityComment> byGeoEntity(Long geoEntityId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("geoEntity").get("id"), geoEntityId);
    }

}
