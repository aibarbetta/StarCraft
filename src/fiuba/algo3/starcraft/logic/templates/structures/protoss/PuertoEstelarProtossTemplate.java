package fiuba.algo3.starcraft.logic.templates.structures.protoss;

import fiuba.algo3.starcraft.logic.map.Point;
import fiuba.algo3.starcraft.logic.structures.ConstructionStructure;
import fiuba.algo3.starcraft.logic.templates.qualities.Life;
import fiuba.algo3.starcraft.logic.templates.qualities.Value;
import fiuba.algo3.starcraft.logic.templates.structures.ConstructionTemplate;
import fiuba.algo3.starcraft.logic.templates.units.protoss.NaveTransporteProtossTemplate;
import fiuba.algo3.starcraft.logic.templates.units.protoss.ScoutTemplate;

public class PuertoEstelarProtossTemplate extends ConstructionTemplate {

    private static PuertoEstelarProtossTemplate instance = new PuertoEstelarProtossTemplate();

    private PuertoEstelarProtossTemplate() {
        name = "Puerto Estelar";
        value = new Value(150,150);
        constructionTime = 10;
        health = 600;
        shield = 600;
        enabledTemplates.add(ScoutTemplate.getInstance());
        enabledTemplates.add(NaveTransporteProtossTemplate.getInstance());
    }

    public static PuertoEstelarProtossTemplate getInstance() {
        return instance;
    }

	public ConstructionStructure create(Point position) {
		return new ConstructionStructure(name, new Life(health, shield), position, enabledTemplates);
	}
}

