package com.atithiai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atithiai.entities.OrderMaster;
import com.atithiai.enums.OrderStatus;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {
	
	List<OrderMaster> findByStatusIn(List<OrderStatus> statuses);

}
