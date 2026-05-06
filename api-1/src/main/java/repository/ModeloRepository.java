package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.VeiculoModeloEntity;
import java.util.List;

@ApplicationScoped
public class ModeloRepository implements PanacheRepository<VeiculoModeloEntity>{

    public List<VeiculoModeloEntity> listarMarcas() {
        return listAll();
    }

    public List<VeiculoModeloEntity> buscarPorMarca(String marcaCodigo) {
        return list("marcaCodigo", marcaCodigo);
    }
}
