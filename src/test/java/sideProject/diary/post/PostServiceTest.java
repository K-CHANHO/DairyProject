package sideProject.diary.post;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired PostService postService;

    private PostDto tempDto = PostDto.builder()
            .email("temp@test.com")
            .title("임시저장 테스트")
            .content("임시저장 테스트")
            .status("TEMP")
            .build();

    private PostDto rawDto = PostDto.builder()
            .email("test@test.com")
            .title("테스트 제목")
            .content("테스트 내용")
            .status("DONE")
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

    @Test
    @DisplayName("게시물 삭제 테스트")
    @Order(4)
    void deletePost(){
        postService.deletePost(1L);

        assertThatThrownBy(() -> postService.getPost(1L)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("임시저장 테스트")
    void tempSavePost(){
        PostDto postDto = postService.savePost(tempDto);

        assertThat(postDto.getStatus()).isEqualTo("TEMP");

    }


    @Test
    @DisplayName("사용자의 모든 일기 조회")
    void getAllPosts() {

        List<PostDto> dtoList = new ArrayList<>();
        IntStream.rangeClosed(1,5).forEach(i->{
                PostDto post = PostDto.builder()
                        .email("test2@test.com")
                        .title(i+"번 일기")
                        .content(i+"번 내용")
                        .status("DONE")
                        .build();
            PostDto postDto = postService.savePost(post);
            dtoList.add(postDto);
                }
        );

        String email = "test2@test.com";
        List<PostDto> allPosts = postService.getAllPosts(email);

        Gson gson = new Gson();
        assertThat(gson.toJson(allPosts)).isEqualTo(gson.toJson(dtoList));
    }
}