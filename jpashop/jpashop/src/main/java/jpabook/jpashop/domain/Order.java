package jpabook.jpashop.domain;


import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //테이블이름지정 order는안됨
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //new Order(); 이런식으로의 코드로 직접생성을 못하게
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //여러 회원이 주문을 할수있기때문에 다대일
    @JoinColumn(name ="member_id") //매핑을 뭘로 할것인지
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)  //cascade는 persist를 전파해준다
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCLE] enum타입


    //연관관계 편의 메서드/// 양방향일때 쓰면좋음
    public void  setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder((this));
    }

    //생성 메서드==//
    //Order가 연관관계를 싹 다 걸면서 셋팅되고 상태나,주문정보까지 다 정리(생성)됨.
    public static Order createOrder(Member member,Delivery delivery, OrderItem...orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    //==비즈니스 로직==//
    //주문취소
    public void cancle(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus((OrderStatus.CANCLE));
        for(OrderItem orderItem : orderItems){
            orderItem.cancle();
        }
    }

    //===조회 로직==//
    //전체 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
          return totalPrice;
    }



}
