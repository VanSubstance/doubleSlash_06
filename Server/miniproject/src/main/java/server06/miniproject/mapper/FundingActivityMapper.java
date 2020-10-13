package server06.miniproject.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import server06.miniproject.model.FundingActivity;

@Mapper
public interface FundingActivityMapper {

	// 펀딩 활동 조회 (fund_act_id를 통해)
	@Select("SELECT * FROM FUNDING_ACTIVITY WHERE FUND_ID=#{fund_id}")
	FundingActivity getFundingAvtivity(@Param("fund_id") int fund_id);

	// 펀딩 활동 조회 (mem_id를 통해)
	@Select("SELECT * FROM FUNDING_ACTIVITY WHERE MEM_ID=#{mem_id}")
	FundingActivity getFundingAvtivityMEM(@Param("mem_id") int mem_id);

	// 펀딩 활동 전체 조회
	@Select("SELECT * FROM FUNDING_ACTIVITY")
	List<FundingActivity> getFundingAvtivityList();

	// 펀딩 활동 등록
	@Insert("INSERT INTO FUNDING_ACTIVITY (FUND_ACT_ID,FUND_ID, MEM_ID, POINT, FUNDDATE, ACU_POINT) VALUES((SELECT NVL(MAX(fund_act_id)+1,0) FROM FUNDING_ACTIVITY),#{fund_id}, #{mem_id}, #{point}, #{funddate}, #{acu_point})")
	int insertFundingAvtivity(@Param("fund_id") int fund_id, @Param("mem_id") int mem_id, @Param("point") int point, @Param("funddate") String funddate, @Param("acu_point") int acu_point);
	
	//펀딩 포인트 수정 
	@Update("UPDATE FUNDING_ACTIVITY SET ACU_POINT = #{acu_point} WHERE FUND_ID = #{fund_id}")
	int updateFundingAvtivity(@Param("fund_id") int fund_id , @Param("acu_point") int acu_point);
	
	//수정된 펀딩 포인트 조회
	@Select("SELECT ACU_POINT FROM FUNDING_ACTIVITY WHERE FUND_ID=#{fund_id}")
	FundingActivity insertFundingAvtivityPoint(@Param("fund_id") int fund_id);
}
