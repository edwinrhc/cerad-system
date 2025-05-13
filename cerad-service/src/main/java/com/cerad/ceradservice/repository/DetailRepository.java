package com.cerad.ceradservice.repository;

import com.cerad.ceradservice.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail,Long> {

}
