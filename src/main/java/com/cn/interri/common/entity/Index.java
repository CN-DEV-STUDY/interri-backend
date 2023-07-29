package com.cn.interri.common.entity;

import com.cn.interri.batch.dto.InteriorTrendsDto;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("idx")
@Getter
public class Index {

    @Id
    private Long idx;
    private InteriorTrendsDto interiorTrendsDto;
}
