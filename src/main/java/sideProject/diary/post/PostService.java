package sideProject.diary.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDto savePost(PostDto postDto) {
        PostEntity postEntity = postRepository.save(PostDto.toEntity(postDto));

        return PostDto.toDto(postEntity);
    }

    public PostDto getPost(Long postId) {
        PostEntity entity = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다. postId를 확인해주세요."));
        return PostDto.toDto(entity);
    }

    public PostDto editPost(PostDto postDto){
        PostEntity entity = postRepository.findById(postDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다. postId를 확인해주세요."));

        PostEntity editEntity = entity.toBuilder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();

        PostEntity saved = postRepository.save(editEntity);

        return PostDto.toDto(saved);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<PostDto> getAllPosts(String email){
        List<PostEntity> postsEntity = postRepository.findAllByEmail(email);
        List<PostDto> postsDto = postsEntity.stream().map(PostDto::toDto).collect(Collectors.toList());

        return postsDto;
    }
}
