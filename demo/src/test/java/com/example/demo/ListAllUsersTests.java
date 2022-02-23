package com.example.demo;

import com.example.demo.controller.NameController;
import com.example.demo.runner.DemoApplication;
import com.example.demo.announcements.AnnounceUserHasReachedEndpoint;
import com.example.demo.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ListAllUsersTests {

    private MockMvc mockMvc;

    private final String usersTextFilePath = "C:\\DEV\\demo\\users.txt";

    @Mock
    private AnnounceUserHasReachedEndpoint announceUserHasReachedEndpoint;

    @InjectMocks
    private NameController nameController;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(nameController)
                .build();

        announceUserHasReachedEndpoint = new AnnounceUserHasReachedEndpoint();

        User user = new User();

    }

    @Test
    public void listAllUsers() throws Exception {

        ArrayList<String> expectedOutput = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            expectedOutput.add(line);
        }

        String actualOutput = nameController.listAllUsers();

        assertEquals(Arrays.toString(expectedOutput.toArray()), actualOutput);

    }

}
