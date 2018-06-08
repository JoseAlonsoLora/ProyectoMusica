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
import modelo.Cancion;
import modelo.CancionPK;

/**
 *
 * @author raymundo
 */
@Stateless
@Path("modelo.cancion")
public class CancionFacadeREST extends AbstractFacade<Cancion> {

    @PersistenceContext(unitName = "ProyectoMusicaServidorPU")
    private EntityManager em;

    private CancionPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idCancion=idCancionValue;albumidAlbum=albumidAlbumValue;albumartistaidArtista=albumartistaidArtistaValue;albumgeneroidGenero=albumgeneroidGeneroValue;albumbibliotecaidBiblioteca=albumbibliotecaidBibliotecaValue;albumbibliotecausuarionombreUsuario=albumbibliotecausuarionombreUsuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        modelo.CancionPK key = new modelo.CancionPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idCancion = map.get("idCancion");
        if (idCancion != null && !idCancion.isEmpty()) {
            key.setIdCancion(new java.lang.Integer(idCancion.get(0)));
        }
        java.util.List<String> albumidAlbum = map.get("albumidAlbum");
        if (albumidAlbum != null && !albumidAlbum.isEmpty()) {
            key.setAlbumidAlbum(new java.lang.Integer(albumidAlbum.get(0)));
        }
        java.util.List<String> albumartistaidArtista = map.get("albumartistaidArtista");
        if (albumartistaidArtista != null && !albumartistaidArtista.isEmpty()) {
            key.setAlbumartistaidArtista(new java.lang.Integer(albumartistaidArtista.get(0)));
        }
        java.util.List<String> albumgeneroidGenero = map.get("albumgeneroidGenero");
        if (albumgeneroidGenero != null && !albumgeneroidGenero.isEmpty()) {
            key.setAlbumgeneroidGenero(new java.lang.Integer(albumgeneroidGenero.get(0)));
        }
        java.util.List<String> albumbibliotecaidBiblioteca = map.get("albumbibliotecaidBiblioteca");
        if (albumbibliotecaidBiblioteca != null && !albumbibliotecaidBiblioteca.isEmpty()) {
            key.setAlbumbibliotecaidBiblioteca(new java.lang.Integer(albumbibliotecaidBiblioteca.get(0)));
        }
        java.util.List<String> albumbibliotecausuarionombreUsuario = map.get("albumbibliotecausuarionombreUsuario");
        if (albumbibliotecausuarionombreUsuario != null && !albumbibliotecausuarionombreUsuario.isEmpty()) {
            key.setAlbumbibliotecausuarionombreUsuario(albumbibliotecausuarionombreUsuario.get(0));
        }
        return key;
    }

    public CancionFacadeREST() {
        super(Cancion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Cancion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Cancion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        modelo.CancionPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cancion find(@PathParam("id") PathSegment id) {
        modelo.CancionPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cancion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cancion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
