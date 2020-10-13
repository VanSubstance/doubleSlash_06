package server06.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.ChallengeFrame;


@Repository
public interface ChallengeFrameMapper {
	List<ChallengeFrame> selectChallengeFrameList();
	
	int updateChallengeFrameCount(@Param("chalfrId") int chalfrId, @Param("item") ChallengeFrame challengeframe);

}
