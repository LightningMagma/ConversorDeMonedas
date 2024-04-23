import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conex {
    Datos datos = new Datos();

    public String conexionAPI() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(datos.getUrl() + datos.getMonedaInicial() + datos.getMonedaFinal() + "/" + datos.getValorUsuario())).build();
        HttpResponse<String> reponse = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return reponse.body();
    }
}
