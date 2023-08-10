package com.example.penpal.domain.letter.repository;

import com.example.penpal.domain.letter.entity.Letter;
import com.example.penpal.web.letter.model.UnreadCountInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    //기능 수정 필요
    @Query("select l from Letter l where l.sendId = :sendId and l.receiveId = :receiveId " +
            "or l.sendId = :receiveId and l.receiveId = :sendId")
    Page<Letter> findBySendIdAndReceiveId(@Param("sendId") Long sendId, @Param("receiveId") Long receiveId, Pageable pageable);

    @Query("select l.member as member, count(*) as unreadCount from Letter l join Member m on m.id = l.member.id " +
            "where l.receiveId = :receiveId and l.isRead = false group by l.member")
    List<UnreadCountInterface> countUnreadLetterByReceiver(@Param("receiveId") Long receiveId);

    @Query("select l from Letter l join fetch Member m where l.receiveId = :userId and l.isArrived = true")
    List<Letter> findRecentLetter(@Param("userId") Long userId);

    @Query("select l from Letter l join fetch Member m where l.receiveId = :userId and l.isArrived = false ")
    List<Letter> findIncomingLetter(@Param("userId") Long userId);

    @Query("select l from Letter l join fetch Member m where l.id = :letterId and (l.sendId = :userId or l.receiveId = :userId)")
    Optional<Letter> findByUserId(@Param("userId") Long userId, @Param("letterId") Long letterId);

    @Modifying
    @Query("update Letter l set l.isRead = true where l.id = :letterId")
    void updateReadStatus(@Param("letterId") Long letterId);

    @Modifying
    @Query("update Letter l set l.receiveDate = :now where l.id = :letterId")
    void updateReceiveDate(@Param("letterId") Long letterId, @Param("now") LocalDateTime now);

    @Modifying
    @Query("delete from Letter l where (l.sendId = :userId and l.receiveId = :otherUserId) " +
            "or (l.sendId = :otherUserId and l.receiveId = :userId)")
    void deleteAllLetter(@Param("userId") Long userId, @Param("otherUserId") Long otherUserId);

    @Modifying
    @Query("update Letter l set l.isArrived = true where l.deliveryTime <= :now")
    void updateArrivedStatus(@Param("now") LocalDateTime now);
}
