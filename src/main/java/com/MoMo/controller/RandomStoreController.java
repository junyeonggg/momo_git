package com.MoMo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MoMo.dto.OwnerDto;
import com.MoMo.service.OwnerService;

@Controller
public class RandomStoreController {
	@Autowired
	private OwnerService ownerService;
	@GetMapping("/randstore")
	public String randstorePage() {
		return  "store/randomStores";
	}
	@ResponseBody
	@PostMapping("/randomResult")
	public List<OwnerDto> res(@RequestParam("city") String city, @RequestParam("category") String category){
		Random rand = new Random();
		int result_size = 5;
		List<OwnerDto> dtos = ownerService.selectrandom(city,category);
		List<OwnerDto> randDtos = new ArrayList<OwnerDto>();
		List<Integer> randNum_list= new ArrayList<Integer>();
		while(randDtos.size() != result_size) {
			int randNum = rand.nextInt(dtos.size());
			if(randNum_list.contains(randNum)) {
				System.out.println("중복된 데이터");
				continue;
			}
			System.out.println("추가합니다.");
			randNum_list.add(randNum);
			randDtos.add(dtos.get(randNum));
			randNum_list.forEach(d->{
				System.out.print(d+" ");
				});
			System.out.println();
		}
		randDtos.forEach(d->System.out.println(d.toString()));
		return randDtos;
		
	}
}
