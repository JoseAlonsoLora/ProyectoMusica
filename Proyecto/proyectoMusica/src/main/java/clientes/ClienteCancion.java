/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import com.mycompany.proyectomusica.MainApp;
import controladores.PantallaPrincipalController;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import modelo.Biblioteca;
import modelo.Cancion;

/**
 * Jersey REST client generated for REST resource:CancionFacadeREST
 * [modelo.cancion]<br>
 * USAGE:
 * <pre>
 * ClienteCancion client = new ClienteCancion();
 * Object response = client.XXX(...);
 * // do whatever with response
 * client.close();
 * </pre>
 *
 * @author raymundo
 */
public class ClienteCancion {

    private WebTarget webTarget;
    private Client client;
    private static String BASE_URI;
    private Properties recurso;

    public ClienteCancion() {
        recurso = MainApp.leerConfig();
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        BASE_URI = "http://" + ip + ":" + puerto + "/todasCanciones/";
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("modelo.cancion");
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void edit(Object requestEntity, String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public List<Cancion> findAll() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    public void close() {
        client.close();
    }

    public List<Cancion> obtenerCancionesBiblioteca() {
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
        Biblioteca bibliotecaUsuario = null;
        List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
        for (Biblioteca biblioteca : bibliotecas) {
            if (biblioteca.getUsuario_nombreusuario().equals(PantallaPrincipalController.nombreUsuario)) {
                bibliotecaUsuario = biblioteca;
            }
        }
        String idBiblioteca = String.valueOf(bibliotecaUsuario.getIdbiblioteca());
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/cancionesPorBiblioteca/" + "?id=" + idBiblioteca);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
    }

    public List<Cancion> obtenerCancionesAlbum(int idAlbum) {
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/cancionesPorAlbum/" + "?id=" + idAlbum);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
    }

    public List<Cancion> obtenerCancionesLista(int idLista) {
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/canciones/" + "?id=" + idLista);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
    }
    
    public List<Cancion> obtenerCancionesGenero(int idAlbum){
         String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/obtenerCancionesPorGenero/" + "?id=" + idAlbum);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
    }

}
