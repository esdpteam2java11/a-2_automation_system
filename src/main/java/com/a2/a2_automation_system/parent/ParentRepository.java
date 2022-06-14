package com.a2.a2_automation_system.parent;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends PagingAndSortingRepository<Parent, Long> {
}
