package org.example;

import org.junit.Test;
import repository.StudentFileRepository;

public class InClassTestCase {
    StudentFileRepository repo = new StudentFileRepository("fisiere/Studenti.txt");

    @Test
    public void addStudentWithValidGrupa() {
        assert true;
    }

    @Test
    public void addStudentWithInvalidGrupa() {
        assert true;
    }
}
