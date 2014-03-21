import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class bgMain {

	public static void main(String[] args) throws IOException{
		File dataFile = new File("src/data");
		Scanner inFile = new Scanner(dataFile);
		String currLine = "";
		String currArtistName = "";
		int currListenerAge = 0;
		String[] currArtistAgePair;
		BipartiteGraph bg = new BipartiteGraph();
		HashMap<Integer, ArtistNode> bgMap = bg.getBGHashMap();
		while(inFile.hasNextLine()){
			currLine = inFile.nextLine();
			currArtistAgePair = currLine.split(",");
			currArtistName = currArtistAgePair[0];
			currListenerAge = Integer.parseInt(currArtistAgePair[1]);
			if(!bgMap.containsKey(currArtistName.hashCode())){
				//if this artist does not exist
				bg.addArtist(currArtistName, currListenerAge);
			}else{
				//else increment the artist's listeners
				bgMap.get(currArtistName.hashCode()).getAgeGroupByAge(currListenerAge).incNumListeners();
			}
			
		}
		inFile.close();
		
		//Here's the fun part
		bg.rankArtistsForAllAges();
		int age1 = 28, age2 = 50;//change ages later
		List<ArtistNode> mostD = bg.findMostDArtists(age1, age2);
		System.out.println("Most Distinctive Artists for " + age1 + " and " + age2 + " are: ");
		printArtistList(mostD);
		System.out.println();
		
		List<ArtistNode> mostH = bg.findMostHArtists();
		System.out.println("Most Homogeneous Artists: ");
		printArtistList(mostH);
		System.out.println();
		
		
		ArtistNode mostP = bg.findMostPopularArtistByAge(age1);
		System.out.println("The Most Popular Artist for age " + age1 + " is: " + mostP.getArtistName());
		System.out.println();
		
		List<ArtistNode> mostS = bg.findMostSArtists(age1, age2);
		System.out.println("Most Shared Artists between " + age1 + " and " + age2 + " are: ");
		printArtistList(mostS);
		
	}
	
	public static void oldTest(){
		BGTester.testAddArtist("YUCHENG");
		BGTester.testAddArtist("Kevin Alford");
		BipartiteGraph bg = BGTester.testBGConstrut();
		bg.addArtist("YUCHENG", 17);
		
		ArtistNode yucheng = bg.getBGHashMap().get(("YUCHENG").hashCode());
		yucheng.getAgeGroupByAge(17).incNumListeners();
		yucheng.getAgeGroupByAge(17).incNumListeners();
		
		yucheng.getAgeGroupByAge(30).incNumListeners();
		yucheng.getAgeGroupByAge(34).incNumListeners();
		yucheng.getAgeGroupByAge(30).incNumListeners();
		yucheng.getAgeGroupByAge(34).incNumListeners();
		yucheng.getAgeGroupByAge(30).incNumListeners();
		
		yucheng.getAgeGroupByAge(22).incNumListeners();
		yucheng.getAgeGroupByAge(22).incNumListeners();
		yucheng.getAgeGroupByAge(22).incNumListeners();
		yucheng.getAgeGroupByAge(22).incNumListeners();
		System.out.println(bg.getNumListenersByArtistAge("YUCHENG", 18));
		System.out.println(bg.getNumListenersByArtistAge("YUCHENG", 23));
		System.out.println(bg.getNumListenersByArtistAge("YUCHENG", 32));
		
		bg.addArtist("Kevin", 16);
		bg.getBGHashMap().get(("Kevin").hashCode()).getAgeGroupByAge(33).incNumListeners();
		bg.getBGHashMap().get(("Kevin").hashCode()).getAgeGroupByAge(31).incNumListeners();
		bg.getBGHashMap().get(("Kevin").hashCode()).getAgeGroupByAge(32).incNumListeners();
		
		
		bg.addArtist("Adam", 17);
		bg.getBGHashMap().get(("Adam").hashCode()).getAgeGroupByAge(16).incNumListeners();
		bg.getBGHashMap().get(("Adam").hashCode()).getAgeGroupByAge(16).incNumListeners();

		bg.getBGHashMap().get(("Adam").hashCode()).getAgeGroupByAge(16).incNumListeners();

		bg.getBGHashMap().get(("Adam").hashCode()).getAgeGroupByAge(16).incNumListeners();
		
		//bg.rankArtistsByAge(16);
		//System.out.println(bg.findMostPopularArtistByAge(16).getArtistName());
		//bg.rankArtistsByAge(30);
		//System.out.println(bg.findMostPopularArtistByAge(31).getArtistName());
		
		bg.rankArtistsForAllAges();
		//System.out.println(bg.findMostPopularArtistByAge(31).getArtistName());
		//System.out.println(bg.findMostPopularArtistByAge(19).getArtistName());
		
		System.out.println(bg.findMostSharedArtist(18, 32).getArtistName());
		System.out.println(bg.findMostDistinctiveArtist(18, 32).getArtistName());
		
		List<ArtistNode> mostS = bg.findMostSArtists(18, 32);
		for(int i = 0; i < mostS.size(); i++){
			System.out.print(mostS.get(i).getArtistName() +", ");
		}
		
		List<ArtistNode> mostD = bg.findMostDArtists(18, 32);
		for(int i = 0; i < mostD.size(); i++){
			System.out.print(mostD.get(i).getArtistName() +", ");
		}
		
		ArtistNode mostPop31 = bg.findMostPopularArtistByAge(31);
		System.out.println(mostPop31.getArtistName());
		
		ArtistNode mostPop15 = bg.findMostPopularArtistByAge(15);
		System.out.println(mostPop15.getArtistName());
		
		List<ArtistNode> mostH = bg.findMostHArtists();
		for(int i = 0; i < mostH.size(); i++){
			System.out.print(mostH.get(i).getArtistName() +", ");
		}
		
	}
	public static void printArtistList(List<ArtistNode> artList){
		for(int i = 0 ; i < 10; i++){
			System.out.print(artList.get(i).getArtistName());
			if(i != artList.size() - 1){
				 System.out.print(", ");
			}
			// 5 artists per line
			if((i + 1) % 5 == 0){
				System.out.println();
			}
			
		}
		System.out.println();
	}
}
