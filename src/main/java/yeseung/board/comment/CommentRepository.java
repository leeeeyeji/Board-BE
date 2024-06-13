package yeseung.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeseung.board.post.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
