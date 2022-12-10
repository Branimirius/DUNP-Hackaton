package com.hackathon.server.model.dto.geoEntityLike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoEntityLikeDTO {

    private Long id;
    private Long geoEntityId;
    private Long userId;

}
