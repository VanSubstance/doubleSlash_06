package server06.miniproject.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import server06.miniproject.model.Trash;

@Repository
public interface TrashMapper {
	
	List<Trash> selectTrashList();
	
	List<Map<String, String>> selectCtgrTrashList(String ctgr);
}
