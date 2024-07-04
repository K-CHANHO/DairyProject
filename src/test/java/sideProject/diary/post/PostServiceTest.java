package sideProject.diary.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    private PostDto rawDto = PostDto.builder()
            .email("test@test.com")
            .title("테스트 제목")
            .content("테스트 내용")
            .build();

    private PostDto editDto = PostDto.builder()
            .postId(1L)
            .title("수정 제목")
            .content("수정 내용")
            .build();

    @Test
    @DisplayName("게시물 저장 테스트")
    @Order(1)
    void savePost() {

        PostDto savedDto = postService.savePost(rawDto);

        // postId는 DB 저장되면서 insert되므로 하나씩 비교.
        assertThat(savedDto.getEmail()).isEqualTo(rawDto.getEmail());
        assertThat(savedDto.getTitle()).isEqualTo(rawDto.getTitle());
        assertThat(savedDto.getContent()).isEqualTo(rawDto.getContent());
    }

    @Test
    @DisplayName("게시물 조회 테스트")
    @Order(2)
    void getPost() {

        PostDto getDto = postService.getPost(1L);
        assertThat(getDto.getEmail()).isEqualTo(rawDto.getEmail());
        assertThat(getDto.getTitle()).isEqualTo(rawDto.getTitle());
        assertThat(getDto.getContent()).isEqualTo(rawDto.getContent());
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    @Order(3)
    void editPost() {
        PostDto editDto = postService.editPost(this.editDto);

        assertThat(editDto.getPostId()).isEqualTo(1L);
        assertThat(editDto.getEmail()).isEqualTo("test@test.com");
        assertThat(editDto.getTitle()).isEqualTo(editDto.getTitle());
        assertThat(editDto.getContent()).isEqualTo(editDto.getContent());
    }
}