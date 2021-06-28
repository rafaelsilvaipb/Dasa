package br.com.dasa.api.controller;

import br.com.dasa.api.dtos.AssociarDTO;
import br.com.dasa.api.repository.ExamsRepository;
import br.com.dasa.api.services.ExamesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UtilControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ExamesService ExamesService;

	@Autowired
	private ExamsRepository examsRepository;

	//Popular o banco

	@Test
	public void testPopulate() throws Exception {
		examsRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/util/populate/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	//Associar

	@Test
	public void testAssociarExams() throws Exception {

		examsRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/util/populate/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/util/associar/")
				.content(getAssociarJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String getAssociarJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(1L).idExame(1L).build());
	}

	//Desassociar

	@Test
	public void testDesassociarExams() throws Exception {

		examsRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/util/populate/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/util/associar/")
				.content(getAssociarJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/util/desassociar/")
				.content(getAssociarJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}