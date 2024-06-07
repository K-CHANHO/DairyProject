package sideProject.diary.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Save Member Test")
    void saveMember() {
        String email = "test@test.com";

        MemberDto member = MemberDto.builder()
                .email(email)
                .password("abcd")
                .name("테스토")
                .imgUrl("test.com")
                .build();

        MemberEntity savedMember = memberRepository.save(MemberDto.dtoToEntity(member));
        log.error("savedMember : {}", savedMember.getEmail() + " / " + savedMember.getPassword() + " / " + savedMember.getName() + " / " + savedMember.getImgUrl() );

        MemberEntity findMember = memberRepository.findById(email).orElseThrow();
        log.error("findMemeber : {}", findMember.getEmail() + " / " + findMember.getPassword() + " / " + findMember.getName() + " / " + findMember.getImgUrl());

        Assertions.assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember.getImgUrl()).isEqualTo(member.getImgUrl());
    }

    @Test
    void loadUserByUsername() {
    }
}