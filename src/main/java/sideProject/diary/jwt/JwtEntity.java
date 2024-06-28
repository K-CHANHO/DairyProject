package sideProject.diary.jwt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diary_refresh_token")
public class JwtEntity {

    @Id
    private String email;

    @Column
    private String refreshToken;
}
