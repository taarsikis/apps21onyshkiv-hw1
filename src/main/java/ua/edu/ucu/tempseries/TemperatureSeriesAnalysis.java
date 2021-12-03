package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int size;


    public TemperatureSeriesAnalysis() {

    }

    private void checkTemperatures(){
        if (Objects.equals(this.temperatureSeries.length, 0)){
            throw new IllegalArgumentException();
        }
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        this.size = temperatureSeries.length;
    }

    public double average() {
        this.checkTemperatures();
        double total = 0;
        for (int i = 0; i < this.temperatureSeries.length ; i++){
            total += this.temperatureSeries[i];
        }
        return total / this.temperatureSeries.length;
    }

    public double deviation() {
        this.checkTemperatures();
        double sd = 0;
        double mean = this.average();

        for(double num: this.temperatureSeries) {
            sd += Math.pow(num - mean, 2);
        }

        return Math.sqrt(sd/this.temperatureSeries.length);
    }

    public double min() {
        this.checkTemperatures();

        double minVal = 999999999;
        for(double num: this.temperatureSeries) {
            if (num < minVal){
                minVal = num;
            }
        }
        return minVal;
    }

    public double max() {
        this.checkTemperatures();

        double maxVal = -999999999;
        for(double num: this.temperatureSeries) {
            if (num > maxVal){
                maxVal = num;
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        this.checkTemperatures();

        double deviation = 9999999;
        double currVal = -9999999;

        for (double num: this.temperatureSeries){
            double currDeviation = Math.abs(tempValue - num);
            if (currDeviation < deviation){
                currDeviation = deviation;
                currVal = num;
            } else if (currDeviation == deviation) {
                if ( num > currVal ){
                    currVal = num;
                }
            }
        }
        return currVal;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] result = new double[this.temperatureSeries.length];
        int currIdx = 0;
        for (double num: this.temperatureSeries) {
            if (num < tempValue){
                result[currIdx] = num;
                currIdx += 1;
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] result = new double[this.temperatureSeries.length];
        int currIdx = 0;
        for (double num: this.temperatureSeries) {
            if (num > tempValue){
                result[currIdx] = num;
                currIdx += 1;
            }
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
    }

    private void checkTemps( double... temps){
        for (double num: temps){
            if (num < 273){
                throw new InputMismatchException();
            }
        }
    }

    public int addTemps(double... temps) {
        this.checkTemps(temps);

        for (double temp : temps) {
            if (temperatureSeries.length == size) {
                temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length * 2);
            }
            temperatureSeries[size] = temp;
            size += 1;
        }
        return 0;
    }
}
