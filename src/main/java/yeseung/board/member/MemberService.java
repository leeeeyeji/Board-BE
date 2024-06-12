package yeseung.board.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
}
