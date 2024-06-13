package yeseung.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeseung.board.comment.dto.CommentForm;
import yeseung.board.member.Member;
import yeseung.board.member.MemberRepository;
import yeseung.board.post.Post;
import yeseung.board.post.PostRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Member login(CommentForm form){
        Member member = memberRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));
        if (member.getPassword().equals(form.getPassword())){
            return member;
        }else {
            return null;
        }
    }

    public String createComment(CommentForm form,Long postId) {

        Member member = login(form);
        if (member == null) return "잘못된 유저";

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

        Comment comment = Comment.builder()
                .content(form.getContent())
                .member(member)
                .post(post)
                .build();

        commentRepository.save(comment);
        member.getCommentList()
                .add(comment);
        return "댓글 저장 완료";
    }




}
