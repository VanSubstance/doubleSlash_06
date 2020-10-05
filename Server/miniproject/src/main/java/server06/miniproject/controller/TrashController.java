package server06.miniproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server06.miniproject.model.Trash;
import server06.miniproject.service.TrashService;

@RestController
public class TrashController {

	// @Autowired는 의존하는 객체를 자동으로 삽입하는 역할
	@Autowired
	private TrashService trashService;
	
	//전체 분리배출법 조회
	@RequestMapping(value="/trash", method = RequestMethod.GET) // 전체 분리수거법 리스트 가져온다.
	public List<Trash> selectTrashList(){
		return trashService.selectTrashList();
	}
	
	//카테고리별 분리배출법 조회
	@RequestMapping(value="/trash/{ctgr}", method = RequestMethod.GET)
	public List<Map<String, String>> selectCtgrTrashList(@PathVariable String ctgr){
		return trashService.selectCtgrTrashList(ctgr);
	}
}
