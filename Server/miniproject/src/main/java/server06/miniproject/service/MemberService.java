package server06.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.MemberMapper;
import server06.miniproject.model.Member;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public List<Member> selectMemberList() {
		return memberMapper.selectMemberList();
	}
	
	public boolean insertMember(Member member) {
		return memberMapper.insertMember(member) > 0 ? true : false;
	}
	
	public boolean updateMemberLocation(int memId, Member member) {

		return memberMapper.updateMemberLocation(memId, member) > 0 ? true : false;
	}
}
