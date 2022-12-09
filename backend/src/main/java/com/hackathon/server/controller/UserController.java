package com.hackathon.server.controller;

import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.model.dto.user.PasswordDTO;
import com.hackathon.server.model.dto.user.PasswordUpdateDTO;
import com.hackathon.server.model.dto.user.ResetPasswordEmailDTO;
import com.hackathon.server.model.dto.user.UserDTO;
import com.hackathon.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 Basic user CRUD controller
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

//
    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestPart(value = "user") UserDTO userDTO,
                                              @RequestPart(value = "image") Optional<MultipartFile> image) {
        if(image.isPresent()) {
            if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(image.get().getContentType())) {
                throw new BadRequestException(ErrorMessageConstants.FILE_TYPE_ERROR);
            }
        }
        return new ResponseEntity<>(userService.updateUser(id, userDTO, image), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return null;
    }

    @GetMapping(value = "/current")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return new ResponseEntity<>(userService.convertToDTO(userService.getCurrentUser()), HttpStatus.OK);
    }

    @PostMapping("/send-credentials-to-all")
    @PreAuthorize("hasPermission('employee', 'read')")
    public ResponseEntity<Void> sendCredentialsEmailToAll(@RequestBody List<Long> usersId) {
        userService.sendCredentialsEmail(usersId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/send-reset-password-email")
    public ResponseEntity<Void> sendLinkForResetPassword(@RequestBody ResetPasswordEmailDTO resetPasswordEmailDTO) {
        userService.sendLinkForResetPassword(resetPasswordEmailDTO.getEmail());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}/profile-image", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> uploadProfileImage(@PathVariable("id") Long id, @RequestPart(value = "image") MultipartFile image) {
        return new ResponseEntity<>(userService.uploadProfileImage(id, image), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/profile-image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("id") Long id) {
        byte[] image = userService.getProfileImage(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Void> validateToken(@RequestBody String token) {
        userService.validatePasswordResetToken(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordDTO passwordDTO) {
        UserDTO updated = userService.resetPassword(passwordDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/update-password")
    public ResponseEntity<UserDTO> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        UserDTO updatedUser = userService.updatePassword(id, passwordUpdateDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
