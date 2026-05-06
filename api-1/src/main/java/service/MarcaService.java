package service;

import cache.VeiculoCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AtualizarVeiculoDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.VeiculoModeloEntity;
import repository.ModeloRepository;

import java.util.List;

@ApplicationScoped
public class MarcaService {

    @Inject
    ModeloRepository repository;

    @Inject
    VeiculoCache cache;

    @Inject
    ObjectMapper mapper;

    @RolesAllowed("user")
    public List<VeiculoModeloEntity> buscarMarcas() {
        return repository.listAll();
    }

    @RolesAllowed("user")
    public List<VeiculoModeloEntity> buscarPorMarca(String marca) {

        String cacheKey = "marca:" + marca;

        try {
            String cached = cache.buscar(cacheKey);

            if (cached != null) {
                return mapper.readValue(
                        cached,
                        mapper.getTypeFactory()
                                .constructCollectionType(List.class, VeiculoModeloEntity.class)
                );
            }

            List<VeiculoModeloEntity> result =
                    repository.buscarPorMarca(marca);

            cache.salvar(
                    cacheKey,
                    mapper.writeValueAsString(result)
            );

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed("user")
    @Transactional
    public VeiculoModeloEntity atualizar(Long id, AtualizarVeiculoDTO dto) {

        VeiculoModeloEntity entity = repository.findById(id);

        if (entity == null) {
            throw new RuntimeException("Veículo não encontrado");
        }

        if (dto.modeloNome != null) {
            entity.modeloNome = dto.modeloNome;
        }

        if (dto.observacao != null) {
            entity.observacao = dto.observacao;
        }

        repository.persist(entity);

        return entity;
    }
}