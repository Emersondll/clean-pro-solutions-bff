package br.com.cleanprosolutions.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Feign client for the Catalog Service.
 *
 * @author Clean Pro Solutions Team
 */
@FeignClient(name = "catalog-service", path = "/services")
public interface CatalogClient {

    @GetMapping("/{id}")
    Map<String, Object> getServiceById(@PathVariable("id") String id);

    @GetMapping
    List<Map<String, Object>> getAllServices();
}
