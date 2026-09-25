package com.delivery.service;



import com.delivery.dto.PratoRequestDTO;
import com.delivery.dto.PratoResponseDTO;
import com.delivery.model.Prato;
import com.delivery.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PratoService {

    private final PratoRepository pratoRepository;

    // Injeção de dependência via construtor
    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    // Não é permitido cadastrar dois pratos com o mesmo nome.
    public PratoResponseDTO criar(PratoRequestDTO dto) {
        if (pratoRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new IllegalArgumentException("Já existe um prato cadastrado com o nome: " + dto.nome());
        }
        Prato prato = toEntity(dto);
        Prato pratoSalvo = pratoRepository.save(prato);
        return toDTO(pratoSalvo);
    }

    public List<PratoResponseDTO> listarTodos() {
        return pratoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PratoResponseDTO buscarPorId(Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com o ID: " + id));
        return toDTO(prato);
    }

    public List<PratoResponseDTO> listarPorCategoria(String categoria) {
        return pratoRepository.findByCategoriaIgnoreCase(categoria)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PratoResponseDTO atualizar(Long id, PratoRequestDTO dto) {
        Prato pratoExistente = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com o ID: " + id));

        // Atualização dos campos
        pratoExistente.setNome(dto.nome());
        pratoExistente.setDescricao(dto.descricao());
        pratoExistente.setValor(dto.valor());
        pratoExistente.setCategoria(dto.categoria());
        pratoExistente.setCalorias(dto.calorias());
        pratoExistente.setQuantidade(dto.quantidade());
        pratoExistente.setUnidadeMedida(dto.unidadeMedida());

        Prato pratoAtualizado = pratoRepository.save(pratoExistente);
        return toDTO(pratoAtualizado);
    }

    public void remover(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new RuntimeException("Prato não encontrado com o ID: " + id);
        }
        pratoRepository.deleteById(id);
    }

    // Métodos privados de conversão
    private Prato toEntity(PratoRequestDTO dto) {
        return new Prato(
                null,
                dto.nome(),
                dto.descricao(),
                dto.valor(),
                dto.categoria(),
                dto.calorias(),
                dto.quantidade(),
                dto.unidadeMedida()
        );
    }

    private PratoResponseDTO toDTO(Prato prato) {
        return new PratoResponseDTO(prato);
    }
}
