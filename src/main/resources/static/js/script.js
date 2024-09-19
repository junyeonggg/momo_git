/* ==========================================회원가입 관련 함수  ====================================================================================*/
//회원 종류 확인
function joinRoleCheck(type) {
	location.href = "/joinForm?type=" + `${type.getAttribute("data-value")}`
}

//아이디 중복확인
function userIdCheck() {
	const user_id = document.querySelector("#user_id");
	if (user_id.value.length < 5) {
		window.alert("5글자 이상으로 작성해 주세요.");
		return user_id.focus();
	}
	$.ajax({
		type: "post",
		dataType: "text",
		url: "http://192.168.1.13:8080/join/userIdCheck",
		data: { userId: user_id.value },
		success: (data) => {
			console.log("data : ", data)
			if (data == "false") {
				window.alert("사용 가능한 아이디 입니다.")
				user_id.setAttribute("readonly", "readonly")
				user_id.setAttribute("data-flag", "true")
				document.querySelector("#userIdResetBtn").setAttribute("type", "button")
				document.querySelector("#userIdCheckBtn").parentElement.setAttribute("hidden", "hidden")
				user_id.parentElement.parentElement.nextElementSibling.removeAttribute("hidden")
				user_id.parentElement.parentElement.nextElementSibling.nextElementSibling.removeAttribute("hidden")

			} else {
				window.alert("사용할 수 없는 아이디 입니다.")
				user_id.setAttribute("data-flag", "false")
			}
		}
	})
}
// 아이디 다시 쓰기
function userIdReset() {
	document.querySelector("#user_id").removeAttribute("readonly");
	document.querySelector("#user_id").setAttribute("data-flag", "false")
	document.querySelector("#userIdResetBtn").parentElement.setAttribute("hidden","hidden");
	document.querySelector("#userIdCheckBtn").parentElement.removeAttribute("hidden");
}


// 비밀번호 일치 확인
function userPwCheck(self) {
	//비교할 노드
	const user_pw = document.querySelector("#user_pw");

	//추가할 위치
	const target = document.querySelector("#pw_target");
	//추가할 것
	//비밀번호 확인 입력 칸
	var user_pw2 = self;


	//현재 입력된 비밀번호
	//이벤트추가
	user_pw2.addEventListener("keydown", (event) => {
		const hidden = self.parentElement.parentElement.nextElementSibling;
		var user_pw_value = user_pw.value;
		var user_pw2_value = user_pw2.value;
		// 누른 키는 문자로 value에 입력이 되어야 한다.
		// 한편 입력이 되지않는 값은 길이가 1이 넘는다는 점 (ex Backspace)을 이용해
		// 길이가 1이면 value에 누른 키를 더하여 비교한다.
		// 이렇게 하는 이유는 value를 가져올 때 누른 키는 포함되지 않았기 때문이다.
		if (event.key.length == 1) {
			user_pw2_value += event.key;
		} else if (event.key == "Backspace") {
			//백스페이스 누를 시 마지막 문자를 지운다.
			user_pw2_value = user_pw2_value.slice(0, -1)
		}
		if (user_pw2_value == user_pw_value) {
			target.style.color = "red"
			target.innerText = "비밀번호가 일치합니다.";
			user_pw.setAttribute("data-flag", "true");
			hidden.removeAttribute("hidden")
			hidden.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.removeAttribute("hidden");
			hidden.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.removeAttribute("hidden");
		} else {
			target.innerText = "";
			user_pw.setAttribute("data-flag", "false");
		}
	})
}

//이메일 인증

