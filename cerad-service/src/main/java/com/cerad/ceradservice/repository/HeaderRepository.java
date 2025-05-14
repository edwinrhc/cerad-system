package com.cerad.ceradservice.repository;

import com.cerad.ceradservice.entity.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Header,Long> {



}
