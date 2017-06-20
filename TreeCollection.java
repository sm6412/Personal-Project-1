import java.util.ArrayList;
import java.util.Collection;


/**
 * This class is used to store all the tree objects
 * and offers methods that can obtain data regarding the 
 * collection of trees.
 *  
 * @author Samira Mantri
 * @version April 22, 2017
 *
 */
public class TreeCollection extends MyBST<Tree> {
	/** an ArrayList that contains all the boroughs of trees in the collection */
	private ArrayList<String> boroList= new ArrayList<String>();
	/** an ArrayList that contains all corresponding number of trees in each borough */
	private ArrayList<Integer> boroNumList= new ArrayList<Integer>();
	/** a list that contains all species contained in the collection */
	ArrayList<String> speciesList = new ArrayList<String>();
	/** an integer that keeps track of the total trees in the collection */
	private int counter=0;
	
	/**
	 * This constructor creates an empty tree collection
	 */
	public TreeCollection(){	
	}
	
	/**
	 * This method adds a tree to the tree collection. If it can
	 * be added the method returns true, else false.
	 * 
	 * @param e
	 * 	a tree object to be added to the tree collection. The tree
	 *  cannot already exist in the collection and must not be null.
	 * 
	 * @return
	 * 	returns true if the tree was added, or false if the tree cannot
	 * 	be added because it is already in the tree collection
	 * 
	 * @throws ClassCastException, NullPointerException
	 * 	it throws a ClassCastException if the object is cast to something other than a tree, 
	 * 	and a NullPointerEsception if the tree object is null
	 */
	@Override
	public boolean add(Tree e) throws ClassCastException, NullPointerException {
		// if e is null throw a NullPointerException
		if (e==null){
			throw new NullPointerException("Null object cannot be added");
		}
		// if the tree is already in the collection return false
		if(contains(e)==true){
			return false;
		}
		// create a new node with the parameter's data
		BSTNode<Tree> temp = new BSTNode<Tree>(e);
		
		// if the root is null the new node becomes the root
		if(root==null){
			root= temp;
		}
		else{
			// add the tree to the collection
			recAdd(temp,root);
		}
		// update tree lists
		listUpdater(temp);
		
		// increment the total tree counter 
		counter++;
		
		// return true
		return true;
	}
	
	/*
	 * This method is a helper method to the add method.
	 * It is responsible for adding the new node into the 
	 * collection.
	 * 
	 * @param temp
	 * 	temp is the new tree node
	 * 
	 * @param node
	 * 	node is the root of the binary search tree
	 */
	private void recAdd(BSTNode<Tree> temp, BSTNode<Tree> node){
		// if the root is greater than the new node set the new node equal to the
		// left node when the method hits null, else call the method again to progress further
		// through the tree
		if(node.getData().compareTo(temp.getData())>0){
        	 if(node.getLeft()==null){
        		 node.setLeft(temp);
        		 return;
        	 }
        	 else{
        		 recAdd(temp,node.getLeft());
        	 }
         }
		// if the root is less than the new node set the new node equal to the
		// right node when the method hits null, else call the method again to progress further
		// through the tree
         else{
        	 if(node.getRight()==null){
        		 node.setRight(temp);
        		 return;
        	 }
        	 else{
        		 recAdd(temp,node.getRight());
        	 }
         }
	}
	
	/*
	 * This method updates the information stored in the speciesMap,
	 * boroList, and boroNumList every time a new element is added to the 
	 * collection
	 * 
	 * @param node
	 * 	the new node added to the collection
	 */
	private void listUpdater(BSTNode<Tree> node){
		// set the species name of the node to lower case
		String speciesName= (node.getData().getSpc()).toLowerCase();
		// set the borough name of the node equal to lower case
		String boroName= (node.getData().getBoro()).toLowerCase();
		
		// check to see whether speciesList already contains the species,
		// else add it 
		if(speciesList.contains(speciesName)==false){
			speciesList.add(speciesName);
		}
		
		// check to see whether the boroList already contains the node's borough
		// if not, add it and add 1 to its corresponding index in the boroNumList
		if(boroList.contains(boroName)==false){
			boroList.add(boroName);
			boroNumList.add(1);
		}
		// if the boroList contains the borough, increment its corresponding index in the boroNumList
		else{
			int index = boroList.indexOf(boroName);
			boroNumList.set(index,boroNumList.get(index)+1);
		}
		
	}
	
	/**
	 * this returns a string representation of the speciesList
	 * 
	 * @return 
	 * 	string representation of the speciesList
	 */
	public String getSpeciesList(){
		return speciesList.toString();
	}
	
	/**
	 * this method returns a string of the boroList
	 * 
	 * @return
	 * 	string representation of boroList
	 */
	public String getBoroList(){
		return boroList.toString();
	}
	
	/**
	 * this method returns a string of the boroNumList
	 * 
	 * @return
	 * 	string representation of the boroNumList
	 */
	public String getBoroNumList(){
		return boroNumList.toString();
	}
	
