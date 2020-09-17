package server06.miniproject.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@GetMapping("/funding/{fundingid}")
	public Funding getFunding(@PathVariable("fundingid") String fundingid) {
		return mapper.getFunding(fundingid);
	}
	
	@GetMapping("/funding")
	public List<Funding> getFundingList(){
		return mapper.getFundingList();
	}
	
	@PostMapping("/funding")
	public void putFunding(@RequestParam("fundingid") String fundingid,  @RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("startdate") Date startdate, @RequestParam("enddate") Date enddate, @RequestParam("targetpoint") int targetpoint, @RequestParam("description") String description) {
		mapper.insertFunding(fundingid, title, content, startdate, enddate, targetpoint, description);
	}
	
	@DeleteMapping("/funding/{fundingid}")
	public void deldteFunding(@PathVariable("fundingid") String fundingid) {
		mapper.deleteFunding(fundingid);
	}
	
}

	

