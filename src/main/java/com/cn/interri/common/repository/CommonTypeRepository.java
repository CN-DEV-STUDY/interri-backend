package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.common.enums.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommonTypeRepository extends JpaRepository<CommonCode, String> {

    List<CommonCode> findByCodeType(CodeType type);

    @Query("select cc.codeNm from CommonCode cc where cc.id in (:commonCodeDesignIdList) and cc.id like 'RT%'")
    List<String> getDesignInquiryRoomList(@Param("commonCodeDesignIdList") List<String> commonCodeDesignIdList);
}
