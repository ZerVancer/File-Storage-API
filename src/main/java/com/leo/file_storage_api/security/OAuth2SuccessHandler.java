package com.leo.file_storage_api.security;

import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.services.userService.UserService;
import jakarta.annotation.Nullable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final UserService userService;
  private final OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
  private final WebClient webClient = WebClient
      .builder()
      .baseUrl("https://api.github.com")
      .build();

  @Override
  public void onAuthenticationSuccess(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Authentication authentication) throws IOException, ServletException {
    OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;

    String oidcProvider = oauth2Token.getAuthorizedClientRegistrationId();
    String oidcId = oauth2Token.getName();

    var user = userService.getUserByOidc(oidcId, oidcProvider);
    if (user.isEmpty()) {
      var createdUser = createUser(oidcId, oidcProvider,  request, authentication);
      if (createdUser == null) {
        response.getWriter().println("Failed to create account, try again");
      } else {
        response.getWriter().println("Registered account: " + createdUser.getEMail());
      }
    } else {
      response.getWriter().println("Logged in as: " + user.get().getEMail());
    }
  }


  @Nullable
  private User createUser(
      String oidcId,
      String oidcProvider,
      HttpServletRequest request,
      Authentication authentication
  ) {
    var authorizedClient = oAuth2AuthorizedClientRepository.loadAuthorizedClient(
        oidcProvider,
        authentication,
        request
    );

    var accessToken = authorizedClient.getAccessToken().getTokenValue();

    var emailResponse = webClient
        .get()
        .uri("/user/emails")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<List<GetEmailsResponse>>() {
        })
        .block();

    if (emailResponse == null) {
      return null;
    }

    if (emailResponse.getStatusCode() != HttpStatus.OK) {
      return null;
    }

    var emails = emailResponse.getBody();
    if (emails == null || emails.isEmpty()) {
      return null;
    }

    var email = emails.getFirst();

    return userService.createOidcUser(email.getEmail(), oidcId, oidcProvider);
  }

  @Getter
  @Setter
  private static class GetEmailsResponse {
    private String email;
  }
}
