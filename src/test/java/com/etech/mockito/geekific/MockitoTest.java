package com.etech.mockito.geekific;

import com.sun.source.tree.ModuleTree;
import jdk.dynalink.linker.LinkerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {


    @Mock
    List<String> mockList;

    @Spy
    List<String> spyList = new LinkedList<>();

    @Captor
    ArgumentCaptor<String> captor;

    @Test
    void mock_list_returns_correct_element() {
        //MockitoAnnotations.initMocks(this); Deprecated
        when(mockList.get(0)).thenReturn("Like");
        assertEquals("Like", mockList.get(0));
    }

    @Test
    void spy_list_adds_element_to_it_correctly() {
        spyList.add("Subscribe");
        verify(spyList).add("Subscribe");
        assertEquals(1, spyList.size());
    }

    @Test
    void spy_list_adds_the_correct_element_to_it() {
        spyList.add("Geekific");
        verify(spyList).add(captor.capture());
        assertEquals("Geekific", captor.getValue());
    }

}
