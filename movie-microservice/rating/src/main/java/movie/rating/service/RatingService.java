package movie.rating.service;

import java.time.Instant;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.LogFactory;

import movie.rating.dto.RatingDto;
import movie.rating.entity.Rating;
import movie.rating.mapper.RatingMapper;

@Service
public class RatingService {

    @Autowired
    private RatingMapper ratingMapper;

	// private static final Log log = LogFactory.getLog("fileInterface");
	private static Logger logger = Logger.getLogger(RatingService.class.getName());
    private static final String MOVIE_RATING_PREFIX = "MOVIE_RATING_PREFIX:";

    public String rating(RatingDto ratingDto){


        Rating rating = new Rating();
        rating.setMid(ratingDto.getMid());
        rating.setScore(ratingDto.getScore());
        rating.setUid(1);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        rating.setTimestamp(Long.parseLong(timestamp));

        ratingMapper.insert(rating);

        // 日志记录
        String logMessage = String.format("%s%d|%d|%f|%s", MOVIE_RATING_PREFIX, 1, ratingDto.getMid(), ratingDto.getScore(), timestamp);
        logger.info(logMessage);
        // log.info(logMessage);

        return "ok";
    }

    // public Double getRate(Integer mid, Jwt jwt){
    //     RatingExample ratingExample = new RatingExample();
    //     ratingExample.createCriteria().andMidEqualTo(mid).andUidEqualTo(Integer.parseInt(jwt.getClaimAsString("sub")));
    //     if (!ratingMapper.selectByExample(ratingExample).isEmpty()) {
    //         return ratingMapper.selectByExample(ratingExample).get(0).getScore();
    //     }
    //     return 0.0;
    // }

    // public Double getRate(Integer mid){
    //     RatingExample ratingExample = new RatingExample();
    //     ratingExample.createCriteria().andMidEqualTo(mid).andUidEqualTo(1);
    //     if (!ratingMapper.selectByExample(ratingExample).isEmpty()) {
    //         return ratingMapper.selectByExample(ratingExample).get(0).getScore();
    //     }
    //     return 0.0;
    // }

    // public RatingRankDto ratingRank(Integer mid){
    //     RatingExample ratingExample = new RatingExample();
    //     ratingExample.createCriteria().andMidEqualTo(mid);
    //     ratingMapper.selectByExample(ratingExample);


    // }
}