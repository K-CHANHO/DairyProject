package sideProject.diary.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity saveMember(@RequestBody MemberDto memberDto) {

        MemberDto savedMember = memberService.saveMember(memberDto);

        return new ResponseEntity(savedMember, HttpStatus.OK);
    }

}
