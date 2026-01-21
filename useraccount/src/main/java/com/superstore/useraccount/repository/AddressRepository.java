package com.superstore.useraccount.repository;

import com.superstore.useraccount.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
