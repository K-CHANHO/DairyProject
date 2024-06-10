package sideProject.diary.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String email = "test@test.com";
    private MemberDto member = MemberDto.builder()
            .email(email)
            .password("abcd")
            .name("테스토")
            .imgUrl("test.com")
            .build();


    @Test
    @DisplayName("Save Member Test")
    @Order(1)
    void saveMember() {
        MemberDto savedMember = memberService.saveMember(member);
        log.error("savedMember : {}", savedMember.getEmail() + " / " + savedMember.getPassword() + " / " + savedMember.getName() + " / " + savedMember.getImgUrl() );

    }

    @Test
    void findMemberByEmail() {

        MemberDto findMember = memberService.findMemberByEmail(email);
        log.error("findMemeber : {}", findMember.getEmail() + " / " + findMember.getPassword() + " / " + findMember.getName() + " / " + findMember.getImgUrl());

        Assertions.assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(passwordEncoder.matches(member.getPassword(), findMember.getPassword())).isTrue();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember.getImgUrl()).isEqualTo(member.getImgUrl());

    }
}