package server06.miniproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import server06.miniproject.model.Funding;

@Mapper //spring boot에서 mapper로 인식
public interface FundingMapper {
	
	@Select("SELECT * FROM FUNDING WHERE FUND_ID=#{fund_id}")
	Funding getFunding(@Param("fund_id") int fund_id);
	
	@Select("SELECT * FROM FUNDING")
	List<Funding> getFundingList();

	@Insert("INSERT INTO FUNDING VALUES(#{fund_id}, #{fund_inst}, #{inst_icon}, #{inst_des}, #{tar_point}, #{acu_point})")
	int insertFunding(@Param("fund_id") int fund_id , @Param("fund_inst") String fund_inst, @Param("inst_icon") String inst_icon, @Param("inst_des") String inst_des, @Param("tar_point") int tar_point, @Param("acu_point") int acu_point);

	@Update("UPDATE FUNDING SET FUND_INST=#{fund_inst}, INST_ICON=#{inst_icon}, INST_DES=#{inst_des}, TAR_POINT=#{tar_point}, ACU_POINT=#{acu_point} WHERE FUND_ID=#{fund_id}")
	int updateFunding(@Param("fund_id") int fund_id , @Param("fund_inst") String fund_inst, @Param("inst_icon") String inst_icon, @Param("inst_des") String inst_des, @Param("tar_point") int tar_point, @Param("acu_point") int acu_point);
	
	@Delete("DELETE FROM FUNDING WHERE FUND_ID=#{fund_id}")
	int deleteFunding(@Param("fund_id") int fund_id);
	
}

