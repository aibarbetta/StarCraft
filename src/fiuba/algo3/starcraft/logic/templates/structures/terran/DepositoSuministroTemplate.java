package fiuba.algo3.starcraft.logic.templates.structures.terran;

import fiuba.algo3.starcraft.logic.structures.Depot;
import fiuba.algo3.starcraft.logic.templates.qualities.Life;
import fiuba.algo3.starcraft.logic.templates.qualities.Value;
import fiuba.algo3.starcraft.logic.templates.structures.StructureTemplate;

public class DepositoSuministroTemplate extends StructureTemplate {
	
	private static DepositoSuministroTemplate instance = new DepositoSuministroTemplate();

	private DepositoSuministroTemplate() {
		name = "Deposito Suministro";
		value = new Value(100,0);
		constructionTime = 6;
		health = 500;
	}

	public Depot create() {
		//TODO: Resolver tiempo de construccion
		return new Depot(name, new Life(health));
	}
	public static DepositoSuministroTemplate getInstance() {
		return instance;
	}

}