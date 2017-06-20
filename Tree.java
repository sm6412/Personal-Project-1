/**
 * The Tree class is responsible for storing the information about the 
 * particular trees that grow in NYC. It is also responsible for ensuring that valid values are 
 * sent to the constructor. 
 *  
 * @author Samira Mantri
 * @version Feb 9, 2017
 *
 */
public class Tree implements Comparable<Tree>  {
	/** the tree's ID **/
	private int tree_id;
	/** the tree's diameter **/
	private int tree_dbh;
	/** the tree's status **/
	private String status;
	/** the tree's health **/
	private String health;
	/** the tree's species name **/
	private String spc_common;
	/** the tree's zipcode **/
	private int zipcode;
	/** the borough in which the tree is located **/
	private String boroname;
	/** the x coordinate of the tree **/
	private double x_sp;
	/** the y coordinate of the tree **/
	private double y_sp;
	
	/**
	 * This constructor ensures that the parameters contain valid data before assigning
	 * them to the instance variables
	 * 
	 * @param id 
	 * 	the tree's id must be a non-negative integer
	 * 
	 * @param diam
	 * 	the tree's diameter must be a non-negative integer
	 * 
	 * @param status
	 * 	the tree's status must be a string that is equal to
	 * 	'Alive','Dead','Stump', null, or is empty
	 * 
	 * @param health
	 * 	the tree's health must be a string that is equal to 
	 * 	'Good','Fair','Poor',null, or an empty string
	 * 
	 * @param spc
	 * 	the tree's species must be a string that may be empty 
	 * 	but cannot be null
	 * 
	 * @param zip
	 * 	the zip must be an integer that is a number from
	 * 	0 to 99999
	 * 
	 * @param boro
	 * 	the tree's borough should be a string equal to 'Manhattan',
	 * 	'Bronx','Brooklyn','Queens', or 'Staten Island'
	 * 
	 * @param x
	 * 	the tree's x coordinate must be a double
	 *  
	 * @param y
	 * 	the tree's y coordinate must be a double 
	 */
	public Tree(int id, int diam, String status, String health, String spc, int zip, String boro, double x, double y){
		// test the tree id parameter by sending it to setId()
		setID(id);
		// test the tree's diameter parameter by sending it to setDiam()
		setDiam(diam);
		// check whether the status is null, if not,
		// test the tree's status parameter by sending it to setStatus()
		if (status==null){
			this.status=status;
		}
		else{
			setStatus(status);
		}
		// check whether the health is null, if not,
		// test the tree's health parameter by sending it to setHealth()
		if (health==null){
			this.health=health;
		}
		else{
			setHealth(health);
		}
		// test the tree's species name parameter by sending it to setSpc()
		setSpc(spc);
		// test the tree's zip parameter by sending it to setZip()
		setZip(zip);
		// test the tree's boro parameter by sending it to setBoro()
		setBoro(boro);
		// test the tree's x coordinate parameter by sending it to setX()
		setX(x);
		// test the tree's y coordinate parameter by sending it to setY()
		setY(y);
	}

	
	/*
	 * This method sets the tree id equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param id
	 * 	the tree's id must be a non-negative integer
	 * 
	 * @throws IllegalArgumentException if the id is not 
	 * 	a positive integer
	 */
	private void setID(int id){
		if (id<0){
			throw new IllegalArgumentException("The tree ID is invalid.");
		}
		else{
			this.tree_id=id;
			}
	}
	
	/*
	 * This method sets the tree diameter equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param diam
	 * 	the tree's diameter must be a non-negative integer
	 * 
	 * @throws IllegalArgumentException if the diameter is not 
	 * 	a positive integer
	 */
	private void setDiam(int diam){
		if (diam<0){
			throw new IllegalArgumentException("The tree diameter is invalid.");
		}
		else{
			this.tree_dbh=diam;
		}
	}
	
