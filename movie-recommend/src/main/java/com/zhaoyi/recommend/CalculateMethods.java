package com.zhaoyi.recommend;

import scala.Tuple3;
import scala.Tuple7;

// 辅助类
public class CalculateMethods {
    /**
     * t7_1: m1_score
     * t7_2: number_of_m1_scores
     * t7_3: m2_score
     * t7_4: number_of_m2_scores
     * t7_5: m1_score * m2_score
     * t7_6: Square(m1_score)
     * t7_7: Square(m2_score)
      */
    public static Tuple3<Double, Double, Double> calculateCorRelation(Iterable<Tuple7<
            Integer,Integer,Integer,Integer,Integer,Integer,Integer>> t7s){
        int groupSize = 0;// 各个向量的长度（即传入的t7的长度）
        int m1m2ProductSum = 0;// (m1_score * m2_score)之和
        int score1Sum = 0;// score1的和
        int score2Sum = 0; // score2之和
        int score1SquareSum = 0;// score1Square之和
        int score2SquareSum = 0; // score2Square之和
        int maxNumberOfScore1 = 0; // max1: number of score1
        int maxNumberOfScore2 = 0; // max2: number of score2

        for (Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> t7 : t7s) {
            groupSize ++; // the t7s length.
            score1Sum += t7._1(); //  Sum(m1_score).
            score2Sum += t7._3(); // Sum(m2_score).
            m1m2ProductSum += t7._5(); // Sum(m1_score * m2_score)
            score1SquareSum += t7._6(); // Sum(Square(m1_score))
            score2SquareSum += t7._7(); //Sum(Square(m2_score))

            // get Max(numberOfScore1)
            int numberOfScore1 = t7._2();
            if(numberOfScore1 > maxNumberOfScore1){
                maxNumberOfScore1 = numberOfScore1;
            }

            // get Max(numberOfScore2)
            int numberOfScore2 = t7._4();
            if(numberOfScore2 > maxNumberOfScore2){
                maxNumberOfScore2 = numberOfScore2;
            }
        }

        // 皮尔逊
        double pearson = calculatePeraSonRelation(groupSize,m1m2ProductSum,score1Sum,score2Sum,score1SquareSum,score2SquareSum);


        return null;

    }

    private static double calculatePeraSonRelation(int groupSize, int m1m2ProductSum, int score1Sum, int score2Sum, int score1SquareSum, int score2SquareSum) {
        // 分子
        double numrator = groupSize * m1m2ProductSum - score1Sum * score2Sum;
        // 分母
        double denominator = Math.sqrt(groupSize * score1SquareSum - score1Sum * score1Sum)
                * Math.sqrt(groupSize * score2SquareSum - score2Sum * score2Sum);

        return numrator / denominator;

    }
}
