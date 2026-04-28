package br.com.cleanprosolutions.bff.dto;

import java.util.List;
import java.util.Map;

/**
 * Aggregated response for the Home Page of the Clean Pro app.
 *
 * @author Clean Pro Solutions Team
 */
public record HomeResponse(
        Map<String, Object> userProfile,
        List<Map<String, Object>> availableServices
) {}
