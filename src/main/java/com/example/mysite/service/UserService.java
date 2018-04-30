package com.example.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mysite.domain.Role;
import com.example.mysite.domain.User;
import com.example.mysite.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean existEmail( String email ) {
		User user = userRepository.findByEmail( email );
		return user != null;
	}
	
	public void join( User user ) {
		//1.DB에 사용정보 저장
		user.setRole( Role.USER );
		userRepository.save( user );
		
		//2. 인증 메일 보내기
	}
	
	public User getUser( String email, String password ) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public User getUser( Long no ) {
		return userRepository.findOne( no );
	}
	
	public boolean modifyUser( User user ) {
		return userRepository.update( user ) == 1;
	}
	
}
