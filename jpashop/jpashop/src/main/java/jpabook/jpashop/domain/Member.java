package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Member entitiy
@Entity
@Getter @Setter //lombok으로 개터세터 열어둠
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")  //매핑(회원테이블) Id 이름지정
    private Long id;

    private String name;

    @Embedded //jpa내장타입을 포함
    private Address address;  //Address 클래스 만들어줌

    @OneToMany(mappedBy = "member") //누구에의해서 매핑이 됐는지 -> ordertable에 있는 member필드에 의해서 매핑됨
    //order의 입장에서는 한 회원이 여러주문을 할수있기때문에 일대다
    private List<Order> orders = new ArrayList<>();  //동적배열생성 //Order 클래스 만들어줌

}
