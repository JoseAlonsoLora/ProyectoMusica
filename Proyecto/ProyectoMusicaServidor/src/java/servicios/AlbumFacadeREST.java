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
import modelo.Album;
import modelo.AlbumPK;

/**
 *
 * @author Irdevelo
 */
@Stateless
@Path("modelo.album")
public class AlbumFacadeREST extends AbstractFacade<Album> {

    @PersistenceContext(unitName = "ProyectoMusicaServidorPU")
    private EntityManager em;

    private AlbumPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idAlbum=idAlbumValue;artistaidArtista=artistaidArtistaValue;generoidGenero=generoidGeneroValue;bibliotecaidBiblioteca=bibliotecaidBibliotecaValue;bibliotecausuarionombreUsuario=bibliotecausuarionombreUsuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        modelo.AlbumPK key = new modelo.AlbumPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idAlbum = map.get("idAlbum");
        if (idAlbum != null && !idAlbum.isEmpty()) {
            key.setIdAlbum(new java.lang.Integer(idAlbum.get(0)));
        }
        java.util.List<String> artistaidArtista = map.get("artistaidArtista");
        if (artistaidArtista != null && !artistaidArtista.isEmpty()) {
            key.setArtistaidArtista(new java.lang.Integer(artistaidArtista.get(0)));
        }
        java.util.List<String> generoidGenero = map.get("generoidGenero");
        if (generoidGenero != null && !generoidGenero.isEmpty()) {
            key.setGeneroidGenero(new java.lang.Integer(generoidGenero.get(0)));
        }
        java.util.List<String> bibliotecaidBiblioteca = map.get("bibliotecaidBiblioteca");
        if (bibliotecaidBiblioteca != null && !bibliotecaidBiblioteca.isEmpty()) {
            key.setBibliotecaidBiblioteca(new java.lang.Integer(bibliotecaidBiblioteca.get(0)));
        }
        java.util.List<String> bibliotecausuarionombreUsuario = map.get("bibliotecausuarionombreUsuario");
        if (bibliotecausuarionombreUsuario != null && !bibliotecausuarionombreUsuario.isEmpty()) {
            key.setBibliotecausuarionombreUsuario(bibliotecausuarionombreUsuario.get(0));
        }
        return key;
    }

    public AlbumFacadeREST() {
        super(Album.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Album entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Album entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        modelo.AlbumPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Album find(@PathParam("id") PathSegment id) {
        modelo.AlbumPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Album> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
