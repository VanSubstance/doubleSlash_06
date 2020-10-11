package server06.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.model.Challenge;
import server06.miniproject.model.ChallengeList;
import server06.miniproject.service.ChallengeService;

@RestController
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;

	@RequestMapping(value = "/challenge", method = RequestMethod.POST)
	public Boolean insertChallenge(@RequestBody Challenge challenge) {
		return challengeService.insertChallenge(challenge);
	}

	@RequestMapping(value="/challenge/{chalId}", method = RequestMethod.DELETE)
	public Boolean deleteChallenge(@PathVariable int chalId) {
		return challengeService.deleteChallenge(chalId);
	}
	
	@RequestMapping(value="/challenge/{memId}", method = RequestMethod.GET)
	public List<ChallengeList> selectChallengeList(@PathVariable int memId){
		return challengeService.selectChallengeList(memId);
	}
	

}
