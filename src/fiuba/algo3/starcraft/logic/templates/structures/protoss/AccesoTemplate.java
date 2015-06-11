package fiuba.algo3.starcraft.logic.templates.structures.protoss;

import java.util.LinkedList;

import fiuba.algo3.starcraft.logic.map.Point;
import fiuba.algo3.starcraft.logic.structures.ConstructionStructure;
import fiuba.algo3.starcraft.logic.templates.qualities.Life;
import fiuba.algo3.starcraft.logic.templates.qualities.Value;
import fiuba.algo3.starcraft.logic.templates.structures.ConstructionTemplate;
import fiuba.algo3.starcraft.logic.templates.units.UnitTemplate;
import fiuba.algo3.starcraft.logic.templates.units.protoss.DragonTemplate;
import fiuba.algo3.starcraft.logic.templates.units.protoss.ZealotTemplate;

public class AccesoTemplate extends ConstructionTemplate {

    public AccesoTemplate() {
        name = "Acceso";
        value = new Value(150,0);
        constructionTime = 8;
        health = 500;
        shield = 500;
        enabledTemplates = new LinkedList<UnitTemplate>();
        enabledTemplates.add(new ZealotTemplate());
        enabledTemplates.add(new DragonTemplate());
    }

    public ConstructionStructure create(Point position) {
        return new ConstructionStructure(name, new Life(health, shield), position, enabledTemplates);
    }
}