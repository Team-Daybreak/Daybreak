package Daybreak.server.domain.subscribe.entity;

import Daybreak.server.domain.user.entity.User;

import javax.persistence.*;

@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
