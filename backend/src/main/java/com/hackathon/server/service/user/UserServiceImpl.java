package com.hackathon.server.service.user;

import com.hackathon.server.config.Constants;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.auth.AuthResponse;
import com.hackathon.server.model.dto.user.PasswordDTO;
import com.hackathon.server.model.dto.user.PasswordUpdateDTO;
import com.hackathon.server.model.dto.user.UserDTO;
import com.hackathon.server.model.dto.user.UserRegisterDTO;
import com.hackathon.server.model.user.User;
import com.hackathon.server.model.user.UserGroup;
import com.hackathon.server.model.user.UserPasswordResetToken;
import com.hackathon.server.model.user.enums.UserType;
import com.hackathon.server.repository.user.UserGroupRepository;
import com.hackathon.server.repository.user.UserPasswordResetTokenRepository;
import com.hackathon.server.repository.user.UserRepository;
import com.hackathon.server.service.util.UtilService;
import com.hackathon.server.util.jwt.JwtUtil;
import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.event.listener.NewUserMailEvent;
import com.hackathon.server.util.LocalFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final JwtUtil jwtUtil;
    private final UserPasswordResetTokenRepository userPasswordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserGroupRepository userGroupRepository;
    private final UtilService utilService;
    private final LocalFileManager localFileManager;

    @Override
    @Transactional
    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        User duplicateUser = userRepository.findOneByUsernameAndEntityStatus(userRegisterDTO.getUsername(), EntityStatus.REGULAR);
        if(duplicateUser  != null) {
            throw new BadRequestException(ErrorMessageConstants.USERNAME_ALREADY_EXIST);
        }
        User newUser = convertFromRegisterDTO(userRegisterDTO);
        if(userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword())) {
            newUser.setPassword(passwordEncoder.encode(String.valueOf(userRegisterDTO.getPassword())));
        } else {
            throw new BadRequestException(ErrorMessageConstants.PASSWORDS_DOESNT_MATCH);
        }
        newUser.setUserType(UserType.USER);
        newUser.setActive(Boolean.TRUE);
        newUser.setEntityStatus(EntityStatus.REGULAR);
        List<UserGroup> userGroups = new ArrayList<>();
        userGroups.add(userGroupRepository.findById(Constants.USER_GROUP_ID).get());
        newUser.setUserGroups(userGroups);

        User user = userRepository.save(newUser);
        return convertToDTO(user);
    }


    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findByEntityStatus(EntityStatus.REGULAR)
                .stream()
                .map(u -> convertToDTO(u))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = convertFromDTO(userDTO);
        newUser.setEntityStatus(EntityStatus.REGULAR);
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        return convertToDTO(userRepository.save(newUser));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO, Optional<MultipartFile> image) {
        User userForUpdate = userRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
        userForUpdate.setFirstName(userDTO.getFirstName());
        userForUpdate.setLastName(userDTO.getLastName());
        userForUpdate.setEmail(userDTO.getEmail());

        if(!userForUpdate.getUserType().equals(userDTO.getUserType())){
            if(userDTO.getUserType() == UserType.USER) {
                List<UserGroup> userGroups = new ArrayList<>();
                userGroups.add(userGroupRepository.findById(Constants.USER_GROUP_ID).get());
                userForUpdate.setUserGroups(userGroups);
            }else if(userDTO.getUserType() == UserType.ADMINISTRATOR) {
                List<UserGroup> userGroups = new ArrayList<>();
                userGroups.add(userGroupRepository.findById(Constants.ADMINISTRATOR_GROUP_ID).get());
                userForUpdate.setUserGroups(userGroups);
            }
            userForUpdate.setUserType(userDTO.getUserType());
        }

        if(image.isPresent()) {
            try {
                userForUpdate.setImageUrl(
                        utilService.saveFileToSystem(
                                image.get().getBytes(),
                                localFileManager.USER_PROFILE_IMAGE_FILES_PATH,
                                "AWS_FILE_PATH"
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
                throw new BadRequestException(ErrorMessageConstants.FILE_UPLOAD_ERROR);
            }
        }

        User updatedUser = userRepository.save(userForUpdate);
        return convertToDTO(updatedUser);
    }

    @Override
    @Transactional
    public UserDTO deleteUser(Long userId) {
        User user = userRepository.findByIdAndEntityStatus(userId, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
        user.setEntityStatus(EntityStatus.DELETED);
        return convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUser(Long userId) {
        User user = userRepository.findByIdAndEntityStatus(userId, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
        return convertToDTO(user);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameAndEntityStatus(username, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND_BY_USERNAME));
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setUserGroups(user.getUserGroups());
        dto.setUserType(user.getUserType());
        dto.setEmail(user.getEmail());
        dto.setImageUrl(user.getImageUrl());
        return dto;
    }

    @Override
    public User convertFromDTO(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setUserGroups(dto.getUserGroups());
        user.setUserType(dto.getUserType());
        user.setEmail(dto.getEmail());
        user.setImageUrl(dto.getImageUrl());
        return user;
    }

    @Override
    public User convertFromRegisterDTO(UserRegisterDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    public AuthResponse generateAuthResponse(String username) {
        AuthResponse authResponse = new AuthResponse();
        User user = userRepository.findByUsernameAndEntityStatus(username, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND_BY_USERNAME));


        String token = jwtUtil.generateToken(username);
        authResponse.setUser(convertToDTO(user));
        authResponse.setToken(token);
        return authResponse;
    }

    @Override
    @Transactional
    public void sendCredentialsEmail(List<Long> usersId) {
        List<User> users= userRepository.findAllById(usersId);
        for(User user : users){
            sendPasswordForNewUser(user);
        }
    }

    @Transactional
    private void sendPasswordForNewUser(User user){
        if (user.getEmail().isEmpty())
            throw new BadRequestException("EMAIL_IS_MISSING");

        UserPasswordResetToken token = new UserPasswordResetToken();
        token.setUser(user);
        token.setEntityStatus(EntityStatus.REGULAR);
        token.setToken(UUID.randomUUID().toString());
        token.setCreatedBy(getCurrentUser().getUsername());
        token.setCreatedDate(Instant.now());
        token = userPasswordResetTokenRepository.save(token);
        NewUserMailEvent mailEvent = NewUserMailEvent.builder()
                .user(user)
                .token(token.getToken())
                .build();
        eventPublisher.publishEvent(mailEvent);
    }

    @Override
    @Transactional
    public void sendLinkForResetPassword(String email) {
        User user = userRepository.findByEmailAndEntityStatus(email, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND_BY_EMAIL));
        UserPasswordResetToken token = new UserPasswordResetToken();
        token.setUser(user);
        token.setEntityStatus(EntityStatus.REGULAR);
        token.setToken(UUID.randomUUID().toString());
        token = userPasswordResetTokenRepository.save(token);
        NewUserMailEvent mailEvent = NewUserMailEvent.builder()
                .user(user)
                .token(token.getToken())
                .build();
        eventPublisher.publishEvent(mailEvent);
    }


    @Override
    public UserPasswordResetToken validatePasswordResetToken(String token) {
        final UserPasswordResetToken passToken = userPasswordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new BadRequestException("TOKEN_NOT_FOUND"));
        if (isTokenExpired(passToken)) {
            throw new BadRequestException("TOKEN_EXPIRED");
        }
        return passToken;
    }

    @Override
    @Transactional
    public UserDTO resetPassword(PasswordDTO passwordDTO) {
        UserPasswordResetToken validatedToken = validatePasswordResetToken(passwordDTO.getToken());
        User user = validatedToken.getUser();
        user.setPassword(passwordEncoder.encode(String.valueOf(passwordDTO.getNewPassword())));
        user.setActive(true);
        User updatedUser = userRepository.save(user);

        validatedToken.setUsed(true);
        userPasswordResetTokenRepository.save(validatedToken);
//        userSearchRepository.save(UserEDTO.convertToEDTO(user));
        return convertToDTO(updatedUser);
    }


    @Override
    @Transactional
    public UserDTO updatePassword(Long userId, PasswordUpdateDTO passwordUpdateDTO) {
        User user = userRepository.findByIdAndEntityStatus(userId, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));

        if(!passwordEncoder.matches(passwordUpdateDTO.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("CURRENT_PASSWORD_IS_NOT_CORRECT");
        }
        if(!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
            throw new BadRequestException("PASSWORDS_DOESNT_MATCH");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    @Transactional
    public UserDTO uploadProfileImage(Long id, MultipartFile profileImage) {
        User user = userRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
        try {
            user.setImageUrl(
                    utilService.saveFileToSystem(
                            profileImage.getBytes(),
                            localFileManager.USER_PROFILE_IMAGE_FILES_PATH,
                            "AWS_FILE_PATH"
                    )
            );
            userRepository.save(user);
            return convertToDTO(user);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorMessageConstants.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public byte[] getProfileImage(Long id) {
        User user = userRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.EMPLOYEE_NOT_FOUND));

        String identifier = user.getImageUrl();
        if(identifier != null) {
            byte[] file =
                    utilService.downloadFileFromSystem(
                        identifier,
                        localFileManager.USER_PROFILE_IMAGE_FILES_PATH,
                        "AWS_FILE_PATH"
                    );
            return file;
        } else {
            return null;
        }
    }

    private boolean isTokenExpired(UserPasswordResetToken passToken){
        return passToken.getCreatedDate().plusSeconds(2592000).isBefore(Instant.now()) || passToken.isUsed();
    }

}
