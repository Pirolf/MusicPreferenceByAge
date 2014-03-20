import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class BipartiteGraph {
	private HashMap<Integer, ArtistNode> BGMap;
	public BipartiteGraph(){
		BGMap = new HashMap<Integer, ArtistNode>();
	}
	
	public void addArtist(String artistName, int listenerAge){
		ArtistNode artist = new ArtistNode(artistName);
		artist.getAgeGroupByAge(listenerAge).incNumListeners();
		BGMap.put(artist.getKey(), artist);
	}
	
	public ArtistNode lookUpArtist(String name){
		if(BGMap.containsKey(name.hashCode())){
			return BGMap.get(name.hashCode());
		}else{
			return null;
		}
	}
	
	public int getNumListenersByArtistAge(String name, int age){
		ArtistNode artist = lookUpArtist(name);
		if(artist == null){
			return 0;
		}else{
			return artist.getAgeGroupByAge(age).getNumListners();
		}
	}
	/**
	 * Rank all artists given an age
	 * @param age
	 */
	public void rankArtistsByAge(int age){
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		int[] listenerArr = new int[allArtists.size()];
		int[] rankings = new int[allArtists.size()];
		//init all ranking items to 1
		for(int i = 0; i < rankings.length; i++){
			rankings[i] = 1;
		}
		int j = 0;
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    listenerArr[j] = currArtist.getAgeGroupByAge(age).getNumListners();
		    j++;
		}
		
		for(int p = 0; p < listenerArr.length; p++){
			for(int q = p + 1; q < listenerArr.length; q++){
				//inc the smaller one's ranking
				if(listenerArr[p] < listenerArr[q]){
					rankings[p] ++;
				}else if(listenerArr[p] > listenerArr[q]){
					rankings[q] ++;
				}//if equal, tie, do nothing
			}
		}
		//update rankings
		int k = 0;
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    currArtist.getAgeGroupByAge(age).setRanking(rankings[k]);
		    k++;
		}
	}
	public void rankArtistsForAllAges(){
		int startAge = ArtistNode.getStartAge();
		int ageCoverage = ArtistNode.getStepAge();
		int currStartAge = startAge;
		for(int i = 0; i < ArtistNode.getNumAgeGroups(); i++){
			rankArtistsByAge(currStartAge);
			currStartAge += ageCoverage * i;
		}
	}
	
	public ArtistNode findMostSharedArtist(int age1, int age2){
		int minRankingDiff = -1;//sentinal value
		ArtistNode mostSharedArtist = null;
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		//traverse through all artists
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int rk1 = currArtist.getAgeGroupByAge(age1).getRanking();
		    int rk2 = currArtist.getAgeGroupByAge(age2).getRanking();
		    int currDiff = Math.abs(rk1 - rk2);
		    if((minRankingDiff != -1 && currDiff < minRankingDiff) || minRankingDiff == -1){
		    	minRankingDiff = currDiff;
		    	mostSharedArtist = currArtist;
		    }
		}
		return mostSharedArtist;
	}
	public List<ArtistNode> findMostSArtists(int age1, int age2){
		List<Integer> rkDiff = new LinkedList<Integer>();
		List<ArtistNode> sArtists = new LinkedList<ArtistNode>();
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int rk1 = currArtist.getAgeGroupByAge(age1).getRanking();
		    int rk2 = currArtist.getAgeGroupByAge(age2).getRanking();
		    int currDiff = Math.abs(rk1 - rk2);
		    if(rkDiff.isEmpty()){
		    	rkDiff.add(currDiff);
		    	sArtists.add(currArtist);
		    }
		    boolean replaced = false;
		    for(int i = 1; i < rkDiff.size() && !replaced; i++){
		    	 if(currDiff <= rkDiff.get(i)){
		    		 rkDiff.add(i, currDiff);
		    		 sArtists.add(i, currArtist);
		    		 replaced = true;
		    	 }
		    }
		}
		return sArtists;
	}
	public ArtistNode findMostDistinctiveArtist(int age1, int age2){
		int maxRkDiff = -1;
		ArtistNode mostDistArtist = null;
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		//traverse through all artists
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int rk1 = currArtist.getAgeGroupByAge(age1).getRanking();
		    int rk2 = currArtist.getAgeGroupByAge(age2).getRanking();
		    int currDiff = Math.abs(rk1 - rk2);
		    if(currDiff > maxRkDiff){
		    	maxRkDiff = currDiff;
		    	mostDistArtist = currArtist;
		    }
		}
		return mostDistArtist;
	}
	public List<ArtistNode> findMostDArtists(int age1, int age2){
		List<ArtistNode> dArtists = new LinkedList<ArtistNode>();
		List<Integer> rkDiff = new LinkedList<Integer>();
		
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int rk1 = currArtist.getAgeGroupByAge(age1).getRanking();
		    int rk2 = currArtist.getAgeGroupByAge(age2).getRanking();
		    int currDiff = Math.abs(rk1 - rk2);
		    if(rkDiff.isEmpty()){
		    	rkDiff.add(currDiff);
		    	dArtists.add(currArtist);
		    }
		    boolean replaced = false;
		    for(int i = 1; i < rkDiff.size() && !replaced; i++){
		    	 if(currDiff >= rkDiff.get(i)){
		    		 rkDiff.add(i, currDiff);
		    		 dArtists.add(i, currArtist);
		    		 replaced = true;
		    	 }
		    }
		}
		return dArtists;
	}
	public ArtistNode findMostPopularArtistByAge(int age){
		int minRk = -1;
		ArtistNode mostPopularArtist = null;
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int currRk = currArtist.getAgeGroupByAge(age).getRanking();
		    if((minRk != -1 && currRk < minRk) || minRk == -1){
		    	minRk = currRk;
		    	mostPopularArtist = currArtist;
		    }
		    
		}
		return mostPopularArtist;
	}
	/**
	 * for finding the most homogeneous artist
	 * @param artist
	 * @return
	 */
	public int findWorstRankingByArtist(ArtistNode artist){
		int worstRk = -1;//worstRk should be the max rk
		int startAge = ArtistNode.getStartAge();
		int ageCoverage = ArtistNode.getStepAge();
		int currStartAge = startAge;
		for(int i = 0; i < ArtistNode.getNumAgeGroups(); i++){
			int currRk = artist.getAgeGroupByAge(currStartAge).getRanking();
			if(currRk > worstRk){
				worstRk = currRk;
			}
			currStartAge += ageCoverage * i;
		}
		return worstRk;	
	}
	
	public ArtistNode findMostHomogeneousArtist(){
		ArtistNode mostHArtist = null;
		int minWorstRk = -1;
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int currWorstRk = findWorstRankingByArtist(currArtist);
		    if((minWorstRk != -1 && minWorstRk < currWorstRk) || minWorstRk == -1){
		    	minWorstRk = currWorstRk;
		    	mostHArtist = currArtist;
		    }
		}
		return mostHArtist;	
	}
	
	public List<ArtistNode> findMostHArtists(int numArtists){
		Set<Map.Entry<Integer, ArtistNode>> allArtists = BGMap.entrySet();
		List<ArtistNode> hArtists = new LinkedList<ArtistNode>();
		List<Integer> minWRks = new LinkedList<Integer>();
		
		for (Map.Entry<Integer, ArtistNode> entry : allArtists) {	    	   
		    ArtistNode currArtist = entry.getValue();
		    int currWorstRk = findWorstRankingByArtist(currArtist);
		    if(minWRks.isEmpty()){
		    	minWRks.add(currWorstRk);
		    	hArtists.add(currArtist);
		    }
		    boolean replaced = false;
		    for(int i = 1; i < minWRks.size() && !replaced; i++){
		    	 if(currWorstRk <= minWRks.get(i)){
		    		 minWRks.add(i, currWorstRk);
		    		 hArtists.add(i, currArtist);
		    		 replaced = true;
		    	 }
		    }
		}
		return hArtists;
	}
	
	public HashMap<Integer, ArtistNode> getBGHashMap(){
		return BGMap;
	}
}
