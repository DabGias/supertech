package br.com.fiap.resources;

import br.com.fiap.domain.servico.model.Servico;
import br.com.fiap.domain.servico.repository.ServicoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/servico")
public class ServicoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(ServicoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Servico s = ServicoRepository.findById(id);
        Response.ResponseBuilder resp;

        if (s != null) {
            resp = Response.ok();

            resp.entity(s);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Servico s) {
        Servico servico = ServicoRepository.save(s);
        final URI servicoUri = UriBuilder
                .fromResource(ServicoResource.class)
                .path("/{id}")
                .build(servico.getId());

        return Response.created(servicoUri).entity(servico).build();
    }
}
