package br.com.fiap.resources;

import br.com.fiap.domain.cliente.model.Cliente;
import br.com.fiap.domain.cliente.repository.ClienteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/cliente")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(ClienteRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Cliente c = ClienteRepository.findById(id);
        Response.ResponseBuilder resp;

        if (c != null) {
            resp = Response.ok();

            resp.entity(c);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Cliente c) {
        Cliente cliente = ClienteRepository.save(c);
        final URI clienteUri = UriBuilder
                .fromResource(ClienteResource.class)
                .path("/{id}")
                .build(cliente.getId());

        return Response.created(clienteUri).entity(cliente).build();
    }
}
