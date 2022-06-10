package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.commons.Role;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageUtil {

    public static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri, String role,
                                              Boolean isActive) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, role, isActive, list.nextPageable().getPageNumber(),
                    list.nextPageable().getPageSize()));
        }
        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, role, isActive,
                    list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        List<String> roles = Stream.of(Role.values()).map(Role::getRole).collect(Collectors.toList());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("users", list.getContent());
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);
        model.addAttribute("isActive", isActive);
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, String role,
                                           Boolean isActive, int page, int size) {
        if (role != null && isActive != null) return String.format("%s?role=%s&isActive=%s&page=%s&size=%s", uri, role,
                isActive, page, size);
        else if (isActive != null) return String.format("%s?isActive=%s&page=%s&size=%s", uri,
                isActive, page, size);
        else if (role != null) return String.format("%s?role=%s&page=%s&size=%s", uri,
                role, page, size);
        else return String.format("%s?page=%s&size=%s", uri, page, size);
    }
}