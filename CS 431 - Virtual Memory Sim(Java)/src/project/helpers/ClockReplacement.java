package project.helpers;

public class ClockReplacement {
	
	public Node start = null;
	public Node end = null;
	public int size = 0;
	
	class Node {
		private int data;
		private Node link;
		
	    public Node(int physMem) {
	    		this.data = physMem;
		}

		public void setLink(Node n)
	    {
	        link = n;
	    }    
	}
	
	public void insertAtEnd(int val) {
        Node n = new Node(val);    
        n.setLink(start);
        
        if (start == null) {            
            start = n;
            n.setLink(start);
            end = start;            
        } else {
            end.setLink(n);
            end = n;            
        }
        size++;
    }
}
