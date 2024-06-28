package sideProject.diary.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long postId;
    private String title;
    private String content;

    public static PostEntity toEntity(PostDto dto){
        PostEntity entity = PostEntity.builder()
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        return entity;
    }

    public static PostDto toDto(PostEntity entity){
        PostDto dto = PostDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

        return dto;
    }
}
