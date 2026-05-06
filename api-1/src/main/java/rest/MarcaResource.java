package rest;

import dto.MarcaDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import service.CargaInicialService;

import java.util.List;

@Path("/marcas")
public class MarcaResource {

    @Inject
    CargaInicialService service;

    @GET
    @Path("/{tipo}")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MarcaDTO> buscar(@PathParam("tipo") String tipo) {
        return service.buscarMarcas(tipo);
    }
}