	/**
	 * This method returns the number of trees in the collection
	 * 
	 * @return
	 * 	and integer representation of the number of trees in the tree collection
	 */
	public int getTotalNumberOfTrees(){
		return counter;
	}
	
	/**
	 * This method returns the number of trees that are located in a 
	 * specific borough 
	 * 
	 * @param boroName
	 * 	the boroName is a string that represents the name of the borough. 
	 * 	It must be equal to 'Manhattan','Bronx','Brooklyn','Queens', 
	 * 	or 'Staten Island'.
	 * 
	 * @return returns an integer that represents the number of trees within the specific borough.
	 * 	If the borough name is not equal to 'Manhattan','Bronx','Brooklyn',
	 * 	'Queens', or 'Staten Island,' return 0;
	 */
	public int getCountByBorough(String boroName){
		// make the boroName lower case
		String boroNameLower = boroName.toLowerCase();
		
		// check to see whether the boroList contains the entered borough
		// if it does not return 0
		if(boroList.contains(boroNameLower)==false){
			return 0;
		}
		// find the index of the boroName located in the boroList
		int index= boroList.indexOf(boroNameLower);
		
		// return the integer at the corresponding index in boroNumList
		return boroNumList.get(index);
	}
	
	/**
	 * This method returns the number of tree objects in the collection whose
	 * species name matches the entered species name
	 * 
	 * @param speciesName
	 * 	a string that is a particular tree species name
	 *  
	 * @return returns an integer that represents the number of matching tree 
	 * 	species in the list.
	 * 	If the species name does not exist within the list the method
	 * 	returns 0.
	 */
	public int getCountByTreeSpecies(String speciesName){
		// create a list that contains the matching species 
		ArrayList<String> matchingSpeciesList = (ArrayList<String>) getMatchingSpecies(speciesName);
		
		// create a counter to count the number of trees whose species matches speciesName
		int counter=0;
		
		// iterate through the list of matching species and check for how many times they
		// appear in the binary search tree
		for (int x=0; x<matchingSpeciesList.size();x++){
			int numOfInstances=speciesCounter(root,matchingSpeciesList.get(x),0);
			counter+=numOfInstances;
		}
		
		// return the counter
		return counter;
	}
	
	/*
	 * This is a helper method to getCountByTreeSpecies and is responsible for finding
	 * the species in the binary search tree and counting how many times they occur
	 * 
	 * @ param root
	 * 	the BSTNode<Tree> root of the binary search tree
	 * 
	 * @ param target
	 * 	a string that represents the desired species to be found in the binary search tree
	 * 
	 * @ param counter
	 * 	an integer that represents the number of times the target is found
	 * 
	 * @ return
	 * 	an integer that represents the number of times a particular tree species was found
	 */
	private int speciesCounter(BSTNode<Tree> root,String target, int counter){
		// if the root is null, return the counter
		if (root==null){
			return counter;
		}
		// if the target is found, increment the counter and explore further nodes
		if(target.compareTo(root.getData().getSpc().toLowerCase())==0){
			counter++;
			counter=speciesCounter(root.getLeft(),target,counter);
		    counter=speciesCounter(root.getRight(),target,counter);
		 	
		}
		// if the target is smaller than the root, call the method again with
		// the left node 
	    if(target.compareTo(root.getData().getSpc().toLowerCase()) < 0){
	    	counter=speciesCounter(root.getLeft(),target,counter);
	    }
	    // if the target is greater than the root, call the method again with
	 	// the right node 
	    if(target.compareTo(root.getData().getSpc().toLowerCase()) > 0){
	    	counter=speciesCounter(root.getRight(),target,counter);
	    }
	    return counter;
	   
	}
	

