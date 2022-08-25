package com.a2.a2_automation_system.relationship;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends PagingAndSortingRepository<Relationship, Long> {
    List<Relationship> findRelationshipByStudentId(Long id);

    Boolean existsByParentIdAndStudentId(Long parentId, Long studentId);

    void deleteAllByStudentId(Long id);
}
