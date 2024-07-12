package sideProject.diary.post;

import java.util.List;

public interface CustomPostRepository {

    List<PostEntity> findAllByEmail(String email);
}
