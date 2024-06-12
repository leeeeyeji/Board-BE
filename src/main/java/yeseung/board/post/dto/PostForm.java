package yeseung.board.post.dto;

import lombok.Data;

@Data
public class PostForm {
    private String email;
    private String password;
    private String title;
    private String content;
}
