package fiuba.algo3.starcraft.logic.map;

public class Volcano implements Extractable {

	@Override
	public Resource extractResource() {
		return new VespeneGas();
	}
	 
}
