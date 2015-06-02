package fiuba.algo3.starcraft.logic.templates;

import fiuba.algo3.starcraft.logic.structures.GasExploiter;


public class RefineriaTemplate extends StructureTemplate {
	
	private static RefineriaTemplate instance = new RefineriaTemplate();

	private RefineriaTemplate() {
		name = "Refineria";
		value = new Value(100,0);
		constructionTime = 6;
		health = 750;
	}
	
	public static RefineriaTemplate getInstance() {
		return instance;
	}
	
	public GasExploiter create() {
		// TODO Resolver tiempo de construccion
		return new GasExploiter(new Life(health));
	}

}