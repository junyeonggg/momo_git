package com.MoMo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoMo.dao.LikeDao;
import com.MoMo.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	private LikeDao likeDao;
	// 관심매장 등록
	@Override
	public void checkLike(String eid, String name) {
		likeDao.checkLike(eid,name);
	}

	
	//관심매장 리스트
	@Override
	public List<String> selectLikeStore(String name) {
		return likeDao.selectLikeStoreByUserId(name);
	}


	//관심매장에서 삭제
	@Override
	public void uncheckLike(String eid, String name) {
		likeDao.unCheckLike(eid,name);
	}
	

}