	/*
	 * This method sets the tree's status equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param status
	 * 	the tree's status must be a string that is equal to
	 * 	'Alive','Dead','Stump', null, or is empty
	 * 
	 * @throws IllegalArgumentException if the status is not 
	 * 	a string that is equal to 'Alive','Dead','Stump', 
	 * 	null, or is empty
	 */
	private void setStatus(String status){
		if (status.length()==0){
			this.status=status;
		}
		else if(status.equalsIgnoreCase("alive")||status.equalsIgnoreCase("dead")||status.equalsIgnoreCase("stump")){
			this.status=status;
		}
		else{
			throw new IllegalArgumentException("The status of this tree is invalid.");
		}
	}
	
	/*
	 * This method sets the tree's health equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param health
	 * 	the tree's health must be a string that is equal to 
	 * 	'Good','Fair','Poor',null, or an empty string
	 * 
	 * @throws IllegalArgumentException if the health is not 
	 * 	a string that is equal to 'Good','Fair','Poor',null, 
	 * 	or an empty string
	 */
	private void setHealth(String health){
		if (health.length()==0){
			this.health=health;
		}
		else if(health.equalsIgnoreCase("good")||health.equalsIgnoreCase("fair")||health.equalsIgnoreCase("poor")){
			this.health=health;
		}
		else{
			throw new IllegalArgumentException("The health of this tree is not valid.");
		}
	}
	
	/*
	 * This method sets the tree's species name equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param spc
	 * 	the tree's species must be a string that may be empty 
	 * 	but cannot be null
	 * 
	 * @throws IllegalArgumentException if the species name is null
	 */
	private void setSpc(String spc){
		if (spc==null){
			throw new IllegalArgumentException("The species of this tree is not valid.");
		}
		else{
			this.spc_common=spc;
		}
	}
	
	/*
	 * This method sets the tree's zip equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param zip
	 * 	the zip must be an integer that is a number from
	 * 	0 to 99999
	 * 
	 * @throws IllegalArgumentException if the zip is not
	 * 	an integer that is a number from 0 to 99999 
	 */
	private void setZip(int zip){
		if (zip>=0&&zip<=99999){
			this.zipcode=zip;
		}
		else{
			throw new IllegalArgumentException("The zipcode of where this tree is located is invalid.");
		}
	}
	
	/*
	 * This method sets the tree's borough equal to its instance variable if
	 * its parameter value is valid
	 * 
	 * @param boro
	 * 	the tree's boro should be a string equal to 'Manhattan',
	 * 	'Bronx','Brooklyn','Queens', or 'Staten Island'
	 * 
	 * @throws IllegalArgumentException if the boro is not
	 * 	a string equal to 'Manhattan','Bronx','Brooklyn','Queens', 
	 * 	or 'Staten Island'
	 */
	private void setBoro(String boro){
		if (boro==null){
			throw new IllegalArgumentException("The borough of this tree is invalid.");
		}
		else if((boro.equalsIgnoreCase("manhattan"))||(boro.equalsIgnoreCase("bronx"))||(boro.equalsIgnoreCase("brooklyn"))||(boro.equalsIgnoreCase("queens"))||(boro.equalsIgnoreCase("staten island"))){
			this.boroname=boro;
		}
		else{
			throw new  IllegalArgumentException("The borough of this tree is invalid.");
		}

	}
	
	/*
	 * This method sets the tree's x coordinate equal to its instance variable
	 * 
	 * @param x
	 * 	the tree's x coordinate must be a double
	 */
	private void setX(double x){
		this.x_sp=x;
	}
	
	/*
	 * This method sets the tree's y coordinate equal to its instance variable
	 * 
	 * @param y
	 * 	the tree's y coordinate must be a double
	 */
	private void setY(double y){
		this.y_sp=y;
	}
	
	/**
	 * This method returns the tree's id
	 * 
	 * @return the tree's id which is an integer
	 */
	public int getId(){
		return this.tree_id;
	}
	
