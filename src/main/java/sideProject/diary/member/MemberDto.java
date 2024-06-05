package sideProject.diary.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public String email;
    public String password;
    public String name;
    public String imgUrl;

    public static MemberEntity dtoToEntity(MemberDto dto){
        MemberEntity entity = MemberEntity.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .imgUrl(dto.getImgUrl())
                .build();

        return entity;
    }

    public static MemberDto EntityToDto(MemberEntity entity) {
        MemberDto dto = MemberDto.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .imgUrl(entity.getImgUrl())
                .build();

        return dto;
    }
}