//joinForm 유효성 체크
function joinFormCheck() {
	//form
	const form = document.forms.joinForm;

	//아이디 유효성 체크 -> 중복확인 함수에서 처리

	//비밀번호 유효성 체크	
	const user_pw = document.querySelector("#user_pw");
	if (user_pw != null) {
		console.log(user_pw.value.length);
		if (!(user_pw.value.length >= 8 && user_pw.value.length <= 15)) {
			user_pw.setAttribute("data-flag", "false");
			window.alert("비밀번호를 확인해 주세요.")
			return user_pw.focus();
		}
	}
	//비밀번호 일치 확인 -> user_pw_check()함수

	// 이름 유효성 체크
	const user_name = document.querySelector("#user_name");
	if (user_name.value.length == 0) {
		window.alert("이름을 입력해 주세요.")
		user_name.setAttribute("data-flag", "false")
		return user_name.focus();
	} else {
		user_name.setAttribute("data-flag", "true")
	}

	// 주소 유효성 체크 나중에 우편번호로 확인 할 수 있게 구현할 것
	const user_addr = document.querySelector("#user_addr");
	if (user_addr.value.length == 0) {
		window.alert("주소를 입력해 주세요.")
		user_addr.setAttribute("data-flag", "false")
		return user_addr.focus();
	} else {
		user_addr.setAttribute("data-flag", "true")
	}

	const user_email = document.querySelector("#user_email");
	if (user_email.value.length == 0) {
		window.alert("이메일을 입력해 주세요.")
		user_email.setAttribute("data-flag", "false")
		return user_email.focus();
	} else {
		user_email.setAttribute("data-flag", "true")
	}

	const user_tel = document.querySelector("#user_tel");
	if (user_tel.value.length == 0) {
		window.alert("전화번호를 입력해 주세요.")
		user_tel.setAttribute("data-flag", "false")
		return user_tel.focus();
	} else {
		user_tel.setAttribute("data-flag", "true")
	}

	const user_birth = document.querySelector("#user_birth");
	if (user_birth.value.length == 0) {
		window.alert("생년월일을 입력해 주세요.")
		user_birth.setAttribute("data-flag", "false")
		return user_birth.focus();
	} else {
		user_birth.setAttribute("data-flag", "true")
	}
	const user_gender = document.getElementsByName("user_gender");
	if ((user_gender[0].checked | user_gender[1].checked) == 0) {
		window.alert("성별을 선택해 주세요.")
		user_gender.forEach(g => g.setAttribute("data-flag", "false"));
		return user_gender[0].focus();
	} else {
		user_gender.forEach(g => g.setAttribute("data-flag", "true"));
	}

	const lastCheckList = document.querySelectorAll(".inputJoin");

	for (el of lastCheckList) {
		if (el.getAttribute("data-flag") == "false") {
			window.alert(el.getAttribute("data-name") + "을(를) 확인해 주세요.")
			return el.focus();

		}
	}


	//여기부터는 매장 정보 -> 삭제
	//	const owner_eid = document.querySelector("#owner_eid")
	//	const owner_tel = document.querySelector("#owner_tel");
	window.alert("전송합니다.")
	form.submit();
}
/* ========================================================회원가입 관련 함수 끝  ====================================================================================*/

/*  what info button click*/
function mineOrStore(self) {
	const targetForm = document.querySelector("#" + self.getAttribute("data-target"));
	if (targetForm.getAttribute("hidden") == "hidden") {
		targetForm.removeAttribute("hidden");
	} else {
		targetForm.setAttribute("hidden", "hidden")
	}
}


/* ========================회원 정보 수정======================================================================================================*/
function updateInfo() {
	const inputList = document.querySelectorAll(".inputJoin");
	const updateComp = document.querySelector("#updateComp");
	inputList.forEach((el) => {
		el.removeAttribute("disabled")
	})
	updateComp.setAttribute("type", "button");
}
// 비밀번호 변경
function updatePwPop() {
	window.open('/updatePw', '비밀번호 변경', 'width=420,height=200,scrollbars=no,resizeable=no')
}
//현재 비밀번호 확인
function currentPwCheck(self) {
	const currentPw = document.querySelector("#currentPw");
	$.ajax({
		type: "post",
		dataType: "text",
		url: "http://192.168.1.13:8080/currentPwCheck",
		data: { cPw: currentPw.value },
		success: (data) => {
			const target = document.querySelector("#pwTarget");
			var trEl = document.createElement("tr");
			trEl.innerHTML = `<td>새 비밀번호</td>`
				+ `<td><input id='user_pw' type="password" data-flag='false'></td>`
			var trEl2 = document.createElement("tr");
			trEl2.innerHTML = `<td>새 비밀번호 확인</td>`
				+ `<td><input type="password" onclick='userPwCheck(this)'></td>`
				+ `<td id='pw_target'></td>`
			var updatebtn = document.createElement("input");
			updatebtn.setAttribute("type", "button");
			updatebtn.setAttribute("value", "수정하기");
			updatebtn.setAttribute("onclick", "sendUpdatePw()");
			if (data == "true") {
				target.append(trEl)
				target.append(trEl2)
				target.append(updatebtn)
				self.setAttribute("disabled", "disabled")
			} else {
				window.alert("비밀번호를 다시 입력해 주세요.");
			}
		}
	})
}
// 서버에 비밀번호 변경 요청
function sendUpdatePw() {
	const updatedPw = document.querySelector("#user_pw")
	$.ajax({
		type: "post",
		url: "http://192.168.1.13:8080/updatePw",
		data: { user_pw: updatedPw.value },
		success: (data) => {
			if (data) {
				window.alert("비밀번호가 변경되었습니다.")
				window.close();
			} else {
				window.alert("비밀번호 변경에 실패하였습니다.")
				window.close();
			}
		}
	})
}

