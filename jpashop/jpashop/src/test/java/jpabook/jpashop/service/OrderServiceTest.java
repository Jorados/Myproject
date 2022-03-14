package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{

        //given
        Member member = createMember();

        Book book = createBook("시골JPA",10000,10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다");
        assertEquals(10000*orderCount,getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다");
        assertEquals(8,book.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");
    }



    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = new Member();
        Item item = createBook("시골JPA",10000,10);
        int orderCount = 2;

        Long orderId  =orderService.order(member.getId(),item.getId(),orderCount);

        //when
        orderService.cancleOrder(orderId);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCLE,getOrder.getStatus(),"주문 취소시 상태는 cancle이다.");
        assertEquals(10,item.getStockQuantity(),"주문이 최소된 상품은 그만큼 재고가 증가해야한다..");
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = new Member();
        Item item = createBook("시골JPA",10000,10);
        int orderCount = 8;
        //when
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount), "재고 수량 부족 예외가 발생해야 한다.");
        //then
        fail("재고수량부족예외가 발행해야한다.");
    }



    private Book createBook(String name, int price , int stockQuantitiy) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantitiy);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member =new Member();
        member.setName("회원1");
        member.setAddress((new Address("서울","강가","123-123")));
        em.persist(member);
        return member;
    }
}