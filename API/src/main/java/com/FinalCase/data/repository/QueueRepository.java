package com.FinalCase.data.repository;

import com.FinalCase.data.models.QueueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface QueueRepository extends JpaRepository<QueueModel,Long> {
    List<QueueModel> findByIdNumberAndBirthDate(Long id, LocalDate localDate);
}
