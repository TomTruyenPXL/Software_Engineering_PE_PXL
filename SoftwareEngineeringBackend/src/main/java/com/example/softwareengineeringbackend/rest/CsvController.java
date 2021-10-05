package com.example.softwareengineeringbackend.rest;

import com.example.softwareengineeringbackend.domain.response.Response;
import com.example.softwareengineeringbackend.service.CsvService;
import com.example.softwareengineeringbackend.utils.CsvHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvController {
    final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFile(@RequestParam("file")MultipartFile file) {
        if(CsvHelper.hasCSVFormat(file)) {
            Response response = csvService.save(file);

            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
        }

        Response response = new Response().setStatus(400).setMessage("Please upload a CSV file");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
