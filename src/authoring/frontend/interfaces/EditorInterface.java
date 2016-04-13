package authoring.frontend.interfaces;

/**
 * This interface handles the editing of various entities and their
 * components.
 * @author benchesnut
 *
 */
public interface EditorInterface extends AuthoringDisplayElement {

	/**
	 * Takes in as a parameter an entity and returns the (possibly)
	 * updated version of that entity.
	 * @param oldEntity
	 * @return newEntity
	 */
	DisplayEntityInterface edit(DisplayEntityInterface oldEntity);
}
