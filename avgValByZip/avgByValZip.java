import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class avgByValZip{
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: avgByValZip <input path> <output path>");
            System.exit(-1);
        }

        Job job = new Job();
        job.setNumReduceTasks(1); 
        job.setJarByClass(avgByValZip.class);
        job.setJobName("avgByValZip");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(avgByValZipMapper.class);
        job.setReducerClass(avgByValZipReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}