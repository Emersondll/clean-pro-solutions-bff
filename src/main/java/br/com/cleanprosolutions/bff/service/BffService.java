package br.com.cleanprosolutions.bff.service;

import br.com.cleanprosolutions.bff.client.CatalogClient;
import br.com.cleanprosolutions.bff.client.UserClient;
import br.com.cleanprosolutions.bff.dto.HomeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * BFF Service to aggregate data from multiple microservices.
 *
 * @author Clean Pro Solutions Team
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BffService {

    private final UserClient userClient;
    private final CatalogClient catalogClient;

    /**
     * Builds the home page response by aggregating user profile and available services.
     * Uses Resilience4j Circuit Breaker to handle failures gracefully.
     *
     * @param userId the user ID
     * @return the aggregated home response
     */
    @CircuitBreaker(name = "bffService", fallbackMethod = "getHomeDataFallback")
    public HomeResponse getHomeData(final String userId) {
        log.info("Aggregating home data for userId: {}", userId);

        final Map<String, Object> userProfile = userClient.getUserById(userId);
        final List<Map<String, Object>> services = catalogClient.getAllServices();

        return new HomeResponse(userProfile, services);
    }

    /**
     * Fallback method invoked when the circuit is open or a service is down.
     *
     * @param userId the user ID
     * @param ex the exception that triggered the fallback
     * @return a safe fallback response
     */
    public HomeResponse getHomeDataFallback(final String userId, final Exception ex) {
        log.warn("Fallback triggered for getHomeData(userId: {}) due to: {}", userId, ex.getMessage());

        final Map<String, Object> fallbackProfile = Map.of(
                "id", userId,
                "name", "User (Offline Mode)"
        );

        return new HomeResponse(fallbackProfile, Collections.emptyList());
    }
}
