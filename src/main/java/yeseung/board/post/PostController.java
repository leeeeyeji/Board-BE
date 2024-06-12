package yeseung.board.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yeseung.board.post.dto.PostForm;

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

}
