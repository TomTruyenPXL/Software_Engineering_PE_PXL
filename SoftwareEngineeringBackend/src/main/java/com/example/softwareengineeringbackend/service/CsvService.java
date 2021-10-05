package com.example.softwareengineeringbackend.service;

import com.example.softwareengineeringbackend.dao.StudentDao;
import com.example.softwareengineeringbackend.domain.Student;
import com.example.softwareengineeringbackend.domain.response.Response;
import com.example.softwareengineeringbackend.utils.CsvHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CsvService {
    final StudentDao studentDao;

    public CsvService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Response save(MultipartFile file) {
        try {
            List<Student> students = CsvHelper.csvToStudents(file.getInputStream());
            studentDao.saveAll(students);
        } catch (IOException e) {
            return new Response().setStatus(400).setMessage("Failed to store CSV");
        }

        return new Response().setStatus(200).setSuccess(true);
    }
}
