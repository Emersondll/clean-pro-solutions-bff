package br.com.cleanprosolutions.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the Backend For Frontend (BFF) Service.
 *
 * <p>Serves as an API Gateway/Aggregator pattern to optimize frontend requests
 * and handle circuit breaking using Resilience4j.</p>
 *
 * @author Clean Pro Solutions Team
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BffApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }
}
