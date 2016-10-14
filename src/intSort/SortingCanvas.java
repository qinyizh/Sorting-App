package intSort;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Algorithms.Algorithms;
   
public class SortingCanvas extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private SortingPanel sortingPanel;
    private Thread thread;
    private Graphics graphics;
    private final Color BAR_COLOR = Color.BLACK;
    private int bars = 15;
    private int min = -40;
    private int max = 40;
    protected int[] barsArray;
    private int delay;
    private int[] array;

    public SortingCanvas(int width, int height, int delay, SortingPanel sortingPanel) {
	setPreferredSize(new Dimension(width, height));
	setBackground(Color.WHITE);
	createBarArray();
	this.sortingPanel = sortingPanel;
    }

    public synchronized void draw(int index1, int index2) {
	BufferStrategy buffer = getBufferStrategy();
	if (buffer == null) {
	    createBufferStrategy(3);
	    return;
	}
	graphics = buffer.getDrawGraphics();
	graphics.setColor(Color.WHITE);
	graphics.fillRect(0, 0, getWidth(), getHeight());
	graphics.setColor(BAR_COLOR);
	
	if(sortingPanel.verticalBarButton.isSelected()){
		graphics.drawLine(0, 200, 1000, 200);
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()/ barsArray.length) {
	    if (index1 == i) {
		graphics.setColor(Color.RED);
	    }
	    if (index2 == i) {
		graphics.setColor(Color.GREEN);
	    }
	    if (barsArray[i] > 0){
		    graphics.fillRect(k + 5, 200 - barsArray[i] , 600 / barsArray.length - 1, barsArray[i]);
		  }
		  if (barsArray[i] < 0){
			  graphics.fillRect(k + 5, 200, 600 / barsArray.length - 1, (-barsArray[i]));
		  }

	  graphics.setColor(BAR_COLOR);
	}
	}
	if(sortingPanel.boxButton.isSelected()){
		
		for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()/ barsArray.length) {
			if (index1 == i) {
				graphics.setColor(Color.RED);
			    }
			    if (index2 == i) {
				graphics.setColor(Color.GREEN);
			    }
			
					graphics.drawRect(k + 5, 100, 600 / barsArray.length - 10, 100 );	
				    graphics.drawString(barsArray[i]+"", k+10, 150);			    
		}	 
		}
	
	graphics.dispose();
	buffer.show();
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	g.setColor(BAR_COLOR);
	if(sortingPanel.verticalBarButton.isSelected()){
		g.drawLine(0, 200, 1000, 200);
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()/ barsArray.length) {
			    if (barsArray[i] > 0){
				    g.fillRect(k + 5, 200 - barsArray[i] , 600 / barsArray.length - 1, barsArray[i] );
				  }
				  if (barsArray[i] < 0){
					  g.fillRect(k + 5, 200, 600 / barsArray.length - 1, (-barsArray[i]));
				  }
	}	 
	}
	if(sortingPanel.boxButton.isSelected()){
	
		for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()/ barsArray.length) {
					g.drawRect(k + 5, 100, 600 / barsArray.length - 10, 100 );	
				    g.drawString(barsArray[i]+"", k+10, 150);    
		}	 
		}

	}
    
 
    @Override
    public void run() {
	try {
		new Algorithms().bubbleSort(barsArray, delay, this);
	   
	} catch (InterruptedException ie) {
	    ie.printStackTrace();
	}
	sortingPanel.runButton.setEnabled(true);

    }

    public void sort(int delay) {
    	this.delay = delay;
    	thread = new Thread(this);
    	thread.start();
        }
    
    public void setBars(int bars, int min, int max) {
	this.bars = bars;
	this.min = min;
	this.max = max;
    }

    private int getBars() {return bars;}
    private int getMin() {return min;}
    private int getMax() {return max;}
    
    public void populate() {
    	createBarArray();
    	repaint();
        }
    public void createBarArray() {
        	int min = getMin();
        	int max = getMax();
        	int bars = getBars();
    	barsArray = new int[bars];
    	for (int i = 0; i < barsArray.length; i++) {
    	    barsArray[i] = (int) (Math.random() * (max - min)) + min; 
    	    System.out.println(barsArray[i]);
    	}

        }

}

