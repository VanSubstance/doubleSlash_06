package server06.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.model.Member;
import server06.miniproject.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	// 전체 회원 조회
	@RequestMapping(value="/member", method=RequestMethod.GET)
	public List<Member> selectMemberList(){
		return memberService.selectMemberList();
	}
	
	// 회원 등록
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public Boolean insertMember(@RequestBody Member member){
		return memberService.insertMember(member);
		
	}
	
	@RequestMapping(value="/member/location/{memId}", method=RequestMethod.PUT)
	public Boolean updateMemberLocation(@PathVariable int memId, @RequestBody Member member) {
		return memberService.updateMemberLocation(memId, member);
	}
	
	@RequestMapping(value="/member/point/{memId}", method=RequestMethod.PUT)
	public Boolean updateMemberPoint(@PathVariable int memId, @RequestBody Member member) {
		return memberService.updateMemberPoint(memId, member);
	}
}

