package Service;

import infra.Container;
import repository.MemberRepository;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(){
        this.memberRepository = Container.memberRepository;
    }


    public int saveMember(String LoginID, String password, String name){
        return memberRepository.saveMember(loginID, password, name);
    }
}
