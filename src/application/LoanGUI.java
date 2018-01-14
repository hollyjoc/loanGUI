package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LoanGUI extends Application 
{

    Button submit;
    Button exit;
    Button clear;
    Stage window;
    double loan;
    double lien;
    double CSO;
    double origination;
    double OPB;
    double interest;
    double payments;
    double payoff;
  
    
    public static void main(String[] args)
        {
            launch(args); // method inside Application case - When program is started, it calls Launch and goes into Application to start a JavaFx application
        }

    @Override
    public void start(Stage primaryStage) throws Exception //called by Application
        {
            //Set Window
            window = primaryStage;
            window.setTitle("Loan Breakdown");
            
            //If X is hit
            window.setOnCloseRequest(e -> closeProgram());
            
            //Set Grid layout
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10,10,10,10)); //padding around entire pane
            grid.setVgap(8);//spacing vertically between individual fields
            grid.setHgap(8);//spacing horizontally between individual fields
            
            //Loan label
            Label loanAmount = new Label("Loan Amount:");
            GridPane.setConstraints(loanAmount, 0, 0);
            
            //Loan Input
            TextField loanInput = new TextField();
            GridPane.setConstraints(loanInput, 1, 0);
            loanInput.getText();
            
            //Lien label
            Label lienAmount = new Label("Lien fee:");
            GridPane.setConstraints(lienAmount, 0, 1);
            
            //Lien Input
            TextField lienInput = new TextField();
            GridPane.setConstraints(lienInput, 1, 1);
            lienInput.getText();
            
            //Enter button
            submit = new Button("Enter");
            GridPane.setConstraints(submit, 0, 2);
            submit.setOnAction(e -> { 
               
            if(isDouble(loanInput, loanInput.getText())==true)
                {
                }
            else {
                closeProgram();
            }
            if(isDouble(lienInput, lienInput.getText())==true)
                {
                }
            else {
                closeProgram();
            }
            
             loan = Double.parseDouble(loanInput.getText());
             lien = Double.parseDouble(lienInput.getText());
            
             origination = originationFee(loan);
             CSO = creditService(loan+lien);
            
             OPB = (loan + lien + origination + CSO);
            
             interest = interestCalc(OPB);
            
             payments = paymentCalc(OPB);
            
             payoff = (payments + loan + lien);
           
             TextField originationFee = new TextField(String.valueOf(origination));
             GridPane.setConstraints(originationFee, 1, 4);
             
             TextField csoFee150 = new TextField(String.valueOf(CSO));
             GridPane.setConstraints(csoFee150, 1, 5);
             
             TextField csoFee30 = new TextField(String.valueOf(CSO/5));
             GridPane.setConstraints(csoFee30, 1, 6);
             
             TextField csoFee1 = new TextField(String.valueOf(CSO/150));
             GridPane.setConstraints(csoFee1, 1, 7);

             TextField interestFee = new TextField(String.valueOf(interest));
             GridPane.setConstraints(interestFee, 1, 8);
             
             TextField paymentFee = new TextField(String.valueOf(payments));
             GridPane.setConstraints(paymentFee, 1, 9);
             
             TextField payoffFee = new TextField(String.valueOf(payoff));
             GridPane.setConstraints(payoffFee, 1, 10);
             
             grid.getChildren().addAll(originationFee, csoFee150, csoFee30, csoFee1, interestFee, paymentFee, payoffFee);
            
            });
            
            //Exit Button
            exit = new Button("Exit");
            exit.setOnAction(e -> closeProgram());
            GridPane.setConstraints(exit, 1, 2);
            
            //Breakdown Label
            Label breakdown = new Label("Loan Breakdown:");
            GridPane.setConstraints(breakdown, 0, 3);
            
            //Origination Fee
            Label originationLabel = new Label("The origination fee:");
            GridPane.setConstraints(originationLabel, 0, 4);
            
            //CSO Fee - 150
            Label csoLabel150 = new Label("The CSO for 150 days:");
            GridPane.setConstraints(csoLabel150, 0, 5);
            
            //CSO Fee - 30
            Label csoLabel30 = new Label("The CSO for 30 days:");
            GridPane.setConstraints(csoLabel30, 0, 6);
            
            //CSO Fee - 1
            Label csoLabel1 = new Label("The CSO for 1 day:");
            GridPane.setConstraints(csoLabel1, 0, 7);

            //Interest
            Label interestLabel = new Label("The interest for 30 days:");
            GridPane.setConstraints(interestLabel, 0, 8);
          
            //Payment
            Label paymentsLabel = new Label("The 4 payments (and to refi on the 150th day):");
            GridPane.setConstraints(paymentsLabel, 0, 9);
            
            //Payoff
            Label payoffLabel = new Label("The payoff on the 150th day:");
            GridPane.setConstraints(payoffLabel, 0, 10);
            
            //Clear Button
            clear = new Button("Clear");
            clear.setOnAction(e -> {lienInput.clear(); loanInput.clear();});
            GridPane.setConstraints(clear, 2, 2);
            
            grid.getChildren().addAll(loanAmount, loanInput, lienAmount, lienInput, breakdown, originationLabel, csoLabel150, csoLabel30, csoLabel1, interestLabel, paymentsLabel, payoffLabel, submit, exit, clear);
            
            Scene scene = new Scene(grid, 500, 375);
            window.setScene(scene);
            window.show();
            
        }
    
    public void closeProgram()
    {
        System.exit(0);
    }
    
    public boolean isDouble(TextField x, String message)
    {
        try 
            {
                double loan = Double.parseDouble(x.getText());
                
                try {
                    if(loan <= 10000 && loan >=0) 
                       { 
                       }
                        else 
                            {
                               
                                return false;
                            }
                    }
                catch(NumberFormatException y) 
                    {
                    }
                return true;        
            }
        catch(NumberFormatException e) 
            {
           
            return false;
            }
    }
    
    public static double creditService(double w)
    {
        double theCSO = (w * .23) * 5;
        return theCSO;
    }
    
    public static double originationFee(double x)
        {
            double origFee = 0;
            double temp = 0;
            if (x <= 500)
                {
                    temp = x * .05;
                    if (temp >= 15)
                        {
                            origFee = 15;
                        }
                    else
                        {
                            origFee = temp;
                        }
                }
            else if (x > 500 && x <= 999)
                {
                    temp = x * .05;
                    if (temp >= 30)
                        {
                            origFee = 30;
                        }
                    else
                        {
                            origFee = temp;
                        }
                }
            else if (x >= 1000 && x <= 4999)
                {
                    temp = x * .05;
                    if (temp >= 100)
                        {
                            origFee = 100;
                        }
                    else
                        {
                            origFee = temp;
                        } 
                }
            else
                {
                   origFee = 250; 
                }
          
              
            return origFee;
            
        }
    
    public static double interestCalc(double y)
        {
            double interest = (((y * .2499 / 365) * 30));
            return interest;
            
        }
    
    public static double paymentCalc(double z)
    {
        double PMT = z * .1243;
        return PMT;
    }
}


    


