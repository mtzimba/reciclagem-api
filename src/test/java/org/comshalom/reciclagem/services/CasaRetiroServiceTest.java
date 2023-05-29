package org.comshalom.reciclagem.services;

import org.comshalom.reciclagem.models.CasaRetiro;
import org.comshalom.reciclagem.repositories.CasaRetiroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CasaRetiroServiceTest {

    @Mock
    private CasaRetiroRepository casaRetiroRepository;

    @InjectMocks
    private CasaRetiroService casaRetiroService;

    @Test
    void testFindAll() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<CasaRetiro> expectedPage = mock(Page.class);

        when(casaRetiroRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<CasaRetiro> resultPage = casaRetiroService.findAll(pageable);

        assertEquals(expectedPage, resultPage);
        verify(casaRetiroRepository, times(1)).findAll(pageable);
    }

    @Test
    void testSave() {
        CasaRetiro casaRetiro = new CasaRetiro();
        CasaRetiro savedCasaRetiro = new CasaRetiro();

        when(casaRetiroRepository.save(casaRetiro)).thenReturn(savedCasaRetiro);

        CasaRetiro resultCasaRetiro = casaRetiroService.save(casaRetiro);

        assertEquals(savedCasaRetiro, resultCasaRetiro);
        verify(casaRetiroRepository, times(1)).save(casaRetiro);
    }
}
