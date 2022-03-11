package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ExtendWith(SpringExtension.class)  //Runwith 대신 씀 //실제로 스프링이랑 엮어서하는것
@Transactional //이게있어야 롤백이됨
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(value = false)  //@Transactional때문에 롤백되는걸 막고 db에 쿼리날리는걸 로그로 보고싶으면 쓰면됨
    public void 회원가입() throws Exception{
        //given 이런게 주어졌을때
        Member member = new Member();
        member.setName("kim");
        //when 이렇게 하면
        Long saveId = memberService.join(member);
        //then 이렇게 된다.
        //em.flush(); //이거쓰면 롤백은되더라도 강제로 db에 쿼리를 보냄.
        assertEquals(member,memberRepository.findOne(saveId));

    }


    @Test
    public void 중복_회원_예외() throws Exception{
        //given 주어졌을때
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when  이렇게 하면
        memberService.join(member1);

        try{
            memberService.join(member2); //예외가 발생해야 한다!!
        }catch (IllegalStateException e){
            return;
        }

        //then  이렇게 된다
        fail("예외가 발생해야 한다.");

    }
}