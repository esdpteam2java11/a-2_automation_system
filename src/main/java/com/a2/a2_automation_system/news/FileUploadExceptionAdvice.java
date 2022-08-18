package com.a2.a2_automation_system.news;

import com.a2.a2_automation_system.exception.NewsNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(Model model) {
        model.addAttribute("error", "Файл должен весить не более 5 мб");
        return "add_news";
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public String handleNewsNotFoundException() {
        return "No_Found";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException() {
        return "No_Found";
    }
}
