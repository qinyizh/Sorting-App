package Algorithms;




import intSort.SortingCanvas;
import intSort.SortingPanel;


public class Algorithms {

	int compare = 0;
	int swap = 0;
	int[]array;
    
	public Algorithms() {}
	public Algorithms(int[] array,SortingCanvas canvas){
		this.array= array;
		for (int i = 0; i < array.length - 1; i++) {
		    for (int k = array.length - 2; k >= i; k--) {
			if (Compare(array, k, k+1)>0)
			    swap(array, k, k + 1);		
			canvas.draw(k, k - 1);
			break;
		    }
		}
	}
    public synchronized void bubbleSort(int[] array, int delay,SortingCanvas canvas) throws InterruptedException {
	canvas.draw(-1, -1);
	for (int i = 0; i < array.length - 1; i++) {
		if(SortingPanel.isStop==true) break;
	    for (int k = array.length - 2; k >= i; k--) {
	    	if(SortingPanel.isStop==true) break;
		if (Compare(array, k, k+1)>0)
		    swap(array, k, k + 1);
		Thread.sleep(delay);
		canvas.draw(k, k - 1);
	    }
	}
	canvas.draw(-1, -1);
    }

    
	public int Compare(int[] array,int i, int j)throws ArrayIndexOutOfBoundsException {
		compare ++;
		SortingPanel.compareCount.setText(compare+"");
		int compare  = array[i]-(array[j]);
		if (compare < 0)
			return -1;
		else if (compare == 0)
			return 0;
		else return 1;
	}
	
    private void swap(int[] array, int index1, int index2) throws ArrayIndexOutOfBoundsException{
    	swap ++;
    	SortingPanel.swapCount.setText(swap+"");
	int temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
    }
}
