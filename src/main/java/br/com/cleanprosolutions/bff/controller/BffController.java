package br.com.cleanprosolutions.bff.controller;

import br.com.cleanprosolutions.bff.dto.HomeResponse;
import br.com.cleanprosolutions.bff.service.BffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for the BFF exposing aggregated endpoints for front-ends.
 *
 * @author Clean Pro Solutions Team
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/bff")
@RequiredArgsConstructor
@Tag(name = "BFF", description = "Backend for Frontend endpoints")
public class BffController {

    private final BffService service;

    @GetMapping("/home/{userId}")
    @Operation(summary = "Get aggregated home data for a specific user")
    public ResponseEntity<HomeResponse> getHomeData(@PathVariable final String userId) {
        log.info("GET /bff/home/{}", userId);
        return ResponseEntity.ok(service.getHomeData(userId));
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Proxy login request to Auth Service")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> request) {
        log.info("POST /bff/auth/login");
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/auth/register")
    @Operation(summary = "Proxy register request to Auth Service")
    public ResponseEntity<Void> register(@RequestBody Map<String, Object> request) {
        log.info("POST /bff/auth/register");
        service.register(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/auth/refresh")
    @Operation(summary = "Proxy refresh token request to Auth Service")
    public ResponseEntity<Map<String, Object>> refresh(@RequestBody Map<String, Object> request) {
        log.info("POST /bff/auth/refresh");
        return ResponseEntity.ok(service.refresh(request));
    }
}
