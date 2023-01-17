package com.general.gen.service.posts;

import com.general.gen.domain.posts.Posts;
import com.general.gen.domain.posts.PostsRepository;
import com.general.gen.web.Dto.PostsListResponseDto;
import com.general.gen.web.Dto.PostsResponseDto;
import com.general.gen.web.Dto.PostsSaveRequestDto;
import com.general.gen.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * readOnly = true 옵션을 주면,
     * 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에,
     * 등록, 수정, 삭제 기능이 전혀 없는 서비스 메서드에서 사용하는 것을 추천한다.
     * <p>
     * .map(PostsListResponseDto::new)
     * =
     * .map(posts -> new PostsListResponseDto(posts))
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
