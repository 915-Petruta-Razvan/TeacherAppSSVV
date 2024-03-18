package org.example;

import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class InClassTestCase {
    public static Service service;

    @BeforeAll
    public static void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        InClassTestCase.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudentWithValidGrupa() {
        Student testStudent = new Student("7890", "Test Student", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("7890") != null);
    }

    @Test
    public void addStudentWithInvalidGrupa() {
        Student testStudent = new Student("9876", "Test Student", -10, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }
}
