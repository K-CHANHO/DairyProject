package sideProject.diary.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sideProject.diary.jwt.JwtDTO;
import sideProject.diary.jwt.JwtService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity saveMember(@RequestBody MemberDto memberDto) {

        memberService.saveMember(memberDto);

        return new ResponseEntity("회원가입을 완료하였습니다. 로그인을 진행해주세요.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto){

        JwtDTO jwt = memberService.login(memberDto.getEmail(), memberDto.getPassword());

        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity test(){
        return new ResponseEntity("TEST", HttpStatus.OK);
    }

}
