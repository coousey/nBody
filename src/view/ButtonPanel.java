
package view;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import nbody.pkg.Listeners;

public class ButtonPanel extends JPanel {
    
    private final JButton randomButton = new JButton("rand");
    private final JButton clearButton = new JButton("clr");
    private final JButton startStopButton = new JButton("► / ||");
    private final JButton saveButton = new JButton("s");
    private final JButton loadButton = new JButton("l");
    private final JButton applyChangesButton = new JButton("apply");
    
    private final JLabel randNLabel = new JLabel("n");
    private final JTextField randNField = new JTextField("100");
    private final JLabel rLabel = new JLabel("r");
    private final JTextField rField = new JTextField("200");
    private final JLabel mLabel = new JLabel("m");
    private final JTextField mField = new JTextField("10");
    private final JLabel dtLabel = new JLabel("dt");
    private final JTextField dtField = new JTextField("0.1");
    private final JLabel GLabel = new JLabel("G");
    private final JTextField GField = new JTextField("1");
    private final JLabel cellDLabel = new JLabel("cellD");
    private final JTextField cellDField = new JTextField("20");
    
    private final JLabel pathLabel = new JLabel("paht");
    private final JCheckBox pathCheck = new JCheckBox();
       
    private final JLabel nonWithVectorsLabel = new JLabel("vec");
    private final JRadioButton nonWithVectorsRadioButton = new JRadioButton();
    private final JLabel nonLabel = new JLabel("non");
    private final JRadioButton nonRadioButton = new JRadioButton();
    private final JLabel colorLabel = new JLabel("col");
    private final JRadioButton colorRadioButton = new JRadioButton();
    private final JLabel vectorFieldLabel = new JLabel("vec");
    private final JRadioButton vectorRadioButton = new JRadioButton();
    private final JLabel mixLabel = new JLabel("mix");
    private final JRadioButton mixRadioButton = new JRadioButton();
    private final ButtonGroup fieldButtonGroup = new ButtonGroup();
    
    private final JLabel bruteLabel = new JLabel("b");
    private final JRadioButton bruteRadioButton = new JRadioButton();
    private final JLabel qTreeLabel = new JLabel("q");
    private final JRadioButton qTreeRadioButton = new JRadioButton();
    private final ButtonGroup calcButtonGroup = new ButtonGroup();
    
    private Listeners listeners;
    
    public ButtonPanel(Window window, Listeners listeners){
        
        // buttons
        randomButton.setPreferredSize(new Dimension(80,22));
        randomButton.addActionListener(listeners.getActionListener());
        add(randomButton);
        
        clearButton.setPreferredSize(new Dimension(80,22));
        clearButton.addActionListener(listeners.getActionListener());
        add(clearButton);
        
        startStopButton.setPreferredSize(new Dimension(80,22));
        startStopButton.addActionListener(listeners.getActionListener());
        add(startStopButton);
        
        saveButton.setPreferredSize(new Dimension(50,22));
        saveButton.addActionListener(listeners.getActionListener());
        add(saveButton);
        
        loadButton.setPreferredSize(new Dimension(50,22));
        loadButton.addActionListener(listeners.getActionListener());
        add(loadButton);
        
        // fields and labels
        add(randNLabel);
        randNField.setPreferredSize(new Dimension(60,22));
        add(randNField);
        
        add(rLabel);
        rField.setPreferredSize(new Dimension(60,22));
        add(rField);
        
        add(mLabel);
        mField.setPreferredSize(new Dimension(60,22));
        add(mField);
        
        applyChangesButton.setPreferredSize(new Dimension(80,22));
        applyChangesButton.addActionListener(listeners.getActionListener());
        add(applyChangesButton);
        
        add(dtLabel);
        dtField.setPreferredSize(new Dimension(60,22));
        add(dtField);
        
        add(GLabel);
        GField.setPreferredSize(new Dimension(60,22));
        add(GField);
        
        add(cellDLabel);
        cellDField.setPreferredSize(new Dimension(60,22));
        add(cellDField);
           
        // checks and labels
        pathCheck.setSelected(false);
        pathCheck.addActionListener(listeners.getActionListener());
        add(pathLabel);
        add(pathCheck);
        
        // field radio buttons
        nonWithVectorsRadioButton.addActionListener(listeners.getActionListener());
        add(nonWithVectorsLabel);       
        add(nonWithVectorsRadioButton); 
        
        nonRadioButton.setSelected(true);
        nonRadioButton.addActionListener(listeners.getActionListener());
        add(nonLabel);       
        add(nonRadioButton); 
        
        colorRadioButton.addActionListener(listeners.getActionListener());
        add(colorLabel);
        add(colorRadioButton); 
        
        vectorRadioButton.addActionListener(listeners.getActionListener());
        add(vectorFieldLabel);
        add(vectorRadioButton);
        
        mixRadioButton.addActionListener(listeners.getActionListener());
        add(mixLabel);
        add(mixRadioButton);
        
        fieldButtonGroup.add(nonWithVectorsRadioButton);
        fieldButtonGroup.add(nonRadioButton);
        fieldButtonGroup.add(colorRadioButton);
        fieldButtonGroup.add(vectorRadioButton);
        fieldButtonGroup.add(mixRadioButton);
        
        // calc radio buttons
        bruteRadioButton.addActionListener(listeners.getActionListener());
        add(bruteLabel);
        add(bruteRadioButton);
        
        qTreeRadioButton.setSelected(true);
        qTreeRadioButton.addActionListener(listeners.getActionListener());
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
