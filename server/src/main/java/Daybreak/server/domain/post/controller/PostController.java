package Daybreak.server.domain.post.controller;

import Daybreak.server.domain.post.dto.PostDto;
import Daybreak.server.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * @author 신건우
     * @param data : 글 생성 데이터
     * @param uid : 유저 id
     * @desc 게시글 생성
     */
    @PostMapping
    public ResponseEntity<PostDto.Response> createPost(
            @RequestBody PostDto.Post data,
            @RequestParam @Positive Long uid) {
        return new ResponseEntity<>(PostDto.Response.fromEntity(postService.create(data, uid)), HttpStatus.CREATED);
    }

    /**
     * @author 신건우
     * @param data : 글 수정 데이터
     * @param uid : 유저 id
     * @desc 게시글 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PostDto.Response> updatePost(
            @RequestBody PostDto.Patch data,
            @RequestParam @Positive Long postId,
            @RequestParam @Positive Long uid) {
        return new ResponseEntity<>(PostDto.Response.fromEntity(postService.update(data, postId, uid)), HttpStatus.OK);
    }

    /**
     * @author 신건우
     * @param id : 글 번호
     * @param uid : 유저 id
     * @desc 게시글 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(
            @PathVariable("id") Long id,
            @RequestParam("uid") @Positive Long uid) {

        postService.delete(id, uid);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @author 신건우
     * @param id : 글 번호
     * @desc 게시글 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> getPost(
            @RequestParam("id") @Positive Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
