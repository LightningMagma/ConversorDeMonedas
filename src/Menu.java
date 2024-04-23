import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Menu {
    Scanner lectura = new Scanner(System.in);
    Datos datos = new Datos();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void mostrarMenu() throws IOException, InterruptedException {
        do {
            imprimirBloque("Elija la moneda desde la que desea hacer la conversión", true);
            var seleccionMonedaInicial = lectura.nextLine();
            if (seleccionMonedaInicial.equalsIgnoreCase("salir") || seleccionMonedaInicial.equals("9")) {
                System.out.println("Saliendo del programa");
                break;
            }
            datos.setMonedaInicial(obtenerCodigoMoneda(seleccionMonedaInicial));
            imprimirBloque("Elija la moneda a la que quiere hacer la conversión", true);
            var seleccionMonedaFinal = lectura.nextLine();
            if (seleccionMonedaFinal.equalsIgnoreCase("salir") || seleccionMonedaFinal.equals("9")) {
                System.out.println("Saliendo del programa");
                break;
            }
            datos.setMonedaFinal(obtenerCodigoMoneda(seleccionMonedaFinal));
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Digite el valor que desea convertir: ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            datos.setValorUsuario(lectura.nextLine());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(datos.getUrl() + datos.getMonedaInicial() + datos.getMonedaFinal() + "/" + datos.getValorUsuario())).build();
            HttpResponse<String> reponse = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = reponse.body();

            MonedaExR monedaExR = gson.fromJson(json, MonedaExR.class);

            ValorMoneda valorMoneda = new ValorMoneda(monedaExR);
            System.out.println("████████████████████████████");
            System.out.println(datos.getValorUsuario() + datos.getMonedaInicial().replace("/", " ") + " convirtiendo a " + datos.getMonedaFinal().replace("/", "-> "));
            System.out.print(valorMoneda + datos.getMonedaFinal().replace("/", " "));
            System.out.println("\n████████████████████████████");
        } while (true);
    }

    private void imprimirBloque(String mensaje, boolean separador) {
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println(mensaje);
        if (separador) {
            System.out.println("1 - ARS - Peso argentino");
            System.out.println("2 - BOB - Boliviano boliviano");
            System.out.println("3 - BRL - Real brasileño");
            System.out.println("4 - CLP - Peso chileno");
            System.out.println("5 - COP - Peso colombiano");
            System.out.println("6 - USD - Dólar estadounidense");
            System.out.println("7 - CAD - Dólar canadiense");
            System.out.println("8 - EUR - Euro");
            System.out.println("9 - Salir del programa");
        }
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
    }

    private String obtenerCodigoMoneda(String seleccionMoneda) {
        switch (seleccionMoneda.toUpperCase()) {
            case "1":
            case "ARS":
                return "/ARS";
            case "2":
            case "BOB":
                return "/BOB";
            case "3":
            case "BRL":
                return "/BRL";
            case "4":
            case "CLP":
                return "/CLP";
            case "5":
            case "COP":
                return "/COP";
            case "6":
            case "USD":
                return "/USD";
            case "7":
            case "CAD":
                return "/CAD";
            case "8":
            case "EUR":
                return "/EUR";
            default:
                System.out.println("XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx");
                System.out.println("Seleccione una opcion correcta");
                System.out.println("XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx");
                return null;
        }
    }
}
