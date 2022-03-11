package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //싱글테이블 (한 테이블에 다 때려넣는) 전략
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();


    //비즈니스 로직==//  //엔티티안에서 할수있는 로직 쓰기
    //stock 재고 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity; //재고수량 더하기
    }

    //stock 재고 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;  //0보다 적으면안돼서 체크
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
