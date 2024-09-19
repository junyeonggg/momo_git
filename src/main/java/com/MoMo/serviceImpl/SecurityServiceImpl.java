package com.MoMo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MoMo.dao.MemberDao;
import com.MoMo.dto.MemberDto;
import com.MoMo.role.MemberRole;
import com.MoMo.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService{
	@Autowired
	private MemberDao memberDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto memberDto = memberDao.selectObjByUserId(username);
		if(memberDto == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(memberDto.getUser_permit() == 9) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}else if (memberDto.getUser_permit() == 5) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.OWNER.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
		}
		
		return new User(memberDto.getUser_id(),memberDto.getUser_pw(),authorities);
	}

}
