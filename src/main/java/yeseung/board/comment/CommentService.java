package yeseung.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import yeseung.board.comment.dto.CommentDto;
import yeseung.board.comment.dto.CommentForm;
import yeseung.board.member.Member;
import yeseung.board.member.MemberRepository;
import yeseung.board.post.Post;
import yeseung.board.post.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CommentDto> getAllComments(@PathVariable Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));
        List<Comment> allComments = commentRepository.findAllByPost(post);

        return allComments.stream().map(comment -> new CommentDto(
                comment.getCommentId(),
                comment.getContent(),
                comment.getMember().getUsername()
        )).collect(Collectors.toList());
    }

    public String deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글"));
        commentRepository.delete(comment);

        return "댓글 삭제 완료";
    }

}
