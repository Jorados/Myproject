package jpabook.jpashop.repository;


import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private  final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //jpa에 저장되기전까지 아무것도없다는뜻
            em.persist(item); //persist로 신규등록 한다는거임
        }else{
            em.merge(item);  //이미 등록되있으면 업데이트한다고 생각하면됨
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)  //여러개 찾는거 jpql작성
                .getResultList();
    }
}
