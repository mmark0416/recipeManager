package com.recipeManager.JSONDB;


import com.recipeManager.utilities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JSONTest {
    JSON underTest;

    @BeforeEach
    void setUp() {
        underTest = new JSON();
    }

    @Test
    void testIsFileExists() {
        assertEquals(true, underTest.isFileExists("admin"));
        assertEquals(false, underTest.isFileExists("admin1"));
    }

    @Test
    void testReadJSON(){
        User user = underTest.readJSON("admin");
        assertEquals(user, underTest.readJSON("admin"));
    }

}