	/**
	 * This method returns the tree's diameter
	 * 
	 * @return the tree's diameter which is an integer
	 */
	public int getDiam(){
		return this.tree_dbh;
	}
	
	/**
	 * This method returns the tree's status
	 * 
	 * @return the tree's status which is a string
	 */
	public String getStatus(){
		return this.status;
	}
	
	/**
	 * This method returns the tree's health 
	 * 
	 * @return the tree's health which is a string
	 */
	public String getHealth(){
		return this.health;
	}
	
	/**
	 * This method return's the tree's species name
	 * 
	 * @return the tree's species name which is a string
	 */
	public String getSpc(){
		return this.spc_common;
	}
	
	/**
	 * This method returns the tree's zip
	 * 
	 * @return the tree's zip which is an integer
	 */
	public int getZip(){
		return this.zipcode;
	}
	
	/**
	 * This method returns the tree's borough
	 * 
	 * @return the tree's borough which is a string
	 */
	public String getBoro(){
		return this.boroname;
	}
	
	/**
	 * This method returns the tree's x coordinate
	 * 
	 * @return the tree's x coordinate which is a double
	 */
	public double getX(){
		return this.x_sp;
	}
	
	/**
	 * This method returns the tree's y coordinate
	 * 
	 * @return the tree's y coordinate which is a double
	 */
	public double getY(){
		return this.y_sp;
	}
	
	/**
	 * This method overrides the equals method to determine whether  
	 * two tree objects have the same IDs, but different species names
	 * 
	 * @param otherTree
	 * 	the otherTree is of type object and may have the same ID as the
	 * 	tree being compared, but it cannot have a different species name
	 * 
	 * @return true if the otherTree is an instance of tree and does not
	 * 	have the same ID but a different species name as the comparing tree.
	 * 	Otherwise it returns false
	 * 
	 * @throws IllegalArgumentException if the comparing trees have the
	 * 	same IDs but come from different species
	 */
	@Override
	public boolean equals(Object otherTree){
		// check to see whether otherTree is an instance of tree
		// if it is not, return false
		if(otherTree instanceof Tree){
			// cast otherTree as a Tree
			Tree newTree=(Tree)otherTree;
			// see whether the two trees have the same ID, but a different species name
			// if that is the case, throw an exception, otherwise return true
			if ((this.tree_id==newTree.getId())&&(this.spc_common.equalsIgnoreCase(newTree.getSpc()))){
				return true;
			}
			else if (((this.tree_id==newTree.getId())&&!(this.spc_common.equalsIgnoreCase(newTree.getSpc())))){
				throw new IllegalArgumentException("Two trees cannot have the same ID but be from different species.");
			}
		}
		return false;

	}
	
	/**
	 * this method return true or false based on whether two trees have 
	 * the same name. This comparison is case insensitive. 
	 * 
	 * @param t
	 * 	t is an entered tree object
	 * 
	 * @return
	 * 	returns true if the names are the same, or false
	 * 	otherwise
	 */
	public boolean sameName(Tree t){
		//check to see whether the names are the same
		if (this.spc_common.equalsIgnoreCase(t.getSpc())){
			return true;
		}
		return false;
	}
	
