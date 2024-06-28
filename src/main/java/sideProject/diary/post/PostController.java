package sideProject.diary.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary/post")
public class PostController {

    private final PostService postService;

    @PostMapping("save")
    @Secured("USER")
    public ResponseEntity savePost(@RequestBody PostDto postDto){
        postService.savePost(postDto);

        return new ResponseEntity("일기 저장 완료", HttpStatus.OK);
    }
}
