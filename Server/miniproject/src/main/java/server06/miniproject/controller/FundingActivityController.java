package server06.miniproject.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.mapper.FundingActivityMapper;
import server06.miniproject.model.Funding;
import server06.miniproject.model.FundingActivity;

@RestController
public class FundingActivityController {
	
	private FundingActivityMapper mapper;
	
	public FundingActivityController(FundingActivityMapper mapper) {
		this.mapper = mapper;
	}
	
	@GetMapping("/funding_activity/{fund_act_id}") //펀딩 활동 조회
	public FundingActivity getFundingAvtivity(@PathVariable("fund_act_id") int fund_act_id) {
		return mapper.getFundingAvtivity(fund_act_id);
	}
	
	@GetMapping("/funding_activity/all") //펀딩 활동  전체 조회
	public List<FundingActivity> getFundingAvtivityList(){
		return mapper.getFundingAvtivityList();
	}
	
	@PostMapping("/funding_activity/{fund_act_id}") //펀딩 활동 추가
	public void postFundingAvtivity(@PathVariable("fund_act_id") int fund_act_id, @RequestParam("fund_id") int fund_id, @RequestParam("mem_id") int mem_id, @RequestParam("point") int point, @RequestParam("funddate") Date funddate) {
		mapper.insertFundingAvtivity(fund_act_id,fund_id,mem_id,point,funddate);
	}

}
