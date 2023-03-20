package br.com.fiap.resources;

import br.com.fiap.domain.equipamento.model.Equipamento;
import br.com.fiap.domain.equipamento.repository.EquipamentoRepository;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/equipamento")
public class EquipamentoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(EquipamentoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Equipamento e = EquipamentoRepository.findById(id);
        Response.ResponseBuilder resp;

        if (e != null) {
            resp = Response.ok();

            resp.entity(e);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Equipamento e) {
        Equipamento equip = EquipamentoRepository.save(e);
        final URI equipUri = UriBuilder
                .fromResource(EquipamentoResource.class)
                .path("/{id}")
                .build(equip.getId());

        return Response.created(equipUri).entity(equip).build();
    }
}
