package com.example.sms.model;

public record SmsDto(String channel,String recipient,String message,String priority) {

}
