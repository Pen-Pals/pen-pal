package com.example.penpal.web.letter.model;

import com.example.penpal.domain.member.entity.Member;

public interface UnreadCountInterface {

    Member getMember();

    Integer getUnreadCount();


}
