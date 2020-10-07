package server06.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.ChallengeMapper;
import server06.miniproject.model.Challenge;
import server06.miniproject.model.ChallengeList;

@Service
@Transactional
public class ChallengeService {

	@Autowired
	private ChallengeMapper challengeMapper;

	public boolean insertChallenge(Challenge challenge) {
		return challengeMapper.insertChallenge(challenge) > 0 ? true : false;
	}

	public int selectChallengeCount(int memId) {
		return challengeMapper.selectChallengeCount(memId);
	}
	
	public boolean deleteChallenge(int chalId) {
		return challengeMapper.deleteChallenge(chalId) > 0 ? true : false;
	}
	
	public List<ChallengeList> selectChallengeList(int memId){
		
		return challengeMapper.selectChallengeList(memId);
	}

}
