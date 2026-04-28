package br.com.cleanprosolutions.bff.controller;

import br.com.cleanprosolutions.bff.dto.HomeResponse;
import br.com.cleanprosolutions.bff.service.BffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
