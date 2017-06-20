/**
 * This class is responsible for creating
 * nodes and providing functions that allow
 * their information to be retrieved, as well
 * as the information of neighboring nodes. 
 * 
 * @author Samira Mantri
 * @version April 22, 2017
 */

public class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> {
	/** the data contained in the node **/
	private E data;
	/** the node to the left of the current node **/
	private BSTNode<E> left;
	/** the node to the right of the current node **/
	private BSTNode<E> right;
	
	/**
	 * This constructor sets the left and right nodes equal to null,
	 * and sets the data sent to the Node class as the 
	 * data of the node
	 * 
	 * @param data
	 * 	generic data sent to this node class
	 */
	public BSTNode (E data) {
		this.data = data;
		this.left=null;
		this.right=null;
	}
	
	/**
	 * This method sets the data of this node
	 * 
	 * @param e
	 * 	generic type data to be set
	 */
	public void setData(E e){
		this.data=e;
	}
	
	/**
	 * this method returns the data of the node
	 * 
	 * @return
	 * 	the generic data of the node
	 */
	public E getData(){
		return data;
	}
	
	/**
	 * This method sets the left node
	 * equal to the node sent to it
	 * 
	 * @param newNode
	 * 	a BSTNode<E> node sent to this method
	 */
	public void setLeft(BSTNode<E> newNode){
		this.left=newNode;
	}
	
	/**
	 * This method sets the right node
	 * equal to the node sent to it
	 * 
	 * @param newNode
	 * 	a BSTNode<E> node sent to this method
	 */
	public void setRight(BSTNode<E> newNode){
		this.right=newNode;
	}
	
	/**
	 * this method retrieves the left node that this class
	 * is pointing to
	 * 
	 * @return
	 * 	a BSTNode<E> node that this classes' node is pointing to
	 */
	public BSTNode<E> getLeft(){
		return left;
	}
	
	/**
	 * this method retrieves the right node that this class
	 * is pointing to
	 * 
	 * @return
	 * 	a BSTNode<E> node that this classes' node is pointing to
	 */
	public BSTNode<E> getRight(){
		return right;
	}
	
	/**
	 * this method compares nodes, and returns
	 * and integer based on their comparison
	 * 
	 * @return
	 * 	returns an integer that represents whether the entered node
	 *  is greater, smaller, or equal to the current node
	 */
	public int compareTo ( BSTNode <E> other ) {
		return this.data.compareTo(other.getData());
		}
}
