package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {}
