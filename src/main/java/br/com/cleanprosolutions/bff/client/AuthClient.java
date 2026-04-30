package br.com.cleanprosolutions.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Feign client for the Authentication Service.
 *
 * @author Clean Pro Solutions Team
 */
@FeignClient(name = "auth-service", path = "/auth")
public interface AuthClient {

    @PostMapping("/login")
    Map<String, Object> login(@RequestBody Map<String, Object> request);

    @PostMapping("/register")
    void register(@RequestBody Map<String, Object> request);

    @PostMapping("/refresh")
    Map<String, Object> refresh(@RequestBody Map<String, Object> request);
}
