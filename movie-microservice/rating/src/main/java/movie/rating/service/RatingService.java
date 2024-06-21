package movie.rating.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import movie.rating.dto.RatingDto;
import movie.rating.dto.RatingRankDto;
import movie.rating.entity.Rating;
import movie.rating.entity.RatingExample;
import movie.rating.mapper.RatingMapper;

@Service
public class RatingService {

	@Autowired
	private RatingMapper ratingMapper;

	public void rating(RatingDto ratingDto, Jwt jwt){

		Rating rating = new Rating();
        rating.setMid(ratingDto.getMid());
        rating.setRating(ratingDto.getRating());
        rating.setUid(Integer.parseInt(jwt.getClaimAsString("sub")));
		
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        rating.setTimestamp(timestamp);

		ratingMapper.insert(rating);
	}

	// public RatingRankDto ratingRank(Integer mid){
	// 	RatingExample ratingExample = new RatingExample();
	// 	ratingExample.createCriteria().andMidEqualTo(mid);
	// 	ratingMapper.selectByExample(ratingExample);


	// }
}
