package com.a2.a2_automation_system.parent;

import com.a2.a2_automation_system.relationship.Relationship;
import com.a2.a2_automation_system.relationship.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final RelationshipRepository relationshipRepository;

    public List<ParentDTO> getParentsBySurname(String surnamePart) {
        List<Parent> parents = parentRepository.findAllBySurnameContainingIgnoreCase(surnamePart);
        return parents.stream().map(ParentDTO::from).collect(Collectors.toList());
    }

    public List<ParentDTO> getParentsBySportsmanId(Long id) {
        List<Relationship> relationships = relationshipRepository.findRelationshipByStudentId(id);
        List<Parent> parents = new ArrayList<>();
        for (Relationship relationship : relationships) {
            parents.add(parentRepository.findById(relationship.getParent().getId()).get());
        }
        return parents.stream().map(ParentDTO::from).collect(Collectors.toList());
    }
}
