package org.comshalom.reciclagem.repositories;

import org.comshalom.reciclagem.models.CasaRetiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRetiroRepository extends JpaRepository<CasaRetiro, Long> {
}
