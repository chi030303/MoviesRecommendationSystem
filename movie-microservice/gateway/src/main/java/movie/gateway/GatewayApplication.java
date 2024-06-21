package movie.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//     @Bean
//     public RouteLocator customRouteLocator(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory) {
//         return builder.routes()
//                 .route("movie", r -> r.path("/movie/**")
//                         .filters(f -> f.filter(filterFactory.apply()))
//                         .uri("lb://movie"))
//                 .route("test", r -> r.path("/test/**")
//                         .filters(f -> f.filter(filterFactory.apply()))
//                         .uri("lb://test"))
//                 .build();
//     }
}
