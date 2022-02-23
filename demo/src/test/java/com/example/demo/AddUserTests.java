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

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddUserTests {

    private final String usersTextFilePath = "C:\\DEV\\demo\\users.txt";
    private final int currentYear = 2022;

    private MockMvc mockMvc;

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
    public void set_user_id() throws Exception {

        int expectedUserId = 100;

        int actualUserId = nameController.addUserToTextFile(new User("Ali"), "Ali", 100, "01/30/1997",
                "Developer", "15 Queen St E,Brampton,ON", true, false, "null").getUserId();

        assertEquals(expectedUserId,actualUserId);
    }


    @Test
    public void set_user_with_different_id() throws Exception {

        int expectedUserId = 150;

        int actualUserId =  nameController.addUserToTextFile(new User("Bob"), "Bob", 150, "01/25/1997",
                "Accountant", "15 Bridgeway,Windsor,ON", true, false, "null").getUserId();

        assertEquals(expectedUserId,actualUserId);
    }

    @Test
    public void set_user_age() throws Exception {

        int expectedUserAge = 6;


        int actualUserAge = currentYear - Integer.parseInt(nameController.addUserToTextFile(new User("Colby"), "Colby", 100,
                "02/18/2016", "Customer Service", "25 King St W,Toronto,ON",
                true,  true, "null"  ).getUserDateOfBirth().split("/")[2]) ;


        assertEquals(expectedUserAge,actualUserAge);

    }

    @Test
    public void set_user_with_different_age() throws Exception {

        int expectedUserAge = 20;

        int actualUserAge = currentYear - Integer.parseInt(nameController.addUserToTextFile(new User("Daryl"), "Daryl", 100,
                "02/18/2002", "Shelfer", "25 King St W,Toronto,ON",
                true,  true, "null"  ).getUserDateOfBirth().split("/")[2]) ;

        assertEquals(expectedUserAge,actualUserAge);


    }



    @Test
    public void set_user_job() throws Exception {

        String expectedUserJob = "Carpenter";

        String actualUserJob =  nameController.addUserToTextFile(new User("Earl"), "Earl", 50, "09/25/1997",
                "Carpenter", "90 Nanwood Cres,Windsor,ON", true, false, "null").getUserJob();


        assertEquals(expectedUserJob,actualUserJob);
    }


    @Test
    public void check_users_txt_file_exists() throws Exception {

        boolean expected = true;

        boolean actual = new File(usersTextFilePath).isFile();

        assertEquals(expected,actual);
    }


}
