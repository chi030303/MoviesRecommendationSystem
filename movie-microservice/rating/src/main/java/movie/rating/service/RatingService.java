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

	public String rating(RatingDto ratingDto, Jwt jwt){

		Rating rating = new Rating();
        rating.setMid(ratingDto.getMid());
        rating.setRating(ratingDto.getRating());
        rating.setUid(Integer.parseInt(jwt.getClaimAsString("sub")));
		// rating.setUid(1);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        rating.setTimestamp(timestamp);

		ratingMapper.insert(rating);
		return "ok";
	}

	// public String rating(RatingDto ratingDto){

	// 	Rating rating = new Rating();
    //     rating.setMid(ratingDto.getMid());
    //     rating.setRating(ratingDto.getRating());
    //     // rating.setUid(Integer.parseInt(jwt.getClaimAsString("sub")));
	// 	rating.setUid(1);
    //     String timestamp = String.valueOf(Instant.now().toEpochMilli());
    //     rating.setTimestamp(timestamp);

	// 	ratingMapper.insert(rating);
	// 	return "ok";
	// }

	public Double getRate(Integer mid, Jwt jwt){
		RatingExample ratingExample = new RatingExample();
		ratingExample.createCriteria().andMidEqualTo(mid).andUidEqualTo(Integer.parseInt(jwt.getClaimAsString("sub")));
		if (!ratingMapper.selectByExample(ratingExample).isEmpty()) {
			return Double.parseDouble(ratingMapper.selectByExampleWithBLOBs(ratingExample).get(0).getRating());
		}
		return 0.0;
	}

	// public Double getRate(Integer mid){
	// 	RatingExample ratingExample = new RatingExample();
	// 	// ratingExample.createCriteria().andMidEqualTo(mid).andUidEqualTo(Integer.parseInt(jwt.getClaimAsString("sub")));
	// 	ratingExample.createCriteria().andMidEqualTo(mid).andUidEqualTo(1);
	// 	System.out.println(ratingMapper.selectByExampleWithBLOBs(ratingExample).stream().findFirst());
	// 	// if (!ratingMapper.selectByExample(ratingExample).isEmpty()) {
	// 	// 	return Double.parseDouble(ratingMapper.selectByExample(ratingExample).get(0).getRating());
	// 	// }
	// 	return 0.0;
	// }

	// public RatingRankDto ratingRank(Integer mid){
	// 	RatingExample ratingExample = new RatingExample();
	// 	ratingExample.createCriteria().andMidEqualTo(mid);
	// 	ratingMapper.selectByExample(ratingExample);


	// }
}
