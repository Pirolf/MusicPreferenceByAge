
public class BGTester {
	public static BipartiteGraph testBGConstrut(){
		BipartiteGraph bg = new BipartiteGraph();
		return bg;
	}
	public static boolean testAddArtist(String name){
		BipartiteGraph bg = new BipartiteGraph();
		bg.addArtist(name, 17);
		String retName =  bg.lookUpArtist(name).getArtistName();
		System.out.println(name + ", " + retName);
		return (name.equals(retName));
	}
}
