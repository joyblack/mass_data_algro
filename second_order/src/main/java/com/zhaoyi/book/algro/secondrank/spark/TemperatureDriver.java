package com.zhaoyi.book.algro.secondrank.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.Map;


public class TemperatureDriver {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("second-rank").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 1. 加载文件，使用1个分区
        JavaRDD<String> lines = sc.textFile("temperature.txt");
        // 2. 将结果转化为元组形式
        JavaPairRDD<String, Integer> dateTemperatures = lines.mapToPair(line -> {
                    String[] strings = line.split(",");
                    String yearMonth = strings[0] + strings[1];
                    Integer temperature = Integer.valueOf(strings[3]);
                    return new Tuple2<String, Integer>(yearMonth, temperature);
                }
        );

        // 3.打印当前结果
        System.out.println("old date temperature list is:");
        dateTemperatures.foreach(dateTemperature ->
                System.out.println("dateMonth:" + dateTemperature._1 + ", temperature = " + dateTemperature._2));




    }

}
