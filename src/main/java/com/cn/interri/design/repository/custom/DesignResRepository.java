package com.cn.interri.design.repository.custom;

import com.cn.interri.design.domain.DesignRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResRepository extends JpaRepository<DesignRes , Long> , DesignResCustomRepository {
}
