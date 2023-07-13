package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.common.enums.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonTypeRepository extends JpaRepository<CommonCode, String> {

    List<CommonCode> findByCodeType(CodeType type);
}