/* ==========================정보 수정 끝 ====================================================================================*/


/*===========================매장 등록하기 ==================================*/
//매장등록하기 버튼
//방법1. 팝업창으로 등록하기
//팝업창으로 했을 시 html파일을 하나더 만들고,
//거기서 사업자 등록번호를 확인하고 
//방법2. 비동기통신으로 등록하기
// eid체크
//비즈노 인증키 : anVueWVvbmcyMzExQGdtYWlsLmNvbSAg
function eidCheck2() {
	const eid = document.querySelector("#eid").value;
	const url = `https://bizno.net/api/fapi?key=anVueWVvbmcyMzExQGdtYWlsLmNvbSAg&gb=1&q=${eid}&type=json`;
	console.log("url : ", url)
	$.ajax({
		type: "get",
		url: url,
		success: (data) => {
			console.log(data)
			console.log(data['items'][0]['bno'])
			console.log(data['items'][0]['company'])
		}
	})
}
// 테스트
function eidCheck3() {
	const eid = document.querySelector("#eid").value;
	var data = {
		"b_no": [`${eid}`], //필수
	}
	//공공데이터 인증키 : A8FzW%2B28cStwTXpe85lFnH89RRyYw0dL6%2BnKG4p1JZwbj43RibbSeEA8Z90xgaRhBwkvo0v1tq%2Bwoz3yWw3qFw%3D%3D
	$.ajax({
		url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=A8FzW%2B28cStwTXpe85lFnH89RRyYw0dL6%2BnKG4p1JZwbj43RibbSeEA8Z90xgaRhBwkvo0v1tq%2Bwoz3yWw3qFw%3D%3D",  // serviceKey 값을 xxxxxx에 입력
		type: "POST",
		data: JSON.stringify(data), // json 을 string으로 변환하여 전송
		dataType: "JSON",
		contentType: "application/json",
		accept: "application/json",
		success: function(result) {
			console.log(result);
		},
		error: function(result) {
			console.log(result.responseText); //responseText의 에러메세지 확인
		}
	});
}



//체크 2
function eidCheck() {
	const form = document.forms.ownerForm;

	const eid = document.querySelector("#eid").value;
	const start_dt = document.querySelector("#start_dt").value;
	const p_nm = document.querySelector("#p_nm").value;
	const b_bm = document.querySelector("#b_bm").value;
	const b_adr = document.querySelector("#b_adr").value;

	var data = {
		"businesses": [
			{
				"b_no": eid, //필수
				"start_dt": start_dt, //필수 
				"p_nm": p_nm, //필수
				"b_bm": b_bm, //상호명
				"b_adr": b_adr //소재지
			}
		]
	}
	//공공데이터 인증키 : A8FzW%2B28cStwTXpe85lFnH89RRyYw0dL6%2BnKG4p1JZwbj43RibbSeEA8Z90xgaRhBwkvo0v1tq%2Bwoz3yWw3qFw%3D%3D
	$.ajax({
		url: "https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=A8FzW%2B28cStwTXpe85lFnH89RRyYw0dL6%2BnKG4p1JZwbj43RibbSeEA8Z90xgaRhBwkvo0v1tq%2Bwoz3yWw3qFw%3D%3D",  // serviceKey 값을 xxxxxx에 입력
		type: "POST",
		data: JSON.stringify(data), // json 을 string으로 변환하여 전송
		dataType: "JSON",
		contentType: "application/json",
		accept: "application/json",
		success: function(result) {
			console.log(result)
			if (result['valid_cnt'] == null) {
				return window.alert("사업자 조회 정보를 확인해 주세요.")
			}
			if (result['data'][0]['status']['b_stt'] == "폐업자") {
				window.alert("폐업자는 매장 등록을 할 수 없습니다.")
			} else {
				window.alert("사업자 신원이 확인되었습니다.")
				//모두 일치할 시 db에 추가
				form.submit();


			}
			console.log(result['data'][0]['status']['b_stt']);
			console.log(result['data'][0]['b_no']);
		},
		error: function(result) {
			return window.alert("사업자 조회 정보를 확인해 주세요.")
		}
	});
}
/*===========================매장 등록하기 끝==================================*/



