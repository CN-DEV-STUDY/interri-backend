package com.cn.interri.design.repository;

import com.cn.interri.design.domain.RoomType;
import com.cn.interri.design.dto.RoomTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    @Query("SELECT new com.cn.interri.design.dto.RoomTypeDto(rt.id, rt.roomTypeNm) FROM RoomType rt")
    List<RoomTypeDto> findAllRoomType();
}
