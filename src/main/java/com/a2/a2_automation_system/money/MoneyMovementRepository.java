package com.a2.a2_automation_system.money;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MoneyMovementRepository extends JpaRepository<MoneyMovement,Long> {

}
