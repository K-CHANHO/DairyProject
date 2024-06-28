package sideProject.diary.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<JwtEntity, String> {
}
