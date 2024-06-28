package sideProject.diary.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;

    private String email;

    public static JwtEntity dtoToEntity(JwtDto jwtDTO){
        JwtEntity entity = JwtEntity.builder()
                .email(jwtDTO.getEmail())
                .refreshToken(jwtDTO.getRefreshToken())
                .build();

        return entity;
    }

    public static JwtDto EntityToDto(JwtEntity entity){
        JwtDto dto = JwtDto.builder()
                .email(entity.getEmail())
                .grantType("Bearer")
                .refreshToken(entity.getRefreshToken())
                .build();

        return dto;
    }
}
