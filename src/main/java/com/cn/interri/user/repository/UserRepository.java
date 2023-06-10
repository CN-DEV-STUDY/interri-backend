package com.cn.interri.user.repository;

import com.cn.interri.user.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    /**
     * 한번이라도 디자인 응답에 글을 작성한 사람은 디자이너이다.
     */
    @Query("select count(*) from User u join DesignRes dr on u.id = dr.user.id")
    Long countAllDesigners();
}
