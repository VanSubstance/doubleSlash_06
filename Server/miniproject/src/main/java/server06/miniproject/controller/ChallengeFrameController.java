package server06.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.model.ChallengeFrame;
import server06.miniproject.service.ChallengeFrameService;



@RestController
public class ChallengeFrameController {
	
	@Autowired
	private ChallengeFrameService challengeframeService;
	
	@RequestMapping(value="/challengeframe", method = RequestMethod.GET)
	public List<ChallengeFrame> selectChallengFrameList(){
		return challengeframeService.selectChallengeFrameList();
	}
	
	@RequestMapping(value="/challengeframe/cnt/{chalfrId}", method = RequestMethod.PUT)
	public Boolean updateChallengeFrameCount(@PathVariable int chalfrId, @RequestBody ChallengeFrame challengeframe) {
		return challengeframeService.updateChallengeFrameCount(chalfrId, challengeframe);
	}
}
