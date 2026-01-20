package com.atithiai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atithiai.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findTopByReferenceTypeAndReferenceIdOrderByPaymentDateDesc(
            String referenceType,
            Long referenceId
    );
}
