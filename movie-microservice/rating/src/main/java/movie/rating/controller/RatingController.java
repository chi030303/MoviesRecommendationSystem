package movie.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import movie.rating.dto.RatingDto;
import movie.rating.service.RatingService;

@RestController
// @RequestMapping()
public class RatingController {

	@Autowired
	RatingService ratingService;

	@PostMapping("rating")
	public String rating(@RequestBody RatingDto ratingDto){
		JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();

		
		return ratingService.rating(ratingDto,jwt);
	}

	@GetMapping("rate")
	public Double getRate(Integer mid){
		JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
		return ratingService.getRate(mid,jwt);
	}
}
