package rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.VeiculoModeloEntity;
import service.MarcaService;

import java.util.List;

@Path("/marcas-db")
@Produces(MediaType.APPLICATION_JSON)
public class MarcaDbResource {

    @Inject
    MarcaService service;

    @GET
    @RolesAllowed("user")
    public List<VeiculoModeloEntity> buscar() {
        return service.buscarMarcas();
    }

    @GET
    @RolesAllowed("user")
    @Path("/{marcaCodigo}/modelos")
    public List<VeiculoModeloEntity> buscarPorMarca(
            @PathParam("marcaCodigo") String marcaCodigo
    ) {
        return service.buscarPorMarca(marcaCodigo);
    }
}