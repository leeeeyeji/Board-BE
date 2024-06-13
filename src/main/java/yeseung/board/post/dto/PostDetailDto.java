package yeseung.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostDetailDto {
    private String title;
    private String author;
    private String content;
}
