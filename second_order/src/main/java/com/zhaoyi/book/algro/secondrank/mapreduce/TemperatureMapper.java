package com.zhaoyi.book.algro.secondrank.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TemperatureMapper extends Mapper<LongWritable, Text, DateTemperature, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split(",");
        String yearMonth = tokens[0] + "-" + tokens[1];
        // tokens[2] is Day, it isn't necessary.
        String day = tokens[2];
        Integer temperature = Integer.valueOf(tokens[3]);
        // k->bean v-temperature
        context.write(new DateTemperature(yearMonth,tokens[2], temperature),new IntWritable(temperature));

    }
}
