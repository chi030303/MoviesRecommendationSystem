package movie.genre;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("movie.genre.mapper")
public class GenreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenreApplication.class, args);
	}
}
