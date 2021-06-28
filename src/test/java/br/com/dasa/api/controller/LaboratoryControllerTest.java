package br.com.dasa.api.controller;

import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.repository.LaboratoryRepository;
import br.com.dasa.api.services.LaboratoryService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LaboratoryControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private LaboratoryService laboratoryService;

	@Autowired
	private LaboratoryRepository laboratoryRepository;


	//Cadastrar

	@Test
	public void testCadastrarLaboratory() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/")
				.content(this.testCadastrarLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarLaboratoryJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Laboratory.builder()
						.nome_laboratorio("Delboni - ALPHAVILLE")
						.build());


	}


	//Alterar

	@Test
	public void testAlterarLaboratory() throws Exception {

		laboratoryRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/")
				.content(this.testCadastrarLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/laboratory/")
				.content(this.testAlterarLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarLaboratoryJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Laboratory.builder()
						.cod_laboratorio(1L)
						.nome_laboratorio("Delboni - ALPHAVILLE - 1")
						.build());
	}

	//Listar Laboratórios

	@Test
	public void testListarLaboratorys() throws Exception {

		laboratoryRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/")
				.content(this.testCadastrarLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/dasa/laboratory/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'cod_laboratorio':1,'nome_laboratorio':'Delboni - ALPHAVILLE'}]"));
	}

}