package fiuba.algo3.starcraft.logic.test.structures;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import fiuba.algo3.starcraft.logic.player.Resources;
import fiuba.algo3.starcraft.logic.structures.Construction;
import fiuba.algo3.starcraft.logic.structures.ConstructionStructure;
import fiuba.algo3.starcraft.logic.structures.exceptions.ConstructionNotFinished;
import fiuba.algo3.starcraft.logic.structures.exceptions.InsufficientResources;
import fiuba.algo3.starcraft.logic.structures.exceptions.QuotaExceeded;
import fiuba.algo3.starcraft.logic.structures.exceptions.TemplateNotFound;
import fiuba.algo3.starcraft.logic.templates.qualities.Life;
import fiuba.algo3.starcraft.logic.templates.units.UnitTemplate;
import fiuba.algo3.starcraft.logic.templates.units.terran.MarineTemplate;
import fiuba.algo3.starcraft.logic.units.MuggleUnit;
import fiuba.algo3.starcraft.logic.units.Unit;

public class ConstructionStructureTest {

	@Test
	public void testUpdateRegeneratesShield() {
		Life life = new Life(100, 140);
		ConstructionStructure constructor = new ConstructionStructure(null, life, null, null);

		constructor.reduceLife(55);
		assertEquals(life.getShield(), 85);		
		constructor.update(null);
		
		assertEquals(life.getShield(), 105);
	}
	
	@Test(expected = TemplateNotFound.class)
	public void testCreateUnitWithInvalidNameThrowsTemplateNotFound() throws QuotaExceeded, InsufficientResources, TemplateNotFound {
		ConstructionStructure constructor = new ConstructionStructure(null, null, null, new LinkedList<UnitTemplate>());

		constructor.create("Hola", null, null, 0, 0);
	}	

	@Test(expected = InsufficientResources.class)
	public void testCreateUnitWithNoResourcesThrowsInsufficientResources() throws QuotaExceeded, InsufficientResources, TemplateNotFound {
		Collection<UnitTemplate> templates =  new LinkedList<UnitTemplate>();
		templates.add(MarineTemplate.getInstance());
		ConstructionStructure constructor = new ConstructionStructure(null, null, null, (Iterable<UnitTemplate>) templates);

		constructor.create("Marine", null, new Resources(0,0), 0, 5);
	}
	
	@Test(expected = QuotaExceeded.class)
	public void testCreateUnitWithNoPopulationSpaceThrowsQuotaExceeded() throws QuotaExceeded, InsufficientResources, TemplateNotFound {
		Collection<UnitTemplate> templates =  new LinkedList<UnitTemplate>();
		templates.add(MarineTemplate.getInstance());
		ConstructionStructure constructor = new ConstructionStructure(null, null, null, (Iterable<UnitTemplate>) templates);

		constructor.create("Marine", null, new Resources(0,0), 0, 0);
	}	
	
	@Test
	public void testCreateUnitReturnsConstructionWithUnitInside() throws QuotaExceeded, InsufficientResources, TemplateNotFound, ConstructionNotFinished {
		Collection<UnitTemplate> templates =  new LinkedList<UnitTemplate>();
		templates.add(MarineTemplate.getInstance());
		ConstructionStructure constructor = new ConstructionStructure(null, null, null, (Iterable<UnitTemplate>) templates);

		Construction<Unit> marineConstruction = constructor.create("Marine", null, new Resources(100, 100), 0, 5);
		while(!marineConstruction.itsFinished())
			marineConstruction.lowerRelease();
		
		assertEquals(marineConstruction.gather().getClass(), MuggleUnit.class);
	}	
	
}