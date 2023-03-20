package br.com.fiap.resources;

import br.com.fiap.domain.equipamento.model.TipoEquipamento;
import br.com.fiap.domain.equipamento.repository.TipoEquipamentoRepository;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/tipo-equipamento")
public class TipoEquipamentoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(TipoEquipamentoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        TipoEquipamento te = TipoEquipamentoRepository.findById(id);
        Response.ResponseBuilder res;

        if (te != null) {
            res = Response.ok();

            res.entity(te);
        } else {
            res = Response.status(404);
        }

        return res.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(TipoEquipamento te) {
        TipoEquipamento tipoEquip = TipoEquipamentoRepository.save(te);
        final URI tipoEquipUri = UriBuilder
                .fromResource(TipoEquipamentoResource.class)
                .path("/{id}")
                .build(tipoEquip.getId());

        return Response.created(tipoEquipUri).entity(tipoEquip).build();
    }
}
