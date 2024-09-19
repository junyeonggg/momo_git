package com.MoMo.paging_search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Paging {
	private int current_page;
	private int totDataCnt; // 전체 데이터 갯수
	private int recordSize; // 한번에 보여줄 데이터 갯수 | 입력받을 값
	private int totPageCnt; // 전체 페이지 갯수 (전체-1)/recordSize) +1
	private int startRecord; // 현재 페이지 * 한번에 보여줄 데이터 갯수 : 다음 페이지의 시작record 인덱스
	
	private int pageSize; //한번에 보여줄 페이지 갯수 | 입력받을 값
	private int startPage;
	private int endPage;
	private boolean prevPage;
	private boolean nextPage;
	
	// 검색기능
	private String keyword;

	//카테고리
	private String category_name;
	
	
	public Paging(int totDataCnt,int recordSize,int pageSize,int current_page){
		this.totDataCnt = totDataCnt;
		this.recordSize = recordSize;
		this.pageSize = pageSize;
		this.totPageCnt = ((totDataCnt-1)/recordSize)+1;
		this.current_page = current_page;
		this.startRecord = (current_page-1)*recordSize;
		this.startPage = ((current_page-1) / pageSize)*pageSize+1;
		this.endPage = startPage+pageSize -1;
		if(endPage > totPageCnt) endPage = totPageCnt;
		this.prevPage = startPage != 1;
		this.nextPage = endPage != totPageCnt;
		
		
	}
	
}
