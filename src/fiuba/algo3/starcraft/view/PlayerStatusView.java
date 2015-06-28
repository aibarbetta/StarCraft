package fiuba.algo3.starcraft.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3.starcraft.game.StarCraft;
import fiuba.algo3.starcraft.logic.player.Player;

public class PlayerStatusView extends JPanel {

	private static final long serialVersionUID = 7906877651611716146L;

	private StarCraft game;
	
	private JLabel playerName = new JLabel();
	private JLabel mineral = new JLabel();
	private JLabel gas = new JLabel();
	
	PlayerStatusView(StarCraft game) {
		this.game = game;
		
		add(playerName);
		
		ImageIcon mineralIcon = new ImageIcon(getClass().getResource("presets/mineralIcon.png"));
		mineral.setIcon(mineralIcon);
		ImageIcon gasIcon = new ImageIcon(getClass().getResource("presets/gasIcon.png"));
		gas.setIcon(gasIcon);
		
		add(mineral);
		add(gas);
	}
	
	public void showActivePlayerStatus() {
		Player player = game.getActivePlayer();
		
		playerName.setText(player.getName());
		playerName.setForeground(player.getColor());
		
		mineral.setText(Integer.toString(player.getMineral()));
		gas.setText(Integer.toString(player.getGas()));
		
	}
}
