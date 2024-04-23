public class ValorMoneda {
    private double valorBase;
    private double valorFinal;

    public ValorMoneda(MonedaExR monedaExR) {
        this.valorBase = monedaExR.conversion_rate();
        this.valorFinal = monedaExR.conversion_result();
    }

    @Override
    public String toString() {
        return "Tasa de conversión: " + valorBase +
                "\nValor convertido: " + valorFinal;
    }


}
