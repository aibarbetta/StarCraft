package fiuba.algo3.starcraft.logic.test.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.starcraft.logic.map.Parcel;
import fiuba.algo3.starcraft.logic.map.Point;
import fiuba.algo3.starcraft.logic.map.areas.LandType;
import fiuba.algo3.starcraft.logic.map.areas.Surface;
import fiuba.algo3.starcraft.logic.map.exceptions.NoResourcesToExtract;
import fiuba.algo3.starcraft.logic.map.resources.ExtractableType;
import fiuba.algo3.starcraft.logic.map.resources.ReservoirType;
import fiuba.algo3.starcraft.logic.templates.units.terran.MarineTemplate;
import fiuba.algo3.starcraft.logic.units.MuggleUnit;


public class ParcelTest {

	Parcel parcel;

	@Before
	public void before() {
		parcel = new Parcel(new Point(0,0), 10);
	}

	@Test
	public void testParcelRecognizesIfContainsAPointFromMap() {
		assertTrue(parcel.containsPoint(new Point(5,5)));
		assertFalse(parcel.containsPoint(new Point(11,5)));
	}

	@Test 
	public void testParcelHasToDisablePassingThroughALandUnitWhenIsOfAirType() {
		parcel.setSurface(LandType.air);
		MuggleUnit marine = (new MarineTemplate()).create(new Point(500,500));

		assertFalse(parcel.letPass(marine));
	}
	
	@Test
	// TODO Implementar
	public void testParcelCanBuildABuildingInsideAParcel() {

	}
	
	@Test
	// TODO Implementar
	public void testParcelHasToDisablePassingThroughALandWithABuilding() {

	}
	
	@Test
	public void testParcelHasMinerals() {
		parcel.setSurface(ReservoirType.volcano);
		
		Surface landWithVolcano = parcel.getLandForExplotation();
		try {
			assertEquals(landWithVolcano.extractResource(), ExtractableType.gas);
		} catch (NoResourcesToExtract e) {
			e.printStackTrace();
		}
	}
}
