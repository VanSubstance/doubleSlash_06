package server06.miniproject.mapper;


import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import server06.miniproject.model.Funding;

@Mapper
public interface FundingMapper {
	
	@Select("SELECT * FROM FUNDING")
	List<Funding> getFundingList();
	
	@Select("SELECT * FROM FUNDING WHERE FUNDINGID=#{fundingid}")
	Funding getFunding(@Param("fundingid") String fundingid);
	
	@Insert("INSERT INTO FUNDING VALUES(#{fundingid}, #{title}, #{content}, #{startdate}, #{enddate}, #{targetpoint}, #{description})")
	int insertFunding(@Param("fundingid") String fundingid, @Param("title") String title, @Param("content") String content, @Param("startdate") Date startdate, @Param("enddate") Date enddate, @Param("targetpoint") int targetpoint, @Param("description") String description);

	@Delete("DELETE FROM FUNDING WHERE FUNDINGID=#{fundingid}")
	int deleteFunding(@Param("fundingid") String fundingid);
}	