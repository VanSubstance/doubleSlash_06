package server06.miniproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.ChallengeActivity;

@Repository
public interface ChallengeActivityMapper {
	
	int insertChallengeActivity(@Param("item") ChallengeActivity challengeactivity);
	
	int deleteChallengeActivity(int chalactId);
	
	List<Map<String, String>> selectChallengeActivityList(int chalId);
}
