package me.example.blogboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 기본 키

    @Column(name = "title" , nullable = false)
    private String title;       // 제목

    @Column(nullable = false)
    private String content;     // 내용

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 떄 수정 시간 저장
    private LocalDateTime updatedAt;


    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title =title;
        this.content = content;
    }
}
