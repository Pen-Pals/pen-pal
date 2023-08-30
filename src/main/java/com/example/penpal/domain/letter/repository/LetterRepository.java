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

    @Query("select l from Letter l where l.sendId = :sendId and l.receiveId = :receiveId and l.deletedBySender = false " +
            "or l.sendId = :receiveId and l.receiveId = :sendId and l.deletedByReceiver = false")
    Page<Letter> findBySendIdAndReceiveId(@Param("sendId") Long sendId, @Param("receiveId") Long receiveId, Pageable pageable);

    @Query("select l.member as member, count(*) as unreadCount from Letter l join Member m on m.id = l.member.id " +
            "where l.receiveId = :receiveId and l.isRead = false and l.deletedByReceiver = false group by l.member")
    List<UnreadCountInterface> countUnreadLetterByReceiver(@Param("receiveId") Long receiveId);

    @Query("select l from Letter l join fetch Member m " +
            "where l.receiveId = :userId and l.isArrived = true and l.deletedByReceiver = false")
    List<Letter> findRecentLetter(@Param("userId") Long userId);

    @Query("select l from Letter l join fetch Member m " +
            "where l.receiveId = :userId and l.isArrived = false and l.deletedByReceiver = false")
    List<Letter> findIncomingLetter(@Param("userId") Long userId);

    @Query("select l from Letter l where l.id = :letterId and " +
            "(l.sendId = :myId and l.receiveId = :userId and l.deletedBySender = false) " +
            "or (l.sendId = :userId and l.receiveId = :myId and l.deletedByReceiver = false)")
    Optional<Letter> findByUserId(@Param("myId") Long myId, @Param("userId") Long userId, @Param("letterId") Long letterId);

    @Modifying
    @Query("update Letter l set l.isRead = true where l.id = :letterId")
    void updateReadStatus(@Param("letterId") Long letterId);

    @Modifying
    @Query("update Letter l set l.receiveDate = :now where l.id = :letterId")
    void updateReceiveDate(@Param("letterId") Long letterId, @Param("now") LocalDateTime now);

    @Modifying
    @Query("update Letter l set l.deletedBySender = true where l.sendId = :myId and l.receiveId = :otherUserId")
    void deleteLetterBySender(@Param("myId") Long myId, @Param("otherUserId") Long otherUserId);

    @Modifying
    @Query("update Letter l set l.deletedByReceiver = true where l.sendId = :otherUserId and l.receiveId = :myId")
    void deleteLetterByReceiver(@Param("myId") Long myId, @Param("otherUserId") Long otherUserId);

    @Modifying
    @Query("delete from Letter l where l.deletedBySender = true and l.deletedByReceiver = true")
    void deleteLetter();

    @Modifying
    @Query("update Letter l set l.isArrived = true where l.deliveryTime <= :now")
    void updateArrivedStatus(@Param("now") LocalDateTime now);
}
