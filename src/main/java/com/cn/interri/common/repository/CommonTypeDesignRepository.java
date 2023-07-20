package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonCodeDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommonTypeDesignRepository extends JpaRepository<CommonCodeDesign, Integer> {
    CommonCodeDesign findByDesignReqInfo_Id(Long id);

    @Query("select  cd.commonCode.id from CommonCodeDesign cd where cd.designReq.id = :reqId")
    List<String> getDesignInquiryCommonCode(@Param("reqId") Long reqId);
}
