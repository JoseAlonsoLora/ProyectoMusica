/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import modelo.Listareproduccion;
import modelo.ListareproduccionPK;

/**
 *
 * @author raymundo
 */
@Stateless
@Path("modelo.listareproduccion")
public class ListareproduccionFacadeREST extends AbstractFacade<Listareproduccion> {

    @PersistenceContext(unitName = "ProyectoMusicaServidorPU")
    private EntityManager em;

    private ListareproduccionPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idlistaReproduccion=idlistaReproduccionValue;usuarionombreUsuario=usuarionombreUsuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        modelo.ListareproduccionPK key = new modelo.ListareproduccionPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idlistaReproduccion = map.get("idlistaReproduccion");
        if (idlistaReproduccion != null && !idlistaReproduccion.isEmpty()) {
            key.setIdlistaReproduccion(new java.lang.Integer(idlistaReproduccion.get(0)));
        }
        java.util.List<String> usuarionombreUsuario = map.get("usuarionombreUsuario");
        if (usuarionombreUsuario != null && !usuarionombreUsuario.isEmpty()) {
            key.setUsuarionombreUsuario(usuarionombreUsuario.get(0));
        }
        return key;
    }

    public ListareproduccionFacadeREST() {
        super(Listareproduccion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Listareproduccion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Listareproduccion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        modelo.ListareproduccionPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Listareproduccion find(@PathParam("id") PathSegment id) {
        modelo.ListareproduccionPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Listareproduccion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Listareproduccion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
