package me.example.blogboard.service;

import lombok.RequiredArgsConstructor;
import me.example.blogboard.domain.Article;
import me.example.blogboard.dto.ArticleRequest;
import me.example.blogboard.dto.UpdateArticleRequest;
import me.example.blogboard.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 블로그 글 추가 메서드
    public Article save(ArticleRequest request){
        return articleRepository.save(request.toEntity());
    }

    // 블로그 글 목록 조회 메서드
    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    // 블로그 글 하나 조회 메서드
    public Article findNById(Long id){
        return articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not foung: " + id));
    }

    // 블로그 글 삭제 메서드
    public  void delete(Long id){
        articleRepository.deleteById(id);
    }

    // 블로그 글 수정 메서드
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not foung: " + id));
        article.update(request.getTitle(), request.getContent());
        return article;

    }
}
