package server06.miniproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server06.miniproject.mapper.TrashMapper;
import server06.miniproject.model.Trash;

@Service
@Transactional   //이 클래스에 트랜잭션 기능이 적용된 프록시 객체가 생성된다.
public class TrashService {

		@Autowired
		private TrashMapper trashMapper;
		
		public List<Trash> selectTrashList(){
			return trashMapper.selectTrashList();
		}
		
		public List<Map<String, String>> selectCtgrTrashList(String ctgr){
			return trashMapper.selectCtgrTrashList(ctgr);
		}
}
