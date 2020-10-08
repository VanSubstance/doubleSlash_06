package server06.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.Member;

@Repository
public interface MemberMapper {
	
	List<Member> selectMemberList();
	
	int insertMember(@Param("item") Member member);
	
	int updateMemberLocation(@Param("memId") int memID, @Param("item") Member member);
	
}
