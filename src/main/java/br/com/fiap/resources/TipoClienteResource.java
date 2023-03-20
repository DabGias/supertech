package br.com.fiap.resources;

import br.com.fiap.domain.cliente.model.TipoCliente;
import br.com.fiap.domain.cliente.repository.TipoClienteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/tipo-cliente")
public class TipoClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(TipoClienteRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        TipoCliente tc = TipoClienteRepository.findById(id);
        Response.ResponseBuilder resp;

        if (tc != null) {
            resp = Response.ok();

            resp.entity(tc);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(TipoCliente tc) {
        TipoCliente tipoCliente = TipoClienteRepository.save(tc);
        final URI tipoClienteUri = UriBuilder
                .fromResource(TipoClienteResource.class)
                .path("/{id}")
                .build(tipoCliente.getId());

        return Response.created(tipoClienteUri).entity(tipoCliente).build();
    }
}
