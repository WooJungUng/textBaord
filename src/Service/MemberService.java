package Service;

import data.Member;
import infra.Container;
import repository.MemberRepository;

import java.util.List;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(){
        this.memberRepository = Container.memberRepository;
    }


    public int saveMember(String LoginId, String password, String name){
        return memberRepository.saveMember(LoginId, password, name);
    }

    public boolean isExistByLoginId(String loginId){
        return memberRepository.isExistByLoginId(loginId);
    }

    public boolean isCorrectInfo(String loginId, String password){
        Member findMember = memberRepository.getMemberByLoginId(loginId);

        if(findMember == null){
            return false;
        }

        if(findMember.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.getMemberByLoginId(loginId);
    }

    public void delete(String loginId) {
        Member findMember = getMemberByLoginId(loginId);
        memberRepository.delete(findMember);

    }

    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }
}
