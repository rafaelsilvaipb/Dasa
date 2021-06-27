package br.com.dasa.api.controller;

import br.com.dasa.api.dtos.AssociarDTO;
import br.com.dasa.api.entities.Exames;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ExamsControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ExamesService ExamesService;

	@Autowired
	private ExamsRepository examsRepository;


	//Cadastrar

	@Test
	public void testCadastrarExams() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/exams/")
				.content(this.testCadastrarExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarExamsJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Exames.builder()
						.nome_exame("Vacinas")
						.build());
	}
	

	//Alterar

	@Test
	public void testAlterarExams() throws Exception {

		examsRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/exams/")
				.content(this.testCadastrarExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/exams/")
				.content(this.testAlterarExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarExamsJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(
				Exames.builder()
						.cod_exame(1L)
						.nome_exame("Vacinas - 1")
						.build());
	}


	//Cadastrar Lote

	@Test
	public void testCadastrarLoteExams() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/exams/lote/")
				.content(this.testCadastrarLoteExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarLoteExamsJson() throws JsonProcessingException {
		Exames ex1 =  Exames.builder().nome_exame("Biópsia").build();
		Exames ex2 =  Exames.builder().nome_exame("Vacinas").build();
		Exames ex3 =  Exames.builder().nome_exame("Raio X").build();
		Exames ex4 =  Exames.builder().nome_exame("Mamografia").build();
		Exames ex5 =  Exames.builder().nome_exame("Tomografia").build();

		List<Exames> listExams = new ArrayList<Exames>();
		listExams.add(ex1);
		listExams.add(ex2);
		listExams.add(ex3);
		listExams.add(ex4);
		listExams.add(ex5);

		return new ObjectMapper().writeValueAsString(listExams);
	}




	//Alterar Lote

	@Test
	public void testAlterarLoteExams() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/exams/lote/")
				.content(this.testCadastrarLoteExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/exams/lote/")
				.content(this.testAlterarLoteExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarLoteExamsJson() throws JsonProcessingException {
		Exames ex1 =  Exames.builder().cod_exame(1L).nome_exame("Biópsia - 1").build();
		Exames ex2 =  Exames.builder().cod_exame(1L).nome_exame("Vacinas - 1 ").build();
		Exames ex3 =  Exames.builder().cod_exame(1L).nome_exame("Raio X - 1").build();
		Exames ex4 =  Exames.builder().cod_exame(1L).nome_exame("Mamografia - 1").build();
		Exames ex5 =  Exames.builder().cod_exame(1L).nome_exame("Tomografia -1").build();

		List<Exames> listExams = new ArrayList<Exames>();
		listExams.add(ex1);
		listExams.add(ex2);
		listExams.add(ex3);
		listExams.add(ex4);
		listExams.add(ex5);

		return new ObjectMapper().writeValueAsString(listExams);
	}


	//Listar Exames

	@Test
	public void testListarExamss() throws Exception {

		examsRepository.deleteAll();

		mvc.perform(MockMvcRequestBuilders.post("/dasa/exams/")
				.content(this.testCadastrarExamsJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/dasa/exams/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'cod_exame':1,'nome_exame':'Vacinas'}]"));

	}


	private String getAssociarJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(1L).idExame(1L).build());
	}

	private String getAssociarJson2() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(3L).idExame(2L).build());
	}

	private String getAssociarJson3() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(AssociarDTO.builder().idLaboratory(4L).idExame(2L).build());
	}

	//Lista Laboratórios pelo nome do Exame

	@Test
	public void testListaLaboratoriosPeloNomeDoExame() throws Exception {

		examsRepository.deleteAll();

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

		mvc.perform(MockMvcRequestBuilders.get("/dasa/exams/nome/Vacinas")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'cod_laboratorio': 1,'nome_laboratorio': 'Delboni - ALPHAVILLE'},{'cod_laboratorio': 2,'nome_laboratorio': 'Delboni - ALTO DE PINHEIROS'}]"));


	}

}