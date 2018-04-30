package com.example.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mysite.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail( String email );
	User findByEmailAndPassword( String email, String password );

	// @Modifying  //벌크성 update, delete Query
	// @Query( value="update User u set u.name=:name where u.no=:no", nativeQuery=false )
	// int update(@Param("name") String name, @Param("no") Long no);

	@Modifying  //벌크성 update, delete Query
	@Query( value="update User u set u.name=:#{#user.name }, u.gender=:#{#user.gender } where u.no=:#{#user.no }", nativeQuery=false )
	int update(@Param("user") User user);

}