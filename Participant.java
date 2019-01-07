import java.io.*;
import java.util.*;

public class Participant {
	private String name ; // name
	private int [] rankings ; // rankings of participants	
	private ArrayList < Integer > matches = new ArrayList < Integer >();// match indices
	private int regret ; // total regret
	private int maxMatches ; // max # of allowed matches / openings
	private int nMatches;
	private int nParticipants;
	// constructors
	public Participant (){
		this.setName("");
		this.setNParticipants(0);
		this.setMaxMatches(0);
	}
	public Participant ( String name , int maxMatches , int nParticipants ){
		this.setName(name);
		this.setMaxMatches(maxMatches);
		this.setNParticipants(nParticipants);
	}
	
	// getters
	public String getName (){
		return this.name;
	}
	public int getRanking ( int i){
		return this.rankings[i];
	}
	public int getMatch ( int i){
		return this.matches.get(i);
	}
	public int getRegret (){
		return this.regret;
	}
	public int getMaxMatches (){
		return this.maxMatches;
	}
	public int getNMatches (){
		return this.matches.size();
	}
	public int getNParticipants (){// return length of rankings array
		return this.rankings.length;
	}
	public boolean isFull (){
		boolean valid=false;
		if (this.matches.size()==this.maxMatches)
			valid=true;
		return valid;
	}
	
	// setters
	public void setName ( String name ){
		this.name= name;
	}
	public void setRanking ( int i, int r){
		this.rankings[r]=i;
	}
	public void setMatch ( int m){
		this.matches.add(m);
	}
	public void setRegret ( int r){
		this.regret=r;
	}
	public void setNParticipants ( int n){// set rankings array size
		this.rankings= new int[n];
		this.nParticipants=n;
	}
	public void setMaxMatches ( int n){
		this.maxMatches=n;
	}
	public void setNMatches(){
		this.nMatches=matches.size();
	}
	
	// methods to handle matches
	public void clearMatches (){
		this.matches.clear();
	}
	// clear all matches
	public int findRankingByID ( int k){
        for( int i=0;i<nParticipants;i++){
            if (rankings[i]==k)
                return i;
        }
        return -1;
	}
	// find rank of participant k
	public int getWorstMatch() {
		int worstRanking = -1, index = 0;
		for (int i = 0; i< matches.size(); i++) {
			int ranking = findRankingByID(matches.get(i));
			if (ranking > worstRanking) {
				worstRanking = ranking;
				index = i;
			}
		}
		return index;
	}
	// find the worst - matched participant
	public void unmatch ( int k){
		for(int i =0;i<matches.size();i++)
			if(matches.get(i)==k)
				matches.remove(i);
	}
	// remove the match with participant k
	public boolean matchExists ( int k) {
		return this.matches.contains(k);
	}
	// check if match to participant k exists
	public int getSingleMatchedRegret ( int k) {
		int i=0;
		i=this.matches.indexOf(k);
		setRegret(findRankingByID(i));
		return this.regret;
	}
	// get regret from match with k
	public double calcRegret () {
		double regret=0;
		for(int i=0;i<matches.size();i++){
			regret+= getSingleMatchedRegret(i);
		}
		return regret;
	}
	// calculate total regret over all matches
	
	// methods to edit data from the user
	public void editInfo ( ArrayList <? extends Participant > P) throws IOException{
		System.out.print("\nName: ");
		String name=Pro5_guoyikai.cin.readLine();
		this.setName(name);
	}
	public void editRankings ( ArrayList <? extends Participant > P,int numberToBeEdit){
		ArrayList<Integer>b=new ArrayList<Integer>();
		System.out.format("\nParticipant %d's Rankings: \n",numberToBeEdit);
		for(int i=0;i<Pro5_guoyikai.totalSchools;i++){
			int a=0;
			int c=0;
			do{
				c=0;
			a=Pro5_guoyikai.getInteger("School"+P.get(i).getName()+": ", 1, Pro5_guoyikai.totalSchools);
			for(int j=0;j<b.size();j++){
				if(a==b.get(j)){
					c=-1;
					System.out.format("ERROR: Rank %d already used!\n\n",a);
				}
			}
			}while(c==-1);
			b.add(a);
			this.setRanking(i, a-1);
		}
	}
	
	// print methods
	public void print ( ArrayList <? extends Participant > P){
	}
	public void printRankings ( ArrayList <? extends Participant > P){
		for(int i=0;i<this.nParticipants;i++){
			System.out.print(P.get(this.getRanking(i)).getName());
			if(i<this.nParticipants-1)
				System.out.print(", ");
		}
		System.out.print("\n");
	}
	public String getMatchNames ( ArrayList <? extends Participant > P){
		String names= "";
		for(int i=0;i<matches.size();i++){
			names += P.get(matches.get(i)).getName();
			if(i<matches.size()-1)
				names +=", ";
		}
		return names;	
	}
	
	// check if this participant has valid info
	//public boolean isValid ()

}
	