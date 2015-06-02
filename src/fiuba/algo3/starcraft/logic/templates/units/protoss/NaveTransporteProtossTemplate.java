package fiuba.algo3.starcraft.logic.templates.units.protoss;

import fiuba.algo3.starcraft.logic.templates.qualities.Life;
import fiuba.algo3.starcraft.logic.templates.qualities.Value;
import fiuba.algo3.starcraft.logic.templates.units.TransportUnitTemplate;
import fiuba.algo3.starcraft.logic.units.TransportUnit;

public class NaveTransporteProtossTemplate extends TransportUnitTemplate {

    private static NaveTransporteProtossTemplate instance = new NaveTransporteProtossTemplate();

    private NaveTransporteProtossTemplate() {
        name = "Nave Transporte";
        value = new Value(200,0);
        constructionTime = 8;
        vision = 8;
        populationQuota = 2;
        health = 80;
        shield = 60;
        capacity = 8;
    }

    public static NaveTransporteProtossTemplate getInstance() {
        return instance;
    }
    
    public TransportUnit create() {
        // TODO Resolver tiempo de construccion
        return new TransportUnit(name, new Life(health, shield), vision, populationQuota, capacity);
    }
}