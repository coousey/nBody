
package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import nbody.pkg.Listeners;

public class ButtonPanel extends JPanel {
    
    private JButton startStopButton = new JButton("► / ||");
    private JButton clearButton = new JButton("clear");
    private JButton saveButton = new JButton("save");
    private JButton loadButton = new JButton("load");
    private JButton randomButton = new JButton("random");
    private JButton applyChangesButton = new JButton("apply");

    private JLabel randNLabel = new JLabel("n");
    private JTextField randNField = new JTextField("100");

    private JLabel rLabel = new JLabel("r");
    private JTextField rField = new JTextField("200");

    private JLabel mLabel = new JLabel("m");
    private JTextField mField = new JTextField("10");

    private JLabel dtLabel = new JLabel("dt");
    private JTextField dtField = new JTextField("0.1");

    private JLabel GLabel = new JLabel("G");
    private JTextField GField = new JTextField("1");

    private JLabel cellDLabel = new JLabel("cellD");
    private JTextField cellDField = new JTextField("20");
    
    private JComboBox calcComboBox = new JComboBox();
    private JComboBox drawerComboBox = new JComboBox();
    
    private Listeners listeners;
    
    public ButtonPanel(Window window, Listeners listeners){

        setBorder(new EmptyBorder(10, 10, 10, 10) );
        setLayout(new GridLayout(24,2,5,5));      
        
        startStopButton.addActionListener(listeners.getActionListener());
        add(startStopButton);
        
        clearButton.addActionListener(listeners.getActionListener());
        add(clearButton);
        
        saveButton.addActionListener(listeners.getActionListener());
        add(saveButton);
         
        loadButton.addActionListener(listeners.getActionListener());
        add(loadButton);
        
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
      
        randomButton.addActionListener(listeners.getActionListener());
        add(randomButton); 
        
        randNLabel.setHorizontalAlignment(JLabel.CENTER);
        add(randNLabel);
        add(randNField);
        
        rLabel.setHorizontalAlignment(JLabel.CENTER);
        add(rLabel);
        add(rField);
        
        mLabel.setHorizontalAlignment(JLabel.CENTER);
        add(mLabel);
        add(mField);
        
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        
        applyChangesButton.addActionListener(listeners.getActionListener());
        add(applyChangesButton);
        
        dtLabel.setHorizontalAlignment(JLabel.CENTER);
        add(dtLabel);
        add(dtField);
        
        GLabel.setHorizontalAlignment(JLabel.CENTER);
        add(GLabel);
        add(GField);
        
        cellDLabel.setHorizontalAlignment(JLabel.CENTER);
        add(cellDLabel);
        add(cellDField);
        
        add(new JPanel());
        add(new JPanel());
        
        drawerComboBox.addItem("nothing");
        drawerComboBox.addItem("path");
        drawerComboBox.addItem("vectors");
        drawerComboBox.addItem("vector field");
        drawerComboBox.addItem("color field");
        drawerComboBox.addItem("mix field");
        drawerComboBox.addActionListener(listeners.getActionListener());
        add(drawerComboBox);

        calcComboBox.addItem("barnes-hut");
        calcComboBox.addItem("brute force");
        calcComboBox.addActionListener(listeners.getActionListener());
        add(calcComboBox);
    }
    
    public int getRandN(){
        
        int randN;
        try {
            randN = Integer.parseInt(randNField.getText());
        }
        catch(NumberFormatException e){
            randN = 0;
            randNField.setText("0");
        }       
        if(randN < 0){
            randN = 0;
            randNField.setText("0");
        }
        return randN;
    }
    
    public int getR(){
        
        int r;
        try {
            r = Integer.parseInt(rField.getText());
        }
        catch(NumberFormatException e){
            r = 0;
            rField.setText("0");
        }       
        if(r < 0){
            r = 0;
            rField.setText("0");
        }
        return r;
    }
    
    public double getM(){
        
        double m;
        try {
            m = Double.parseDouble(mField.getText());
        }
        catch(NumberFormatException e){
            m = 0;
            mField.setText("0");
        }       
        if(m < 0){
            m = 0;
            mField.setText("0");
        }
        return m;
    }
    
    public double getDt(){
        
        double dt;
        try {
            dt = Double.parseDouble(dtField.getText());
        }
        catch(NumberFormatException e){
            dt = 0;
            dtField.setText("0");
        }       
        if(dt < 0){
            dt = 0;
            dtField.setText("0");
        }
        return dt;
    }
    
    public double getG(){
        
        double G;
        try {
            G = Double.parseDouble(GField.getText());
        }
        catch(NumberFormatException e){
            G = 0;
            GField.setText("1");
        }       
        if(G < 0){
            G = 0;
            GField.setText("1");
        }
        return G;
    }
    
    public int getCellD(){
        
        int cellD;
        try {
            cellD = Integer.parseInt(cellDField.getText());
        }
        catch(NumberFormatException e){
            cellD = 0;
            cellDField.setText("0");
        }       
        if(cellD < 0){
            cellD = 0;
            cellDField.setText("0");
        }
        return cellD;
    }
    
    public JButton getRandomButton(){ return randomButton; }
    public JButton getApplyChangesButton(){ return applyChangesButton; }
    public JButton getClearButton(){ return clearButton; }
    public JButton getStartStopButton(){ return startStopButton; }
    public JButton getSaveButton(){ return saveButton; }
    public JButton getLoadButton(){ return loadButton; }
    
    public JComboBox getDrawerComboBox(){ return drawerComboBox; }
    public JComboBox getCalcComboBox(){ return calcComboBox; }  
}
