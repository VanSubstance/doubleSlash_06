package server06.miniproject.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/funding_activity/{fund_id}") //펀딩 활동 조회
	public FundingActivity getFundingAvtivity(@PathVariable("fund_id") int fund_id) {
		return mapper.getFundingAvtivity(fund_id);
	}
	
	@GetMapping("/funding_activity_mem/{mem_id}") //펀딩 활동 조회(mem_id를 통해)
	public FundingActivity getFundingAvtivityMEM(@PathVariable("mem_id") int mem_id) {
		return mapper.getFundingAvtivityMEM(mem_id);
	}
	
	@GetMapping("/funding_activity/all") //펀딩 활동  전체 조회
	public List<FundingActivity> getFundingAvtivityList(){
		return mapper.getFundingAvtivityList();
	}
	
	@PostMapping("/funding_activity") //펀딩 활동 추가
	public void postFundingAvtivit(@RequestParam("fund_id") int fund_id, @RequestParam("mem_id") int mem_id, @RequestParam("point") int point, @RequestParam("funddate") String funddate , @RequestParam("acu_point") int acu_point) {
		mapper.insertFundingAvtivity(fund_id,mem_id,point,funddate,acu_point);
	}
	
	@PutMapping("/funding_activity/point/{fund_id}")
	public void putFundingAvtivitPoint(@RequestParam("fund_id") int fund_id, @RequestParam("acu_point") int acu_point) {
		mapper.updateFundingAvtivity(fund_id,acu_point);
	}
	
	@GetMapping("/funding_activity/getpoint/{fund_id}")
	public FundingActivity getFundingAvtivityPoint(@RequestParam("fund_id") int fund_id) {
		return mapper.insertFundingAvtivityPoint(fund_id);
	}

}
