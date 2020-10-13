package server06.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.ChallengeActivityMapper;
import server06.miniproject.model.ChallengeActivity;

@Service
@Transactional
public class ChallengeActivityService {
	
	@Autowired
	ChallengeActivityMapper challengeactivityMapper;
	
	public boolean insertChallengeActivity(ChallengeActivity challengeactivity) {
		return challengeactivityMapper.insertChallengeActivity(challengeactivity) > 0 ? true : false;
	}
	
	public boolean deleteChallengeActivity(int chalactId) {
		return challengeactivityMapper.deleteChallengeActivity(chalactId) > 0 ? true : false;
	}
	
	public List<ChallengeActivity> selectChallengeActivityList(int memId){
		return challengeactivityMapper.selectChallengeActivityList(memId);
	}
	
	public boolean updateChallengeActivityCount(int chalactId, ChallengeActivity challengeactivity) {
		return challengeactivityMapper.updateChallengeActivityCount(chalactId, challengeactivity) > 0 ? true : false;
	}
	
	public List<ChallengeActivity> selectChallengeActivityChalList(int chalId){
		return challengeactivityMapper.selectChallengeActivityChalList(chalId);
	}
}
