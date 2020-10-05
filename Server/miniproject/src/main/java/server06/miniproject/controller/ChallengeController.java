package server06.miniproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.service.ChallengeService;

@RestController
public class ChallengeController {
	
	@Autowired
	private ChallengeService challengeService;
	
	@RequestMapping(value="/challenge", method = RequestMethod.GET)
	public List<Map<String, String>> selectChallengList(){
		return challengeService.selectChallengeList();
	}
}
