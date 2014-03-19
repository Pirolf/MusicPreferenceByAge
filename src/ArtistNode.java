
public class ArtistNode extends BNode{
	private static final int numAgeGroups = 10;
	private static final int startAge = 10;
	private static final int ageCoverage = 5;
	private String artistName;
	private int artistKey = 0;
	private AgeGroupNode[] ageGroupArr;
	public ArtistNode(String artistName){
		super();
		this.artistName = artistName;
		artistKey = artistName.hashCode();
		ageGroupArr = new AgeGroupNode[numAgeGroups];
		int lastGroupHiAge = startAge - 1;
		for(int i = 0; i < ageGroupArr.length; i++){
			int currStartAge = lastGroupHiAge + 1;
			//10-14, 15-19, 20-24
			ageGroupArr[i] = new AgeGroupNode(currStartAge, currStartAge + ageCoverage - 1);
			lastGroupHiAge = currStartAge + ageCoverage - 1;
		}
	}
	
	public int getKey(){
		return artistKey;
	}
	public String getArtistName(){
		return artistName;
	}
	public AgeGroupNode[] getAgeGroupArr(){
		return ageGroupArr; 
	}
	public AgeGroupNode getAgeGroupByAge(int age){
		for(int i = 0; i < ageGroupArr.length; i++){
			if(age >= ageGroupArr[i].getLo() && age <= ageGroupArr[i].getHi()){
				return ageGroupArr[i];
			}
		}
		return null;
	}
	
	public static int getNumAgeGroups(){
		return numAgeGroups;
	}
	public static int getStartAge(){
		return startAge;
	}
	public static int getStepAge(){
		return ageCoverage;
	}
}
