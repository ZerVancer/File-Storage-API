package com.leo.file_storage_api.requests.userRequests;

public record LoginUserRequest (
  String eMail,
  String password
) {

}
