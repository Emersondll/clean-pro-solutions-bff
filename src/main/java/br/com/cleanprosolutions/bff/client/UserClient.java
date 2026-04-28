package br.com.cleanprosolutions.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * Feign client for the User Service.
 *
 * @author Clean Pro Solutions Team
 */
@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}")
    Map<String, Object> getUserById(@PathVariable("id") String id);
}
