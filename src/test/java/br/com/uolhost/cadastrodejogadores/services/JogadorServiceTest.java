package br.com.uolhost.cadastrodejogadores.services;

import br.com.uolhost.cadastrodejogadores.dto.JsonDTO;
import br.com.uolhost.cadastrodejogadores.dto.XmlDTO;
import br.com.uolhost.cadastrodejogadores.exception.ResourceHttpIsNotAvailableException;
import br.com.uolhost.cadastrodejogadores.repository.GroupRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class JogadorServiceTest {
    @InjectMocks
    private JogadorService service;
    @Mock
    private HttpResourceService httpService;
    @Mock
    private GroupRepository groupRepository;

    @Spy
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_call_get_codinomes_should_ok() throws ResourceHttpIsNotAvailableException, JsonProcessingException {
        when(httpService.requestLiga()).thenReturn(this.getXmlDTO());
        when(httpService.requestVingadores()).thenReturn(this.getJsonDTO());
        final ResponseEntity codiNomesAvailable = this.service.getCodiNomesAvailable();
        assertEquals(HttpStatus.OK, codiNomesAvailable.getStatusCode() );
    }

    public XmlDTO getXmlDTO(){
        final XmlDTO xmlDTO = new XmlDTO();
        xmlDTO.setCodNames(Arrays.asList(
            "Lanterna Verde",
            "Flash",
            "Aquaman",
            "Batman",
            "Superman",
            "Mulher Maravilha"
        ));
        return xmlDTO;
    }

    public JsonDTO getJsonDTO(){
        final JsonDTO jsonDTO = new JsonDTO();
        jsonDTO.setVingadores(Arrays.asList(
                Map.of("codinome", "Hulk"),
                Map.of("codinome", "Capitão América"),
                Map.of("codinome", "Visão"),
                Map.of("codinome", "Feiticeira Escarlate"),
                Map.of("codinome", "Thor"),
                Map.of("codinome", "Homem de Ferro"),
                Map.of("codinome", "Pantera Negra")
        ));
        return jsonDTO;
    }
}
