package server06.miniproject.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.mapper.ChallengeProfileMapper;
import server06.miniproject.model.ChallengeProfile;

@RestController
public class ChallengeProfileController {
	private ChallengeProfileMapper mapper;
	
	public ChallengeProfileController(ChallengeProfileMapper mapper) {
		this.mapper = mapper;
	}
	
	@GetMapping("/challenge/{ch_id}") //챌린지 조회
	public ChallengeProfile getChallengeProfile(@PathVariable("ch_id") String ch_id) {
		return mapper.getChallengeProfile(ch_id);
	}

	@GetMapping("/challenge/all") //챌린지 전체 조회
	public List<ChallengeProfile> getChallengeProfileList(){
		return mapper.getChallengeProfileList();
	}
	
	@PostMapping("/challenge/{ch_id}") //챌린지 추가
	public void postChallengeProfile(@PathVariable("ch_id") String ch_id, @RequestParam("mem_id") String mem_id, @RequestParam("ch_point") int ch_point, @RequestParam("ch_title") String ch_title, @RequestParam("ch_content") String ch_content, @RequestParam("ch_regdate") Date ch_regdate, @RequestParam("ch_deadline") Date ch_deadline, @RequestParam("ch_lat") float ch_lat, @RequestParam("ch_lon") float ch_lon, @RequestParam("ch_success") char ch_success) {
		mapper.insertChallengeProfile(ch_id,mem_id,ch_point,ch_title,ch_content,ch_regdate,ch_deadline,ch_lat,ch_lon,ch_success);
	}
	
	@PutMapping("/challenge/{ch_id}") //챌린지 수정
	public void putChallengeProfile(@PathVariable("ch_id") String ch_id, @RequestParam("mem_id") String mem_id, @RequestParam("ch_point") int ch_point, @RequestParam("ch_title") String ch_title, @RequestParam("ch_content") String ch_content, @RequestParam("ch_regdate") Date ch_regdate, @RequestParam("ch_deadline") Date ch_deadline, @RequestParam("ch_lat") float ch_lat, @RequestParam("ch_lon") float ch_lon, @RequestParam("ch_success") char ch_success) {
		mapper.updateChallengeProfile(ch_id,mem_id,ch_point,ch_title,ch_content,ch_regdate,ch_deadline,ch_lat,ch_lon,ch_success);
	}
	
	@DeleteMapping("/challenge/{ch_id}") //챌린지 삭제
	public void deleteChallengeProfile(@PathVariable("ch_id") String ch_id) {
		mapper.deleteChallengeProfile(ch_id);
	}
	
}
