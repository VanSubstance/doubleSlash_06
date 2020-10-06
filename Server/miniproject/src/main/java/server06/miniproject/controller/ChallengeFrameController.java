package server06.miniproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.service.ChallengeFrameService;



@RestController
public class ChallengeFrameController {
	
	@Autowired
	private ChallengeFrameService challengeframeService;
	
	@RequestMapping(value="/challengeframe", method = RequestMethod.GET)
	public List<Map<String, String>> selectChallengFrameList(){
		return challengeframeService.selectChallengeFrameList();
	}
}
