package com.zeed.one.world.assessment.repository;


import com.zeed.one.world.assessment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findById(String id);

    @Transactional
    @Modifying
    @Query("update User c set c.deleted = true , c.dateDeactivated = :date where c.id = :id and c.deleted = false")
    int updateDeleteStatus(LocalDateTime date, String id);
}
