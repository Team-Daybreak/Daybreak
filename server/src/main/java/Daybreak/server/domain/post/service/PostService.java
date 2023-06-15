package Daybreak.server.domain.post.service;

import Daybreak.server.domain.post.dto.PostDto;
import Daybreak.server.domain.post.repository.PostRepository;
import Daybreak.server.error.CommonException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Logger log = (Logger) LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public PostDto.Response create(String title, String content) {
        log.info("aaa");
        return null;
    }

    public PostDto.Response update(String title, String content) {
        log.info("aa");
        return PostDto.
    }

    public void delete(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            log.error("POST-003 : 잘못된 Post ID 입니다. 입력된 ID : {} , Exception : {}", id, e);
            throw new CommonException("POST-003", HttpStatus.BAD_REQUEST);
        }
    }
}
