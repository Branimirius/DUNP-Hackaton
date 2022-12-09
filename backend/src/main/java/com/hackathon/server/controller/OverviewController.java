package com.hackathon.server.controller;

import com.hackathon.server.util.LocalFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OverviewController {

    private final LocalFileManager localFileManager;

    @RequestMapping(method = RequestMethod.GET, value = "/local/user-profile-image/{imageUrl}")
    public ResponseEntity<byte[]> getLocalProfilePicture(@PathVariable String imageUrl) {
        return new ResponseEntity<>(localFileManager.downloadFile(imageUrl, localFileManager.USER_PROFILE_IMAGE_FILES_PATH), HttpStatus.OK);
    }


}
