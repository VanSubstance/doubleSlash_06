package server06.miniproject.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import server06.miniproject.model.Member;

@Repository
public interface MemberMapper {
	
	List<Member> selectMemberList();
	
	int insertMember(@Param("item") Member member);
	
	int updateMemberLocation(@Param("memId") int memId, @Param("item") Member member);
	
	int updateMemberPoint(@Param("memId") int memId, @Param("item") Member member);
	
	List<Map<String, BigDecimal>> selectCloseMember(@Param("memId") int memId);
	
}
