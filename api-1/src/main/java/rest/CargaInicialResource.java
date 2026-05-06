package rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import service.CargaInicialService;

@Path("/carga-inicial")
public class CargaInicialResource {

    @Inject
    CargaInicialService service;

    @POST
    @Path("/{tipo}")
    @RolesAllowed("user")
    public Response iniciarCarga(@PathParam("tipo") String tipo) {

        var marcas = service.buscarMarcas(tipo);

        marcas.forEach(m ->
                System.out.println(m.code + " - " + m.name)
        );

        return Response.ok(marcas).build();
    }
}