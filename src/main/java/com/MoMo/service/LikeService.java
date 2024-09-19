package com.MoMo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {

	void checkLike(String eid, String name);

	List<String> selectLikeStore(String name);

	void uncheckLike(String eid, String name);

}
