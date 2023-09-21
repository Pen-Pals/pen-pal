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

    @Query("select m from Member m where m.id in " +
            "(select distinct l.receiveId from Letter l where l.sendId = :sendId and l.deletedBySender = false)")
    List<Member> findReceiversBySendId(@Param("sendId") Long sendId);

    @Query("select m from Member m where m.id in " +
            "(select distinct l.sendId from Letter l where l.receiveId = :receiveId and l.deletedByReceiver = false)")
    List<Member> findSendersByReceiveId(@Param("receiveId") Long receiveId);

    @Query("select m from Member m join fetch m.profile p where " +
            "m.nickname like concat(:query, '%') order by length(m.nickname)")
    List<Member> findMembersWithProfile(@Param("query") String query);

}
