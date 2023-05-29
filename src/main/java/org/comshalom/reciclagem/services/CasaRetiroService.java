package org.comshalom.reciclagem.services;

import jakarta.transaction.Transactional;
import org.comshalom.reciclagem.models.CasaRetiro;
import org.comshalom.reciclagem.repositories.CasaRetiroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CasaRetiroService {

    final CasaRetiroRepository casaRetiroRepository;

    public CasaRetiroService(CasaRetiroRepository casaRetiroRepository) {
        this.casaRetiroRepository = casaRetiroRepository;
    }

    public Page<CasaRetiro> findAll(Pageable pagina) {
        return casaRetiroRepository.findAll(pagina);
    }

    @Transactional
    public CasaRetiro save(CasaRetiro casaRetiro) {
        return casaRetiroRepository.save(casaRetiro);
    }

}
