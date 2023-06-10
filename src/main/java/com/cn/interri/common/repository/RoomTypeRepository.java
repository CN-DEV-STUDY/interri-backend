package com.cn.interri.common.repository;

import com.cn.interri.common.dto.RoomTypeDto;
import com.cn.interri.common.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    @Query("SELECT new com.cn.interri.common.dto.RoomTypeDto(rt.id, rt.roomTypeNm) FROM RoomType rt")
    List<RoomTypeDto> findAllRoomType();
}
