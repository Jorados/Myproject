package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service   //@Component가 포함되어있어서 스프링빈으로 등록됨
@Transactional(readOnly = true) //jpa 모든 데이터변경이나 로직은 여기안에서 다 실행 되어야한다.
//읽기 기능이 있으면 readOnly를 트루로 해준다. 그게아니면 그냥 @Transactional로 한다.  디폴트값은 false.
@RequiredArgsConstructor  //final이 있는 필드만을 가지고 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;   //이 필드 변경 할 일이없으므로 final을 붙혀준다.

    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);    //중복회원 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {    //중복회원 검사 로직
     //EXEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
