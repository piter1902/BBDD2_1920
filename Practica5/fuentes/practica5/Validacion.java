package practica5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Validacion {
	
	
	
	public static double[] leerThetas(String path) {
		double[] values = null;
		try {
			Scanner scan = new Scanner(new File(path));
			String line = scan.nextLine();
			String[] toks = line.split(",");
			values = new double[toks.length];
			for (int i = 0; i < values.length; i++) {
				values[i] = Double.parseDouble(toks[i]);
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error: fichero no existe " + path);
		}
		return values;
	}
	
	public static void main(String[] args) throws IOException {
		//leer thetas de fichero
		double[] thetas = leerThetas("thetas.csv");
		
		//para cada cliente del fichero, leer caracteristicas xi y clasificacion real yi 
		//calcular clasificacion predicha segun thetas: yiPredicha = 1/(1 + exp(-xi*thetas)>0.5
		//calcular datos de la matriz de confusion
		
		//mostrar matriz de confusion y porcentaje de aciertos
		
		
		
	}
	
	
}
