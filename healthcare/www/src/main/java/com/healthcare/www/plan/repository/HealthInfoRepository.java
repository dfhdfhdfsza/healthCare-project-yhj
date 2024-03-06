package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.HealthInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface HealthInfoRepository extends JpaRepository<HealthInfo, Long>, PagingAndSortingRepository<HealthInfo, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE health_info", nativeQuery = true)
    void resetHealthInfo();

    HealthInfo findTopByOrderByInfoNoDesc();


    Page<HealthInfo> findByTarget(String bodypart, PageRequest pageable);

    Page<HealthInfo> findByEquipment(String equipment, PageRequest pageable);

    HealthInfo findByName(String name);

    Page<HealthInfo> findByNameContaining(String keyword, PageRequest pageable);
}
