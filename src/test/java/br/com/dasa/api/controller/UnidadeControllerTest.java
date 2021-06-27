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

import java.util.ArrayList;
import java.util.List;

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



	//Cadastrar Lote

	@Test
	public void testCadastrarLoteUnidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/unidade/lote/")
				.content(this.testCadastrarLoteUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarLoteUnidadeJson() throws JsonProcessingException {
		Unidade ex1 =  Unidade.builder().nome_unidade("Biópsia").build();
		Unidade ex2 =  Unidade.builder().nome_unidade("Vacinas").build();
		Unidade ex3 =  Unidade.builder().nome_unidade("Raio X").build();
		Unidade ex4 =  Unidade.builder().nome_unidade("Mamografia").build();
		Unidade ex5 =  Unidade.builder().nome_unidade("Tomografia").build();

		List<Unidade> listUnidades = new ArrayList<Unidade>();
		listUnidades.add(ex1);
		listUnidades.add(ex2);
		listUnidades.add(ex3);
		listUnidades.add(ex4);
		listUnidades.add(ex5);

		return new ObjectMapper().writeValueAsString(listUnidades);
	}




	//Alterar Lote

	@Test
	public void testAlterarLoteUnidade() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/unidade/lote/")
				.content(this.testCadastrarLoteUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/unidade/lote/")
				.content(this.testAlterarLoteUnidadeJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarLoteUnidadeJson() throws JsonProcessingException {
		Unidade ex1 =  Unidade.builder().cod_unidade(1L).nome_unidade("Biópsia - 1").build();
		Unidade ex2 =  Unidade.builder().cod_unidade(1L).nome_unidade("Vacinas - 1 ").build();
		Unidade ex3 =  Unidade.builder().cod_unidade(1L).nome_unidade("Raio X - 1").build();
		Unidade ex4 =  Unidade.builder().cod_unidade(1L).nome_unidade("Mamografia - 1").build();
		Unidade ex5 =  Unidade.builder().cod_unidade(1L).nome_unidade("Tomografia -1").build();

		List<Unidade> listUnidade = new ArrayList<Unidade>();
		listUnidade.add(ex1);
		listUnidade.add(ex2);
		listUnidade.add(ex3);
		listUnidade.add(ex4);
		listUnidade.add(ex5);

		return new ObjectMapper().writeValueAsString(listUnidade);
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


	//Lista Laboratórios pelo nome do Exame

	@Test
	public void testListaLaboratoriosPeloNomeDaUnidade() throws Exception {

		unidadeRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/util/populate/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/util/desassociar/")
				.content(getAssociarJson2())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/util/desassociar/")
				.content(getAssociarJson3())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/dasa/unidade/nome/Unidade")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'cod_laboratorio':1,'nome_laboratorio':'Delboni - ALPHAVILLE'},{'cod_laboratorio':2,'nome_laboratorio':'Delboni - ALTO DE PINHEIROS'},{'cod_laboratorio':3,'nome_laboratorio':'Delboni - ALTO DE SANTANA'},{'cod_laboratorio':4,'nome_laboratorio':'Delboni - ATENDIMENTO MÓVEL'}]"));


	}

}