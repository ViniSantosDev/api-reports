package com.vinisantosdev.apireports.apireports.repository;

import com.vinisantosdev.apireports.apireports.model.ReturnData1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnDataRepository extends JpaRepository<ReturnData1, Long> {



}
