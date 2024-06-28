package sideProject.diary.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDto savePost(PostDto postDto) {
        PostEntity postEntity = postRepository.save(PostDto.toEntity(postDto));

        return PostDto.toDto(postEntity);
    }
}