	/**
	 * This method obtains the number of a specific species of trees that exists
	 * within a specified borough
	 * 
	 * @param speciesName
	 * 	the speciesName is the species of a tree. It should already exist within 
	 * 	the list
	 * 
	 * @param boroName
	 * 	the boroName is the name of the borough. It must be equal to 'Manhattan',
	 * 	'Bronx','Brooklyn','Queens', or 'Staten Island'. 
	 * 
	 * @return returns the number of a specific species of tree that exists within 
	 * 	a specified borough. If the species does not exist within the list or the 
	 * 	borough does not exist within NYC return 0.
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName){
		// check to see whether the boroList contains the borough
		// if it does not return 0
		if (boroList.contains(boroName.toLowerCase())==false){
			return 0;
		}
		// create a counter to count the number of trees whose species matches speciesName
		int counter=0;
		
		// create a list that contains the matching species 
		ArrayList<String> matchingSpeciesList= (ArrayList<String>) getMatchingSpecies(speciesName);
		
		// iterate through the list of matching species and check for how many times they
		// appear in the binary search tree
		for (int x=0; x<matchingSpeciesList.size();x++){
			int numOfInstances=speciesInBoroCounter(root,matchingSpeciesList.get(x),0,boroName.toLowerCase());
			counter+=numOfInstances;
		}
		return counter;
		
	}
	
	/*
	 * This is a helper method to getCountByTreeSpeciesBorough and is responsible for finding
	 * the species in the binary search tree and counting how many they appear in the desired borough
	 * 
	 * @ param root
	 * 	the BSTNode<Tree> root of the binary search tree
	 * 
	 * @ param target
	 * 	a string that represents the desired species to be found in the binary search tree
	 * 
	 * @ param counter
	 * 	an integer that represents the number of times the target has the same borough as the entered one
	 * 
	 * @param boro
	 * 	the intended borough to be searched for in the matching species 
	 * 
	 * @ return
	 * 	an integer that represents the number of times a particular tree species was located in the specified borough
	 */
	private int speciesInBoroCounter(BSTNode<Tree> root,String target, int counter, String boro){
		// if the root is null, return the counter
		if (root==null){
			return counter;
		}
		String rootBoro= root.getData().getBoro().toLowerCase();
		// if the target is found, increment the counter if it has the desired borough
		// then explore further nodes
		  if(target.compareTo(root.getData().getSpc().toLowerCase())==0){
			  if(rootBoro.equals(boro)){
				  counter++;
			  }
			  counter=speciesInBoroCounter(root.getLeft(),target,counter,boro);
			  counter=speciesInBoroCounter(root.getRight(),target,counter,boro);
			  
		 }
		// if the target is smaller than the root, call the method again with
		// the left node 
	    if(root.getLeft()!=null && target.compareTo(root.getData().getSpc().toLowerCase()) < 0){
	    	counter=speciesInBoroCounter(root.getLeft(),target,counter,boro);
	    }
	 // if the target is greater than the root, call the method again with
	 // the right node 
	    if(root.getRight()!=null && target.compareTo(root.getData().getSpc().toLowerCase()) > 0){
 		    counter=speciesInBoroCounter(root.getRight(),target,counter,boro);
	    }
	    return counter;
	   
	}
	

	/**
	 * This method creates an ArrayList of tree species strings that 
	 * match the species string sent to the method
	 * 
	 * @param speciesName
	 * 	the speciesName is the name of the entered tree species
	 * 
	 * @return returns a list of the matching tree species names
	 */
	public Collection<String> getMatchingSpecies(String speciesName){
		// create an ArrayList that will contain tree species strings
		ArrayList<String> treesInSpc= new ArrayList<String>();
		
		// make the species lower case to allow for case insensitivity
		String lowercaseSpc= speciesName.toLowerCase();
		
		// loop through the list to determine matching species 
		for (int x=0; x<speciesList.size(); x++){
			String specificTreeSpc= (speciesList.get(x));
			String specificTreeLower= (specificTreeSpc).toLowerCase();
			// if a specific species is found, add it to treesInSpc
			if ((specificTreeLower.contains(lowercaseSpc))){
				if (!(treesInSpc.contains(specificTreeSpc))){
					treesInSpc.add(specificTreeSpc);
				}
			}
		}
		// return the matching species ArrayList
		return treesInSpc;
	}
	
	/**
	 * This method overrides the toString method of object in order to create 
	 * a string that contains all the trees in the collection and their information
	 * 
	 * @return the method returns a string representation of the all
	 * 	the trees in the collection and their information
	 */
	@Override
	public String toString(){
		// create a string to hold the information of all the trees
		String treeList="";
		// if the collection is empty, return that it is
		if(root==null){
			return "No trees currently in TreeCollection";
		}
		// return the string representation of all the trees in the collection
		return recToString(root,treeList);		
	}
	
	/*
	 * This method is a helper method to toString. It recursively moves through
	 * the binary search tree and gathers information about the individual
	 * nodes. It then takes the information and builds a large string containing
	 * all the information
	 * 
	 * @ return
	 * 	returns a string composed of information about every tree in the collection
	 */
	private String recToString(BSTNode<Tree> root, String treeList){
		// add the node's information to treeList
		Tree treeObject=root.getData();
		treeList+=(treeObject.getSpc()).toUpperCase()+"\n";
		treeList+="Tree ID: "+(treeObject.getId())+"\n";
		treeList+="Tree Diameter: "+(treeObject.getDiam())+"\n";
		treeList+="Tree Status: "+(treeObject.getStatus())+"\n";
		treeList+="Tree Health: "+(treeObject.getHealth())+"\n";
		treeList+="Tree Zipcode: "+String.format("%05d", (treeObject.getZip()))+"\n";
		treeList+="Tree Boroname: "+(treeObject.getBoro())+"\n";
		treeList+="Tree X Coordinate: "+(treeObject.getX())+"\n";
		treeList+="Tree Y Coordinate: "+(treeObject.getY())+"\n";
		treeList+="\n";
		
		// continue traversal to left node if it is not null
	    if(root.getLeft() != null){
	    	return recToString(root.getLeft(),treeList);
	    }
	    // continue traversal to right node if it is not null
	    if(root.getRight() != null){
	    	return recToString(root.getRight(),treeList);
	    }
	    
	    // return treeList
	    return treeList;
	  }
	
	
	

}
