package bases2;

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
		String datos = "dataTest.csv";
		try{
			Scanner scanner = new Scanner(new File(datos));
			String line = null;
			int apos = 0;  int bfp = 0;
			int afp = 0;  int bpos = 0;

			while ( scanner.hasNextLine() ){
				line = scanner.nextLine();
				String[] elems = line.split(",");
				String class = elems[1];

				double prod = thetas[0]; // pq la primera es un 1 siempre
				for (int i=2; i < elems.length; i++){
					prod += elems[i]*thetas[i-1];
				}
				double g = (1 / (1+ Math.exp(-prod)));
				if ( class.equals("A")){
					//letra es A
					if ( g < 0.5) 
						//modelo predice A --> apos
						apos++;
					else bfp++; // modelo predice B y es A --> bfp
				}
				else {
					//letra es B
					if ( g < 0.5)
						//modelo predice A --> afp
						afp++;
					else bpos++; // modelo predice B y es B --> bpos
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.err.println("Error: fichero no existe " + path);
		}
		//mostrar matriz de confusion y porcentaje de aciertos
		System.out.println("\tA\t\tB");
		System.out.println("A\t"+apos+"\t\t"+bfp);
		System.out.println("B\t"+afp+"\t\t"+bpos);
	}
	
	
}
