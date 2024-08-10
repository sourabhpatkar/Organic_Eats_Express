package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

}
