
import authoring_environment.frontend.design_interfaces.*;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AuthoringFrontendUseCase {
	
	TabBarInterface tabBar = new TabBar();
	
	/**
	 * Use case 1: Create a new Tower and open it in the TowerEditor
	 * @author benchesnut
	 * 
	 */
	public TowerDisplayEntity createNewTower() {
		return (TowerDisplayEntity) tabBar.getTowersPanel().getEditor().edit(new TowerDisplayEntity());
	}

	/**
	 * Use case 2: Set a tower's attack damage to 10
	 * @author benchesnut
	 *
	 */
	public void setTowerAttackDamage() {
		TowerDisplayEntity tower = new TowerDisplayEntity();
		tower.setAttackDamage(10);
	}
	
	
	class TabBar implements TabBarInterface {
		
		TowersPanel towersPanel;
		
		public Node buildNode() {
			return new TabPane();	// not needed for this use case
		}
		
		public PanelInterface getTowersPanel() {
			return towersPanel;
		}
		
		public PanelInterface getGamePanel() {
			return null;	// not needed for this use case
		}
		
		public PanelInterface getModesPanel() {
			return null;	// not needed for this use case
		}
		
		public PanelInterface getLevelsPanel() {
			return null;	// not needed for this use case
		}
		
		public PanelInterface getEnemiesPanel() {
			return null;	// not needed for this use case
		}
	}
	
	class TowersPanel implements PanelInterface {
		
		TowerEditor towerEditor;
		
		public Node buildNode() {
			return new Pane();	//not needed for this use case
		}
		
		public TowerEditor getEditor() {
			return towerEditor;
		}
	}
	
	class TowerEditor implements EditorInterface {
		
		public Node buildNode() {
			return new Pane();	// not needed for this use case
		}
		
		public TowerDisplayEntity edit(DisplayEntityInterface oldEntity) {
			TowerDisplayEntity newEntity = update((TowerDisplayEntity) oldEntity);
			return newEntity;
		}
		
		private TowerDisplayEntity update(TowerDisplayEntity t) {
			// do something to the tower; irrelevant for this use case
			return t;
		}
	}
	
	class TowerDisplayEntity implements DisplayEntityInterface {
		
		private int attackDamage;
		
		public Node buildNode() {
			return new ImageView();	// not needed for this use case
		}
		
		public void setAttackDamage(int k) {
			attackDamage = k;
		}
	}
}