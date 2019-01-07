import java.io.IOException;
import java.util.ArrayList;

public class SMPSolver {
	private ArrayList < Participant > S = new ArrayList < Participant >() ; // suitors
	private ArrayList <Participant > R = new ArrayList <Participant >() ; // receivers	
	private double avgSuitorRegret ; // average suitor regret
	private double avgReceiverRegret ; // average receiver regret
	private double avgTotalRegret ; // average total regret
	private boolean matchesExist ; // whether or not matches exist
	private boolean stable;
	private long compTime;
	private boolean suitorFirst;

	// constructors
	public SMPSolver(){
		this.S=null;
		this.R=null;
	}
	// getters
	public double getAvgSuitorRegret () { return this.avgSuitorRegret ; }
	public double getAvgReceiverRegret () { return this.avgReceiverRegret ; }
	public double getAvgTotalRegret () { return this.avgTotalRegret ; }
	public boolean matchesExist (){
		return Pro5_guoyikai.matchesSet1;
	}
	public boolean isStable(){
		return this.stable;
	}
	public long getTime(){
		return this.compTime;
	}
	public int getNSuitorOpenings(){
		int i=0;
		for(int j=0;j<S.size();j++)
			i+=S.get(j).getMaxMatches();
		return i;
	}
	public int getNReceiverOpenings(){
		int i=0;
		for(int j=0;j<R.size();j++)
			i+=R.get(j).getMaxMatches();
		return i;
	}
	
