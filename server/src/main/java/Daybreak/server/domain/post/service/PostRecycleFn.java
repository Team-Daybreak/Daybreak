//package Daybreak.server.domain.post.service;
//
//import Daybreak.server.domain.post.entity.Kind;
//import Daybreak.server.domain.post.entity.Post;
//import Daybreak.server.domain.post.entity.Status;
//import Daybreak.server.domain.post.repository.PostRepository;
//import Daybreak.server.domain.user.entity.Member;
//import Daybreak.server.error.CommonException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PostRecycleFn {
//    private final Logger log = LoggerFactory.getLogger(PostService.class);
//    private final PostRepository postRepository;
//
//    public PostRecycleFn(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    public Post checkPostStatus(Long id, Status status, Member member) {
//
//        Post post = null;
//
//        try {
//            post = postRepository.findByIdAndCheckStatus(id, status, member).orElse(null);
//
//            if (post == null) {
//                log.error("POST-002 : 존재하지 않는 게시글 [ 게시글 번호 : {}", post.getId());
//            }
//        } catch (Exception e) {
//            log.error("POST-001 : SQL 게시글 조회 에러 [ 게시글 번호 : {}, 삭제여부 : {}", id, status);
//            throw new CommonException("POST-001", HttpStatus.NOT_FOUND);
//        }
//
//        return post;
//    }
//}
