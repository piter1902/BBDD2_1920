package bases2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class Importacion {

	static class ImportMapper
	             extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> { 
		@Override	
		public void map(LongWritable offset, Text line, Context context) throws IOException, InterruptedException {

			String lineString = line.toString();
			String[] toks = lineString.split(",");

			Put put = null;
			for (int i = 0; i < toks.length; i++) {
				if (i == 0) {
					//crear put con la clave=codigo cliente
					put = new Put(toks[i].getBytes()); 
				} else if (i == 1) {
					//guardar clasificacion del cliente
					//asociar a familia_columna:calificador_columna->valor,
					//esto es, cli:class-> A o B
								

				} else {
					//guardar solo caracteristicas no nulas
					//car:<numerocaracteristica>->1 
					//Por ejemplo, car:585->1
					if (Double.parseDouble(toks[i]) == 1.0){
						put.addColumn("car".getBytes(), 
								Bytes.toBytes("" + (i-1)), 
								toks[i].getBytes());
					}
				}
			} 
			//emitir put para escribir en la tabla
			context.write(new ImmutableBytesWritable(), put);
			
		}
		
	}

	public static void main(String[] args){
		Configuration conf = HBaseConfiguration.create();
		try {
			Job job = Job.getInstance(conf, "Import from file ");

			//configurar el trabajo
			job.setJarByClass(Importacion.class);
			job.setMapperClass(ImportMapper.class);
			job.setOutputFormatClass(TableOutputFormat.class);
			job.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, "Clientes");
			//no es necesario reducer para este trabajo
			job.setNumReduceTasks(0); 

			FileInputFormat.addInputPath(job, new Path("data.csv"));
			
			//lanzar el trabajo y esperar a que termine
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

