package com.a2.a2_automation_system.relationship;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends PagingAndSortingRepository<Relationship, Long> {

}
