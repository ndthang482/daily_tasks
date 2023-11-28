package com.game.daily_tasks.repository;

import com.game.daily_tasks.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
