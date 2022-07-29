package com.a2.a2_automation_system.news;

import com.a2.a2_automation_system.util.PageUtil;
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
        if (allNews.hasNext()) {
            model.addAttribute("nextPageLink", PageUtil.constructPageUri(uriBuilder.getRequestURL().toString(),
                    allNews.nextPageable().getPageNumber(),
                    allNews.nextPageable().getPageSize()));
        }
        if (allNews.hasPrevious()) {
            model.addAttribute("prevPageLink", PageUtil.constructPageUri(uriBuilder.getRequestURL().toString(),
                    allNews.previousPageable().getPageNumber(),
                    allNews.previousPageable().getPageSize()));
        }
        model.addAttribute("url", uriBuilder.getRequestURL() + "?");
        model.addAttribute("page", allNews);
        model.addAttribute("hasNext", allNews.hasNext());
        model.addAttribute("hasPrev", allNews.hasPrevious());
        model.addAttribute("defaultPageSize", allNews.getTotalElements());
        return "all_news";
    }
}
