package movie.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("movie.movie.mapper")
@EnableFeignClients
public class MovieApplication {

	public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}