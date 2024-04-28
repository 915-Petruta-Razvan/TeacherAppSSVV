package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class IncrementalTestCase {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void addStudent() {
        Student testStudent = new Student("87498", "Test Student 1", 935, "teststudent1@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("87498") != null);
    }

    @Test
    public void addStudent_addAssignment() {
        Student testStudent = new Student("423243", "Test Student 1", 935, "teststudent1@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("423243") != null);

        Tema temaToAdd = new Tema("54353", "Some description", 8, 6);

        try {
            service.addTema(temaToAdd);
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findTema("54353") != null);
    }

    @Test
    public void addStudent_addAssignment_addGrade()
    {
        Student testStudent = new Student("432235", "Test Student 1", 935, "teststudent1@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("432235") != null);

        Tema temaToAdd = new Tema("64634", "Some description", 4, 1);

        try {
            service.addTema(temaToAdd);
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findTema("64634") != null);

        Nota notaToAdd = new Nota("432325", "432235", "64634", 9, LocalDate.parse("2024-04-15"));

        try {
            service.addNota(notaToAdd, "Spate");
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findNota("432325") != null);
    }
}
