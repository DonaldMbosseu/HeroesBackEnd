package com.example.TestAub;

import com.example.TestAub.controller.HeroController;
import com.example.TestAub.model.dto.HeroDTO;
import com.example.TestAub.service.HeroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class TestAubApplicationTests {

	/*@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired*/

	@Mock
	private HeroService heroService;
	@InjectMocks
	private HeroController heroController;

	private MockMvc mockMvc;

	@Test
	public void getsAllUsersSuccess() throws Exception {
		HeroDTO heroDTO = new HeroDTO("hero1");
		HeroDTO heroDTO1 = new HeroDTO("hero2");
		HeroDTO heroDTO2 = new HeroDTO("hero3");

		List<HeroDTO> allHeroes = new ArrayList<>();
		allHeroes.add(heroDTO);
		allHeroes.add(heroDTO1);
		allHeroes.add(heroDTO2);

		// Set up mock service response
		ResponseEntity<List<HeroDTO>> responseEntity = ResponseEntity.ok(allHeroes);
		when(heroService.getAllHeroes()).thenReturn(responseEntity);

		mockMvc = MockMvcBuilders.standaloneSetup(heroController).build();

		mockMvc.perform(get("/heroes/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].name", is("hero3")));
	}



}
