package com.example.softwareengineeringbackend.utils;

import com.example.softwareengineeringbackend.domain.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvHelper {
    public static String TYPE = "text/csv";

    public enum CsvHeaders {
        NUMBER(0),
        GENDER(1),
        GIVEN_NAME(2),
        SURNAME(3),
        EMAIL(4),
        GUID(5);

        private final int index;

        CsvHeaders(int index) { this.index = index; }

        public int getIndex() { return index; }
    }

    static String[] HEADERS = {"Number", "Gender", "GivenName", "Surname", "EmailAddress", "GUID"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Student> csvToStudents(InputStream is) {
        List<Student> students = new ArrayList<>();

        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(CSVRecord csvRecord: csvRecords) {
                Student student = new Student(
                        csvRecord.get(CsvHeaders.GUID.getIndex()),
                        Integer.parseInt(csvRecord.get(CsvHeaders.NUMBER.getIndex())),
                        csvRecord.get(CsvHeaders.GENDER.getIndex()),
                        csvRecord.get(CsvHeaders.GIVEN_NAME.getIndex()),
                        csvRecord.get(CsvHeaders.SURNAME.getIndex()),
                        csvRecord.get(CsvHeaders.EMAIL.getIndex())
                );

                students.add(student);
            }

        } catch (IOException e) {
            System.out.println("Failed to parse CSV File");
        }

        return students;
    }
}
