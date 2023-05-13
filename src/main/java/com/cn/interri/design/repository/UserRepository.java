package com.cn.interri.design.repository;

import com.cn.interri.user.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
