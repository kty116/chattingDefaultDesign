package com.kyoungae.chatting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatModel {

    private String profileImage;
    private String nicName;
    private String message;

    public ChatModel(String message) {
        this.message = message;
    }
}
