package com.zhaoyi.book.algro.secondrank.mapreduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TemperatureGroupComparator extends WritableComparator {

    public TemperatureGroupComparator(){
        super(DateTemperature.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return ((DateTemperature) a).getYearMonth().compareTo(((DateTemperature)b).getYearMonth());
    }
}
