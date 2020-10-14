package server06.miniproject.controller;

import java.util.List;

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
	
	@RequestMapping(value="/challengeactivity/{chalId}", method=RequestMethod.DELETE)
	public boolean deleteChallengeActivity(@PathVariable int chalId) {
		return challengeactivityService.deleteChallengeActivity(chalId);
	}
	
	@RequestMapping(value="/challengeactivity/{memId}", method=RequestMethod.GET)
	public List<ChallengeActivity> selectChallengeActivity(@PathVariable int memId){
		return challengeactivityService.selectChallengeActivityList(memId);
	}
	
	@RequestMapping(value="/challengeactivity/cnt/{chalactId}", method=RequestMethod.PUT)
	public boolean updateChallengeActivityCount(@PathVariable int chalactId, @RequestBody ChallengeActivity challengeactivity) {
		return challengeactivityService.updateChallengeActivityCount(chalactId, challengeactivity);
	}
	
	@RequestMapping(value="/challengeactivity/actlist/{chalId}", method=RequestMethod.GET)
	public List<ChallengeActivity> selectChallengeActivityChalList(@PathVariable int chalId){
		return challengeactivityService.selectChallengeActivityChalList(chalId);
	}
	
}
