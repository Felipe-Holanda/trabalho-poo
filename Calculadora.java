public class Calculadora {
  public String soma(String x, String y) {
    int v2 = Integer.parseInt(x);
    int v1 = Integer.parseInt(y);
    String resultado = String.valueOf(v1 + v2);

    return resultado;
  }

  public String subtrai(String x, String y) {
    int v2 = Integer.parseInt(x);
    int v1 = Integer.parseInt(y);
    String resultado = String.valueOf(v2 - v1);

    return resultado;
  }

  public String multiplica(String x, String y) {
    int v1 = Integer.parseInt(x);
    int v2 = Integer.parseInt(y);
    int m = v1 * v2;
    String resultado = String.valueOf(m);

    return resultado;
  }

  public String divide(String x, String y) {
    double v1 = Double.valueOf(x);
    double v2 = Double.valueOf(y);
    String resultado = String.valueOf(v1 / v2);

    return resultado;
  }
}