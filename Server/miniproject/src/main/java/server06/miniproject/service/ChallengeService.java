package server06.miniproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.ChallengeMapper;

@Service
@Transactional
public class ChallengeService {
	
	@Autowired
	private ChallengeMapper challengeMapper;
	
	public List<Map<String, String>> selectChallengeList() {
		return challengeMapper.selectChallengeList();
	}
}
