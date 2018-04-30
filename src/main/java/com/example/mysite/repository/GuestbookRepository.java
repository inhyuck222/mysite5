package com.example.mysite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mysite.domain.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
	List<Guestbook> findAllByOrderByRegDateDesc();
	
	@Query("select gb from Guestbook gb where gb.no > :startNo")
	Page<Guestbook> findAllByStratNo(@Param("startNo") Long startNo, Pageable pageable);
}
