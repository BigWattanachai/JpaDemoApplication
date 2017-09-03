package com.example.jpademo.repositories;

import com.example.jpademo.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepo extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
}
