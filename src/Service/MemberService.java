package Service;

import infra.Container;
import repository.MemberRepository;

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
}
