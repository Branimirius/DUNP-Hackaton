package com.hackathon.server.model.geoEntityLike;

import com.hackathon.server.model.AbstractStatusEntity;
import com.hackathon.server.model.geoEntity.GeoEntity;
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
@Table(name = "geo_entity_like")
public class GeoEntityLike extends AbstractStatusEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "geo_entity_id")
    private GeoEntity geoEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
