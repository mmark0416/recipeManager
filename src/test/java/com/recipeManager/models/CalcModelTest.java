package com.recipeManager.models;

import com.recipeManager.Exception.DivideByZero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalcModelTest {
    CalcModel underTest;

    @BeforeEach
    void setUp() {
        underTest = new CalcModel();
    }

    @Test
    void testChoiceSymbol() {
        assertEquals("+", underTest.choiceSymbol("Plus"));
        assertEquals("-", underTest.choiceSymbol("Minus"));
        assertEquals("/", underTest.choiceSymbol("Divide"));
        assertEquals("*", underTest.choiceSymbol("Multiply"));
    }

    @Test
    void testCalculate(){
        assertEquals("10", underTest.calculate("+", 5,5));
        assertEquals("22", underTest.calculate("-", 24,2));
        assertEquals("15", underTest.calculate("/", 30,2));
        assertEquals("16", underTest.calculate("*", 4,4));
        assertEquals("", underTest.calculate("/", 5, 0));
    }
}
