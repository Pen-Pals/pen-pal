package com.example.penpal.domain.member.repository;

import com.example.penpal.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("select m from Member m where m.id in (select l.receiveId from Letter l where l.sendId = :sendId)")
    List<Member> findReceiversBySendId(@Param("sendId") Long sendId);

    @Query("select m1 from Member m1 where m1.id in " +
            "(select distinct m2.id from Member m2 join Letter l on m2.id = l.member.id where l.receiveId = :receiveId)")
    List<Member> findSendersByReceiveId(@Param("receiveId") Long receiveId);

}
