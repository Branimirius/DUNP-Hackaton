package com.hackathon.server.model.geoEntity;

import com.hackathon.server.model.AbstractStatusEntity;
import com.hackathon.server.model.geoEntity.enums.GeoEntityCategory;
import com.hackathon.server.model.user.User;
import com.hackathon.server.model.user.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geo_entity")
public class GeoEntity extends AbstractStatusEntity {

    @Column(name = "category", nullable = false)
    private GeoEntityCategory category;

    @Column(name = "latitude", precision = 18, scale = 15)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 18, scale = 15)
    private BigDecimal longitude;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "like_numbers")
    private Integer likeNumbers;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
