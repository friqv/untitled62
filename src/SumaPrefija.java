import java.io.*;
import java.util.ArrayList;

public class SumaPrefija {

    public static double convertirValor(String valor) { //CONVERTIRLO de cadena a decimal
        valor = valor.replace("$", "").replace(",", ""); //elimina los caracteres
        return Double.parseDouble(valor);
    }

    public static double calcularSumaVentasPrefija(ArrayList<String> ventasPrefijas) { //AQUI SE CALCULA LA SUMA PREFIJA DE LAS VENTAS
        double sumatoria = 0;
        for (String venta : ventasPrefijas) {
            sumatoria += convertirValor(venta); //suma los valores convertidos
        }
        return sumatoria;
    }

    public static void main(String[] args) {
        ArrayList<String> ventasPrefijass = new ArrayList<>(); //ALMACENA LAS VENTAS EN UN ARRAY


        //PATH DE LOS ARCHIVOS
        String csv = "C:\\Users\\CM\\OneDrive\\Escritorio\\car_sales.csv";
        String csvOutput = "C:\\Users\\CM\\OneDrive\\Escritorio\\car_sales_output.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csv));
             BufferedWriter bw = new BufferedWriter(new FileWriter(csvOutput))) {

            String linea;
            boolean primeraLinea = true;
            double sumaTotal = 0;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    bw.write(linea + ",SumaPrefija");
                    bw.newLine();
                    continue;
                }
                String[] valores = linea.split(",");
                String valorVentas = valores[valores.length - 1];
                ventasPrefijass.add(valorVentas);
                sumaTotal = calcularSumaVentasPrefija(ventasPrefijass); //CALCULAMOS  LA SUMA PREFIJA

                bw.write(linea + "," + sumaTotal); //ESCIRBIR EN EL CSV
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SUMAS:  " + csvOutput);
    }
}
