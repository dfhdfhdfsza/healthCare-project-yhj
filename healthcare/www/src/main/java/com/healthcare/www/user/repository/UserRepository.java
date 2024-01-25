package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserName(String userName);

    User findByUserName(String username);

    User findByUserId(String id);
}
