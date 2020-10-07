package server06.miniproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.Challenge;
import server06.miniproject.model.ChallengeList;

@Repository
public interface ChallengeMapper {
	
	// 챌린지 등록
	int insertChallenge(@Param("item") Challenge challenge);
	
	// 회원번호로 챌린지 개수 조회
	int selectChallengeCount(int memId);
	
	// 챌린지 삭제
	int deleteChallenge(int chalId);
	
	List<ChallengeList> selectChallengeList(int memId);
}
