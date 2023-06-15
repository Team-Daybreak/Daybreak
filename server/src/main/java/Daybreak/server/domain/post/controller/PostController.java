package Daybreak.server.domain.post.controller;

import Daybreak.server.domain.post.dto.PostDto;
import Daybreak.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * @author 신건우
     * @param data : 글 생성 데이터
     * @desc 게시글 생성
     */
    @PostMapping
    public ResponseEntity<PostDto.Response> createPost(@RequestBody PostDto.Post data) {
        return new ResponseEntity<PostDto.Response>(postService.create(data), HttpStatus.CREATED);
    }

    /**
     * @author 신건우
     * @param data : 글 수정 데이터
     * @desc 게시글 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PostDto.Response> updatePost(@RequestBody PostDto.Patch data) {
        return new ResponseEntity<PostDto.Response>(postService.update(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") Long id) {
       return new ResponseEntity(postService. HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> getPost(@PathVariable("id") Long id) {
        return new ResponseEntity<PostDto.Response>(postService.delete(id), HttpStatus.OK);
    }
}
