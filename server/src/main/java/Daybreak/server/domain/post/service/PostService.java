package Daybreak.server.domain.post.service;

import Daybreak.server.domain.post.dto.PostDto;
import Daybreak.server.domain.post.entity.Post;
import Daybreak.server.domain.post.entity.Status;
import Daybreak.server.domain.post.repository.PostRepository;
import Daybreak.server.domain.user.entity.Member;
import Daybreak.server.domain.user.repository.MemberRepository;
import Daybreak.server.error.CommonException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final PostRecycleFn postRecycleFn;
    private final MemberRepository memberRepository;

    public Post create(PostDto.Post data, Long uid) {

        Post post;
        Member member;

        try {
            member = memberRepository.getReferenceById(uid);
        } catch (Exception e) {
            log.error("MEMBER-001 : 유저 조회 오류 - uid : {}", uid);
            throw new CommonException("MEMBER-001", HttpStatus.NOT_FOUND);
        }

        try {
            post = postRepository.save(data.toEntity(member));

        } catch (Exception e) {
            log.error("POST-001 : 게시글 생성 실패 - 작성한 유저 : {}", member.getId());
            throw new CommonException("POST-001", HttpStatus.NOT_FOUND);
        }

        return post;
    }

    public Post update(PostDto.Patch data, Long postId, Long uid) {
        Optional<Post> post;
        Member member;

        try {
            member = memberRepository.getReferenceById(uid);
        } catch (Exception e) {
            log.error("MEMBER-001 : 유저 조회 오류 - uid : {}", uid);
            throw new CommonException("MEMBER-001", HttpStatus.NOT_FOUND);
        }

        try {
            post = postRepository.findById(postId);
        } catch (Exception e) {
            log.error("POST-002 : 게시글 조회 실패 - 글번호 : {}", postId);
            throw new CommonException("POST-002", HttpStatus.NOT_FOUND);
        }

        Post aPost = post.get();

        if (!Objects.equals(aPost.getMember().getMemberId(), member.getMemberId())) {
            log.error("AUTH-001 : 해당 유저가 작성한 글이 아닙니다. - 유저id : {}, 글번호 : {}", member.getMemberId(), aPost.getId());

            throw new CommonException("AUTH-001 : 해당 유저가 작성한 글이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        /* 삭제된 글이면 Exception */
        if (aPost.getStatus() == Status.DELETED) {
            throw new CommonException("POST-003 : 삭제된 글입니다.", HttpStatus.NOT_FOUND);
        }

        aPost.updatePost(data.getTitle(), data.getContent());

        return aPost;
    }

    public void delete(Long id, Long uid) {

        Optional<Post> post;
        Member member;

        try {
            member = memberRepository.getReferenceById(uid);
        } catch (Exception e) {
            log.error("MEMBER-001 : 유저 조회 오류 - uid : {}", uid);
            throw new CommonException("MEMBER-001", HttpStatus.NOT_FOUND);
        }

        try {
            post = postRepository.findById(id);
        } catch (Exception e) {
            log.error("POST-002 : 게시글 조회 실패 - 글번호 : {}", id);
            throw new CommonException("POST-002", HttpStatus.NOT_FOUND);
        }

        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            log.error("POST-003 : 잘못된 Post ID 입니다. 입력된 ID : {} , Exception : {}", id, e);
            throw new CommonException("POST-003", HttpStatus.BAD_REQUEST);
        }

        post.get().setStatus(Status.DELETED);
    }

    @Transactional(readOnly = true)
    public Post searchOne(Long id, Long uid) {
        Optional<Post> post;
        Member member;

        try {
            member = memberRepository.getReferenceById(uid);
        } catch (Exception e) {
            log.error("MEMBER-001 : 유저 조회 오류 - uid : {}", uid);
            throw new CommonException("MEMBER-001", HttpStatus.NOT_FOUND);
        }

        try {
            post = postRepository.findById(id);
        } catch (Exception e) {
            log.error("POST-002 : 게시글 조회 실패 - 글번호 : {}", id);
            throw new CommonException("POST-002", HttpStatus.NOT_FOUND);
        }

        try {
            postRecycleFn.checkPostStatus(id, Status.ACTIVE, member);
        } catch (Exception e) {

            log.error("POST-001 : 게시글 조회 실패 - uid : {}, 글번호: {}", member.getMemberId(), post.get().getId());

            throw new CommonException("POST-001", HttpStatus.NOT_FOUND);
        }

        return post.get();
    }
}