/*===========================게시글 폼 관련 ========================*/
function addMenu(self) {
	self.setAttribute("type", "hidden")
	var cnt = self.getAttribute("data-index")
	const add_menu_div = document.createElement("div");
	add_menu_div.setAttribute("class", "add_menu_div")
	add_menu_div.innerHTML = `<input name='menuDtos[${cnt}].menu_name' type="text" placeholder="메뉴이름">`
		+ `<input name="menuDtos[${cnt}].menu_no" type="hidden" value="0">`
		+ `<input name='menuDtos[${cnt}].menu_price' type="text" placeholder="가격">`
		+ `<input name='menuDtos[${cnt}].multfile' type="file" value="사진 등록하기"><br>`
		+ `<input type="button" onclick="addMenu(this)" data-index="${++cnt}" value="메뉴추가">`
	const target = document.querySelector("#menu");
	target.appendChild(add_menu_div)
	console.log(target)
}


// 폼 send
function sendForm() {
	const form = document.forms.owner_store_form;
	form.submit();
}
/*===========================게시글 폼 관련 끝 ========================*/

/*===========================게시글 수정 관련 =========================*/
//메뉴 삭제하기
function deleteMenu(self) {
	const menu_no = self.getAttribute("data-menu_no");
	$.ajax({
		type: "get",
		url: "/delete/menu",
		data: { val: menu_no, col: "menu_no" },
		success: () => {
			self.parentElement.parentElement.parentElement.removeChild(self.parentElement.parentElement)
		}
	})
}
//메뉴 사진 바꾸기
function changeMenuPhoto(self) {
	const menu_no = self.getAttribute("data-menu_no")
	const hiddenTarget = document.querySelector(`#hiddenTarget${menu_no}`);
	hiddenTarget.setAttribute("hidden", "hidden");
	self.setAttribute("type", "file")
	/*self.setAttribute("name","store_menu_photo")*/
}
/*===========================게시글 수정 관련 끝=========================*/



/*========================== 댓글 관련 함수 ============================*/
function insertCommentBtn() {
	const hiddenCtlr = document.querySelector("#commentFormDiv");
	if (hiddenCtlr.getAttribute("hidden")) {
		hiddenCtlr.removeAttribute("hidden")
	} else {
		hiddenCtlr.setAttribute("hidden", "hidden")
	}
}

// 댓글 등록 ajax
function insertComment() {
	const user_id = document.querySelector("#user_id").value
	const comment_content = document.querySelector("#comment_content").value
	const owner_eid = document.querySelector("#owner_eid").value
	const url = "/commentInsert";
	$.ajax({
		type: "post",
		url: url,
		data: { user_id: user_id, comment_content: comment_content, owner_eid: owner_eid },
		success: (data) => {
			if (data) {
				window.alert("댓글이 등록되었습니다.")
				window.location.reload();
			} else {
				window.alert("댓글 등록에 실패하였습니다.")
			}
		}

	})
}

/*========================== 댓글 관련 함수 끝============================*/



/*===========================aside 상세지역===============================*/
function aside_si_gun_gu(city) {
	const url = "/si_gun_gu"
	const ulEl = document.createElement("ul");
	ulEl.setAttribute("class", "cities_detail")
	const si_do = city.getAttribute("data-city");
	// 켜져있으면, 제거
	if (city.getAttribute("data-flag") == "true") {
		city.parentElement.removeChild(city.parentElement.lastElementChild);
		city.setAttribute("data-flag", "false");
		city.innerText = "▽";
	} else {
		$.ajax({
			type: "post",
			url: url,
			data: { city: si_do },
			success: (si_gun_gu) => {
				si_gun_gu.forEach((s) => {
					ulEl.innerHTML += `<li style="list-style:none;"><a style="color:black;" href='/stores?si_do=${si_do}&si_gun_gu=${s}'>${s}</a></li>`
				});
				city.parentElement.append(ulEl);
				city.setAttribute("data-flag", "true")
				city.innerText = "△";
			}

		})
	}
}
/*===========================aside 상세지역  끝===============================*/


