package view;

import Application.Main;
import Stacks.ResizableStack;
import Stacks.Stackviewer;
import Stacks.Stopwatch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class rootcontroller {
	
	 @FXML
	 public LineChart<String, Integer> linechart;

	 @FXML
	 private CategoryAxis xAxis;
	    
	 @FXML
	 private NumberAxis yAxis;
	 
	 @FXML
	 private ComboBox<String> methodbox;
	 
	 @FXML
	 TableView<ObservableList> stattable;
		
	 private TableColumn [] tableColumns;
	 private List<String> columns;

	    
	 public  XYChart.Series series;
	 private ObservableList<String> methodoptions = FXCollections.observableArrayList("viewtopitem", "viewbottomitem", "viewnthitem", "removetopitem", "removebottomitem", "removenthitem");
	 private String selectedmethod = "viewtopitem";

    //Local attributes
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    
    public rootcontroller() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws IOException 
     */
    @FXML
    private void initialize() throws IOException {
    	//populated animal table
    
    	//loading configuration
    	xAxis.setLabel("size in N");
        yAxis.setLabel("time in miliseconds");
        xAxis.setAnimated(false);
        spawnmethodbox();

    }
    
    //search models in math package
    public void spawndata()
    {
    	cleardata();
    	ArrayList data = null;
    	try {
			data = generatedata(selectedmethod);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	series = new Series<String, Number>();
        series.setName(selectedmethod);
     	for (int i = 0; i < 10; i++)
     	{
     		int size = (int) Math.pow(2, i);
     		String n = String.valueOf(10000*size);
     		series.getData().add(new XYChart.Data(n, data.get(i)));
     		//System.out.println(anidata.get(i));
     	}         	
     	linechart.getData().add(series);
     	
     	
     	//stat table
    	columns = new ArrayList<String>();
    	columns.add("Size N");
    	columns.add("Avg Time");
     	
    	int columnIndex = 0;
		tableColumns = new TableColumn[columns.size()];  
		for(int i = 0; i<columns.size(); i++) 
		{
			final int j = i;
			TableColumn name = new TableColumn(columns.get(i));
			name.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

			tableColumns[columnIndex++] = name;
		}
    	stattable.getColumns().addAll(tableColumns);
    	
    	//spawn data
     	
     	
     	ObservableList<ObservableList> timedata = FXCollections.observableArrayList();
     	
     	for (int i = 0; i<10; i++)
     	{
     		List<String> rowdata = new ArrayList();
     		int size = (int) Math.pow(2, i) * 2500;
     		rowdata.add(String.valueOf(size));
     		rowdata.add(String.valueOf(data.get(i)));
     		
     		
     		
     		ObservableList<String> row = FXCollections.observableArrayList();
     		for (int j = 0; j<rowdata.size(); j++)
     		{
     			row.add(rowdata.get(j));
     		}
     		timedata.add(row);
     	}    	
    	stattable.setItems(timedata);
    	stattable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);     	
    }
           
    public void cleardata()
    {
    	if (series != null)
    	{
    		series.getData().clear();
    		linechart.getData().clear();
    	} 
    	stattable.getColumns().clear();
    } 
    
    private ArrayList generatedata(String methodname) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
    	ArrayList data = new ArrayList();
    	Method method = null;
    	String type;
    	if (methodname.equalsIgnoreCase("viewnthitem") || methodname.equalsIgnoreCase("removenthitem"))
    	{
    		Class[] parameterTypes = new Class[1];
            parameterTypes[0] = int.class;
            method = Stackviewer.class.getMethod(methodname, parameterTypes);
            type = "type 2";
    	}
    	else
    	{	
    		method = Stackviewer.class.getMethod(methodname, null);
    		type = "type 1";
    	}
    	
    	for (int i = 0; i<10; i++)
		{	
    		double totaltime = 0.0;
    		for (int j = 0; j<100; j++)
    		{
				int size = (int) Math.pow(2, i);
				ResizableStack<Integer> rs = Stackviewer.randomstack(2500*size);
				Random random = new Random();
				int randomindex = random.nextInt(rs.size());
				Stackviewer sv = new Stackviewer(rs);
				Stopwatch timer = new Stopwatch();
				if (type.equalsIgnoreCase("type 1"))
				{
					method.invoke(sv, null);
				}
				else if (type.equalsIgnoreCase("type 2"))
				{
					method.invoke(sv, randomindex);
				}
				double time = timer.elapsedTime();
				totaltime = (time+totaltime);
    		}
    		data.add(totaltime/10);
		}
    	return data;
    }
    
    private void spawnmethodbox()
    {
    	methodbox.setItems(methodoptions);
    	methodbox.setVisibleRowCount(5);
    	methodbox.setValue("viewtopitem");
    	methodbox.setOnAction(methodevent);
    }
    
    private EventHandler<ActionEvent> methodevent = new EventHandler<ActionEvent>()
	{
		public void handle(ActionEvent e)
		{
			selectedmethod = String.valueOf(methodbox.getValue());
		}
	};
    
    // auto generate labels, sliders, text field based on the data entry.
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        
        main.getPrimaryStage().setResizable(false);
                             
    }

}

