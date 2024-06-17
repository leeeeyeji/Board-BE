package yeseung.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yeseung.board.comment.dto.CommentDto;
import yeseung.board.comment.dto.CommentForm;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentForm form, @PathVariable Long postId){
        String result = commentService.createComment(form,postId);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long postId){
        List<CommentDto> allComments = commentService.getAllComments(postId);
        return ResponseEntity.ok(allComments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        String result = commentService.deleteComment(commentId);
        return ResponseEntity.ok(result);
    }
}
