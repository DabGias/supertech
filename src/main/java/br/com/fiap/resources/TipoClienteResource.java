package br.com.fiap.resources;

import br.com.fiap.domain.cliente.repository.TipoClienteRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tipo-cliente")
public class TipoClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(TipoClienteRepository.findAll()).build(); }
}
