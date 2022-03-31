package com.hooked.app.repositories;

import com.hooked.app.models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    List<UserStatus> findAllByCustomer_id(Long id);
}
