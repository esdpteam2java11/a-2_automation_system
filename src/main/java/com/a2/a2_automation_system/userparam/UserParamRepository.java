package com.a2.a2_automation_system.userparam;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserParamRepository extends CrudRepository<UserParam, Long> {
    List<UserParam> findByUserId(Long id);
}
