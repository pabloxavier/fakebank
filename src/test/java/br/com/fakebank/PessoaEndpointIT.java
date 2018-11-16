package br.com.fakebank;

import br.com.fakebank.domain.Pessoa;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.util.StringUtils.isEmpty;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStart.class, properties = { "spring.profiles.active=it" })
@Sql
public class PessoaEndpointIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    private static final String PESSOA_ENDPOINT = "/pessoas";
    private static final String GET_BY_ID_ENDPOINT = PESSOA_ENDPOINT + "/{codigo}";
    private static final String PESQUISAR_ENDPOINT = PESSOA_ENDPOINT + "/pesquisa";

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

   // @Test
    //public void deveRetornarPessoaQuandoBuscarPorId() throws Exception {
        //given
       // String pessoaId = "1";

        //when
        //MvcResult result = performGet(GET_BY_ID_ENDPOINT, pessoaId);
        //Pessoa pessoa = mapToPessoa(result.getResponse().getContentAsString());

        //then
        //assertNotNull(pessoa);
   // }

    //TODO: Implementar teste do endpoint /pesquisa
    @Test
    @Ignore
    public void deveRetornarPessoaQuandoPesquisar() throws Exception {
        //given
        String[] filtros = { };

        //when
        MvcResult result = performGet(PESQUISAR_ENDPOINT, filtros);
        List<Pessoa> pessoas = mapToPessoas(result.getResponse().getContentAsString());

        //then
        assertNotNull(pessoas);
    }

    private MvcResult performGet(String endpoint, Object... params) throws Exception {
        return mvc.perform(get(endpoint, params)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    private Pessoa mapToPessoa(String json) throws Exception {
        if (isEmpty(json)) {
            return null;
        }

        return objectMapper.readValue(json, Pessoa.class);
    }

    private List<Pessoa> mapToPessoas(String json) throws Exception {
        if (isEmpty(json)) {
            return null;
        }

        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Pessoa.class));
    }
}
