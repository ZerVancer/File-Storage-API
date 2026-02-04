package com.leo.file_storage_api.requests.userRequests;

public record CreateUserRequest(
    String eMail,
    String password
) {
}
