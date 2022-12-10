package com.hackathon.server.model.geoEntityCommentLike;

import com.hackathon.server.model.AbstractStatusEntity;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import com.hackathon.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geo_entity_comment_like")
public class GeoEntityCommentLike extends AbstractStatusEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "geo_entity_comment_id")
    private GeoEntityComment geoEntityComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
