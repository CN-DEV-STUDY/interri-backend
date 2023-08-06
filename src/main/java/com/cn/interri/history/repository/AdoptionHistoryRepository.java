package com.cn.interri.history.repository;

import com.cn.interri.batch.entity.AdoptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionHistoryRepository extends JpaRepository<AdoptionHistory, Long> {
}