/*============================관심 매장 =========================*/
function likeCheck(self) {
	$.ajax({
		type: "post",
		url: `/likeCheck?owner_eid=${self.getAttribute("data-owner_eid")}`,
		success: () => {
			window.location.reload();
			self.innerText = "★";
		}
	})
}
function likeUnCheck(self) {
	$.ajax({
		type: "post",
		url: `/likeUnCheck?owner_eid=${self.getAttribute("data-owner_eid")}`,
		success: () => {
			window.location.reload();
			self.innerText = "☆";
		}
	})
}

/*============================관심 매장  끝=========================*/

/*=========================아이디 찾기/ 비밀번호 찾기=============*/
function openModal(what) {
	const id = what.getAttribute("id")
	const modal = document.querySelector(`#${id}`);
	modal.style.display = "flex"
}
function escModal(what) {
	const id = what.getAttribute("id")
	const modal = document.querySelector(`#${id}`);
	modal.style.display = "none"
}
function findId() {
	const findId_name = document.querySelector("#findId_name").value;
	const findId_email = document.querySelector("#findId_email").value;
	const findId_tel = document.querySelector("#findId_tel").value;
	$.ajax({
		type: "post",
		url: "/find/id",
		data: { user_name: findId_name, user_email: findId_email, user_tel: findId_tel },
		success: (id) => {
			console.log(id);
			if (id == "false") {
				window.alert("일치되는 아이디가 없습니다.");
				return;
			}
			window.alert("회원님의 아이디는 [ " + id + " ]입니다.")
		}
	})
}
function findPw() {
	const findPw_id = document.querySelector("#findPw_id").value
	const findPw_name = document.querySelector("#findPw_name").value;
	const findPw_email = document.querySelector("#findPw_email").value;
	const findPw_tel = document.querySelector("#findPw_tel").value;
	$.ajax({
		type: "post",
		url: "/find/pw",
		data: { user_id: findPw_id, user_name: findPw_name, user_email: findPw_email, user_tel: findPw_tel },
		success: (id) => {
			if (id != "false") {
				window.alert("이메일로 임시 비밀번호를 전송하였습니다.")
				return;
			} else {
				window.alert("회원정보와 일치하는 회원이 없습니다. ")
			}
		}
	})
}

/*=========================아이디 찾기/ 비밀번호 찾기=============*/



function test(self) {
	const addr = self.value;
	console.log(addr)
	const place_name = document.querySelector("#place_name").value;
	const key = "ceba68919c99f579897438e68e1c6dba";
	//수정 REST API 호출
	const url = "https://dapi.kakao.com/v2/local/search/keyword.json";
	$.ajax({
		type: "get",
		url: url,
		data: { query: `${addr}` },
		headers: { Authorization: `KakaoAK ${key}` },
		success: (data) => {
			console.log(data)
			for (a in data['documents']) {
				if (data['documents'][a]['place_name'] == place_name) {
					console.log("이름이 같아요 ", place_name)
					const x = data['documents'][a]['x']
					const y = data['documents'][a]['y']
					const place_id = data['documents'][a]['id']
					console.log(`x : ${x} , y: ${y}`)
					const frame = document.querySelector("#locFrame");
					var iframeEl = document.createElement("iframe");
					iframeEl.setAttribute("scr", `https://map.kakao.com/link/map/${place_id}`)
					iframeEl.style.width = '500px';
					iframeEl.style.height = '500px';
					const target = document.querySelector("#iframe_target");
					target.append(iframeEl)



					/*					var container = document.getElementById('loc'); //지도를 담을 영역의 DOM 레퍼런스
										var options = { //지도를 생성할 때 필요한 기본 옵션
											center: new kakao.maps.LatLng(y, x), //지도의 중심좌표.
											level: 3 //지도의 레벨(확대, 축소 정도)
										};
										var map = new kakao.maps.Map(container, options); // 지도를 생성합니다*/

					/*					// 마커가 표시될 위치입니다 
										var markerPosition = new kakao.maps.LatLng(y, x);
					
										// 마커를 생성합니다
										var marker = new kakao.maps.Marker({
											position: markerPosition
										});*/

					// 마커가 지도 위에 표시되도록 설정합니다
					//					marker.setMap(map);
				}
			}
		}
	})

}
function addr_check() {
	const addr1 = document.querySelector("#addr1").value
	const addr2 = document.querySelector("#addr2").value
	const addr = addr1 + addr2
	console.log(addr)
	const place_name = document.querySelector("#place_name").value;
	const key = "ceba68919c99f579897438e68e1c6dba";
	//수정 REST API 호출
	const url = "https://dapi.kakao.com/v2/local/search/keyword.json";
	$.ajax({
		type: "get",
		url: url,
		data: { query: `${addr}` },
		headers: { Authorization: `KakaoAK ${key}` },
		success: (data) => {
			console.log(data)
			if (data['documents'].length == 0) {
				window.alert("주소를 확인해 주세요.")
				return;
			}
			var category2 = document.querySelector("#category");
			var store_place_no = document.querySelector("#store_place_no");
			var store_tel = document.querySelector("#store_tel");

			for (a in data['documents']) {
				console.log(`data : ${data['documents'][a]['place_name']} place_name : ${place_name}`)
				if (data['documents'][a]['place_name'] == place_name) {
					window.alert("주소가 확인되었습니다.")
					console.log("이름이 같아요 ", place_name)
					const x = data['documents'][a]['x']
					const y = data['documents'][a]['y']
					const place_id = data['documents'][a]['id']
					store_place_no.value = place_id
					// 카테고리 가져오기
					const category = data['documents'][a]['category_name']
					category2.value = category
					const tel = data['documents'][a]['phone']
					store_tel.value = tel;

					console.log(`cate : ${category} tel : ${tel}`)
					$.ajax({
						type: "post",
						url: "/insert_category",
						data: { category: category }
					})
				}
			}
			change_loc();
			return;
		}
	})
}

