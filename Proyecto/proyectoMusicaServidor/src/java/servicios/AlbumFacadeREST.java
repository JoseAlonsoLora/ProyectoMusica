/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.Album;
import modelo.AlbumPOST;

/**
 *
 * @author raymundo
 */
@Stateless
@Path("modelo.album")
public class AlbumFacadeREST extends AbstractFacade<Album> {

    @PersistenceContext(unitName = "proyectoMusicaServidorPU")
    private EntityManager em;

    public AlbumFacadeREST() {
        super(Album.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(AlbumPOST album) {
        super.create(album.getAlbum());
        //System.out.println(album.getFile().getAbsolutePath());
//        File file = new File(System.getProperty("user.home" + "/Desktop"));
//        byte[] buffer = new byte[1024];
//        InputStream inStream = null;
//        OutputStream outStream = null;
//        try {
//            inStream = new FileInputStream(album.getFile());
//            outStream = new FileOutputStream(file);
//            int length;
//            while ((length = inStream.read(buffer)) > 0) {
//                outStream.write(buffer, 0, length);
//            }
//            inStream.close();
//            outStream.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AlbumFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(AlbumFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Album entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Album find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Album> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
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

//    @PUT
//    @Path("{nombreArchivo}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void subirArchivo(@PathParam("nombreArchivo") String nombreArchivo, File archivo) {
//        System.out.println("-----------------------------------------------------------------" + archivo.getName());
//    }
}
