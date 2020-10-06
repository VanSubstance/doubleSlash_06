package server06.miniproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.ChallengeFrameMapper;

@Service
@Transactional
public class ChallengeFrameService {
	
	@Autowired
	private ChallengeFrameMapper challengeframeMapper;
	
	public List<Map<String, String>> selectChallengeFrameList() {
		return challengeframeMapper.selectChallengeFrameList();
	}
}
