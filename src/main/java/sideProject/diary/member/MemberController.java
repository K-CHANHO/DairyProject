package sideProject.diary.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sideProject.diary.jwt.JwtDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity saveMember(@RequestBody MemberDto memberDto) {

        memberDto.setRoles(List.of("USER"));
        memberService.saveMember(memberDto);

        return new ResponseEntity("회원가입을 완료하였습니다. 로그인을 진행해주세요.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginMember(@RequestBody MemberDto memberDto){

        JwtDto jwt = memberService.login(memberDto.getEmail(), memberDto.getPassword());

        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity test(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return new ResponseEntity("Login Member : " + authentication.getName(), HttpStatus.OK);
    }

}
