package com.rest.dao;

import com.rest.model.Billionaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillionaireJPARepository extends JpaRepository<Billionaire, Long> {
}
