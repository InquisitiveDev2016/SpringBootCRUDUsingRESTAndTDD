package com.example.demo;

import com.example.demo.controller.NameController;
import com.example.demo.runner.DemoApplication;
import com.example.demo.announcements.AnnounceUserHasReachedEndpoint;
import com.example.demo.user.User;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

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
	public void check_post_announcement_is_correct() throws Exception {

		String expectedAnnouncement = "bobby has reached this endpoint";

		String actualAnnouncement = nameController.post(new User("bobby"), "bobby").getName() +
				announceUserHasReachedEndpoint.announce();

		assertEquals(expectedAnnouncement, actualAnnouncement);
	}

	@Test
	public void check_number_of_invocations_for_announcement_service() throws Exception {

		int expectedNumberOfInvocations = 1;

		int actualNumberOfInvocations = announceUserHasReachedEndpoint.getNumberOfInvocations();

		assertEquals(expectedNumberOfInvocations, actualNumberOfInvocations);

	}

	@Test
	public void check_post_endpoint_correct() throws Exception {

		String json = "{\n" +
				"  \"name\": \"Ali\"\n" +
				"}";

		mockMvc.perform(post("/Ali")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.is("Ali")));
	}

	@Test
	public void check_post_with_different_name() throws Exception {

		String json = "{\n" +
				"  \"name\": \"Sam\"\n" +
				"}";

		mockMvc.perform(post("/Sam")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
  				.andExpect(jsonPath("$.name", Matchers.is("Sam")));
	}

	@Test
	public void check_post_with_no_name() throws Exception {

		String json = "{\n" +
				"}";

		mockMvc.perform(post("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isNotFound());
	}

	@Test
	public void check_post_response_status() throws Exception {

		String json = "{\n" +
				"  \"name\": \"Ali\"\n" +
				"}";

		mockMvc.perform(post("/Ali")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void check_post_endpoint_not_found() throws Exception {

		String json = "{\n" +
				"  \"name\": \"Ali\"\n" +
				"}";

		mockMvc.perform(post("/Ali/Example")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isNotFound());
	}





}
