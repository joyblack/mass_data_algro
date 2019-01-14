package com.zhaoyi.book.algro.secondrank.mapreduce;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DateTemperature implements Writable, WritableComparable<DateTemperature> {
    // year month
    private String yearMonth;
    // day
    private String day;
    // temperature
    private Integer temperature;

    // reflect must be need.
    public DateTemperature() {
    }

    public DateTemperature(String yearMonth, String day, Integer temperature) {
        this.yearMonth = yearMonth;
        this.day = day;
        this.temperature = temperature;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    // 1. 排序接口
    public int compareTo(DateTemperature o) {
        int result = this.getYearMonth().compareTo(o.getYearMonth());
        if(result == 0){
            result = this.getTemperature().compareTo(o.getTemperature());
        }
        // 降序排序。若要升序排序，直接返回result.
        return  -1 * result;
    }

    // 2. 序列化接口
    public void write(DataOutput out) throws IOException {
        out.writeUTF(yearMonth);
        out.writeUTF(day);
        out.writeInt(temperature);
    }

    public void readFields(DataInput in) throws IOException {
        yearMonth = in.readUTF();
        day = in.readUTF();
        temperature = in.readInt();
    }

    @Override
    public String toString() {
        return yearMonth + ","+ temperature;
    }
}
