package intSort;

import java.applet.Applet;
import java.awt.GridLayout;



public class RunApplet extends Applet{
	public void init(){
		resize(1000,500);
		setLayout(new GridLayout(1,1));
		SortingPanel myPanel = new SortingPanel();
		this.add(myPanel);

	}
	

}
