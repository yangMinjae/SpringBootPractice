package com.jay.shop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 자식 엔티티들에게 본인의 멤버 필드를 컬럼으로 물려줌
@EntityListeners(AuditingEntityListener.class)  // 엔티티 변경 감시 리스너 등록
public class BaseTimeEntity {
    @CreatedDate
    @Column(name = "fst_reg_dt", updatable = false)
    private LocalDateTime fstRegDt;

    @LastModifiedDate
    @Column(name = "lst_chg_dt")
    private LocalDateTime lstChgDt;
}
