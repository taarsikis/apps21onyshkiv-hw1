package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }


    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(3.7416573, seriesAnalysis.deviation(), 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-5.0, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(5.0, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(1, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0,-1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(1, seriesAnalysis.findTempClosestToValue(0), 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(analysis.findTempsGreaterThen(2.0)[0], new double[]{3.0, 5.0}[0], 0.00001);
        assertEquals(analysis.findTempsLessThen(2.0)[0], new double[]{-5.0, 1.0}[0], 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        assertEquals(seriesAnalysis.addTemps(temperatureSeries), 4, 0.00001);

        TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries);

        TempSummaryStatistics summary = seriesAnalysis1.summaryStatistics();
        assertEquals(summary.getMaxTemp(), 5, 0.00001);
        assertEquals(summary.getAvgTemp(), 1, 0.00001);
        assertEquals(summary.getDevTemp(), 3.74165, 0.00001);
        assertEquals(summary.getMinTemp(), -5, 0.00001);

    }

    @Test(expected = InputMismatchException.class)
    public void testCheckTemps(){
        double[] temperatureSeries = {3.0, -300.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        seriesAnalysis.addTemps(temperatureSeries);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckTemp(){
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.average();
        seriesAnalysis.max();
        seriesAnalysis.min();
        seriesAnalysis.deviation();
        seriesAnalysis.findTempClosestToValue(1);
        seriesAnalysis.findTempClosestToZero();
    }
}
