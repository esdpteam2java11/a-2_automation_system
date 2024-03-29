package com.a2.a2_automation_system.news;

import com.a2.a2_automation_system.exception.NewsNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsDTO addNewNews(MultipartFile file, NewsDTO newsDTO) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String path = "/opt/upload";
        log.info("Saving news {}", newsDTO);
        News news = News.builder()
                .title(newsDTO.getTitle())
                .description(newsDTO.getDescription())
                .image(fileName)
                .date(LocalDateTime.now())
                .build();
        newsRepository.save(news);
        try {
            FileUploadUtil.saveFile(fileName, path, file);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Did not find the file");
        }
        return NewsDTO.from(news);
    }

    public Page<NewsDTO> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable).map(NewsDTO::from);
    }

    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(NewsDTO::from)
                .sorted(Comparator.comparing(NewsDTO::getDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public NewsDTO getOneNews(Long id) {
        return NewsDTO.from(newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Такую новость не нашел")));
    }

    public void editNews(Long id, NewsDTO newsDTO, MultipartFile file) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Такую новость не нашел"));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String path = "/opt/upload";
        news.setDate(LocalDateTime.now());
        news.setTitle(newsDTO.getTitle());
        news.setDescription(newsDTO.getDescription());
        news.setImage(fileName);
        newsRepository.save(news);
        try {
            FileUploadUtil.saveFile(fileName, path, file);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Did not find the file");
        }
    }

    public void delete(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Такую новость не нашел"));
        newsRepository.delete(news);
    }
}
