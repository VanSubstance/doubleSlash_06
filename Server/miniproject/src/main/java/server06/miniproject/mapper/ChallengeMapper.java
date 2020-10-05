package server06.miniproject.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeMapper {
	List<Map<String, String>> selectChallengeList();
}
