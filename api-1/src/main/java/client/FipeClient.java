package client;

import dto.MarcaDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/{vehicleType}/brands")
@RegisterRestClient(configKey = "fipe-api")
public interface FipeClient {

    @GET
    List<MarcaDTO> getMarcas(@PathParam("vehicleType") String vehicleType);
}