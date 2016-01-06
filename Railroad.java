/* Name: Sayd Mateen */


import java.util.*;

public class Railroad{
	public static void main(String[] args) {
		NodeList routingTable = new NodeList();

		Node sac = new Node("Sacramento");
		Node sf = new Node("SFO");
		Node det = new Node("Detroit");
		Node atl = new Node("Atlanta");
		Node la = new Node("LA");
		Node chi = new Node("Chicago");
		Node nj = new Node("NJ");
		Node mia = new Node("Miami");
		Node veg = new Node("Vegas");
		Node bos = new Node("Boston");
		Node dc = new Node("DC");
		Node dal = new Node("Dallas");
		Node ny = new Node("NY"); 

		sac.addAdjacent("SFO", 50);
		sac.addAdjacent("Atlanta", 35);
		sf.addAdjacent("LA", 60);
		sf.addAdjacent("Chicago", 35);
		sf.addAdjacent("Detroit", 70);
		det.addAdjacent("Chicago", 10);
		atl.addAdjacent("Detroit", 20);
		atl.addAdjacent("Chicago", 60);
		atl.addAdjacent("NJ", 45);
		la.addAdjacent("Vegas", 30);
		chi.addAdjacent("Vegas", 5);
		chi.addAdjacent("Miami", 40);
		nj.addAdjacent("Miami", 30);
		nj.addAdjacent("Boston", 25);
		mia.addAdjacent("DC", 50);
		veg.addAdjacent("Dallas", 20);
		bos.addAdjacent("NY", 20);
		dc.addAdjacent("NY", 15);
		dal.addAdjacent("NY", 10);

		routingTable.addNode(sac);
		routingTable.addNode(sf);
		routingTable.addNode(det);
		routingTable.addNode(atl);
		routingTable.addNode(la);
		routingTable.addNode(chi);
		routingTable.addNode(nj);
		routingTable.addNode(mia);
		routingTable.addNode(veg);
		routingTable.addNode(bos);
		routingTable.addNode(dc);
		routingTable.addNode(dal);
		routingTable.addNode(ny);

		String source = "Sacramento";
		String v = source;
		routingTable.setDv(v, 0);

		while(routingTable.isFalseStatus()){
			routingTable.updateStatus(v);
			int vDist = routingTable.getDv(v);
			int count = routingTable.getAdjacentCount(v);

			for(int i = 1; i <= count; i++ ){
				String w = routingTable.searchAdjacent(v, i);

				if(!routingTable.checkStatus(w)){
					int wDist = routingTable.getDv(w);
					int checkDist = vDist + routingTable.cost(v, w);

					if(wDist == -1 || checkDist < wDist){
						routingTable.setDv(w, checkDist);
						routingTable.setPv(w, v);
					}
				}
			}
			v = routingTable.updateV(v, routingTable); 
		}
		routingTable.toStringOutput();
	}
}

class Adjacent{
	String city;
	int cost; 
	Adjacent next;
	public Adjacent(String value, int price) {
        city = value;
        cost = price;
    }
    public Adjacent getNext() {
        return next;
    }
    public void setNext(Adjacent nextValue) {
        next = nextValue;
    }
    public Adjacent(String value) {
        city = value;
    } 
}

class AdjList {
    Adjacent head = new Adjacent(null);
    int listCount = 0;    
    public void add(String data, int cost) 
    {
        Adjacent temp = new Adjacent(data, cost);
        Adjacent current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(temp);
        listCount++;
    }
    public String search(int c){
        Adjacent current = head;
        int count = 0;
        while(c != count ){
            current = current.getNext();
            count++;
            if(count == c){
                return current.city;
            }
            
        }
        return "error";
    }
    public int getCost(String w){
        Adjacent current = head;
        int count = 0;
        while(current.getNext() != null){
        	current = current.getNext();
        	if(current.city.equals(w)){
    			return current.cost;
    		}
        }
        return -1;
    }

}
class Node {
    String city = "";
    boolean status = false;
    int dv = -1;
    String pv = "";
    int cost;
    AdjList adjacents = new AdjList();
    Node next = null;
    // Node constructor
    public Node(String value, int price) {
        city = value;
        cost = price;
    }
    public Node(String value) {
        city = value;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node nextValue) {
        next = nextValue;
    }             
    public void addAdjacent(String name, int price){
    	adjacents.add(name, price);
    }        
}

class NodeList {
    Node head = new Node(null);
    int listCount = 0; 
    public void add(String data, int cost)
    {
        Node temp = new Node(data, cost);
        Node current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(temp);
        listCount++;
    }
    public String searchAdjacent(String v, int i){
    	Node current = head;
        while(current.getNext() != null){
            current = current.getNext();
            if(current.city.equals(v)){
    			return current.adjacents.search(i);
    		}
        }
        return "error2";   	
    }
    public int cost(String v, String w){
        Node current = head;
        int count = 0;
        while(current.getNext() != null){
        	current = current.getNext();
        	if(current.city.equals(v)){
    			return current.adjacents.getCost(w);
    		}
        }
        return -1;
    }
    public void addNode(Node temp){
        Node current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(temp);
        listCount++;
    }
    public boolean isFalseStatus(){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(!current.status){
    			return true;
    		}
    	}
    	return false; 
    }
    public void updateStatus(String name){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.city.equals(name)){
    			current.status = true;
    			return;
    		}
    	}
    }
    public String updateV(String name, NodeList routingTable){
    	Node current = head;
    	int smallestCost = 9999999;
    	String smallestCity = "Error1";
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.status == false){
            String city = current.city;
    			int cost = routingTable.getDv(city);
	    		if(cost != -1 && cost < smallestCost){
	    			smallestCost = cost;
	    			smallestCity = city;
	    		}
	    	} 
    	}
    	return smallestCity; 
    }
    public boolean checkStatus(String name){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.city.equals(name)){
    			return current.status;
    		}
    	}
    	return true;
    }
    public void setDv(String name, int value){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if((current.city).equals(name)){
    			current.dv = value;
    			return;
    		}
    	}
    }
    public void setPv(String name, String value){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.city.equals(name)){
    			current.pv = value;
    			return;
    		}
    	}
    }
    public int getDv(String name){
    	Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.city.equals(name)){
    			return current.dv;
    		}
    	}
    	return -1;
    }
    public String getPv(String name){
        Node current = head; 
        while(current.getNext() != null){
            current = current.getNext();
            if(current.city.equals(name)){
                return current.pv;
            }
        }
        return "";
    }
    public int getAdjacentCount(String name){
		Node current = head; 
    	while(current.getNext() != null){
    		current = current.getNext();
    		if(current.city.equals(name)){
    			return current.adjacents.listCount;
    		}
    	}
    	return -1;
    }
    public void toStringOutput() {
        Node current = head.getNext();
        String output = "";
        while (current != null) {
            String city = current.city;
            int cost = getDv(city);
            while (!city.equals("Sacramento")){
                output =  "->" + city + output;
                city = getPv(city);
            }
            current = current.getNext();
            output =  "Sacramento" + output + "\t Price: $"+ cost;
            System.out.println(output);
            output = "";
        }
    }
}
