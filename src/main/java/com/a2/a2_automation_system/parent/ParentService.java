package com.a2.a2_automation_system.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;

    public List<ParentDTO> getParentsBySurname(String surnamePart) {
        List<Parent> parents = parentRepository.findAllBySurnameContainingIgnoreCase(surnamePart);
        return parents.stream().map(ParentDTO::from).collect(Collectors.toList());
    }
}
