public class Datos {
    private final String url="https://v6.exchangerate-api.com/v6/8feba338ced86c8526edef20/pair";
    private String monedaInicial;
    private String monedaFinal;
    private String valorUsuario;

    public String getMonedaInicial() {
        return monedaInicial;
    }

    public void setMonedaInicial(String monedaInicial) {
        this.monedaInicial = monedaInicial;
    }

    public String getMonedaFinal() {
        return monedaFinal;
    }

    public void setMonedaFinal(String monedaFinal) {
        this.monedaFinal = monedaFinal;
    }

    public String getValorUsuario() {
        return valorUsuario;
    }

    public void setValorUsuario(String valorUsuario) {
        this.valorUsuario = valorUsuario;
    }

    public String getUrl() {
        return url;
    }
}