// 메뉴 추가
function add_menu(self) {
	const target = self.parentElement;
	console.log(target)
	var divEl = document.createElement("div");
	divEl.innerHTML = `메뉴 이미지 : <input type="file" multiple="true">`
		+ `<div class="menuInfo">`
		+ `<table>`
		+ `<tr>`
		+ `<td><input type="text" placeholder="메뉴 이름"> /</td>`
		+ `<td><input type="text" placeholder="메뉴 가격 ex)2000"></td>`
		+ `</tr>`
		+ `<tr>`
		+ `<td colspan="2"><input style="width:365px;" type="text" placeholder="메뉴 설명"></td>`
		+ `</tr>`
		+ `<tr>`
		+ `<td colspan="2"><input type="button" onclick="insert_menu(this)" value="등록">`
		+ `<input type="button" onclick="removeDiv(this)" value='취소'></td>`
		+ `</tr>`
		+ `</table>`
		+ `</div>`;
	target.append(divEl);
	var inputEl = document.createElement("div");
	inputEl.setAttribute("class", "menuImg");
	inputEl.innerHTML = `<div>`
		+ `<input type="button" onclick="add_menu(this)" value="메뉴추가">`
		+ `</div >`;
	self.parentElement.parentElement.parentElement.append(inputEl)
	self.parentElement.removeChild(self);
}
function removeDiv(self) {
	self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.removeChild(self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement)
}

function insert_menu(self) {
	const url = `/insert_menu`;
	const input_list = self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.getElementsByTagName("input");
	const menu_img = input_list[0].files[0];
	const menu_name = input_list[1].value;
	const menu_price = input_list[2].value;
	const menu_info = input_list[3].value;
	const owner_eid = document.querySelector("#owner_eid").value;
	var formData = new FormData();
	formData.append('multfile', menu_img)
	formData.append('owner_eid', owner_eid)
	formData.append('menu_name', menu_name)
	formData.append('menu_price', menu_price)
	formData.append('menu_info', menu_info)
	$.ajax({
		type: 'post',
		url: url,
		contentType: false,
		processData: false,	// 필수
		data: formData,
		success: (data) => {
			console.log(data)
			if (data != null) {
				window.alert("정상적으로 등록되었습니다.")
				var divEl = document.createElement("div");
				divEl.innerHTML =
					`<input type="button" onclick="deleteMenu(this)" data-menu_no="${data.menu_no}" value="메뉴삭제">`
					+ `<img alt="s" src="/downloadMenuImg?menu_no=${data.menu_no}">`
					+ `<div class="menuInfo">`
					+ `<p class="underline_menu">${data.menu_name} / ${data.menu_price}</p>`
					+ `<p class="underline_menu">${data.menu_info}</p>`
					+ `</div>`
				self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.append(divEl);
				self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.removeChild(self.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement);
			}
		}
	})
}

