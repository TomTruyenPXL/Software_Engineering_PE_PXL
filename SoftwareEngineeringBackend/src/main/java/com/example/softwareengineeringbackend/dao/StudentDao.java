package com.example.softwareengineeringbackend.dao;

import com.example.softwareengineeringbackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, String> {

}
