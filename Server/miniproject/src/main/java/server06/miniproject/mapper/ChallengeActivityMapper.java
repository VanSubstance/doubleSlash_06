package server06.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.ChallengeActivity;

@Repository
public interface ChallengeActivityMapper {
	
	
	int insertChallengeActivity(@Param("item") ChallengeActivity challengeactivity);
	
	int deleteChallengeActivity(int chalId);
	
	List<ChallengeActivity> selectChallengeActivityList(int memId);
	
	int updateChallengeActivityCount(@Param("chalactId") int chalactId, @Param("item") ChallengeActivity challengeactivity);
	
	List<ChallengeActivity> selectChallengeActivityChalList(int chalId);
}
