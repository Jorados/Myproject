package jpabook.jpashop.domain;



import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.DeliveryStatus;
import jpabook.jpashop.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded //내장타입
    private Address address;

    @Enumerated(EnumType.STRING)    //enum타입을 넣을때 이 어노테이션 써야함
    private DeliveryStatus status; //READY, COMP

}
