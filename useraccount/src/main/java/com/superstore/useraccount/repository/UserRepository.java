package com.superstore.useraccount.repository;

import com.superstore.useraccount.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
