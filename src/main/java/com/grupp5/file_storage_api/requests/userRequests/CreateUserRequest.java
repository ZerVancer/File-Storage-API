package com.grupp5.file_storage_api.requests.userRequests;

public record CreateUserRequest(
    String eMail,
    String password
) {
}
