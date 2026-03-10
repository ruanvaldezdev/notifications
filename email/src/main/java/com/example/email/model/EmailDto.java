package com.example.email.model;

import java.util.UUID;

public record EmailDto(UUID userId,String recipient,String message, String data) {

}
