package fiuba.algo3.starcraft.logic.map;

import java.util.ArrayList;

public class Map {
	final double PARCEL_SIDE = 10;

	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();

	private double side;
	
	public Map(double side) {
		this.side = side;
		createParcels(side/PARCEL_SIDE);
	}
	
	private void createParcels(double numberOfParcels) {
		for (int x = 0 ; x < numberOfParcels ; x ++) {
			for (int y = 0 ; y < numberOfParcels ; y ++) {
				Parcel parcel = new Parcel(new Point(x * PARCEL_SIDE,y * PARCEL_SIDE),PARCEL_SIDE);
				parcel.setSurface(LandType.land);
				parcels.add(parcel);
			}
		}
	}
	
	public void resetMap() {
		for (Parcel parcel : parcels) {
			parcel.setSurface(LandType.land);
		}
	}
	
	public Parcel getParcelContainingPoint(Point point) throws IndexOutOfBoundsException{
		for (Parcel parcel: parcels) {
			if (parcel.containsPoint(point)) {
				return parcel;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	public ArrayList<Parcel> getParcelsContainedInARect(Point point, int side) {
		ArrayList<Parcel> parcels = new ArrayList<Parcel>();
		
		for (int x = (int)point.getX() ; x < (int)point.getX() + side ; x += PARCEL_SIDE) {
			for (int y = (int)point.getY() ; y < (int)point.getY() + side ; y += PARCEL_SIDE) {	
				Parcel parcel = getParcelContainingPoint(new Point(x,y));
				parcels.add(parcel);
			}
		}
		return parcels;
	}
	
	public boolean isPointInsideRadiousOfPivotePoint(Point pivotePoint, double radious, Point otherPoint) {
		return (pivotePoint.distance(otherPoint)) <= radious;
	}
}
