package server06.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.ChallengeFrameMapper;
import server06.miniproject.model.ChallengeFrame;

@Service
@Transactional
public class ChallengeFrameService {
	
	@Autowired
	private ChallengeFrameMapper challengeframeMapper;
	
	public List<ChallengeFrame> selectChallengeFrameList() {
		return challengeframeMapper.selectChallengeFrameList();
	}
}
