package com.vasanth.okta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceCallerService {
    private final RestTemplate restTemplate;
    private final OktaTokenService oktaTokenService;
    private final String resourceServiceUrl;

    public ResourceCallerService(
            RestTemplate restTemplate,
            OktaTokenService oktaTokenService,
            @Value("${resource-service.url}") String resourceServiceUrl) {
        this.restTemplate = restTemplate;
        this.oktaTokenService = oktaTokenService;
        this.resourceServiceUrl = resourceServiceUrl;
    }

    public String callResourceService() {
        String token = oktaTokenService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                resourceServiceUrl,
                HttpMethod.GET,
                request,
                String.class);

        return response.getBody();
    }
}
