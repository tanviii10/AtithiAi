package com.atithiai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atithiai.entities.OrderMaster;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

}
