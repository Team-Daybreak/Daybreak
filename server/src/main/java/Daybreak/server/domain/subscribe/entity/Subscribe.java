package Daybreak.server.domain.subscribe.entity;

import Daybreak.server.domain.member.entity.Member;

import javax.persistence.*;

@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
