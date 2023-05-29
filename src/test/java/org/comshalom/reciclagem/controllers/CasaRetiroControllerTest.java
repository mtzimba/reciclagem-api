package org.comshalom.reciclagem.controllers;

import org.comshalom.reciclagem.dtos.CasaRetiroDto;
import org.comshalom.reciclagem.models.CasaRetiro;
import org.comshalom.reciclagem.services.CasaRetiroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CasaRetiroControllerTest {

    @Mock
    private CasaRetiroService casaRetiroService;

    private MockMvc mockMvc;

    @Test
    void testFindAll() throws Exception {
        List<CasaRetiro> casaRetiroList = new ArrayList<>();
        CasaRetiro casaRetiro1 = new CasaRetiro();
        casaRetiro1.setNome("Casa Retiro 1");
        casaRetiroList.add(casaRetiro1);
        CasaRetiro casaRetiro2 = new CasaRetiro();
        casaRetiro2.setNome("Casa Retiro 2");
        casaRetiroList.add(casaRetiro2);

        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<CasaRetiro> casaRetiroPage = new PageImpl<>(casaRetiroList, pageable, casaRetiroList.size());

        when(casaRetiroService.findAll(pageable)).thenReturn(casaRetiroPage);

        mockMvc = MockMvcBuilders.standaloneSetup(new CasaRetiroController(casaRetiroService)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/casasretiro"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].nome").value("Casa Retiro 1"))
                .andExpect(jsonPath("$.content[1].nome").value("Casa Retiro 2"));

        verify(casaRetiroService, times(1)).findAll(pageable);
    }

    @Test
    void testSaveCasaRetiro() throws Exception {
        CasaRetiroDto casaRetiroDto = new CasaRetiroDto();
        casaRetiroDto.setNome("Casa Retiro Teste");

        CasaRetiro casaRetiro = new CasaRetiro();
        casaRetiro.setNome("Casa Retiro Teste");

        when(casaRetiroService.save(any(CasaRetiro.class))).thenReturn(casaRetiro);

        mockMvc = MockMvcBuilders.standaloneSetup(new CasaRetiroController(casaRetiroService)).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/casasretiro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Casa Retiro Teste\" }"))
                .andExpect(status().isCreated())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        Assertions.assertEquals("{\"nome\":\"Casa Retiro Teste\"}", responseContent);

        verify(casaRetiroService, times(1)).save(any(CasaRetiro.class));
    }
}

