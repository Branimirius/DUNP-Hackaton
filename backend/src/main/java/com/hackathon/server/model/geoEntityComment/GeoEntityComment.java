package com.hackathon.server.model.geoEntityComment;


import com.hackathon.server.model.AbstractStatusEntity;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geo_entity_comment")
public class GeoEntityComment extends AbstractStatusEntity {

    @ManyToOne
    @JoinColumn(name = "geo_entity_id")
    private GeoEntity geoEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_text")
    private String commentText;

}
