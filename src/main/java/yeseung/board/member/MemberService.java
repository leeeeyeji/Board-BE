package yeseung.board.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeseung.board.member.dto.LoginForm;
import yeseung.board.member.dto.MemberForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String createMember(MemberForm form) {
        Member member = Member.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .username(form.getUsername())
                .build();

        memberRepository.save(member);
        return "유저 생성 완료";
    }

    public boolean login(String email,String password){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));

        if (member.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }
}
