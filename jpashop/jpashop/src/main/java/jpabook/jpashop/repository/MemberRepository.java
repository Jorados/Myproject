package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);    //em db에 insert콜이날라감
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);   //단건 조회
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){ //회원을 이름에 의해서 조회하는 코드
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
