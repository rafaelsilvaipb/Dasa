package br.com.dasa.api.controller;

import br.com.dasa.api.dtos.AssociarDTO;
import br.com.dasa.api.entities.Unidade;
import br.com.dasa.api.repository.UnidadeRepository;
import br.com.dasa.api.services.UnidadeService;
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
public class UnidadeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private UnidadeRepository unidadeRepository;


	//Cadastrar

	@Test
	public void testCadastrarUnidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/unidade/")
				.content(this.testCadastrarUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarUnidadeJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Unidade.builder()
						.nome_unidade("Vacinas")
						.build());
	}
	

	//Alterar

	@Test
	public void testAlterarExams() throws Exception {

		unidadeRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/unidade/")
				.content(this.testCadastrarUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/unidade/")
				.content(this.testAlterarUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarUnidadeJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Unidade.builder()
						.cod_unidade(1L)
						.nome_unidade("Vacinas - 1")
						.build());
	}

	//Listar Exames

	@Test
	public void testListarUnidades() throws Exception {

		unidadeRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/unidade/")
				.content(this.testCadastrarUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/dasa/unidade/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'cod_unidade':1,'nome_unidade':'Vacinas'}]"));

	}


	private String getAssociarJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(1L).idExame(1L).idUnidade(1L).build());
	}

	private String getAssociarJson2() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(3L).idExame(2L).idUnidade(2L).build());
	}

	private String getAssociarJson3() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(4L).idExame(2L).idUnidade(1L).build());
	}

}