// 위치 정보 수정
function change_loc() {
	const target = document.querySelector("#iframe_target");
	const place_no = document.querySelector("#store_place_no").value;
	console.log("no : ", place_no)
	target.lastElementChild.parentElement.removeChild(target.lastElementChild);
	var iframeEl = document.createElement("iframe");
	iframeEl.setAttribute("id", "locFrame")
	iframeEl.setAttribute("src", `https://map.kakao.com/link/map/${place_no}`)
	target.append(iframeEl);
}

function testt(self) {
	const eid = self.getAttribute("data-eid")
	window.location.replace(`/store?store=${eid}`)
}
function filter(self) {
	const target = document.querySelector("#filter2");
	const url = `/getcategory?=${self.value}`
	$.ajax({
		type: "post",
		url: url,
		data: { filter1: self.value },
		success: data => {
			while (1) {
				if (target.firstElementChild) {
					target.removeChild(target.firstElementChild)
				} else {
					break;
				}
			}

			for (i of data) {
				divEl = document.createElement("div");
				divEl.innerHTML = `<input name="cate2" value="${i}" onclick="catesearch(this)" type="radio">`
					+ `<span>${i}</span>`
				target.append(divEl)
			}
		}
	})
}

function catesearch(self) {
	const cate = self.value;
	const cate_list = document.querySelectorAll(".categories");
	const si_do = document.querySelector("#si_do").value;
	console.log(si_do);
	const url = `/stores?si_do=${si_do}&category_name=${cate}`
	window.location.replace(url)
	self.checked;

	/*	for(c of cate_list){
			if(c.innerText != `<${cate}>`){
				c.parentElement.setAttribute("hidden","hidden"); 
			}else{
				c.parentElement.removeAttribute("hidden")
			}
		}*/
}
// 공지사항 모달 창 열기
function mentionDivOpen() {
	const mentionModal = document.querySelector("#mentionModal")

	var winX = window.pageXOffset;
	var winY = window.pageYOffset;
	mentionModal.style.display = "flex";
}
function insert_mention(self) {
	const user_id = document.querySelector("#user_id").value;
	const eid = document.querySelector("#owner_eid").value;
	const mention_title = document.querySelector("#mention_title").value;
	if (mention_title.length == 0) {
		window.alert("공지 제목을 입력해 주세요.")
		document.querySelector("#mention_title").focus()
		return;
	}
	const mention_content = document.querySelector("#mention_content").value;
	var formData = new FormData();
	formData.append('comment_content', mention_content);
	formData.append('mention_title', mention_title);
	formData.append('user_id', user_id);
	formData.append('owner_eid', eid);
	$.ajax({
		type: "post",
		url: '/commentInsert',
		contentType: false,
		processData: false,
		data: formData,
		success: data => {
			if (data == true) {
				window.alert("공지사항이 등록 되었습니다.")
				window.location.reload();
			}
		}

	})
}
function cancer_mention() {
	const target_modal = document.querySelector("#mentionModal")
	target_modal.style.display = "none";
}




