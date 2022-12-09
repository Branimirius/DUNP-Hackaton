package com.hackathon.server.service.user;

import com.hackathon.server.model.auth.AuthResponse;
import com.hackathon.server.model.dto.user.PasswordDTO;
import com.hackathon.server.model.dto.user.PasswordUpdateDTO;
import com.hackathon.server.model.dto.user.UserDTO;
import com.hackathon.server.model.dto.user.UserRegisterDTO;
import com.hackathon.server.model.user.User;
import com.hackathon.server.model.user.UserPasswordResetToken;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO register(UserRegisterDTO userRegisterDTO);

    User getCurrentUser();

    UserDTO convertToDTO(User user);

    User convertFromDTO(UserDTO dto);

    User convertFromRegisterDTO(UserRegisterDTO dto);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO, Optional<MultipartFile> image);

    UserDTO deleteUser(Long userId);

    UserDTO getUser(Long userId);

    List<UserDTO> findAllUsers();


    AuthResponse generateAuthResponse(String username);

    void   sendCredentialsEmail(List<Long> usersId);

    UserPasswordResetToken validatePasswordResetToken(String token);

    UserDTO resetPassword(PasswordDTO passwordDTO);

    UserDTO updatePassword(Long userId, PasswordUpdateDTO passwordUpdateDTO);

    void sendLinkForResetPassword(String email);

    UserDTO uploadProfileImage(Long id, MultipartFile profileImage);

    byte[] getProfileImage(Long id);

}
