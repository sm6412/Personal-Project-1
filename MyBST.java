import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * this class provides the implementation of the binary search tree
 * 
 * @author Samira Mantri
 * @version April 22, 2017
 */

public class MyBST<E extends Comparable<E>>{
	/** the root of the tree */
	protected BSTNode<E> root;
	
	/**
	 * this constructor creates an empty binary search tree
	 */
	public void MyBST(){
		root=null;
	}
	
	/**
	 * This method adds an element into the tree. The element cannot be null
	 * or already contained in the tree.
	 * 
	 * @param e
	 * 	it is of type E and its data is to be added into the tree. Cannot be 
	 * 	null or already in the tree
	 * 
	 * @return
	 * 	 true if it was successfully added, else false 
	 * 
	 * @throws ClassCastException
	 * 	if e is cast to a different type not related to the class
	 * 	of its tree
	 * 
	 * @throws NullPointerException
	 * 	if e is null
	 */
	public boolean add(E e) throws ClassCastException, NullPointerException {
		// throw NullPointerException if e is null
		if (e==null){
			throw new NullPointerException();
		}
		// if the tree contains e already, return false 
		if(contains(e)==true){
			return false;
		}
		// create a node with the entered data
		BSTNode<E> temp = new BSTNode<E>(e);
		// if the root is null, make the root the new node 
		if(root==null){
			root= temp;
		}
		// if not, search through the tree to find where it should be added
		else{
			recAdd(temp,root);
		}
		// return true
		return true;
	}
	
