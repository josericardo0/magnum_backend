package auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthResource {

    @GET
    @Path("/token")
    public String gerarToken() {
        return Jwt.issuer("fipe-api")
                .subject("user")
                .groups("user")
                .expiresAt((System.currentTimeMillis() / 1000) + 3600)
                .sign();
    }
}