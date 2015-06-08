package fiuba.algo3.starcraft.logic.test.player.integration.units;

import static org.junit.Assert.*;

import org.junit.Test;

import fiuba.algo3.starcraft.logic.player.Player;
import fiuba.algo3.starcraft.logic.player.Resources;
import fiuba.algo3.starcraft.logic.structures.Construction;
import fiuba.algo3.starcraft.logic.structures.ConstructionStructure;
import fiuba.algo3.starcraft.logic.structures.builders.ProtossBuilder;
import fiuba.algo3.starcraft.logic.structures.exceptions.ConstructionNotFinished;
import fiuba.algo3.starcraft.logic.structures.exceptions.InsufficientResources;
import fiuba.algo3.starcraft.logic.structures.exceptions.MissingStructureRequired;
import fiuba.algo3.starcraft.logic.structures.exceptions.QuotaExceeded;
import fiuba.algo3.starcraft.logic.structures.exceptions.TemplateNotFound;
import fiuba.algo3.starcraft.logic.templates.structures.protoss.AccesoTemplate;
import fiuba.algo3.starcraft.logic.units.Unit;

public class DragonTest {

	@Test
	public void testDragonCreationWith1Pilon1AccesoAnd120M50G() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished {
		Resources initialResources = new Resources(375,50);
		Player player = new Player(null, null, ProtossBuilder.getInstance(), null, initialResources);
		player.newStructureWithName("Pilon", null);
		for(int i = 0; i < 6; i++) player.newTurn();
		
		player.pays(150, 0);
		ConstructionStructure acceso = AccesoTemplate.getInstance().create(null);
		player.receiveNewStructure(acceso);
		
		Construction<Unit> construction = acceso.create("Dragon", null, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit dragon = (Unit) construction.gather();
		player.receiveNewUnit(dragon);
		assertEquals(player.getMineral(), 0);
		assertEquals(player.getGas(), 0);
		assertEquals(player.currentPopulation(), 2);
	}
}