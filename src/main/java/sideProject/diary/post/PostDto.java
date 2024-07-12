package sideProject.diary.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long postId; // 일기 고유번호
    private String title; // 일기제목
    private String content; // 일기내용
    private String status; // 상태

    private String email; // 작성자 아이디

    public static PostEntity toEntity(PostDto dto){
        PostEntity entity = PostEntity.builder()
                .email(dto.getEmail())
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .status(dto.getStatus())
                .build();

        return entity;
    }

    public static PostDto toDto(PostEntity entity){
        PostDto dto = PostDto.builder()
                .email(entity.getEmail())
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .status(entity.getStatus())
                .build();

        return dto;
    }
}
