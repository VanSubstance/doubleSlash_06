package server06.miniproject.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import server06.miniproject.model.ChallengeProfile;

@Mapper //spring boot에서 mapper로 인식
public interface ChallengeProfileMapper {
	
	@Select("SELECT * FROM CHALLENGE WHERE CH_ID=#{ch_id}")
	ChallengeProfile getChallengeProfile(@Param("ch_id") String ch_id);
	
	@Select("SELECT * FROM CHALLENGE")
	List<ChallengeProfile> getChallengeProfileList();

	@Insert("INSERT INTO CHALLENGE VALUES(#{ch_id}, #{mem_id}, #{ch_point}, #{ch_title}, #{ch_content}, #{ch_regdate}, #{ch_deadline}, #{ch_lat}, #{ch_lon}, #{ch_success})")
	int insertChallengeProfile(@Param("ch_id") String ch_id , @Param("mem_id") String mem_id, @Param("ch_point") int ch_point, @Param("ch_title") String ch_title, @Param("ch_content") String ch_content, @Param("ch_regdate") Date ch_regdate, @Param("ch_deadline") Date ch_deadline, @Param("ch_lat") float ch_lat, @Param("ch_lon") float ch_lon, @Param("ch_success") char ch_success);

	@Update("UPDATE CHALLENGE SET MEM_ID=#{mem_id}, CH_POINT=#{ch_point}, CH_TITLE=#{ch_title}, CH_CONTENT=#{ch_content}, CH_REGDATE=#{ch_regdate}, CH_DEADLINE=#{ch_deadline}, CH_LAT=#{ch_lat}, CH_LON=#{ch_lon}, CH_SUCCESS=#{ch_success} WHERE CH_ID=#{ch_id}")
	int updateChallengeProfile(@Param("ch_id") String ch_id , @Param("mem_id") String mem_id, @Param("ch_point") int ch_point, @Param("ch_title") String ch_title, @Param("ch_content") String ch_content, @Param("ch_regdate") Date ch_regdate, @Param("ch_deadline") Date ch_deadline, @Param("ch_lat") float ch_lat, @Param("ch_lon") float ch_lon, @Param("ch_success") char ch_success);
	
	@Delete("DELETE FROM CHALLENGE WHERE CH_ID=#{ch_id}")
	int deleteChallengeProfile(@Param("ch_id") String ch_id);
	
}

