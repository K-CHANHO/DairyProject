package sideProject.diary.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;

    private String email;

    public static JwtEntity dtoToEntity(JwtDTO jwtDTO){
        JwtEntity entity = JwtEntity.builder()
                .email(jwtDTO.getEmail())
                .refreshToken(jwtDTO.getRefreshToken())
                .build();

        return entity;
    }

    public static JwtDTO EntityToDto(JwtEntity entity){
        JwtDTO dto = JwtDTO.builder()
                .email(entity.getEmail())
                .grantType("Bearer")
                .refreshToken(entity.getRefreshToken())
                .build();

        return dto;
    }
}
