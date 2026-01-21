package com.atithiai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atithiai.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findTopByReferenceTypeAndReferenceIdOrderByPaymentDateDesc(
            String referenceType,
            Long referenceId
    );
    
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p")
    Double getTotalRevenue();
}
