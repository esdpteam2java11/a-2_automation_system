package com.a2.a2_automation_system.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public String getAddNews() {
        return "add_news";
    }

    @PostMapping
    public String addNewNews(@RequestParam("file") MultipartFile file, NewsDTO newsDTO) {
        newsService.addNewNews(file, newsDTO);
        return "redirect:/";
    }
}
