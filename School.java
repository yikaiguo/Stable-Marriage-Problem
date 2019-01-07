import java.io.IOException;
import java.util.ArrayList;

public class School extends Participant{
    private double alpha ; // GPA weight
    // constructors
    public School (){
    	super();
        this.setAlpha(0);      
    }
    public School ( String name , double alpha , int maxMatches,int nStudents ){
    	super(name,maxMatches,nStudents);
        this.setAlpha(alpha);
    }
    
    // getters
    public double getAlpha (){
        return this.alpha;
    }
    // setters
    public void setAlpha ( double alpha ){
        this.alpha= alpha;
    }
    public void editInfo ( ArrayList<Student> S, boolean canEditRankings ) throws IOException{
    	super.editInfo(S);
    	double alpha = Pro5_guoyikai.getDouble("GPA weight: ", 0.00, 1.00);
        this.setAlpha(alpha);
        int maxMatch =Pro5_guoyikai.getInteger("Maximum number of matches: ",1,Integer.MAX_VALUE);
        this.setMaxMatches(maxMatch);
    }
    public void calcRankings( ArrayList<Student> S){
        for(int i=0;i<Pro5_guoyikai.totalStudents;i++){
        	int index=0;
            for(int j=0;j<Pro5_guoyikai.totalStudents;j++){
                double a=0.,b=0.;
                a=this.alpha*S.get(i).getGPA()+(1-this.alpha)*S.get(i).getES();
                b=this.alpha*S.get(j).getGPA()+(1-this.alpha)*S.get(j).getES();
                if(i!=j){
                    if(i<j){
                        if(a>=b){
                            index +=1;
                        }
                    }else if(i>j){
                        if(a>b){
                            index +=1;
                        }
                    }
                }
            }
            this.setRanking(i,Pro5_guoyikai.totalStudents-index-1);
        }
    }
    public void print (ArrayList<Student> P, ArrayList<? extends Participant> S,boolean rankingsSet,boolean matchesSet){
    	if ((rankingsSet==false)&& (matchesSet==false)){
            System.out.format(" %-41s%7d%8.2f  -                                       -\n",super.getName(),this.getMaxMatches(),this.alpha);
        }else if (matchesSet==false){
            System.out.format(" %-41s%7d%8.2f  -                                       ",super.getName(),this.getMaxMatches(),this.alpha);
            super.printRankings(S);
        }else{
            System.out.format(" %-41s%7d%8.2f  ",super.getName(),this.getMaxMatches(),this.alpha);
            if(Pro5_guoyikai.totalSchools==2){
            	System.out.format("%-40s",super.getMatchNames(S));
            }
            super.printRankings(S);
        }
    }                                   
    
    
}