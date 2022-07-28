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
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;

    public void addNewNews(MultipartFile file, NewsDTO newsDTO) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String path = "upload";
        log.info("Saving news {}", newsDTO);
        newsRepository.save(News.builder()
                .title(newsDTO.getTitle())
                .description(newsDTO.getDescription())
                .image(fileName)
                .date(LocalDate.now())
                .build());
        try {
            FileUploadUtil.saveFile(fileName, path, file);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Did not find the file");
        }
    }

    public Page<NewsDTO> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable).map(NewsDTO::from);
    }

    public NewsDTO getOneNews(Long id) {
        return NewsDTO.from(newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Такую новость не нашел")));
    }
}
