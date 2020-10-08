package server06.miniproject.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import server06.miniproject.model.ChallengeFrameList;

@Repository
public interface ChallengeFrameMapper {
	List<ChallengeFrameList> selectChallengeFrameList();
}
