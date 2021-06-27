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

import java.util.ArrayList;
import java.util.List;

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

	//Deletar

//	@Test
//	public void testDeletarLaboratory() throws Exception {
//
//		laboratoryRepository.deleteAll();
//
//		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/")
//				.content(this.testCadastrarLaboratoryJson())
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//
//
//		mvc.perform(MockMvcRequestBuilders.delete("/dasa/laboratory/1")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//	}


	//Cadastrar Lote

	@Test
	public void testCadastrarLoteLaboratory() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/lote/")
				.content(this.testCadastrarLoteLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testCadastrarLoteLaboratoryJson() throws JsonProcessingException {
		Laboratory lab1 = Laboratory.builder().nome_laboratorio("Delboni - ALPHAVILLE").build();
		Laboratory lab2 = Laboratory.builder().nome_laboratorio("Delboni - ALTO DE PINHEIROS").build();
		Laboratory lab3 = Laboratory.builder().nome_laboratorio("Delboni - ALTO DE SANTANA").build();
		Laboratory lab4 = Laboratory.builder().nome_laboratorio("Delboni - ATENDIMENTO MÓVEL").build();

		List<Laboratory> listLaboratory = new ArrayList<Laboratory>();
		listLaboratory.add(lab1);
		listLaboratory.add(lab2);
		listLaboratory.add(lab3);
		listLaboratory.add(lab4);

		return new ObjectMapper().writeValueAsString(listLaboratory);
	}


	//Alterar Lote

	@Test
	public void testAlterarLoteLaboratory() throws Exception {

		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/lote/")
				.content(this.testCadastrarLoteLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.put("/dasa/laboratory/lote/")
				.content(this.testAlterarLoteLaboratoryJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String testAlterarLoteLaboratoryJson() throws JsonProcessingException {
		Laboratory lab1 = Laboratory.builder().cod_laboratorio(1L).nome_laboratorio("Delboni - ALPHAVILLE - 1").build();
		Laboratory lab2 = Laboratory.builder().cod_laboratorio(2L).nome_laboratorio("Delboni - ALTO DE PINHEIROS - 1").build();
		Laboratory lab3 = Laboratory.builder().cod_laboratorio(3L).nome_laboratorio("Delboni - ALTO DE SANTANA - 1").build();
		Laboratory lab4 = Laboratory.builder().cod_laboratorio(4L).nome_laboratorio("Delboni - ATENDIMENTO MÓVEL - 1").build();

		List<Laboratory> listLaboratory = new ArrayList<Laboratory>();
		listLaboratory.add(lab1);
		listLaboratory.add(lab2);
		listLaboratory.add(lab3);
		listLaboratory.add(lab4);

		return new ObjectMapper().writeValueAsString(listLaboratory);
	}


	//Deletar em Lote

//	@Test
//	public void testDeletarLoteLaboratory() throws Exception {
//
//		mvc.perform(MockMvcRequestBuilders.post("/dasa/laboratory/lote/")
//				.content(this.testCadastrarLoteLaboratoryJson())
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//
//
//		mvc.perform(MockMvcRequestBuilders.delete("/dasa/laboratory/lote/")
//				.content("[1, 2, 3 ]")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//	}


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