package com.hooked.app.repositories;

import com.hooked.app.models.angler.Friend;
import com.hooked.app.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

//    boolean existsByFirstUserAndSecondUser(User first, User second);

//    List<Friend> findByFirstUser(User user);
//    List<Friend> findBySecondUser(User user);
}
