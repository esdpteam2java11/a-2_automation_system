package com.a2.a2_automation_system.news;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private MultipartFile file;

    @Mock
    private FileUploadUtil fileUploadUtil;

    private News news;

    @BeforeEach
    void setupBeforeEach() {
        news = News.builder()
                .id(1L)
                .title("title")
                .description("description")
                .image("qwe")
                .date(LocalDateTime.now())
                .build();
        file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
    }

    @Test
    void addNewNews() {
        when(newsRepository.save(ArgumentMatchers.any(News.class))).thenReturn(news);
        NewsDTO created = newsService.addNewNews(file, NewsDTO.from(news));
        Assertions.assertThat(news.getTitle()).isEqualTo(created.getTitle());
    }

    @Test
    void editNews() {
        NewsDTO news2 = NewsDTO.builder()
                .id(2L)
                .title("title2")
                .description("description2")
                .image("qwe2")
                .date(LocalDateTime.now())
                .build();
        given(newsRepository.findById(news.getId())).willReturn(Optional.of(news));
        newsService.editNews(news.getId(), news2, file);
    }

    @Test
    void getAllNews() {
        int pageNumber = 0;
        int pageSize = 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<News> page = new PageImpl<>(Collections.singletonList(news));
        when(newsRepository.findAll(pageable)).thenReturn(page);
        Page<NewsDTO> all = newsService.getAllNews(pageable);
        assertEquals(all.getNumberOfElements(), 1);
    }

    @Test
    void testGetAllNews() {
        List<NewsDTO> allNews = newsService.getAllNews();
        allNews.add(new NewsDTO());
        allNews.add(new NewsDTO());
        assertEquals(2, allNews.size());
        verify(newsRepository).findAll();
    }

    @Test
    void getOneNews() {
        doReturn(Optional.of(news)).when(newsRepository).findById(1L);
        NewsDTO oneNews = newsService.getOneNews(1L);
        Assertions.assertThat(1L).isEqualTo(oneNews.getId());
    }

    @Test
    void delete() {
        News news1 = new News();
        news1.setId(2L);
        news1.setImage("qwe");
        when(newsRepository.findById(news1.getId())).thenReturn(Optional.of(news1));
        newsService.delete(news1.getId());
    }


}