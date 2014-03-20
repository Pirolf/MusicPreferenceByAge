
public class AgeGroupNode extends BNode{
	//inclusive
	private int ageLo, ageHi;
	private int numListeners;
	private int ranking;
	public AgeGroupNode(int ageLo, int ageHi){
		super();
		this.ageLo = ageLo;
		this.ageHi = ageHi;
		numListeners = 0;
		ranking = 0;
	}
	public int getLo(){
		return ageLo;
	}
	public int getHi(){
		return ageHi;
	}
	
	public int getNumListners(){
		return numListeners;
	}
	
	public void incNumListeners(){
		numListeners ++;
	}
	
	public int getRanking(){
		return ranking;
	}
	
	public void setRanking(int rk){
		ranking = rk;
	}
}
