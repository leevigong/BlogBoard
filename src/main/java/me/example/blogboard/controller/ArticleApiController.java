package me.example.blogboard.controller;

import lombok.RequiredArgsConstructor;
import me.example.blogboard.domain.Article;
import me.example.blogboard.dto.ArticleRequest;
import me.example.blogboard.dto.ArticleResponse;
import me.example.blogboard.dto.UpdateArticleRequest;
import me.example.blogboard.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class ArticleApiController {
    final ArticleService articleService;

    @PostMapping("/api/articles")
    // @RequestBody 요청 본문 매핑
    public ResponseEntity<Article> addArticle(@RequestBody ArticleRequest request){
        Article savedArticle = articleService.save(request);
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = articleService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = articleService.findNById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updatedArticle = articleService.update(id, request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}