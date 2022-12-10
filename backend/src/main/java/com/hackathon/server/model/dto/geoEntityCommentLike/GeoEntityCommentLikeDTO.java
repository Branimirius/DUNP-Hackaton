package com.hackathon.server.model.dto.geoEntityCommentLike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoEntityCommentLikeDTO {

    private Long id;
    private Long geoEntityCommentId;
    private Long userId;

}
