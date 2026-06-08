package com.vasanth.okta.client;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OktaTokenService {
    private final RestTemplate restTemplate;
    private final OktaTokenProperties properties;

    public OktaTokenService(RestTemplate restTemplate, OktaTokenProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public String getAccessToken() {
        String tokenUrl = UriComponentsBuilder
                .fromHttpUrl(properties.getIssuerUri())
                .path("/v1/token")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(properties.getClientId(), properties.getClientSecret(), StandardCharsets.UTF_8);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.put("grant_type", Collections.singletonList("client_credentials"));
        body.put("scope", Collections.singletonList(properties.getScope()));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        OktaTokenResponse response = restTemplate.postForObject(tokenUrl, request, OktaTokenResponse.class);

        if (response == null || response.getAccessToken() == null) {
            throw new IllegalStateException("Okta did not return an access token");
        }

        return response.getAccessToken();
    }
}
