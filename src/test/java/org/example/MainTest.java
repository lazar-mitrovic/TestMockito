package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mockitoTest() {
        Main mainMock = Mockito.mock(Main.class);
        Mockito.when(mainMock.returnHelloOnly()).thenReturn("BYE");
        assertEquals("BYE", mainMock.returnHelloOnly());
    }
}