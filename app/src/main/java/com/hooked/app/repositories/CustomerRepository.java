package com.hooked.app.repositories;

import com.hooked.app.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    Customer findByUser_id(Long id);

}
