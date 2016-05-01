package engine.controller;

import java.util.List;

import engine.backend.game_features.ShopItem;

public interface IEngineController {
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height, boolean show);

	public void updateShop(List<ShopItem> shoplist);

	public void waveIsOver(double time);

	public void levelIsWon();

	public void levelIsLost();
}
