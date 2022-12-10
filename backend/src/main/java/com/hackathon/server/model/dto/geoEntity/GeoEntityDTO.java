package com.hackathon.server.model.dto.geoEntity;

import com.hackathon.server.model.geoEntity.enums.GeoEntityCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoEntityDTO {

    private Long id;

    private GeoEntityCategory category;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String imageUrl;

    private Integer likeNumbers;

    private String description;

    private Long userId;

}
