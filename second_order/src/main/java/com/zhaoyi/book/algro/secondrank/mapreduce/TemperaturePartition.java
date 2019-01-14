package com.zhaoyi.book.algro.secondrank.mapreduce;

        import org.apache.hadoop.io.IntWritable;
        import org.apache.hadoop.mapreduce.Partitioner;

public class TemperaturePartition extends Partitioner<DateTemperature, IntWritable> {
    @Override
    public int getPartition(DateTemperature dateTemperature, IntWritable temperature, int numPartitions) {
        return Math.abs(dateTemperature.getYearMonth().hashCode() % numPartitions);
    }
}
