package com.zhaoyi.book.algro.secondrank.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.UUID;

public class TemperatureDriver {
    public static void main(String[] args) throws Exception {
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(TemperatureDriver.class);
        job.setMapperClass(TemperatureMapper.class);
        job.setReducerClass(TemperatureReducer.class);

        job.setMapOutputKeyClass(DateTemperature.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setPartitionerClass(TemperaturePartition.class);
        job.setNumReduceTasks(4);
        // add your group comparator class.
        job.setGroupingComparatorClass(TemperatureGroupComparator.class);


        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("temperature.txt"));
        FileOutputFormat.setOutputPath(job,new Path("output_" + UUID.randomUUID()) );
        System.exit(job.waitForCompletion(true)? 1:0);

    }
}
