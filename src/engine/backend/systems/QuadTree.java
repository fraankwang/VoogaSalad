package engine.backend.systems;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.IEntity;

/**
 * Quad collision detection
 * @author clz4
 *
 */
public class QuadTree {

	private int MAX_OBJECTS = 10;
	private int MAX_LAYER = 5;

	private int layer;
	private List<IEntity> objects;
	private QuadTree[] nodes;

	public QuadTree(int layer) {
		this.layer = layer;
		objects = new ArrayList<IEntity>();
		nodes = new QuadTree[4];
		
	}
	
	/**
	 * Clears the objects in the node.
	 */
	public void clear() {
		objects.clear();
		for (QuadTree node : nodes) {
			if (node != null) {
				node.clear();
				node = null;
			}
		}
	}
	
	/**
	 * 
	 */
	private void splitNode() {
		
	}
	

}
