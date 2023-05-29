package org.comshalom.reciclagem.controllers;

import jakarta.validation.Valid;
import org.comshalom.reciclagem.dtos.CasaRetiroDto;
import org.comshalom.reciclagem.dtos.EnderecoDto;
import org.comshalom.reciclagem.models.CasaRetiro;
import org.comshalom.reciclagem.models.Endereco;
import org.comshalom.reciclagem.services.CasaRetiroService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/casasretiro")
public class CasaRetiroController {

    final CasaRetiroService casaRetiroService;

    public CasaRetiroController(CasaRetiroService casaRetiroService) {
        this.casaRetiroService = casaRetiroService;
    }

    @GetMapping
    public ResponseEntity<Page<CasaRetiroDto>> findAll(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){

        Page<CasaRetiro> casaRetiroPage = casaRetiroService.findAll(pageable);

        Page<CasaRetiroDto> casaRetiroDtoPage = casaRetiroPage.map(this::conveterPraDto);

        return ResponseEntity.status(HttpStatus.OK).body(casaRetiroDtoPage);
    }

    @PostMapping
    public ResponseEntity<Object> saveCasaRetiro(@RequestBody @Valid CasaRetiroDto casaRetiroDto){
        CasaRetiro casaRetiro = converterParaModel(casaRetiroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(casaRetiroService.save(casaRetiro));
    }

    private CasaRetiroDto conveterPraDto(CasaRetiro casaRetiro) {
        var casaRetiroDto = new CasaRetiroDto();
        BeanUtils.copyProperties(casaRetiro, casaRetiroDto);
        var enderecoDto = new EnderecoDto();
        BeanUtils.copyProperties(casaRetiro.getEndereco(), enderecoDto);
        casaRetiroDto.setEndereco(enderecoDto);
        return casaRetiroDto;
    }

    private CasaRetiro converterParaModel(CasaRetiroDto casaRetiroDto) {
        var casaRetiro = new CasaRetiro();
        BeanUtils.copyProperties(casaRetiroDto, casaRetiro);
        var endereco = new Endereco();
        BeanUtils.copyProperties(casaRetiroDto.getEndereco(), endereco);
        casaRetiro.setEndereco(endereco);
        return casaRetiro;
    }
}
