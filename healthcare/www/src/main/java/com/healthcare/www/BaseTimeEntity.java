package com.healthcare.www;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity { // 현재 날짜 저장 용도 추상 클래스(테이블에 자동 추가)

    // 생성날짜
    @CreatedDate
    LocalDate createdDate;
    
    // 수정날짜
    @LastModifiedDate
    LocalDate modifiedDate;
    
}