	/**
	 * This method sorts tree objects alphabetically, and if
	 * tree objects have the same name, it sorts them by their
	 * ID number
	 * 
	 * @param comparingTree
	 * 	a tree object to be compared
	 * 
	 * @return if two trees have different species names, the method returns a -1 if
	 * 	the character of the first tree's name is less than the character of the 
	 *  comparingTree's name. It returns 1 if the character of the first tree's name is greater 
	 *  than the character of the comparingTree's name. In the case of trees with the same name,
	 *  the method returns 1 if the first tree's ID is greater than the second tree's.
	 *  It returns 0 if the IDs are the same, and lastly a -1 if the first tree's
	 *  ID is smaller than the comparingTree's ID.
	 */
	public int compareTo(Tree comparingTree){
		if(comparingTree==null){
			throw new NullPointerException();
		}
		// create a variable that represents the length of the first tree's name
		int givenTreeSpcLength=(this.spc_common).length();
		// create a variable that represents the length of the second tree's name
		int comparingTreeSpcLength=(comparingTree.getSpc()).length();
		// find the min length of the two tree names
		int minLengthOfStrings= Math.min(givenTreeSpcLength, comparingTreeSpcLength);
		// create an array of the chars that compose the first tree's name
		char string1[]= (this.spc_common.toLowerCase()).toCharArray();
		//create an array of the chars that compose the second tree's name
		char string2[]=((comparingTree.getSpc()).toLowerCase()).toCharArray();
		
		// create a counter to keep track of whether the while loop has exceeded the
		// length of the shorter tree's name
		int counter=0;
		while (counter<minLengthOfStrings){
			// if the char of the first tree's name is less than the char of the 
			// second tree's name, return -1
			if (string1[counter]<string2[counter]){
				return -1;
			}
			// if the char of the first tree's name is greater than the char of the 
			// second tree's name, return 1 
			else if(string1[counter]>string2[counter]){
				return 1;
			}
			// increase the counter
			counter++;
		}
		
		// if the two trees have the same species name they are sorted based on
		// the value of their IDs
		// if the first tree's ID is greater than the comparingTree's ID return 1
		if (this.tree_id>comparingTree.getId()){
			return 1;
		}
		// if the first tree's ID is equal to the comparingTree's ID return 0
		else if(this.tree_id==comparingTree.getId()){
			return 0;
		}
		// else return -1
		else{
			return -1;
		} 
	}
	
	/**
	 * This method compares tree objects based on comparisons performed
	 * on their species
	 * 
	 * @param t
	 * 	a tree object to be compared
	 * 
	 * @return if two trees have different species names, the method returns a -1 if
	 * 	the character of the first tree's name is less than the character of the 
	 *  comparingTree's name. It returns 1 if the character of the first tree's name is greater 
	 *  than the character of the comparingTree's name.
	 */
	public int compareName(Tree t){
		// if the tree is null throw a NullPointerException
		if (t==null){
			throw new NullPointerException();
		}
		// create a variable that represents the length of the first tree's name
		int givenTreeSpcLength=(this.spc_common).length();
		// create a variable that represents the length of the second tree's name
		int comparingTreeSpcLength=(t.getSpc()).length();
		// find the min length of the two tree names
		int minLengthOfStrings= Math.min(givenTreeSpcLength, comparingTreeSpcLength);
		// create an array of the chars that compose the first tree's name
		char string1[]= (this.spc_common.toLowerCase()).toCharArray();
		//create an array of the chars that compose the second tree's name
		char string2[]=((t.getSpc())).toLowerCase().toCharArray();
		// create a counter to keep track of whether the while loop has exceeded the
		// length of the shorter tree's name
		int counter=0;
		while (counter<minLengthOfStrings){
			// if the char of the first tree's name is less than the char of the 
			// second tree's name, return -1
			if (string1[counter]<string2[counter]){
				return -1;
			}
			// if the char of the first tree's name is greater than the char of the 
			// second tree's name, return 1 
			else if(string1[counter]>string2[counter]){
				return 1;
			}
			// increase the counter
			counter++;
		}
		// if the trees have the same species name return 0
		return 0;
	}
	
	/**
	 * This method overrides the toString method of object in order to create a string
	 * with the tree object's information
	 * 
	 * @return the method returns a string representation of the tree's species name and id
	 */
	@Override
	public String toString(){
		// create a string to hold the tree's information
		String treeInfo="";
		// add the tree's name and id to treeInfo
		treeInfo+="Species:"+(this.spc_common)+" Id:"+(this.tree_id);
		// return the treeInfo
		return treeInfo;
	}
	

}



