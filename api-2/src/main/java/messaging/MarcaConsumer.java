package messaging;

import client.FipeClient;
import dto.MarcaDTO;
import dto.ModeloResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.VeiculoModeloEntity;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import repository.VeiculoModeloRepository;

import java.util.logging.Logger;

@ApplicationScoped
public class MarcaConsumer {

    private static final Logger LOG = Logger.getLogger(MarcaConsumer.class.getName());

    @Inject
    @RestClient
    FipeClient fipeClient;

    @Inject
    VeiculoModeloRepository repository;

    @Incoming("marcas-in")
    @Transactional
    public void consumir(MarcaDTO marca) {

        LOG.info("Recebido: " + marca.name + " | " + marca.vehicleType);

        ModeloResponse response =
                fipeClient.getModelos(marca.vehicleType, marca.code);

        response.models.forEach(modelo -> {

            VeiculoModeloEntity entity = new VeiculoModeloEntity();

            entity.marcaCodigo = marca.code;
            entity.marcaNome = marca.name;

            entity.modeloCodigo = modelo.code;
            entity.modeloNome = modelo.name;

            repository.persist(entity);

            LOG.info("Salvo no banco: " +
                    marca.name + " - " + modelo.name);
        });
    }
}
