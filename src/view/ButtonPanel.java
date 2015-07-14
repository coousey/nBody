
package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    
    private final JLabel pathLabel = new JLabel("draw paht");
    private final JCheckBox pathCheck = new JCheckBox();
       
    private final JLabel nonWithVectorsLabel = new JLabel("vectors");
    private final JRadioButton nonWithVectorsRadioButton = new JRadioButton();
    
    private final JLabel nonLabel = new JLabel("no field");
    private final JRadioButton nonRadioButton = new JRadioButton();
    
    private final JLabel colorLabel = new JLabel("color field");
    private final JRadioButton colorRadioButton = new JRadioButton();
    
    private final JLabel vectorFieldLabel = new JLabel("vector field");
    private final JRadioButton vectorRadioButton = new JRadioButton();
    
    private final JLabel mixLabel = new JLabel("mix field");
    private final JRadioButton mixRadioButton = new JRadioButton();
    
    private final ButtonGroup fieldButtonGroup = new ButtonGroup();

    private final JLabel bruteLabel = new JLabel("brute force");
    private final JRadioButton bruteRadioButton = new JRadioButton();

    private final JLabel qTreeLabel = new JLabel("barnes-hut");
    private final JRadioButton qTreeRadioButton = new JRadioButton();
    
    private final ButtonGroup calcButtonGroup = new ButtonGroup();
    
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
           
        pathCheck.setSelected(false);
        pathCheck.addActionListener(listeners.getActionListener());
        pathLabel.setHorizontalAlignment(JLabel.CENTER);
        add(pathLabel);
        add(pathCheck);
        
        add(new JPanel());
        add(new JPanel());

        nonWithVectorsRadioButton.addActionListener(listeners.getActionListener());
        nonWithVectorsLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nonWithVectorsLabel);       
        add(nonWithVectorsRadioButton); 
        
        nonRadioButton.setSelected(true);
        nonRadioButton.addActionListener(listeners.getActionListener());
        nonLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nonLabel);       
        add(nonRadioButton); 
        
        colorRadioButton.addActionListener(listeners.getActionListener());
        colorLabel.setHorizontalAlignment(JLabel.CENTER);
        add(colorLabel);
        add(colorRadioButton); 
        
        vectorRadioButton.addActionListener(listeners.getActionListener());
        vectorFieldLabel.setHorizontalAlignment(JLabel.CENTER);
        add(vectorFieldLabel);
        add(vectorRadioButton);
        
        mixRadioButton.addActionListener(listeners.getActionListener());
        mixLabel.setHorizontalAlignment(JLabel.CENTER);
        add(mixLabel);
        add(mixRadioButton);
        
        add(new JPanel());
        add(new JPanel());
        
        fieldButtonGroup.add(nonWithVectorsRadioButton);
        fieldButtonGroup.add(nonRadioButton);
        fieldButtonGroup.add(colorRadioButton);
        fieldButtonGroup.add(vectorRadioButton);
        fieldButtonGroup.add(mixRadioButton);

        bruteRadioButton.addActionListener(listeners.getActionListener());
        bruteLabel.setHorizontalAlignment(JLabel.CENTER);
        add(bruteLabel);
        add(bruteRadioButton);
        
        qTreeRadioButton.setSelected(true);
        qTreeRadioButton.addActionListener(listeners.getActionListener());
        qTreeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(qTreeLabel);
        add(qTreeRadioButton);
        
        calcButtonGroup.add(bruteRadioButton);
        calcButtonGroup.add(qTreeRadioButton);
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
    
    public boolean getPathCheck(){ return pathCheck.isSelected(); }
    
    public JRadioButton getNonWithVectorsRadioButton(){ return nonWithVectorsRadioButton; }
    public JRadioButton getNonRadioButton(){ return nonRadioButton; }
    public JRadioButton getColorRadioButton(){ return colorRadioButton; }
    public JRadioButton getVectorRadioButton(){ return vectorRadioButton; }
    public JRadioButton getMixRadioButton(){ return mixRadioButton; }
    public JRadioButton getBruteRadioButton(){ return bruteRadioButton; }
    public JRadioButton getQTreeRadioButton(){ return qTreeRadioButton; }
}
