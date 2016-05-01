package engine.backend.systems;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.utilities.*;

/**
 * Quad collision detection
 * 
 * @author from Steven Lambert on evantotuts, implemented for voogaSalad by clz4
 *
 */
public class QuadTree {

	private int MAX_OBJECTS = 10;
	private int MAX_LAYER = 5;

	private int layer;
	private List<IEntity> objects;
	private Rectangle bounds;
	private QuadTree[] nodes;

	public QuadTree(int layer, Rectangle bounds) {
		this.layer = layer;
		this.bounds = bounds;
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
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[0] = new QuadTree(layer + 1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new QuadTree(layer + 1, new Rectangle(x, y, subWidth, subHeight));
		nodes[2] = new QuadTree(layer + 1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new QuadTree(layer + 1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	}
	
	/**
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 * @param entity
	 * @return
	 */
	private int getIndex(IEntity entity) {
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		PositionComponent myPosition = (PositionComponent) entity.getComponent(ComponentTagResources.positionComponentTag);
		SizeComponent mySize = (SizeComponent) entity.getComponent(ComponentTagResources.sizeComponentTag);
		boolean topQuadrant = inTopQuadrants(horizontalMidpoint, myPosition, mySize);
		boolean bottomQuadrant = inBottomQuadrants(horizontalMidpoint, myPosition);
		if (inLeftQuadrant(verticalMidpoint, myPosition, mySize)) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		else if (inRightQuadrant(verticalMidpoint, myPosition)) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	private boolean inRightQuadrant(double verticalMidpoint, PositionComponent myPosition) {
		return myPosition.getX() > verticalMidpoint;
	}

	private boolean inLeftQuadrant(double verticalMidpoint, PositionComponent myPosition, SizeComponent mySize) {
		return myPosition.getX() < verticalMidpoint && myPosition.getX() + mySize.getWidth() < verticalMidpoint;
	}

	private boolean inBottomQuadrants(double horizontalMidpoint, PositionComponent myPosition) {
		boolean bottomQuadrant = (myPosition.getY() > horizontalMidpoint);
		return bottomQuadrant;
	}

	private boolean inTopQuadrants(double horizontalMidpoint, PositionComponent myPosition, SizeComponent mySize) {
		boolean topQuadrant = (myPosition.getX() < horizontalMidpoint&& myPosition.getY() + mySize.getHeight() < horizontalMidpoint);
		return topQuadrant;
	}

	/**
	 * Insert the object into the quadtree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 * 
	 * @param entity
	 */
	public void insert(IEntity entity) {
		if (nodes[0] != null) {
			int index = getIndex(entity);

			if (index != -1) {
				nodes[index].insert(entity);

				return;
			}
		}

		objects.add(entity);

		if (objects.size() > MAX_OBJECTS && layer < MAX_LAYER) {
			if (nodes[0] == null) {
				splitNode();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	/**
	 * Return all objects that could collide with the given object
	 * @param retrieved
	 * @param entity
	 * @return
	 */
	public Collection<IEntity> retrieve(Collection<IEntity> retrieved, IEntity entity) {
		int index = getIndex(entity);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(retrieved, entity);
		}
		retrieved.addAll(objects);

		return retrieved;
	}

}
