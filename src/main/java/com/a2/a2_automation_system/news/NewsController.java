package com.a2.a2_automation_system.news;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("{id}")
    public String getNews(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.getOneNews(id));
        return "one_news";
    }

    @GetMapping("/all")
    public String getAllNews(Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)
                                     Pageable pageable, HttpServletRequest uriBuilder) {
        Page<NewsDTO> allNews = newsService.getAllNews(pageable);
        model.addAttribute("news", allNews);
        model.addAttribute("url", uriBuilder.getRequestURL());
        return "all_news";
    }
}
