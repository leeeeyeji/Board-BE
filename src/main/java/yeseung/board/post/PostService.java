package yeseung.board.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeseung.board.member.Member;
import yeseung.board.member.MemberRepository;
import yeseung.board.member.MemberService;
import yeseung.board.post.dto.PostDetailDto;
import yeseung.board.post.dto.PostDto;
import yeseung.board.post.dto.PostForm;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public String createPost(PostForm form){

        Member member = login(form);

        if (member == null) return "잘못된 유저";

        Post post = Post.builder()
                .content(form.getContent())
                .title(form.getTitle())
                .member(member)
                .build();
        postRepository.save(post);
        member.getPostList()
                .add(post);

        return "저장완료";
    }

    private Member login(PostForm form) {
        Member member = memberRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));
        boolean login = memberService.login(form.getEmail(), form.getPassword());

        if(!login){
            return null;
        }
        return member;
    }

    public String updatePost(PostForm form,Long postId){
        Member member = login(form);
        if (member == null)
            return "잘못된 유저";

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지않는 게시글"));

        post.updateTitle(form.getTitle());
        post.updateContent(form.getContent());

        postRepository.save(post);

        return "수정성공";
    }

    public String deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));
        postRepository.delete(post);

        return "삭제 완료";
    }

    public List<PostDto> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream().map(post -> new PostDto(
                post.getPostId(),
                post.getTitle(),
                post.getMember().getUsername()
        )).collect(Collectors.toList());
    }


    public PostDetailDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));
        return PostDetailDto.builder()
                .title(post.getTitle())
                .author(post.getMember().getUsername())
                .content(post.getContent())
                .build();
    }
}


