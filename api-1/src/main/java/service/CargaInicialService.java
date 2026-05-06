package service;

import client.FipeClient;
import dto.MarcaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.logging.Logger;
import jakarta.annotation.security.RolesAllowed;

@ApplicationScoped
public class CargaInicialService {

    private static final Logger LOG = Logger.getLogger(CargaInicialService.class.getName());

    @Inject
    @RestClient
    FipeClient fipeClient;

    @Inject
    @Channel("marcas-out")
    Emitter<MarcaDTO> emitter;

    @RolesAllowed("user")
    public List<MarcaDTO> buscarMarcas(String tipo) {
        LOG.info("Buscando marcas para tipo: " + tipo);

        try {
            List<MarcaDTO> marcas = fipeClient.getMarcas(tipo);

            if (marcas == null || marcas.isEmpty()) {
                throw new RuntimeException("Nenhuma marca retornada pela FIPE");
            }

            marcas.forEach(m -> {
                emitter.send(m);
                LOG.info("Enviado para fila: " + m.name);
            });

            return marcas;

        } catch (Exception e) {
            LOG.severe("Erro ao chamar API FIPE: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar marcas da FIPE", e);
        }
    }
}