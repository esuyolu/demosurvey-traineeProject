package com.demo.repositories;

import com.demo.models.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {

}
