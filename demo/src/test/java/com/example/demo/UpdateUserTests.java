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
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UpdateUserTests {

    private MockMvc mockMvc;

    private final String usersTextFilePath = "C:\\DEV\\demo\\users.txt";
    private final int currentYear = 2022;

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
    public void update_ali_user_id() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        String expectedUserId = "id: 125";


        nameController.updateUser(new User("Ali"), "Ali", 125, "01/30/1997",
                "Developer", "15 Queen St E,Brampton,ON", true, false, "null");

        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        String actualUserId = "";

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Ali")) {
                actualUserId = testList.get(i+1);
                break;
            }
        }

        assertEquals(expectedUserId, actualUserId);

    }

    @Test
    public void update_ali_user_age() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        int expectedUserAge = 15;

        nameController.updateUser(new User("Ali"), "Ali", 125, "01/30/2007",
                "Developer", "15 Queen St E,Brampton,ON", true, false, "null");


        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        int actualUserAge = 0;

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Ali")) {
                actualUserAge = currentYear - Integer.parseInt(testList.get(i+2).split("/")[2]);
                break;
            }
        }

        assertEquals(expectedUserAge, actualUserAge);
    }

    @Test
    public void update_ali_user_job() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        String expectedUserJob = "job: Tester";

        nameController.updateUser(new User("Ali"), "Ali", 150, "01/30/2007",
                "Tester", "15 Queen St E,Brampton,ON", true, false, "null");



        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        String actualUserJob = "";

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Ali")) {
                actualUserJob = testList.get(i+3);
                break;
            }
        }

        assertEquals(expectedUserJob, actualUserJob);
    }


    @Test
    public void update_daryl_user_id() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        String expectedUserId = "id: 92";

        nameController.updateUser(new User("Daryl"), "Daryl", 92, "01/30/1962",
                "Shelfer", "25 King St W,Toronto,ON", true, false, "null");



        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        String actualUserId = "";

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Daryl")) {
                actualUserId = testList.get(i+1);
                break;
            }
        }

        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void update_daryl_user_age() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        int expectedUserAge = 45;

        nameController.updateUser(new User("Daryl"), "Daryl", 92, "01/30/1977",
                "Shelfer", "25 King St W,Toronto,ON", true, false, "null");


        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        int actualUserAge = 0;

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Daryl")) {
                actualUserAge = currentYear - Integer.parseInt(testList.get(i+2).split("/")[2]);
                break;
            }
        }

        assertEquals(expectedUserAge, actualUserAge);
    }


    @Test
    public void update_daryl_user_job() throws Exception {

        ArrayList<String> testList = new ArrayList<>();

        String expectedUserJob = "job: Stocker";

        nameController.updateUser(new User("Daryl"), "Daryl", 92, "01/30/1977",
                "Stocker", "25 King St W,Toronto,ON", true, false, "null");



        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            testList.add(line);
        }

        String actualUserJob = "";

        for(int i = 0; i < testList.size(); i++) {
            if(testList.get(i).contains("Name: Daryl")) {
                actualUserJob = testList.get(i+3);
                break;
            }
        }

        assertEquals(expectedUserJob, actualUserJob);
    }

}
