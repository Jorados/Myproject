package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders") //테이블이름지정 order는안됨
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne //여러 회원이 주문을 할수있기때문에 다대일
    @JoinColumn(name ="member_id") //매핑을 뭘로 할것인지
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status; //주문상태 [ORDER, CANCLE] enum타입



}
