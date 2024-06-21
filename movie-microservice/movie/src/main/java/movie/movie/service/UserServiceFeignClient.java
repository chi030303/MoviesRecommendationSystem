package movie.movie.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import movie.movie.config.FeignConfig;

@FeignClient(name = "user", url = "http://localhost:8094/user", configuration = FeignConfig.class)
public interface UserServiceFeignClient {

	@GetMapping("/first")
    boolean isFirst();
}
