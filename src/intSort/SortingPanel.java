package intSort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Algorithms.Algorithms;
import intSort.SortingCanvas;






public class SortingPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

    private JRadioButton slowButton;
    private JRadioButton mediumButton;
    private JRadioButton fastButton;
    protected JRadioButton boxButton;
    protected JRadioButton verticalBarButton;
    protected JButton runButton;
    protected JButton stopButton;
    protected JButton nextStepButton;
    protected JButton populateButton;
    private SortingCanvas sortingCanvas;
    private JTextField minNumber;
    private JTextField maxNumber;
    private JTextField numberOfArray;
    public static JTextField compareCount;
    public static JTextField swapCount;
    private int bars;
    private int min;
    private int max;
    public static boolean isStop = false;
    

    public SortingPanel() {
	// Sets layout and background color of main panel.
//	Color background = new Color(0xFFFFFF);
	setLayout(new BorderLayout());
//	this.setBackground(background);

	// Creates southPanel components
	JPanel northPanel = new JPanel();
	JLabel minLabel = new JLabel( "Minimum number: ");
	JLabel maxLabel = new JLabel ( "Maximum number: ");
	JLabel numberLabel = new JLabel( "Number of array: ");
	bars = 15;
	min = -40;
	max = 40;
	minNumber = new JTextField("" + min, 2);
	maxNumber = new JTextField("" + max, 2);
	numberOfArray = new JTextField("" + bars, 2);
	populateButton = new JButton(" Populate ");

	northPanel.setBorder(BorderFactory.createRaisedBevelBorder());
//	northPanel.setBackground(background);
	northPanel.add(minLabel);
	northPanel.add(minNumber);
	northPanel.add(maxLabel);
	northPanel.add(maxNumber);
	northPanel.add(numberLabel);
	northPanel.add(numberOfArray);
	northPanel.add(populateButton);



	// Creates centerPanel components
	JPanel centerPanel = new JPanel();

	JPanel showPanel = new JPanel(new GridLayout(1, 2));
	sortingCanvas = new SortingCanvas(600, 400, 80, this);
	centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
//	centerPanel.setBackground(background);
	centerPanel.add(sortingCanvas);
	
	JLabel compareLabel = new JLabel("Total Compare:");
	JLabel swapLabel = new JLabel("Total Swap:");
	swapCount = new JTextField(4);
	compareCount = new JTextField(4);
	swapCount.setEditable(false);
	compareCount.setEditable(false);
	
	showPanel.add(compareLabel);
	showPanel.add(compareCount);
	showPanel.add(swapLabel);
	showPanel.add(swapCount);

	centerPanel.add(showPanel);
//	centerPanel.add(compareCount);
//	centerPanel.add(swapLabel);
//	centerPanel.add(swapCount);

	// Creates westPanel components
	JPanel westPanel = new JPanel(new GridLayout(5, 1));
	JPanel speedPanel = new JPanel(new GridLayout(1, 3));
	JPanel typePanel = new JPanel(new GridLayout(1, 2));
	JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

	// Creates westPanel components

	JLabel speedLabel = new JLabel("SORTING SPEEDS:");
	JLabel typeLabel = new JLabel("VISUAL TYPE:");
	ButtonGroup speedButtonGroup = new ButtonGroup();
	ButtonGroup showTypeButtonGroup = new ButtonGroup();
	
	
	slowButton = new JRadioButton("Slow");
	mediumButton = new JRadioButton("Medium");
	fastButton = new JRadioButton("Fast", true);

	boxButton = new JRadioButton("Box");
	verticalBarButton = new JRadioButton("Vertical Bar", true);
	
	runButton = new JButton("Run");
	stopButton = new JButton("Stop");
	nextStepButton = new JButton("Next Step");
	

	speedButtonGroup.add(slowButton);
	speedButtonGroup.add(mediumButton);
	speedButtonGroup.add(fastButton);
	showTypeButtonGroup.add(boxButton);
	showTypeButtonGroup.add(verticalBarButton);
	// Adds buttons to appropriate panel
	

	speedPanel.add(slowButton);
	speedPanel.add(mediumButton);
	speedPanel.add(fastButton);

	typePanel.add(boxButton);
	typePanel.add(verticalBarButton);
    buttonPanel.add(runButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(nextStepButton);
   

	// Adds components to westPanel
	westPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	westPanel.add(speedLabel);
	westPanel.add(speedPanel);
	westPanel.add(typeLabel);
	westPanel.add(typePanel);
	westPanel.add(buttonPanel);


	
	


//	 Register listeners and add border layout panels to main panel

	runButton.addActionListener(this);
	stopButton.addActionListener(this);
	nextStepButton.addActionListener(this);
	populateButton.addActionListener(this);

	add(westPanel, BorderLayout.WEST);
	add(centerPanel, BorderLayout.CENTER);
	add(northPanel, BorderLayout.NORTH);
    }
    

    
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == runButton) {
	    setBars();
	    isStop = false;
	    sortingCanvas.sort(getDelaySpeed());
	    runButton.setEnabled(false);
	    nextStepButton.setEnabled(false);
	    stopButton.setEnabled(true);
	    populateButton.setEnabled(false);
	}
	
	if (e.getSource() == stopButton) {
		runButton.setEnabled(true);
		nextStepButton.setEnabled(true);
		stopButton.setEnabled(false);
		isStop = true;
	}
	if (e.getSource() == nextStepButton) {

		Algorithms s = new Algorithms(sortingCanvas.barsArray,sortingCanvas);

	}
	if (e.getSource() == populateButton) {
		setBars();
	    sortingCanvas.populate();
	    compareCount.setText(0+"");
	    swapCount.setText(0+"");
	}

    }
    
    private void setBars() {
    	try {
    	    bars = Integer.parseInt(numberOfArray.getText());
    	    min = Integer.parseInt(minNumber.getText());
    	    max = Integer.parseInt(maxNumber.getText());
    	} catch (NumberFormatException nfe) {
    	    JOptionPane.showMessageDialog(SortingPanel.this,
    		    "Please enter a number between 1 and 125",
    		    "Number Format Error", JOptionPane.ERROR_MESSAGE);
    	    return;
    	}
    	if (bars < 2 || bars > 125) {
    	    JOptionPane.showMessageDialog(SortingPanel.this,
    		    "Please enter a number between 1 and 125",
    		    "Number Format Error", JOptionPane.ERROR_MESSAGE);
    	    return;
    	}
    	if (min >= max || (max-min+1) < bars) {
    	    JOptionPane.showMessageDialog(SortingPanel.this,
    		    "Please proper mininum number and maximum number",
    		    "Number Format Error", JOptionPane.ERROR_MESSAGE);
    	    return;
    	}
    	sortingCanvas.setBars(bars,min,max);

        }
    
    private int getDelaySpeed() {
	if (slowButton.isSelected()) {
	    return 500;
	}
	if (mediumButton.isSelected()) {
	    return 150;
	}
	if (fastButton.isSelected()) {
	    return 25;
	}
	return -1;
    }
    


}


