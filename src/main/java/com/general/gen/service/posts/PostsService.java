package com.general.gen.service.posts;

import com.general.gen.domain.posts.Posts;
import com.general.gen.domain.posts.PostsRepository;
import com.general.gen.web.Dto.PostsResponseDto;
import com.general.gen.web.Dto.PostsSaveRequestDto;
import com.general.gen.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    /**
     * 데이터베이스에 쿼리를 날리는 부분이 없다.
     * 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
     * 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
