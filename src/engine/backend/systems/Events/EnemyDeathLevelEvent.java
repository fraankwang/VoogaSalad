package engine.backend.systems.Events;

public class EnemyDeathLevelEvent extends LevelEvent {

	public EnemyDeathLevelEvent(int levelindex) {
		super.setLevelindex(levelindex);
	}

}
