package yeseung.board.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yeseung.board.post.dto.PostDetailDto;
import yeseung.board.post.dto.PostDto;
import yeseung.board.post.dto.PostForm;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostForm form){
        String result = postService.createPost(form);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@RequestBody PostForm form, @PathVariable Long postId){
        String result = postService.updatePost(form,postId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        String result = postService.deletePost(postId);
        return ResponseEntity.ok(result);
    }

    //모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    //게시글 상세 조회(댓글 미구현)
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailDto> getPostDetail(@PathVariable Long postId) {
        PostDetailDto postDetail = postService.getPostDetail(postId);

        return ResponseEntity.ok(postDetail);
    }

}
