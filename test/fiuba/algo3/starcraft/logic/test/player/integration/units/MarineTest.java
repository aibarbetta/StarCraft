package fiuba.algo3.starcraft.logic.test.player.integration.units;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.starcraft.logic.map.Map;
import fiuba.algo3.starcraft.logic.map.NoResourcesToExtract;
import fiuba.algo3.starcraft.logic.map.Point;
import fiuba.algo3.starcraft.logic.player.Player;
import fiuba.algo3.starcraft.logic.player.Resources;
import fiuba.algo3.starcraft.logic.structures.Construction;
import fiuba.algo3.starcraft.logic.structures.ConstructionStructure;
import fiuba.algo3.starcraft.logic.structures.builders.TerranBuilder;
import fiuba.algo3.starcraft.logic.structures.exceptions.ConstructionNotFinished;
import fiuba.algo3.starcraft.logic.structures.exceptions.InsufficientResources;
import fiuba.algo3.starcraft.logic.structures.exceptions.MissingStructureRequired;
import fiuba.algo3.starcraft.logic.structures.exceptions.QuotaExceeded;
import fiuba.algo3.starcraft.logic.structures.exceptions.TemplateNotFound;
import fiuba.algo3.starcraft.logic.templates.structures.terran.BarracaTemplate;
import fiuba.algo3.starcraft.logic.units.Unit;

public class MarineTest {
	Map map;
	Point position;
	Point position2;
	Resources initialResources;
	Resources insufficientResources;
	Player player;
	@Before
	public void before() {
		initialResources = new Resources(100000,0);
		insufficientResources = new Resources(200,0);
		map = new Map(1000);
		position = new Point(54,70);
		position2 = new Point(10,70);
		player = new Player(null, null, new TerranBuilder(), position, initialResources, map);
	}

	@Test
	public void testMarineCreationWith1DepositoSuministro1BarracaAnd50M() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		
		Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit marine = construction.gather();
		player.receiveNewUnit(marine);
		
		assertEquals(player.currentPopulation(), 1);
	}

	@Test
	public void test2MarineCreationWith1DepositoSuministro1BarracaAnd100M() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		
		Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit marine = construction.gather();
		player.receiveNewUnit(marine);
		Construction<Unit> construction1 = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction1.itsFinished()) {
			construction1.lowerRelease();
		}
		Unit marine1 = construction1.gather();
		player.receiveNewUnit(marine1);
		
		assertEquals(player.currentPopulation(), 2);
	}
	
	@Test(expected = InsufficientResources.class)
	public void test2MarineCreationWith1DepositoSuministro1BarracaAnd50M() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player = new Player(null, null, new TerranBuilder(), position, insufficientResources, map);
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit marine = construction.gather();
		player.receiveNewUnit(marine);
		
		Construction<Unit> construction1 = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction1.itsFinished()) {
			construction1.lowerRelease();
		}
		Unit marine1 = construction1.gather();
		player.receiveNewUnit(marine1);
	}
	
	@Test(expected = QuotaExceeded.class)
	public void testMarineCreationWith1BarracaAnd50M() throws InsufficientResources, QuotaExceeded, TemplateNotFound, ConstructionNotFinished {
		player.pays(150, 0);
		ConstructionStructure barraca = new  BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		
		Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit marine = construction.gather();
		player.receiveNewUnit(marine);
		
		assertEquals(player.currentPopulation(), 2);
	}
	
	@Test
	public void test2MarineCreationAnd1MarineDeadLeavesPopulationAt1() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction.itsFinished()) {
			construction.lowerRelease();
		}
		Unit marine = construction.gather();
		player.receiveNewUnit(marine);
		Construction<Unit> construction1 = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
		while(!construction1.itsFinished()) {
			construction1.lowerRelease();
		}
		Unit marine1 = construction1.gather();
		player.receiveNewUnit(marine1);
		assertEquals(player.currentPopulation(), 2);
		
		marine1.reduceLife(40);
		player.newTurn();
		
		assertEquals(player.currentPopulation(), 1);
	}
	
	@Test
	public void test4MarineCreationAnd4MarineDeadLeavesPopulationAt0() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);

		for (int i = 0; i < 4; i++) {
			Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
			while(!construction.itsFinished()) {
				construction.lowerRelease();
			}
			Unit marine = construction.gather();
			player.receiveNewUnit(marine);
			
			assertEquals(player.currentPopulation(), 1);
			marine.reduceLife(40);
			player.newTurn();
		}
		
		assertEquals(player.currentPopulation(), 0);
	}
	
	@Test
	public void test7MarineCreationAnd49TurnsLeavesPopulationAt7() throws InsufficientResources, QuotaExceeded, TemplateNotFound, MissingStructureRequired, ConstructionNotFinished, NoResourcesToExtract {
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.newStructureWithName("Deposito Suministro", position);
		for(int i = 0; i < 7; i++) player.newTurn();
		player.pays(150, 0);
		ConstructionStructure barraca = new BarracaTemplate().create(position);
		player.receiveNewStructure(barraca);
		for (int i = 0; i < 7; i++) {
			Construction<Unit> construction = barraca.create("Marine", position, player.getResources(), player.currentPopulation(), player.populationQuota());
			while(!construction.itsFinished()) {
				construction.lowerRelease();
			}
			Unit marine = construction.gather();
			player.receiveNewUnit(marine);
			assertEquals(player.currentPopulation(), i + 1);
		}
		
		assertEquals(player.currentPopulation(), 7);
	}
}
