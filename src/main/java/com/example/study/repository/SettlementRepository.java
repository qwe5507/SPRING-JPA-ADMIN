package com.example.study.repository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import com.example.study.model.entity.Settlement;
import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {
}
