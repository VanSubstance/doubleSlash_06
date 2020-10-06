package server06.miniproject.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeFrameMapper {
	List<Map<String, String>> selectChallengeFrameList();
}
