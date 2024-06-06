package sideProject.diary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public ResponseEntity home(){
        return new ResponseEntity("테스트", HttpStatus.OK);
    }
}
