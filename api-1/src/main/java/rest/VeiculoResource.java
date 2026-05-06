package rest;

import dto.AtualizarVeiculoDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.VeiculoModeloEntity;
import service.MarcaService;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    @Inject
    MarcaService service;

    @PUT
    @Path("/{id}")
    @RolesAllowed("user")
    public VeiculoModeloEntity atualizar(
            @PathParam("id") Long id,
            AtualizarVeiculoDTO dto
    ) {
        return service.atualizar(id, dto);
    }
}