package br.com.fiap.resources;

import br.com.fiap.domain.servico.model.TipoServico;
import br.com.fiap.domain.servico.repository.TipoServicoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/tipo-servico")
public class TipoServicoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(TipoServicoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        TipoServico ts = TipoServicoRepository.findById(id);
        Response.ResponseBuilder resp;

        if (ts != null) {
            resp = Response.ok();

            resp.entity(ts);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(TipoServico ts) {
        TipoServico tipoServ = TipoServicoRepository.save(ts);
        final URI tipoServUri = UriBuilder
                .fromResource(TipoServicoResource.class)
                .path("/{id}")
                .build(tipoServ.getId());

        return Response.created(tipoServUri).entity(tipoServ).build();
    }
}
