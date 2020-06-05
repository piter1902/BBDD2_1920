package bases2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;

public class Logistica {
	private static final int TOTAL_FEATURES = 1600;
	private static final double ALPHA = 0.01;
	// private static final int TOTAL_PASOS = 20;
	private static final int TOTAL_PASOS = 1;
	public static double[] thetasAct = new double[TOTAL_FEATURES + 1];

	public static class LogisticMapper extends TableMapper<LongWritable, DoubleWritable> {

		private LongWritable keyout = new LongWritable();
		private DoubleWritable valout = new DoubleWritable();

		@Override
		public void map(ImmutableBytesWritable key, Result value, Context context)
				throws IOException, InterruptedException {
			// key=identificador de cliente
			// value=toda la informacion asociada al cliente: clasificación A o B,
			// caracteristicas no nulas

			// obtener un diccionario para la familia de columnas car:
			// calificador_columna->valor
			NavigableMap<byte[], byte[]> mapCar = value.getFamilyMap(Bytes.toBytes("car"));

			// para el cliente key, crear un vector xi donde se almacenarán sus 1 + 1600
			// caracteristicas
			double[] xi = new double[thetasAct.length];
			// caracteristica 0 siempre es igual a 1
			xi[0] = 1;

			// De esta manera nos aseguramos de que se ponen a 0
			for (int i = 1; i < xi.length; i++) {
				xi[i] = 0;
			}

			// Con los datos de mapCar, rellenar en xi las caracteristicas
			for (byte[] i : mapCar.keySet()) {
				xi[Integer.parseInt(Bytes.toString(i))] = 1.0;
			}

			double prod_Esc = 0.0;

			// Hallar producto escalar thetasAct*xi
			for (int i = 0; i < xi.length; i++) {
				prod_Esc += thetasAct[i] * xi[i];
			}

			// leer de parametro value, clasificacion A o B de cliente
			String classi = Bytes.toString(value.getValue(Bytes.toBytes("cli"), Bytes.toBytes("class")));
			double yi = classi.equals("A") ? 0.0 : 1.0;

			double g = 0.0;

			g = (1 / 1 + Math.exp(-prod_Esc));

			g = yi - g;

			// emitir para cada dimenson j el valor correspondiente a
			// uno de los sumandos del sumatorio del algoritmo del enunciado
			for (int j = 0; j < TOTAL_FEATURES + 1; j++) {
				context.write(new LongWritable(j), new DoubleWritable(g * xi[j]));
			}
		}

		public static class LogisticReducer
				extends Reducer<LongWritable, DoubleWritable, LongWritable, DoubleWritable> {

			public void reduce(LongWritable key, Iterable<DoubleWritable> values, Context context)
					throws IOException, InterruptedException {

				double sum = 0;
				for (DoubleWritable value : values) {
					sum += value.get();
				}
				try {
					// Se escribira en el fichero el par <j,'sumaDeValoresj'>
					context.write(key, new DoubleWritable(sum));
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Configuration conf = HBaseConfiguration.create();

		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < thetasAct.length; i++) {
			thetasAct[i] = 0.0;
		}

		for (int paso = 0; paso < TOTAL_PASOS; paso++) {
			// preparar el trabajo map/reduce para calcular el gradiente
			Job job = null;
			try {
				job = Job.getInstance(conf, "Regresion logistica");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Scan para leer de la tabla
			Scan scan = new Scan();
			scan.setCaching(300);

			try {
				TableMapReduceUtil.initTableMapperJob("Clientes",  	    // nombre de la tabla
						scan, 										  	// Instancia scan para controlar CF y atributos
						LogisticMapper.class,                           // Clase mapper
						LongWritable.class,                             // Tipo de clave de salida del mapper
						DoubleWritable.class,                           // Tipo de valor de salida del mapper
						job);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			FileOutputFormat.setOutputPath(job, new Path("out"));

			// lanzar y esperar a que finalice el trabajo
			boolean exito = false;
			try {
				exito = job.waitForCompletion(true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// si el trabajo finalizo sin exito, acabar
			if (!exito)
				System.exit(-1);

			// una vez finalizado el trabajo que calcula el gradiente,
			// leer de los ficheros el gradiente calculado

			// actualizar los thetasAct
			// thetasAct = actualizarThetas(thetasAct, grad, ALPHA);

			// borrar directorio out, preparando el siguiente paso
			try {
				fs.delete(new Path("out"), true);
			} catch (IllegalArgumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Guardar a fichero los thetasAct
		try {
			guardar(thetasAct, "thetas.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void guardar(double[] thetas, String nomFich) throws IOException {
		PrintWriter out = new PrintWriter(nomFich);
		out.print(Double.toString(thetas[0]));
		for (int i = 1; i < thetas.length; i++) {
			out.print("," + Double.toString(thetas[i]));
		}
		out.close();
	}

}
