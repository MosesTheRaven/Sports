package sports.base;

public class WorldOfSport {
	private SportStructure worldSportStructure = null;
	private static WorldOfSport instance = null;
	
	private WorldOfSport(){}
	
	public static WorldOfSport getWorldOfSport(){
		if(instance == null){
			instance = new WorldOfSport();
		}
		return instance;
	}
	
	public void setWorldSportStructure(SportStructure s){
		this.worldSportStructure = s;
	}
	
	public SportStructure getWorldSportStructure(){
		return worldSportStructure;
	}
}
