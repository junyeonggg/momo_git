package com.MoMo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.MoMo.dto.CityDto;
import com.MoMo.dto.MenuDto;
import com.MoMo.dto.OwnerDto;
import com.MoMo.paging_search.Paging;

@Mapper
public interface OwnerDao {

	@Insert("insert into tbl_owner values(#{owner_eid},#{user_id},#{owner_bm},#{owner_addr},null,null,null,0,0)")
	void insertOwner(OwnerDto ownerDto);
	
	//매장 정보 가져오기
	@Select("select * from tbl_owner where user_id = #{user_id}")
	List<OwnerDto> selectAllByUserIdFromOwner(String user_id);

	//게시글 정보
	@Select("select * from tbl_owner where owner_eid=#{owner_eid}")
	OwnerDto selectOneByEidFromOwner(String owner_eid);
	
	// 총 데이터 갯수
	@Select("select count(*) from tbl_owner,tbl_category where tbl_category.no = tbl_owner.store_category and category2 like concat('%',#{category_name},'%') and owner_addr like concat('%',#{si_do_si_gun_gu},'%') and owner_bm like concat('%',#{keyword},'%');")
	int selectAllCountStoreBySiDo(@Param("si_do_si_gun_gu")String si_do_si_gun_gu,@Param("keyword")String keyword ,@Param("category_name")String category_name);
	
	//si_do 별 게시글 출력 ( 페이징 적용 )
	@Select("select * from tbl_owner,tbl_category where tbl_category.no = tbl_owner.store_category and owner_addr like concat('%',#{si_do_si_gun_gu},'%') and owner_bm like concat('%',#{paging.keyword},'%') and category2 like concat('%',#{paging.category_name},'%') limit #{paging.startRecord},#{paging.recordSize};") // 
	List<OwnerDto> selectAllStoreBySiDo(@Param("si_do_si_gun_gu")String si_do_si_gun_gu,@Param("paging")Paging paging);
	
	
	//게시글 정보 등록
	@Update("update tbl_owner set store_tel=#{store_tel},store_addr=#{store_addr},store_place_no=#{store_place_no},store_category=#{store_category} where owner_eid=#{owner_eid}")
	void updateStore(OwnerDto ownerDto);

	// 홈페이지 정보
	@Select("select * from tbl_category,tbl_owner")//tbl_owner.store_category = tbl_category.no
	List<OwnerDto> selectAllStore();

	// 메뉴 등록
	@Insert("insert into tbl_menu values(null,#{menu_name},#{menu_price},#{menu_info},#{owner_eid},#{photo_path},#{photo_name},#{photo_org_name})")
	void insertMenu(MenuDto menu);

	//삭제
	@Delete("delete from ${tbl} where ${col}=#{val}")
	void delete(@Param("tbl")String tbl,@Param("col")String col, @Param("val")String val);

	//메뉴 업데이트
	@Update("update tbl_menu set menu_name=#{menu_name},menu_price=#{menu_price},photo_path=#{photo_path},photo_name=#{photo_name},photo_org_name=#{photo_org_name} where menu_no=#{menu_no}")
	void updateMenu(MenuDto menu);

	//갯수
	@Select("select "
			+ "count(*)"
			+ "from "
			+ "tbl_like_store,tbl_owner "
			+ "where "
			+ "tbl_owner.user_id=#{name} "
			+ "and "
			+ "tbl_owner.user_id=tbl_like_store.user_id "
			+ "and "
			+ "tbl_owner.owner_eid=tbl_like_store.owner_eid")
	int selectLikeCtnStoresByUserId(String name);
	
	//관심 매장 리스트
	@Select("select "
			+ "* "
			+ "from "
			+ "tbl_like_store,tbl_owner,tbl_category "
			+ "where "
			+ "tbl_category.no = tbl_owner.store_category "
			+ "and "
			+ "tbl_owner.user_id=#{name} "
			+ "and "
			+ "tbl_owner.user_id=tbl_like_store.user_id "
			+ "and "
			+ "tbl_owner.owner_eid=tbl_like_store.owner_eid "
			+ "limit #{paging.startRecord},#{paging.recordSize}")
	List<OwnerDto> selectLikeStoresByUserId(@Param("name")String name,@Param("paging")Paging paging);

	@Update("update tbl_owner set store_view = #{i} where owner_eid = #{owner_eid}")
	void increView(@Param("owner_eid") String owner_eid, @Param("i")int i);

	@Select("select * from tbl_owner where owner_addr like concat('%',#{string},'%') or owner_addr like concat('%',#{string2},'%') or owner_addr like concat('%',#{string3},'%') order by store_view desc limit 5 ")
	List<OwnerDto> selectSG(@Param("string")String string,@Param("string2") String string2, @Param("string3")String string3);

	@Select("select * from tbl_owner where owner_addr like concat('%',#{string},'%') order by store_view desc limit 5 ")
	List<OwnerDto> selectG(@Param("string") String string);

	@Select("select * from tbl_owner where owner_addr like concat('%',#{string},'%') or owner_addr  like concat('%',#{string2},'%') or owner_addr like concat('%',#{string3},'%') order by store_view desc limit 5 ")
	List<OwnerDto> selectCDS(@Param("string")String string,@Param("string2") String string2,@Param("string3") String string3);
	
	@Select("select * from tbl_owner where owner_addr like concat('%',#{string},'%') or owner_addr like concat('%',#{string2},'%') or owner_addr like concat('%',#{string3},'%') or owner_addr like concat('%',#{string4},'%') order by store_view desc limit 5 ")
	List<OwnerDto> selectGUBD(@Param("string") String string,@Param("string2") String string2,@Param("string3") String string3,@Param("string4") String string4);
	
	@Select("select * from tbl_owner where owner_addr like concat('%',#{string},'%') or owner_addr like concat('%',#{string2},'%') or owner_addr like concat('%',#{string3},'%') order by store_view desc limit 5 ")
	List<OwnerDto> selectGGG(@Param("string") String string,@Param("string2") String string2,@Param("string3") String string3);

	@Select("select * from tbl_menu where photo_name = #{photo_name}")
	MenuDto selectOneMenuByPhotoName(String photo_name);

	@Select("select count(*) from tbl_category where category1 = #{cate1} and category2 = #{cate2}")
	int checkCate(@Param("cate1")String cate1, @Param("cate2")String cate2);

	@Insert("insert into tbl_category values(null,#{cate1},#{cate2})")
	void insertCate(@Param("cate1")String cate1, @Param("cate2")String cate2);

	@Select("select no from tbl_category where category1=#{string} and category2=#{string2}")
	int selectCateNo(@Param("string")String string, @Param("string2")String string2);

	@Select("SELECT distinct category1 FROM tbl_category;")
	List<String> selectCate1();

	@Select("select category2 from tbl_category where category1=#{cate1}")
	List<String> getcate2(String cate1);

	@Select("select * from tbl_owner,tbl_category where tbl_owner.store_category=tbl_category.no \r\n"
			+ "and owner_addr like concat('%',#{city},'%') and category2 = #{category};")
	List<OwnerDto> selelectrandom(@Param("city")String city, @Param("category")String category);

	@Update("update tbl_member set user_permit=#{i} where user_id=#{name}")
	void setPermit(@Param("name")String name, @Param("i")int i);

	@Select("select * from tbl_category where no=#{category_no}")
	HashMap<String, Object> selectCateNameByNo(int category_no);
}
