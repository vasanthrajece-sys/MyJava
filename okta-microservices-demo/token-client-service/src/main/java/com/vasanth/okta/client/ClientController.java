package com.vasanth.okta.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ResourceCallerService resourceCallerService;

    public ClientController(ResourceCallerService resourceCallerService) {
        this.resourceCallerService = resourceCallerService;
    }

    @GetMapping("/call-resource")
    public String callResource() {
        return resourceCallerService.callResourceService();
    }
}
