
public class bgMain {

	public static void main(String[] args) {
		BGTester.testAddArtist("YUCHENG");
		BGTester.testAddArtist("Kevin Alford");
		BipartiteGraph bg = BGTester.testBGConstrut();
		bg.addArtist("YUCHENG", 17);
		System.out.println(bg.getNumListenersByArtistAge("YUCHENG", 18));
		ArtistNode yucheng = bg.getBGHashMap().get(("YUCHENG").hashCode());
		yucheng.getAgeGroupByAge(17).incNumListeners();
		System.out.println(bg.getNumListenersByArtistAge("YUCHENG", 18));
		
		bg.addArtist("Kevin", 16);
		System.out.println(bg.findMostPopularArtistByAge(17).getArtistName());
	}

}
