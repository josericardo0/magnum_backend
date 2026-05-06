package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.VeiculoModeloEntity;

@ApplicationScoped
public class VeiculoModeloRepository implements PanacheRepository<VeiculoModeloEntity> {
}