package br.com.fiap.resources;

import br.com.fiap.domain.documento.model.TipoDocumento;
import br.com.fiap.domain.documento.repository.TipoDocumentoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

public class TipoDocumentoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(TipoDocumentoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        TipoDocumento td = TipoDocumentoRepository.findById(id);
        Response.ResponseBuilder resp;

        if (td != null) {
            resp = Response.ok();

            resp.entity(td);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(TipoDocumento td) {
        TipoDocumento tipoDoc = TipoDocumentoRepository.save(td);
        final URI tipoDocUri = UriBuilder
                .fromResource(TipoDocumentoResource.class)
                .path("/{id}")
                .build(tipoDoc.getId());

        return Response.created(tipoDocUri).entity(tipoDoc).build();
    }
}
