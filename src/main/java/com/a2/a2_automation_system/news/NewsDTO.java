package com.a2.a2_automation_system.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    public static NewsDTO from(News news) {
        return NewsDTO.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .image(news.getImage())
                .date(news.getDate())
                .build();
    }
}
