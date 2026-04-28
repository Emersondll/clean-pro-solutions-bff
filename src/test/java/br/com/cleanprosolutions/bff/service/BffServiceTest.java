package br.com.cleanprosolutions.bff.service;

import br.com.cleanprosolutions.bff.client.CatalogClient;
import br.com.cleanprosolutions.bff.client.UserClient;
import br.com.cleanprosolutions.bff.dto.HomeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link BffService}.
 *
 * @author Clean Pro Solutions Team
 */
@ExtendWith(MockitoExtension.class)
class BffServiceTest {

    @Mock
    private UserClient userClient;

    @Mock
    private CatalogClient catalogClient;

    @InjectMocks
    private BffService service;

    private Map<String, Object> userProfile;
    private List<Map<String, Object>> services;

    @BeforeEach
    void setUp() {
        userProfile = Map.of("id", "user-1", "name", "John Doe");
        services = List.of(Map.of("id", "svc-1", "name", "Cleaning"));
    }

    @Test
    @DisplayName("shouldAggregateHomeData")
    void shouldAggregateHomeData() {
        when(userClient.getUserById("user-1")).thenReturn(userProfile);
        when(catalogClient.getAllServices()).thenReturn(services);

        final HomeResponse response = service.getHomeData("user-1");

        assertThat(response).isNotNull();
        assertThat(response.userProfile()).isEqualTo(userProfile);
        assertThat(response.availableServices()).isEqualTo(services);
    }

    @Test
    @DisplayName("shouldReturnFallbackDataWhenExceptionOccurs")
    void shouldReturnFallbackDataWhenExceptionOccurs() {
        final HomeResponse response = service.getHomeDataFallback("user-1", new RuntimeException("Service down"));

        assertThat(response).isNotNull();
        assertThat(response.userProfile().get("name")).isEqualTo("User (Offline Mode)");
        assertThat(response.availableServices()).isEmpty();
    }
}
