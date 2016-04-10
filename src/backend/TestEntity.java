package backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestEntity {
	
	Entity entity;
	
	@Before
	public void setup() {
		entity = new Entity(10);
	}

	/*@Test
	public void testAddComponent() {
		Component component = new DisplayComponent("First");
		entity.addComponent(component);
		List<Component> components = entity.getComponents();
		assertEquals("First", components.get(0).getName());
	}*/

}
