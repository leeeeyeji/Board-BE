package yeseung.board.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private String author;
}
