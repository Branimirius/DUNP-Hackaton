package com.hackathon.server.event.listener;

import com.hackathon.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserMailEvent {

    private User user;

    private String token;

}
