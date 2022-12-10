package com.hackathon.server.model.dto.geoEntityComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoEntityCommentDTO {

    private Long id;
    private Long geoEntityId;
    private Long userId;
    private String commentText;
    private String username;

}
