package view;

import Application.Main;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.List;



public class rootcontroller {
	

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

    }
    
    //search models in math package
        
    

    
    // auto generate labels, sliders, text field based on the data entry.
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        
        main.getPrimaryStage().setResizable(false);
                             
    }

}

