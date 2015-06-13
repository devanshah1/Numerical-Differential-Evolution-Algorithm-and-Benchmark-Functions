import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This class extends the ApplicationFrame class from the JFreeChart library to give the ability to
 * store the data points using the XYSeries function which is later accessed to retrieve the X and 
 * the Y values that are going to be graphed. The graph is then displayed in a JFrame.
 * @author Devan Shah 100428864, Parth Patel 100392782, Ravikumar Patel 100423830
 * 
 */
public class Graph extends ApplicationFrame {
	
	// The serialVersionUID is here because Graph is extending a class that is in an library 
	private static final long serialVersionUID = 3591354975820502576L;
	// Variable/Object Definitions
	private XYSeries series;
	private XYSeriesCollection dataset = new XYSeriesCollection();
	static String yAxisLabel;

	/**
	 * This constructor is used to set the preferences and generate a dataset that consists of the x and 
	 * y points that will be plotted on the graph. This constructor is also responsible for selecting the
	 * correct settings depending on the selected function. This includes setting the title and the ranges of the 
	 * axis depending on when the optimal solution is reached. 
	 * 
	 * @param componentTitle is a sting that holds the name of the component that the graph will display in
	 * @param benchmarkSelection is the integer value that the user inputs, which is used to select the function
	 * @param type is a string specifying the type of graph to display either log of linear 
	 */
	public Graph(String componentTitle, int benchmarkSelection, String type) {
		super(componentTitle);
		
		this.series = new XYSeries("");
		
		// Storing all the values that are in the series object into dataset 
		dataset.addSeries(series);
		// Making sure that the Y label is correct depending on the type of graph
		if (type.equals("linear")){
			yAxisLabel = "Best Fitness Value So Far (Linear)";
		}
		else if (type.equals("log")){
			yAxisLabel = "Best Fitness Value So Far (Logarithmic Scale)";
		}
		// Setting the preference of the graph layout and range depending on the function the user selects
		if (benchmarkSelection == 1) {
			JFreeChart chart = ChartFactory.createXYLineChart(
					"1st De Jong \n Best Fitness Value So Far Vs. Number of Function Calls", // Title of graph	
					"Number of Function Calls", // X-axis Label
					yAxisLabel, // Y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
			
			// Setting the Range of the X-axis depending on log or linear so that the graphs can be seen clearly
			if (type.equals("linear")){
				chart.getXYPlot().getDomainAxis().setRange(0, 120);
				
			}
			else if (type.equals("log")){
				chart.getXYPlot().getDomainAxis().setRange(0, 30000);
				
			}
			
			ChartPanel panel = new ChartPanel(chart);
			
			// Setting the height and width of the panel
			panel.setMaximumDrawHeight(700);
			panel.setMaximumDrawWidth(700);
			
			// Show the graph screen randomly on the screen
			RefineryUtilities.positionFrameRandomly(this); 
			
			this.setContentPane(panel);
		} 
		else if (benchmarkSelection == 2) {
			JFreeChart chart = ChartFactory.createXYLineChart(
					"Axis Parallel Hyper-Ellipsoid \n Best Fitness Value So Far Vs. Number of Function Calls", // Title of graph	
					"Number of Function Calls", // X-axis Label
					yAxisLabel, // Y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
			
			if (type.equals("linear")){
				chart.getXYPlot().getDomainAxis().setRange(0, 120);
			}
			else if (type.equals("log")){
				chart.getXYPlot().getDomainAxis().setRange(0, 30000);
			}

			ChartPanel panel = new ChartPanel(chart);

			panel.setMaximumDrawHeight(700);
			panel.setMaximumDrawWidth(700);
			
			RefineryUtilities.positionFrameRandomly(this); 
			
			this.setContentPane(panel);
		} 
		else if (benchmarkSelection == 3) {
			JFreeChart chart = ChartFactory.createXYLineChart(
					"Schwefel's Problem 1.2 \n Best Fitness Value So Far Vs. Number of Function Calls", // Title of graph	
					"Number of Function Calls", // X-axis Label
					yAxisLabel, // Y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
			
			if (type.equals("linear")){
				chart.getXYPlot().getDomainAxis().setRange(0, 120);
			}
			else if (type.equals("log")){
				chart.getXYPlot().getDomainAxis().setRange(0, 30000);
			}

			ChartPanel panel = new ChartPanel(chart);

			panel.setMaximumDrawHeight(700);
			panel.setMaximumDrawWidth(700);
			
			RefineryUtilities.positionFrameRandomly(this); 
			
			this.setContentPane(panel);
		}
		else if (benchmarkSelection == 4) {
			JFreeChart chart = ChartFactory.createXYLineChart(
					"Rosenbrock's Valley \n Best Fitness Value So Far Vs. Number of Function Calls", // Title of graph	
					"Number of Function Calls", // X-axis Label
					yAxisLabel, // Y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
			
			if (type.equals("linear")){
				chart.getXYPlot().getDomainAxis().setRange(0, 20000);
			}
			else if (type.equals("log")){
				chart.getXYPlot().getDomainAxis().setRange(0, 30000);
			}

			ChartPanel panel = new ChartPanel(chart);

			panel.setMaximumDrawHeight(700);
			panel.setMaximumDrawWidth(700);
			
			RefineryUtilities.positionFrameRandomly(this); 
			
			this.setContentPane(panel);
		} 
		else if (benchmarkSelection == 5) {
			JFreeChart chart = ChartFactory.createXYLineChart(
					"Rastrigin's Function \n Best Fitness Value So Far Vs. Number of Function Calls", // Title of graph	
					"Number of Function Calls", // X-axis Label
					yAxisLabel, // Y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
			
			if (type.equals("linear")){
				chart.getXYPlot().getDomainAxis().setRange(0, 1000);
			}
			else if (type.equals("log")){
				chart.getXYPlot().getDomainAxis().setRange(0, 30000);
			}

			ChartPanel panel = new ChartPanel(chart);

			panel.setMaximumDrawHeight(700);
			panel.setMaximumDrawWidth(700);
			
			RefineryUtilities.positionFrameRandomly(this); 
			
			this.setContentPane(panel);
		}
	}
	
	/**
	 * This method is responsible for storing the X and the Y values into the series object to be used
	 * later on to graph. This method takes the fitnessHolder and constructs a linear graph.
	 * Has a complexity of O(n)
	 * 
	 * @param fitnessHolder is a List that holds all the fitness values of 30000 generations
	 */
	public void show(List<Double> fitnessHolder) {  
		for (int y = 0; y < 30000; y++) {      // complexity of O(n)
			series.add(y, fitnessHolder.get(y));
		}
	}
	
	/**
	 * This method is responsible for storing the X and Y values into the series object to be used
	 * later on to graph. This methods takes the fitnessHolder and constructs a log graph.
	 * Has a complexity of O(n)
	 * @param fitnessHolder is a List that holds all the fitness values of 30000 generations
	 */
	public void showLog(List<Double> fitnessHolder){
		for (int y = 0; y < 30000; y++) {         // complexity of O(n)
			if (fitnessHolder.get(y) == 0 ){
				fitnessHolder.set(y, fitnessHolder.get(y-1));
			}
			series.add(y, Math.log(fitnessHolder.get(y)));
		}
	}
}
