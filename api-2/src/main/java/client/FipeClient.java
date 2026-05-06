package client;

import dto.ModeloResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "fipe-api")
@Path("/{vehicleType}/brands")
public interface FipeClient {

    @GET
    @Path("/{brandId}/models")
    ModeloResponse getModelos(
            @PathParam("vehicleType") String vehicleType,
            @PathParam("brandId") String brandId
    );

}