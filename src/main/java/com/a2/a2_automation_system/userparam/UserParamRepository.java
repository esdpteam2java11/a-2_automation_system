package com.a2.a2_automation_system.userparam;

import com.a2.a2_automation_system.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserParamRepository extends CrudRepository<UserParam, Long> {

    List<UserParam> findByUserId(Long id);

    @Query(value = "select * from user_params up where up.user_id = ?1 and up.creation_date <= ?2 " +
            "order by up.creation_date desc limit 1",
            nativeQuery = true)
    Optional<UserParam> findUpToDateParamsByUser(Long userId, Date date);

    Boolean existsByUserAndHeightAndWeightAndCreationDate(@NotNull User user,
                                                          @NotNull Double height,
                                                          @NotNull Double weight,
                                                          @NotNull Date creationDate);
}
