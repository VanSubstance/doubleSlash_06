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

import server06.miniproject.mapper.FundingMapper;
import server06.miniproject.model.Funding;

@RestController
public class FundingController {
	private FundingMapper mapper;
	
	public FundingController(FundingMapper mapper) {
		this.mapper = mapper;
	}
	
	@GetMapping("/funding/{fund_id}") //펀딩 조회
	public Funding getFunding(@PathVariable("fund_id") int fund_id) {
		return mapper.getFunding(fund_id);
	}

	@GetMapping("/funding/all") //펀딩 전체 조회
	public List<Funding> getFundingList(){
		return mapper.getFundingList();
	}
	
	@PostMapping("/funding/{fund_id}") //펀딩 추가
	public void postFunding(@PathVariable("fund_id") int fund_id, @RequestParam("fund_inst") String fund_inst, @RequestParam("inst_icon") String inst_icon, @RequestParam("inst_des") String inst_des, @RequestParam("tar_point") int tar_point, @RequestParam("acu_point") int acu_point) {
		mapper.insertFunding(fund_id,fund_inst,inst_icon,inst_des,tar_point,acu_point);
	}
	
	@PutMapping("/funding/{fund_id}") //펀딩 수정
	public void putFunding(@PathVariable("fund_id") int fund_id, @RequestParam("fund_inst") String fund_inst, @RequestParam("inst_icon") String inst_icon, @RequestParam("inst_des") String inst_des, @RequestParam("tar_point") int tar_point, @RequestParam("acu_point") int acu_point) {
		mapper.updateFunding(fund_id,fund_inst,inst_icon,inst_des,tar_point,acu_point);
	}
	
	@DeleteMapping("/funding/{fund_id}") //펀딩 삭제
	public void deleteFunding(@PathVariable("fund_id") int fund_id) {
		mapper.deleteFunding(fund_id);
	}
}
