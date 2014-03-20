import java.util.List;


public class bgMain {

	public static void main(String[] args) {
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
	}

}
