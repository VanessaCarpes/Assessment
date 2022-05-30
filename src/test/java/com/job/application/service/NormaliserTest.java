package com.job.application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NormaliserTest {
    private final Normaliser normaliser = new Normaliser();

    @Test
    void normaliseTestingJavaEngineer() {
        String result = normaliser.normalise("Java engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    void normaliseTestingCEngineer() {
        String result = normaliser.normalise("C# engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    void normaliseTestingAccountant() {
        String result = normaliser.normalise("Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    void normaliseTestingChiefAccountant() {
        String result = normaliser.normalise("Chief Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    void normaliseTestingAccountantSoftwareEngineer() {
        String result = normaliser.normalise("Accountant Software Engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    void normaliseTestingNoneMatches() {
        String result = normaliser.normalise("Cooker");
        assertNull(result);
    }
}