	/*
	 * this is a helper method to the add method. It recursively adds the 
	 * new node into the tree
	 * 
	 * @param temp
	 * 	the new node to be added
	 * 
	 * @param node
	 * 	the root of the tree
	 */
	private void recAdd(BSTNode<E> temp, BSTNode<E> node){
		// if the root's data is greater than the target node's data move to
		// the left node to see if it can be added
		if(node.getData().compareTo(temp.getData())>0){
        	 if(node.getLeft()==null){
        		 node.setLeft(temp);
        		 return;
        	 }
        	 // if the next node is not null it cannot be added yet
        	 // move further down the tree
        	 else{
        		 recAdd(temp,node.getLeft());
        	 }
         }
		// do the same for the right hand side 
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
	
	/**
	 * This methods removes a node from the tree. If it can be removed it returns
	 * true, otherwise false. 
	 * 
	 * @param o
	 * 	the object to be removed
	 * 
	 * @return
	 * 	true if the object was removed, else, false if it was not in the 
	 * 	tree to begin with
	 * 
	 * @throws ClassCastException
	 * 	if o is cast to a different type not related to the class
	 * 	of its tree
	 * 
	 * @throws NullPointerException
	 * 	if o is null
	 */
	public boolean remove(Object o) throws ClassCastException{
		// if o is null throw a NullPointerException
		if(o==null){
			throw new NullPointerException("Null object cannot be removed");
		}
		// if the tree does not contain o return false
		if(contains(o)==false){
			return false;
		}
		// adjust the root if need be, and remove o
		root= recRemove(root,(E)o);
		return true;
	}

	/*
	 * this is a helper method that determines whether the node to be
	 * removed has children or not
	 * 
	 * @param node
	 * 	the node to be removed
	 * 
	 * @return
	 * 	the node to be the root
	 */
	private BSTNode<E> removeHelper(BSTNode<E> node){
		// remove the node
		if (node.getLeft()==null ){
			return node.getRight();
		}
		if(node.getRight()==null){
			return node.getLeft();
		}
		// if node has children, remove node and adjust tree
		E data= getPredecessor(node);
		node.setData(data);
		node.setLeft(recRemove(node.getLeft(),data));
		return node;
	}
	
	/*
	 * This method finds the predecessor of the tree
	 * 
	 * @param node
	 * 	the node of the root
	 * 
	 * @return
	 * 	the data of the predecessor node 
	 */
	private E getPredecessor (BSTNode<E> node){
		// if the left node is null throw a NullPointerException
		if(node.getLeft()==null){
			throw new NullPointerException("Error");
		}
		else{
			// move one node to the left of the root, then keep
			// moving right until the rightmost element is found
			BSTNode <E> current = node.getLeft();
			while (current.getRight() != null ){
				current = current.getRight();
			}
			// return the elements data 
			return current.getData();
		}
	}
	
	/*
	 * This method is a helper method to the remove method. It removes the desired
	 * node from the tree
	 * 
	 * @param node
	 * 	the root of the tree
	 * 
	 * @data
	 * 	the data of the node to be removed
	 * 
	 * @returns 
	 * 	returns the reference to the new, possibly modified tree 
	 * 
	 */
	private BSTNode<E> recRemove(BSTNode<E> node, E data ){
		if(node==null){
			// do nothing
		}
		// if the data is less than the data of the root move to the left node
		else if(data.compareTo(node.getData())<0){
			node.setLeft(recRemove( node.getLeft(),data));
		}
		// if the data is more than the data of the root move to the right node
		else if(data.compareTo(node.getData())>0){
			node.setRight(recRemove(node.getRight(),data));
		}
		else{
			// send the node to the removeHelper method
			node= removeHelper(node);
		}
		// return node
		return node;
	}
	
	/**
	 * this method checks to see if a particular object is stored
	 * in the tree. It returns true if found, otherwise false
	 * if it is not
	 * 
	 * @param o
	 * 	the object to be searched for in the tree, cannot be null
	 * 
	 * @return
	 *  true if the object was removed, else false 
	 *  
	 * @throws ClassCastException
	 * 	if o is cast to a different type not related to the class
	 * 	of the tree
	 * 
	 * @throws NullPointerException
	 * 	if o is null
	 */
	public boolean contains(Object o) throws ClassCastException {
		// if the object is null throw NullPointerException
		if(o==null){
			throw new NullPointerException();
		}
		// if the root is null return false
		if(root==null){
			return false;
		}
		// cast the object to type E
		E newNodeData= (E)o;
		// search the tree for the node containing the specified data
		// return it
		return recContains(root,newNodeData);
	}
	
	/*
	 * This is a helper method to the contains method. It recursively
	 * moves through the tree to see if the the entered object can be found
	 * or not. It returns true if found, otherwise, false 
	 * 
	 * @param root
	 * 	the root of the tree
	 * 
	 * @param data
	 * 	the data of the node being searched for in the tree
	 */
	private boolean recContains(BSTNode<E> root, E data){
		// if the node containing the data is found, return true 
		if(root.getData().equals(data)){
			return true;
		}
		// move to the left node if the data is smaller than the root's data
	    if(root.getLeft() != null && data.compareTo(root.getData()) < 0){
	    	return recContains(root.getLeft(),data);
	    }
	    // move to the right node if the data is greater than the root's data
	    if(root.getRight() != null && data.compareTo(root.getData()) > 0){
	    	return recContains(root.getRight(),data);
	    }
	    // no matching node was found
	    return false;
	}
	
	/**
	 * this method returns the data of the lowest node
	 * in the tree
	 * 
	 * @return
	 * 	the data of the lowest node in the tree
	 * 
	 * @throws NoSuchElementException
	 * 	if the tree is empty
	 */
	public E last(){
		// if the root is null throw a NoSuchElementException
		if(root==null){
			throw new NoSuchElementException();
		}
		else{
			// create a temporary node equal to the root
			BSTNode<E> temp = root;
			// traverse the tree for the lowest node
			while(temp.getRight()!=null){
				temp=temp.getRight();
			}
			// return the found node's data
			return temp.getData();
		}
	}
	
	/**
	 * this method returns the data of the highest node
	 * in the tree
	 * 
	 * @return
	 * 	the data of the highest node in the tree
	 * 
	 * @throws NoSuchElementException
	 * 	if the tree is empty
	 */
	public E first(){
		// if root is null throw NoSuchElementException
		if(root==null){
			throw new NoSuchElementException();
		}
		else{
			// create a temporary node equal to the root
			BSTNode<E> temp = root;
			// traverse the tree for the highest node
			while(temp.getLeft()!=null){
				temp=temp.getLeft();
			}
			// return the found node's data
			return temp.getData();
		}
	}
	
	/**
	 * This method prints the string representation of the tree
	 * according to an inorder traversal
	 * 
	 * @return
	 * 	a string representing inorder traversal of tree
	 */
	@Override
	public String toString(){
		// create a new arrayList
		ArrayList<String> list= new ArrayList<String>();
		
		// create a new string representing inorder traversal 
		ArrayList<String> newString= (inOrder(root,list));
		
		// return string
		return newString.toString();
	}
	
	/*
	 * This method is a helper method to the toString method.
	 * It is responsible for traversing the tree and adding an
	 * element to an arrayList according to an inorder traversal
	 * 
	 * @param node
	 * 	the root of the tree
	 * 
	 * @param list
	 * 	the list to contain elements in tree
	 */
	private ArrayList<String> inOrder(BSTNode<E> node,ArrayList<String> list){
		// descend to left node
		if(node.getLeft()!=null){
			inOrder(node.getLeft(),list);
		}
		// add the node to the list
		list.add(node.getData().toString());
		//descend to right node
		if(node.getRight()!=null){
			inOrder(node.getRight(),list);
		}
		//return list
		return list;
	}




	
	 
	 
	
	
}
	
	

	
	
	
	
	
	

