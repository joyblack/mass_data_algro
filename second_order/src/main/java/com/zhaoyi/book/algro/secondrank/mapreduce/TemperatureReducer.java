package com.zhaoyi.book.algro.secondrank.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TemperatureReducer extends Reducer<DateTemperature, IntWritable,Text, Text> {
    @Override
    protected void reduce(DateTemperature key, Iterable<IntWritable> temperatures, Context context) throws IOException, InterruptedException {
        StringBuilder values = new StringBuilder();
        for (IntWritable temperature : temperatures) {
            values.append(temperature);
            values.append(",");
        }
        // delete the last symbol
        values.deleteCharAt(values.length() - 1);
        // output like 2018-01 22,23...
        context.write(new Text(key.getYearMonth()), new Text(values.toString()));
    }
}