function mention_detail(self) {
	const no = self.getAttribute("data-target")
	const target_id = 'mention_modal_div' + no
	const target = document.querySelector(`#${target_id}`)
	target.style.display = "flex";
}
function cancer_mention2(self) {
	const no = self.getAttribute("data-target")
	const target_id = 'mention_modal_div' + no
	const target = document.querySelector(`#${target_id}`)
	const title_id = 'mention_title_span' + no
	const content_id = 'mention_content_span' + no
	const title = document.querySelector(`#${title_id}`)
	const content = document.querySelector(`#${content_id}`)
	const updateBtn = document.querySelector(`#updateBtn${no}`)
	updateBtn.setAttribute("hidden", "hidden");
	title.setAttribute("disabled", "disabled")
	content.setAttribute("disabled", "disabled")
	target.style.display = "none";
}
/* 수정하기 누르면  disabled가 없어지고, 등록하기 버튼이 생긴다. */
function updateMention(self) {
	const no = self.getAttribute("data-target")
	const title_id = 'mention_title_span' + no
	const content_id = 'mention_content_span' + no
	const title = document.querySelector(`#${title_id}`)
	const content = document.querySelector(`#${content_id}`)
	const updateBtn = document.querySelector(`#updateBtn${no}`)
	updateBtn.removeAttribute("hidden");
	title.removeAttribute("disabled")
	content.removeAttribute("disabled")
}
function update_mention(self) {
	const user_id = document.querySelector("#user_id").value;
	const owner_eid = document.querySelector("#owner_eid").value;
	const no = self.getAttribute("data-target")
	const title_id = 'mention_title_span' + no
	const content_id = 'mention_content_span' + no
	const comment_no = document.querySelector(`#comment_no${no}`).value;

	const update_title = document.querySelector(`#${title_id}`).value
	const update_content = document.querySelector(`#${content_id}`).value

	var formData = new FormData();
	formData.append('user_id', user_id);
	formData.append('owner_eid', owner_eid);
	formData.append('mention_title', update_title);
	formData.append('comment_content', update_content);
	formData.append('comment_no', comment_no);


	$.ajax({
		type: "post",
		url: "/update_mention",
		contentType: false,
		processData: false,
		data: formData,
		success: data => {
			if (data == true) {
				window.alert("수정이 완료 되었습니다.");
				window.location.reload();
			}
		}
	})
}
function deleteMention(self) {
	const no = self.getAttribute("data-target")
	const comment_no = document.querySelector(`#comment_no${no}`).value;
	$.ajax({
		type: 'post',
		url: "/deleteComment",
		data: { comment_no: comment_no },
		success: data => {
			if (data) {
				window.alert("삭제가 완료되었습니다.")
				window.location.reload();
			}
		}
	})
}
function openMap() {
	const target = document.querySelector("#item_title");
	if (target == null) {
		console.log("null입니다.")
		window.removeEventListener('scroll', openMap);
		return;
	}
	const targetOffset = target.offsetTop;
	const winOffset = window.innerHeight;
	if (targetOffset < window.scrollY + winOffset) {
		document.querySelector("#iframe_target").removeAttribute("hidden");
		window.removeEventListener('scroll', openMap);
	}
}
window.addEventListener('scroll', openMap);

function modalOpen() {
	const modal = document.querySelector("#filter_city_div")
	modal.style.display = "flex";
}


function city_detail(self) {
	const list = document.querySelectorAll(".ss");
	list.forEach(l => {
		l.style.backgroundColor = "white";
		l.style.color = "black"
	})
	self.style.backgroundColor = "#BB2649";
	self.style.color = "white"
	/*	const modal = document.querySelector("#filter_city_div")
		modal.style.display = "none"
		const target = document.querySelector("#city_target")
		target.append(self);*/


}
function randResult() {
	const city_list = document.querySelectorAll(".ss")
	var city = "";
	city_list.forEach(c => {
		if (c.style.backgroundColor == "rgb(187, 38, 73)") {
			city = c.getAttribute("data-city");
		}
	});
	console.log("city : " + city);

	const category_list = document.getElementsByName("cate2");
	var category = "";
	category_list.forEach((l) => {
		if (l.checked) {
			category = l;
		}
	})
	console.log(category.value);
	const result_tbl = document.querySelector("#result_tbl");
	$.ajax({
		type: "post",
		url: "/randomResult",
		data: { city: city, category: category.value },
		success: (data) => {
			console.log(data)
			data.forEach(d => {
				var trEl = document.createElement("tr");
				console.log(d)
				trEl.innerHTML = `<td style="width:150px;"><a href="/store?store=${d.owner_eid}">${d.owner_bm}</a></td>`;
				console.log("추가")
				result_tbl.append(trEl);
			})
		}
	})
}
function filter20(self) {
	const target = document.querySelector("#filter2");
	const url = `/getcategory?=${self.value}`
	$.ajax({
		type: "post",
		url: url,
		data: { filter1: self.value },
		success: data => {
			while (1) {
				if (target.firstElementChild) {
					target.removeChild(target.firstElementChild)
				} else {
					break;
				}
			}

			for (i of data) {
				divEl = document.createElement("div");
				divEl.innerHTML = `<input name="cate2" value="${i}" type="radio">`
					+ `<span>${i}</span>`
				target.append(divEl)
			}
		}
	})
}
function delete_member(self){
	const user_id = self.getAttribute("data-user-id")
	$.ajax({
		type:"post",
		url:`/delete_member?user_id=${user_id}`,
		success : self.parentElement.parentElement.setAttribute("hidden","hidden")
	})
}

