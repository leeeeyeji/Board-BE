package yeseung.board.comment.dto;

import lombok.Data;

@Data
public class CommentForm {
    private String email;
    private String password;
    private String content;
}
