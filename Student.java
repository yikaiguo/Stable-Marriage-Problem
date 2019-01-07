import java.io.IOException;
import java.util.*;

public class Student extends Participant{
	private double GPA ; // GPA
	private int ES ; // extracurricular score
	// constructors
	public Student (){
		super();
		this.setES(0);
		this.setGPA(0.00);
	}
	public Student ( String name , double GPA , int ES , int nSchools ){
		super(name,1,nSchools);
		this.setES(ES);
		this.setGPA(GPA);
	}
	// getters
	public double getGPA (){
		return this.GPA;
	}
	public int getES (){
		return this.ES;
	}
	
	// setters
	public void setGPA ( double GPA){
		this.GPA= GPA;
	}
	public void setES ( int ES){
		this.ES= ES;
	}

	public void editInfo ( ArrayList < School > H, boolean canEditRankings,int studentToBeEdit )throws IOException{
		super.editInfo(H);
		double GPA =Pro5_guoyikai.getDouble("GPA: ", 0.00, 4.00);
		this.setGPA(GPA);
		int ES = Pro5_guoyikai.getInteger("Extracurricular score: ", 0, 5);
		this.setES(ES);
		int maxMatch =Pro5_guoyikai.getInteger("Maximum number of matches: ",1,Integer.MAX_VALUE);
	        this.setMaxMatches(maxMatch);
		if (canEditRankings== true){
			int a=0;
			do{
				a=0;
			System.out.print("Edit rankings (y/n): ");
			String b=Pro5_guoyikai.cin.readLine();
			if (b.equalsIgnoreCase("Y")){
				super.editRankings(H,studentToBeEdit);
			}else if (b.equalsIgnoreCase("n")){
				return;
			}else{
				System.out.print("ERROR: Choice must be 'y' or 'n'!\n");
				a=-1;
			}
			}while(a==-1);
		}
	}
	public void print ( ArrayList < ? extends Participant > H, boolean matchesSet){
		/*if ((rankingsSet==false)&& (matchesSet==false)){
			System.out.format(" %-31s%-7.2f%-3d-                          -\n",this.name,this.GPA,this.ES);
		}else */if (matchesSet==false){
			System.out.format(" %-44s%-7.2f%-3d-                                       ",super.getName(),this.GPA,this.ES);
			super.printRankings(H);
		}else {
			System.out.format(" %-44s%-7.2f%-3d",super.getName(),this.GPA,this.ES);
			System.out.format("%-40s",super.getMatchNames(H));
			super.printRankings(H);
		}
	}
	public boolean isValid (){
		boolean valid=false;
		return valid;
	}
}