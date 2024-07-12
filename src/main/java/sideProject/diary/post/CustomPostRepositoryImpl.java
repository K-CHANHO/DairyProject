package sideProject.diary.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private QPostEntity post = QPostEntity.postEntity;

    @Override
    public List<PostEntity> findAllByEmail(String email) {

        return jpaQueryFactory.selectFrom(post)
                .where(post.email.eq(email))
                .fetch();

    }
}