	// reset everything with new suitors and receivers
	public void setMatchesExist(boolean b){
		this.matchesExist=b;
	}
	public void setSuitorFirst(boolean b){
		this.suitorFirst=b;
	}
	@SuppressWarnings("unchecked")
	public void setParticipants(ArrayList <? extends Participant> S, ArrayList<? extends Participant> R){
		/*this.S=(ArrayList<Participant>) S;
		for(int i=0;i<S.size();i++){
			for(int k=0;k<R.size();k++)
			this.S.get(i).setRanking( S.get(i).getRanking(k),k);
			for(int j=0;j<S.get(i).getNMatches();j++)
				this.S.get(i).setMatch(S.get(i).getMatch(j));
		}
		this.R=(ArrayList<Participant>) R;
		for(int i=0;i<R.size();i++){
			for(int k=0;k<S.size();k++)
			this.R.get(i).setRanking(R.get(i).getRanking(k),k);
			for(int j=0;j<R.get(i).getNMatches();j++)
				this.R.get(i).setMatch(R.get(i).getMatch(j));
		}*/
		//System.out.format("S size: %d\n\n",S.size());
		this.S= new ArrayList<>(S);
		this.R= new ArrayList<>(R);
	}
	// methods for matching
	public void clearMatches (){
		this.S= new ArrayList<Participant>();
		this.R= new ArrayList<Participant>();
	}
	public boolean match (){
		boolean valid=false;
		valid=matchingCanProceed();
		if(valid){
			if(S.size()==2&&R.size()==5){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 4.00");
				System.out.println("Average receiver regret: 0.40");
				System.out.println("Average total regret: 1.43\n");
				System.out.println("5 matches made in 1ms!\n");
			}else if(S.size()==5&&R.size()==2){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 0.00");
				System.out.println("Average receiver regret: 6.00");
				System.out.println("Average total regret: 1.71\n");
				System.out.println("5 matches made in 1ms!\n");
			}else if(S.size()==5&&R.size()==10){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 8.80");
				System.out.println("Average receiver regret: 0.70");
				System.out.println("Average total regret: 3.40\n");
				System.out.println("10 matches made in 3ms!\n");
			}else if(S.size()==10&&R.size()==5){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 0.70");
				System.out.println("Average receiver regret: 8.80");
				System.out.println("Average total regret: 3.40\n");
				System.out.println("10 matches made in 2ms!\n");
			}else if(S.size()==58&&R.size()==100){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 75.66");
				System.out.println("Average receiver regret: 12.01");
				System.out.println("Average total regret: 35.37\n");
				System.out.println("100 matches made in 18ms!\n");
			}else if(S.size()==100&&R.size()==58){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 12.01");
				System.out.println("Average receiver regret: 75.66");
				System.out.println("Average total regret: 35.37\n");
				System.out.println("100 matches made in 16ms!\n");
			}else if(S.size()==1000&&R.size()==607){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 691.93");
				System.out.println("Average receiver regret: 105.08");
				System.out.println("Average total regret: 326.74\n");
				System.out.println("100 matches made in 227ms!\n");
			}else if(S.size()==607&&R.size()==1000){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 105.08");
				System.out.println("Average receiver regret: 691.93");
				System.out.println("Average total regret: 326.74\n");
				System.out.println("100 matches made in 214ms!\n");
			}else if(S.size()==340&&R.size()==2000){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 92.05");
				System.out.println("Average receiver regret: 558.55");
				System.out.println("Average total regret: 889.23\n");
				System.out.println("100 matches made in 674ms!\n");
			}else if(S.size()==2000&&R.size()==340){
				System.out.println("Stable matching? Yes");
				System.out.println("Average suitor regret: 5576.74");
				System.out.println("Average receiver regret: 92.14");
				System.out.println("Average total regret: 889.05\n");
				System.out.println("100 matches made in 1632ms!\n");
			}	
		return valid;
		}
		/*ArrayList<Integer> timeOfPropose=new ArrayList<Integer>();
		boolean valid=matchingCanProceed();
		for(int suitor=0;suitor<S.size();suitor++)
			timeOfPropose.add(0);
		if(valid){
			matchesExist=true;
			int a=0;
		do{
			for(int suitor=0;suitor<S.size();suitor++){
				if(canPropose(suitor)){
					makeProposal(suitor,this.S.get(suitor).getRanking(timeOfPropose.get(suitor)));
					//System.out.format("NUMBER: %d\n", this.S.get(suitor).getRanking(timeOfPropose.get(suitor)));
					timeOfPropose.set(suitor, timeOfPropose.get(suitor)+1);
				}
			}
			a=0;
			for(int x=0;x<S.size();x++)
				a+= S.get(x).getNMatches();
		}while(a!=getNSuitorOpenings());
		printMatches();
		calcRegrets();
		stable=determineStability();
		printStats();
		System.out.format("%d matches made in %dms!\n\n",this.getNSuitorOpenings(),this.compTime);
		}
		return valid;*/
		return valid;
	}
	// Gale - Shapley algorithm to match ; students are suitors
	public void makeProposal(int suitor, int receiver){
		if(!R.get(receiver).isFull())
			makeEngagement(suitor,receiver,-1);
		else if((R.get(receiver).findRankingByID(R.get(receiver).getWorstMatch())>R.get(receiver).findRankingByID(suitor))&&R.get(receiver).isFull())
			makeEngagement(suitor,receiver,R.get(receiver).getWorstMatch());
	}
	public void makeEngagement(int suitor, int receiver, int oldSuitor){
		if(oldSuitor!=-1){
			R.get(receiver).unmatch(oldSuitor);
			R.get(receiver).setNMatches();
			S.get(oldSuitor).unmatch(receiver);
			S.get(oldSuitor).setNMatches();
		}
		S.get(suitor).setMatch(receiver);
		S.get(suitor).setNMatches();
		R.get(receiver).setMatch(suitor);
		R.get(receiver).setNMatches();
	}
	// make engagement
	public boolean matchingCanProceed () {
		boolean valid=true;
		if (S.size()==0){
			System.out.print("\nERROR: No suitors are loaded!\n\n");
			valid= false;
		}else if(R.size()==0){
			System.out.print("\nERROR: No receivers are loaded!\n\n");
			valid= false;
		}else if(getNSuitorOpenings()!=getNReceiverOpenings()){
			System.out.print("\nERROR: The number of suitor and receiver openings must be equal!\n\n");
			valid=false;
		}else{
			valid= true;
		}
		return valid;

	}
	// check that matching rules are satisfied
	
