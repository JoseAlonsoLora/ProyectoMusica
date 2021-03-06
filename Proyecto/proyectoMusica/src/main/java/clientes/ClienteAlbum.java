/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import com.mycompany.proyectomusica.MainApp;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import modelo.Album;

/**
 * Jersey REST client generated for REST resource:AlbumFacadeREST
 * [modelo.album]<br>
 * USAGE:
 * <pre>
        ClienteAlbum client = new ClienteAlbum();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author raymundo
 */
public class ClienteAlbum {
    private Properties recurso;

    private javax.ws.rs.client.WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static String BASE_URI;

    public ClienteAlbum() {
        recurso = MainApp.leerConfig();
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        BASE_URI = "http://"+ip+":"+puerto+"/crearAlbum/";
        
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }

    public String countREST() throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void edit(Object requestEntity, String id) throws javax.ws.rs.ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public Album find(String id) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<Album>(){});
    }

    public <T> T findRange(Class<T> responseType, String from, String to) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void create(Object requestEntity) throws javax.ws.rs.ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public List<Album> findAll() throws javax.ws.rs.ClientErrorException {
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        String idBiblioteca = "1";
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/todosLosAlbumes/");
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Album>>(){});
    }

    public void remove(String id) throws javax.ws.rs.ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }
    
    public List<Album> obtenerAlbumesArtista(int idArtista){
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/AlbumesPorArtista/?id="+idArtista);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Album>>(){});
    }
    public void close() {
        client.close();
    }
    
    public List<Album> obtenerAlbumesBiblioteca(int idBiblioteca){
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/albumesPorBiblioteca/?id="+idBiblioteca);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Album>>(){});
        
    }
    
    public List<Album> obtenerAlbumesGenero(int idGenero){
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/obtenerAlbumPorGenero/?id="+idGenero);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Album>>(){});
        
    }
    
}
