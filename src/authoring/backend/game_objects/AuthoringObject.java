//This entire file is part of my masterpiece
//Jonathan Ma

/*
 * The purpose of this code is to act as an abstract class for the game objects in the authoring environment.
 * This way, all of the authoring objects, like AuthoringEntity and AuthoringLevel, will be subclasses of
 * this class.Previously, all of the objects in the authoring were split into their own separate classes, 
 * but I decided that it would be much better design to use an abstract class since there were so many similar
 * features. For instance, each separate had the exact same "myInfo" Map and all of them also had the exact
 * same "equals()" method. Creating this abstract class shows good design and a good implementation of the 
 * Open-Closed principle taught earlier in the semester. This is good design because it eliminates duplicate
 * code from the previously separated object classes. Using this Open-Closed principle is key because it allows
 * for future programmers to easily extend from this abstract class if they ever want to create even more 
 * authoring environment objects to potentially mirror new engine objects. This will make extending much easier
 * since the basic outline is already covered in this abstract class. Additionally, this also means that future
 * classes can't modify this super class, meaning that it will remain constant and intact. Making sure that the
 * code you write is extendable is an important aspect of design and is probably one of the most important
 * things that I've learned from CS308. This class also shows smaller details of good design, such as avoiding
 * the use of magic-numbers (or magic-Strings in this case) by using static final variables. I also make sure
 * that all of the goblal variables are encapsulated and can only be accessed by getter and setter methods.
 * Finally, I use "Map" instead of using "HashMap" to declare new maps and for getter methods that return the
 * map. This further improves code extendability, as now other programmers can use these getter methods without
 * having to worry about the specific kind of map they're receiving. 
 */

package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;

public abstract class AuthoringObject {
	
	protected static final String TYPE = "Type";
	protected static final String NAME = "Name";
	protected static final String CLASS_TYPE = "Entity";
	protected static final String COMMA_SPLIT = ",";
	protected static final String SEMICOLON_SPLIT = ":";
	protected static final String UNDERSCORE_SPLIT = "_";
	protected static final String SPACE_SPLIT = " ";
	protected static final String PLUS_SPLIT = "+";
	protected static final String EMPTY = "";
	
	private String myName;
	private Map<String, String> myInfo;
	
	public AuthoringObject(String myName) {
		this.myName = myName;
		this.myInfo = new HashMap<String, String>();
		initializeInfo();
		initializeSpecificInfo();
	}
	
	private void initializeInfo() {
		myInfo.put(TYPE, this.getClass().getSimpleName());
		myInfo.put(NAME, myName);
	}
	
	protected abstract void initializeSpecificInfo();
	
	public Map<String, String> getInfo() {
		return myInfo;
	}
	
	public String getName() {
		return myName;
	}
	
	public void setName(String name) {
		this.myName = name;
		myInfo.put(NAME, myName);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof AuthoringObject) {
			AuthoringObject author = (AuthoringObject) object;
			if (this.myName.equals(author.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
