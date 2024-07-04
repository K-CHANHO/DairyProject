package sideProject.diary.post;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary/post")
public class PostController {

    private final PostService postService;

    @PostMapping("save")
    @Secured("ROLE_USER")
    public ResponseEntity savePost(@RequestBody PostDto postDto){
        postService.savePost(postDto);

        return new ResponseEntity("일기 저장 완료", HttpStatus.OK);
    }

    @PostMapping("get")
    @PermitAll
    public ResponseEntity getPost(@RequestBody Map<String, String> json){
        PostDto post = postService.getPost(Long.valueOf(json.get("postId")));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("postId", post.getPostId());
        jsonObject.addProperty("title", post.getTitle());
        jsonObject.addProperty("content", post.getContent());

        Gson gson = new Gson();
        String returnString = gson.toJson(jsonObject);

        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @PostMapping("edit")
    @PermitAll
    public ResponseEntity editPost(@RequestBody PostDto postDto) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
