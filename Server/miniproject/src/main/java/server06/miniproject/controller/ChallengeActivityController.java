package server06.miniproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.model.ChallengeActivity;
import server06.miniproject.service.ChallengeActivityService;

@RestController
public class ChallengeActivityController {
	
	@Autowired
	ChallengeActivityService challengeactivityService;

	@RequestMapping(value="/challengeactivity", method=RequestMethod.POST)
	public boolean insertChallengeActivity(@RequestBody ChallengeActivity challengeactivity) {
		return challengeactivityService.insertChallengeActivity(challengeactivity);
	}
	
	@RequestMapping(value="/challengeactivity/{chalactId}", method=RequestMethod.DELETE)
	public boolean deleteChallengeActivity(@PathVariable int chalactId) {
		return challengeactivityService.deleteChallengeActivity(chalactId);
	}
	
	@RequestMapping(value="/challengeactivity/{chalId}", method=RequestMethod.GET)
	public List<Map<String, String>> selectChallengeActivity(@PathVariable int chalId){
		return challengeactivityService.selectChallengeActivityList(chalId);
	}
	
}
