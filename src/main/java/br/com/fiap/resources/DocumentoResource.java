package br.com.fiap.resources;

import br.com.fiap.domain.documento.model.Documento;
import br.com.fiap.domain.documento.repository.DocumentoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/documento")
public class DocumentoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() { return Response.ok(DocumentoRepository.findAll()).build(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Documento d = DocumentoRepository.findById(id);
        Response.ResponseBuilder resp;

        if (d != null) {
            resp = Response.ok();

            resp.entity(d);
        } else {
            resp = Response.status(404);
        }

        return resp.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Documento d) {
        Documento doc = DocumentoRepository.save(d);
        final URI docUri = UriBuilder
                .fromResource(DocumentoResource.class)
                .path("/{id}")
                .build(doc.getId());

        return Response.created(docUri).entity(doc).build();
    }
}
