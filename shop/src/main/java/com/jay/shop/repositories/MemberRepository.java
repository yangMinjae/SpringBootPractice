package com.jay.shop.repositories;

import com.jay.shop.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);
}
