package util;

import static com.googlecode.charts4j.Color.WHITE;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

/**
 * Para mas ejemplos ver http://code.google.com/p/charts4j/
 * 
 */
public class Plotter {
	
	public static String plot(float[] points, String xLabel, String yLabel) {
		
		return plot(points, xLabel, yLabel, "");
	}
	
	public static String plot(float[] points1, String xLabel, String yLabel, String title) {
		// Defining lines
		double[] points = MoreMath.asDouble(points1);
		Line line1 = createStyledLine("This is a line", points);

		// Defining chart.
		LineChart chart = createStyledLineChart(title, line1);
		chart.setSize(600, 450);

		// Add X axis
//		AxisLabels xAxisLabels = AxisLabelsFactory.newAxisLabels("Nov", "Dec", "Jan", "Feb", "Mar");
		AxisLabels xAxisTitle = AxisLabelsFactory.newAxisLabels(xLabel, 50.0);
//		chart.addXAxisLabels(xAxisLabels);
		chart.addXAxisLabels(xAxisTitle);
		// Add Y Axis
//		AxisLabels yAxisLabels = AxisLabelsFactory.newAxisLabels("", "25", "50", "75", "100");
		AxisLabels yAxisTitle = AxisLabelsFactory.newAxisLabels(yLabel, 50.0);
//		chart.addYAxisLabels(yAxisLabels);
		chart.addYAxisLabels(yAxisTitle);

		// Set Label styles
//		AxisStyle axisLabelStyle = AxisStyle.newAxisStyle(WHITE, 12, AxisTextAlignment.CENTER);
		AxisStyle axisTitleStyle = AxisStyle.newAxisStyle(WHITE, 14, AxisTextAlignment.CENTER);
//		xAxisLabels.setAxisStyle(axisLabelStyle);
		xAxisTitle.setAxisStyle(axisTitleStyle);
//		yAxisLabels.setAxisStyle(axisLabelStyle);
		yAxisTitle.setAxisStyle(axisTitleStyle);

		String url = chart.toURLString();
		System.out.println("Use this url string in your web browser to see the graph");
		System.out.println(url);
		return url;
	}

	private static Line createStyledLine(String title, double[] points) {
		Color lineColor = Color.newColor("CA3D05");
		Line line = Plots.newLine(Data.newData(points), lineColor, title);
		line.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
		line.addShapeMarkers(Shape.DIAMOND, lineColor, 12);
		line.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);
		return line;
	}

	private static LineChart createStyledLineChart(String title, Line... lines) {
		LineChart chart = GCharts.newLineChart(lines);
		chart.setTitle(title, WHITE, 14);
		chart.setGrid(25, 25, 3, 2);
		// Defining background and chart fills.
		Color bgColor = Color.newColor("1F1D1D");
		chart.setBackgroundFill(Fills.newSolidFill(bgColor));
		LinearGradientFill fill = Fills.newLinearGradientFill(0,
				Color.newColor("363433"), 100);
		fill.addColorAndOffset(Color.newColor("2E2B2A"), 0);
		chart.setAreaFill(fill);
		return chart;
	}
	
	@SuppressWarnings("unused")
	private static double[] getPoints() {
		final int NUM_POINTS = 30;
		final double[] points = new double[NUM_POINTS];
		for (int i = 0; i < NUM_POINTS; i++) {
			points[i] = (Math.cos(30 * i * Math.PI / 180) * 10 + 50) * i / 20;
		}
		return points;
	}
}