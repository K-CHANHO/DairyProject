package sideProject.diary.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dairy_member")
public class MemberEntity {

    @Id
    private String userId;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String imgUrl;


}
