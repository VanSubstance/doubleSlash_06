package server06.miniproject.service;

import java.util.List;
import java.util.Map;

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
	
	public List<Map<String, String>> selectChallengeActivityList(int chalId){
		return challengeactivityMapper.selectChallengeActivityList(chalId);
	}
}