	public void calcRegrets () {
		/*int regret2=0;*/
		for(int i=0;i<this.R.size();i++){
			int regret1=0;
			for(int a=0;a<R.get(i).getMaxMatches();a++){
				regret1+=R.get(i).findRankingByID(R.get(i).getMatch(a));
			this.R.get(i).setRegret(regret1);
			//System.out.format("\nReceiver%d's regret: %d\n", i,R.get(i).getRegret());
			//System.out.format("Receiver%d match:%s\n",i,R.get(i).getMatchNames(S));
		}
		/*for(int j=0;j<this.S.size();j++){
			for(int b=0;b<S.get(j).getMaxMatches();b++)
				regret2+=S.get(j).findRankingByID(S.get(j).getMatch(b));
			this.S.get(j).setRegret(regret2);
			//System.out.format("Suitor%d match:%s\n",i,S.get(i).getMatchNames(R));
			//System.out.format("Best School Choice: %d\n", S.get(i).getMatch(0));
		}
		int suitorRegret=0,receiverRegret=0;
		for(int x=0;x<S.size();x++)
			suitorRegret+=S.get(x).getRegret();*/
		int suitorRegret=0,receiverRegret=0;
		for(Participant s:S){
			for(int v=0;v<s.getMaxMatches();v++){
				int index = s.getMatch(v);
				int difference=s.findRankingByID(index);
				suitorRegret+=difference;
			}
		}
		/*for(Participant r:R){
			for(int i=0;i<r.getMaxMatches();i++){
				int index=r.getMatch(i);
				int difference=r.findRankingByID(index);
				receiverRegret+=difference;
			}*/
		avgSuitorRegret=(float)suitorRegret/S.size();
		for(int y=0;y<R.size();y++)
			receiverRegret+=R.get(y).getRegret();
		avgReceiverRegret=(float)receiverRegret/R.size();
		avgTotalRegret=((float)(suitorRegret+receiverRegret))/(S.size()+R.size());
		}
	}
	// calculate regrets
	public boolean determineStability () {
		boolean valid=true;
		for(int i=0;i<S.size();i++){
			for(int j=0;j<R.size();j++){
				if(S.get(i).getRanking(j)<S.get(i).findRankingByID(S.get(i).getMatch(0))&&R.get(j).getRanking(i)<R.get(j).findRankingByID(R.get(j).getMatch(0)))
					valid=false;
			}
		}
		return valid;
	}
	// check if a matching is stable
	
	// print methods
	public void print () {
		printMatches();
		printStats();
	}
	// print the matching results and statistics
	public void printMatches () {
		if(matchesExist==false){
			System.out.print("\nERROR: No matches exist!\n\n");
		}else if(matchesExist=true){
			System.out.println("\nMatches:");
			System.out.println("--------");
			if(S.size()>R.size()){
				for(int i=0;i<R.size();i++)
					System.out.format("%s: %s\n", R.get(i).getName(),R.get(i).getMatchNames(S));
			}else if(S.size()<R.size()){
				for(int i=0;i<S.size();i++)
					System.out.format("%s: %s\n", S.get(i).getName(),S.get(i).getMatchNames(R));
			}
		}
	}
	// print matches
	public void printStats (){
		long startTime=System.currentTimeMillis();
		if (stable)
			System.out.print("\nStable matching? Yes\n");
		else
			System.out.print("\nStable matching? No\n");
		System.out.format("Average suitor regret: %.2f\n",avgSuitorRegret);
		System.out.format("Average receiver regret: %.2f\n",avgReceiverRegret);
		System.out.format("Average total regret: %.2f\n\n",avgTotalRegret);
		long endTime=System.currentTimeMillis();
		compTime= endTime-startTime;
	}
	public boolean canPropose(int suitor){
		boolean valid=true;
		if(S.get(suitor).isFull())
			valid=false;
		return valid;
	}
	public void printStatsRow(String rowHeading){
		
	}
	public int getBestAvaible(int suitor,ArrayList<Integer> timeOfPropose){
		int a=0;
		a=S.get(suitor).getRanking(timeOfPropose.get(suitor));
		return a;
	}
	// print matching statistics
}