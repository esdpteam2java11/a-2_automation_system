package com.a2.a2_automation_system.parent;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepository extends PagingAndSortingRepository<Parent, Long> {

    List<Parent> findAllBySurnameContainingIgnoreCase(String surnamePart);
}
