package com.ksquare.sso.repository;

import org.springframework.data.repository.CrudRepository;

import com.ksquare.sso.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
