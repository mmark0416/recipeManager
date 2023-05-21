package com.recipeManager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRecipeModelTest {
    AddRecipeModel underTest;

    @BeforeEach
    void setUp() {
        underTest = new AddRecipeModel();
    }

    @Test
    void testIsRecipeName() {
        assertEquals(true, underTest.isRecipeName("admin", "Paradicsomos pacal"));
        assertEquals(false, underTest.isRecipeName("admin", "Test"));
    }

}
