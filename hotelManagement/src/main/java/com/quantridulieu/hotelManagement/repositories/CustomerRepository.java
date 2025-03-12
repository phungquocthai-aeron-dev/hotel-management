package com.quantridulieu.hotelManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	@Query(value = "SELECT * FROM customer WHERE customer_name = :customerName", nativeQuery = true)
	List<Customer> findCustomerByName(@Param("customerName") String customerName);
	
	@Query(value = "SELECT * FROM customer WHERE customer_phone = :customerPhone", nativeQuery = true)
	Customer findCustomerByPhone(@Param("customerPhone") String customerPhone);
	
	@Query(value = "SELECT * FROM customer WHERE address = :address", nativeQuery = true)
	List<Customer> findCustomerByAddress(@Param("address") String address);
	
	@Query(value = "SELECT * FROM customer WHERE customer_id IN (:customerIds)", nativeQuery = true)
    List<Customer> findByCustomerIds(@Param("customerIds") List<String> customerIds);
	
	@Modifying
	@Procedure(name = "SearchCustomer")
    List<Customer> SearchCustomer(
        @Param("p_customer_id") String customerId,
        @Param("p_customer_name") String customerName,
        @Param("p_customer_phone") String customerPhone,
        @Param("p_address") String address
    );
}
