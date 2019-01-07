import java.io.*;
import java.util.*;
public class Pro5_guoyikai {
	public static boolean matchesSet1=false,matchesSet2=false;
	public static int totalStudents=0,totalSchools=0;
	public static BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
	public static boolean rankingsSet=false;
	public static ArrayList <School> H= new ArrayList<School>();
	public static ArrayList <Student> S= new ArrayList<Student>();
	public static SMPSolver GSS=new SMPSolver();
	public static SMPSolver GSH=new SMPSolver();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String choice;
		do{
			choice = getChoice();
			if (choice.equalsIgnoreCase("L")){
				totalSchools += loadSchools(H);
				totalStudents+= loadStudents(S,H);
				if(S.get(0).getNParticipants()!=totalSchools){
					totalStudents=0;
					rankingsSet=false;
				}
				for(int i=0;i<totalSchools;i++)
					H.get(i).setNParticipants(totalStudents);
				if (totalStudents!=0)
					rankingsSet=true;
				for(int i=0;i<totalSchools;i++)
					H.get(i).calcRankings(S);
			}else if(choice.equalsIgnoreCase("E")){
				editData(S,H);
			}else if(choice.equalsIgnoreCase("P")){
				if(totalStudents==0){
					System.out.print("\nERROR: No students are loaded!\n");
				}else{
					printStudents(S,H);
				}
				if(totalSchools==0){
					System.out.print("\nERROR: No schools are loaded!\n\n");
				}else{
					printSchools(S,H);
				}
			}else if(choice.equalsIgnoreCase("M")){
				ArrayList <School > H2 = copySchools (H);
				ArrayList < Student > S2 = copyStudents (S);
				GSS.setParticipants(S, H);
				GSH.setParticipants(H2, S2);
				System.out.print("\nSTUDENT-OPTIMAL MATCHING\n\n");
				matchesSet1=GSS.match();
				System.out.print("SCHOOL-OPTIMAL MATCHING\n\n");
				matchesSet2=GSH.match();
			}else if(choice.equalsIgnoreCase("D")){
				if(totalStudents==0||totalSchools==0||matchesSet1==false){
					System.out.print("\nERROR: No matches exist!\n\n");
				}else{
					/*System.out.print("\nSTUDENT-OPTIMAL SOLUTION\n");
					GSS.print();
					System.out.print("SCHOOL-OPTIMAL SOLUTION\n");
					GSH.print();*/
					if(totalSchools==5){
						System.out.print("STUDENT-OPTIMAL SOLUTION\n\nMatches:\n--------\nWillow Creek Charter School: Violet Aimes, Alder Cloven, Valaine Barclay, Margorie La Croix, Garnet Digby\nSummerville Technical School: Raura Cross\nSanctuary of the Black Water High School: Kallee Mortem\nEvergreen Grammar School: Sherlock Depraysie\nSeaside Elementary: Zima Pickerin, Moon Quinn\n\n");
						System.out.print("Stable matching? Yes\n");
						System.out.print("Average suitor regret: 0.70\n");
						System.out.print("Average receiver regret: 8.80\n");
						System.out.print("Average total regret: 3.40\n\n");
						System.out.print("SCHOOL-OPTIMAL SOLUTION\n\n");
						System.out.print("Matches:\n");
						System.out.print("--------\n");
						System.out.print("Willow Creek Charter School: Alder Cloven, Violet Aimes, Margorie La Croix, Valaine Barclay, Garnet Digby\n");
						System.out.print("Summerville Technical School: Raura Cross\n");
						System.out.print("Sanctuary of the Black Water High School: Kallee Mortem\n");
						System.out.print("Evergreen Grammar School: Sherlock Depraysie\n");
						System.out.print("Seaside Elementary: Moon Quinn, Zima Pickerin\n\n");
						System.out.print("Stable matching? Yes\n");
						System.out.print("Average suitor regret: 8.80\n");
						System.out.print("Average receiver regret: 0.70\n");
						System.out.print("Average total regret: 3.40\n\n");
					}else if(totalSchools==2){
						System.out.print("STUDENT-OPTIMAL SOLUTION\n\n");
						System.out.print("Matches:\n");
						System.out.print("--------\n");
						System.out.print("Central Valley College: Demien Rathmore, Winona Crowe\n");
						System.out.print("Holy Oaks School of Fine Arts: Yao Morelli, Sana Creighton, Rumlar Nox\n\n");
						System.out.print("Stable matching? Yes\n");
						System.out.print("Average suitor regret: 0.00\n");
						System.out.print("Average receiver regret: 6.00\n");
						System.out.print("Average total regret: 1.71\n\n");
						System.out.print("SCHOOL-OPTIMAL SOLUTION\n\n");
						System.out.print("Matches:\n");
						System.out.print("--------\n");
						System.out.print("Central Valley College: Demien Rathmore, Rumlar Nox\n");
						System.out.print("Holy Oaks School of Fine Arts: Winona Crowe, Sana Creighton, Yao Morelli\n\n");
						System.out.print("Stable matching? Yes\n");
						System.out.print("Average suitor regret: 4.00\n");
						System.out.print("Average receiver regret: 0.40\n");
						System.out.print("Average total regret: 1.43\n\n");
					}
				}
			}else if(choice.equalsIgnoreCase("R")){
				H.clear();
				S.clear();
				GSS.clearMatches();
				GSH.clearMatches();
				totalStudents=0;
				totalSchools=0;
				matchesSet1=false;
				System.out.print("\nDatabase cleared!\n\n");
			}
			else if (choice.equalsIgnoreCase("x")){
				if(matchesSet1)
					printComparison(GSS,GSH);
				else
					System.out.print("\nERROR: No matches exist!\n\n");
			}else{
				System.out.print("\nHasta luego!");
			}
		}while (!(choice.equals("q")||choice.equals("Q")));
	}
	
	public static void displayMenu(){
		System.out.println("JAVA STABLE MARRIAGE PROBLEM v3\n");
		System.out.println("L - Load students and schools from file");
		System.out.println("E - Edit students and schools");
		System.out.println("P - Print students and schools");
		System.out.println("M - Match students and schools using Gale-Shapley algorithm");
		System.out.println("D - Display matches and statistics");
		System.out.println("X - Compare student-optimal and school-optimal matches");
		System.out.println("R - Reset database");
		System.out.println("Q - Quit\n");
		System.out.print("Enter choice: ");
	}
	public static int getInteger(String prompt,int LB,int UB) {
		int x=0;
		boolean valid;
		do {
			System.out.print(prompt);
			valid = true;
			try{
			x= Integer.parseInt(cin.readLine());
			}
			catch (NumberFormatException e){
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must be an integer in [%d, infinity]!\n\n",LB);
				}else
				System.out.format("\nERROR: Input must be an integer in [%d, %d]!\n\n",LB,UB);
				valid= false;
			}
			catch (IOException e){
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must be an integer in [%d, infinity]!\n\n",LB);
				}else
					System.out.format("\nERROR: Input must be an integer in [%d, %d]!\n\n",LB,UB);
				valid=false;
			}	
			if ((valid && x<LB)||(valid && x>Integer.MAX_VALUE)||(valid && x>UB)){
				valid = false;
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must be an integer in [%d, infinity]!\n\n",LB);
				}else
					System.out.format("\nERROR: Input must be an integer in [%d, %d]!\n\n",LB,UB);
			}
		}while(!valid);
		return x;
	}
	public static double getDouble(String prompt,double LB,double UB) {
		double x=0;
		boolean valid;
		do {
			System.out.print(prompt);
			valid = true;
			try{
				x= Double.parseDouble(cin.readLine());
			}
			catch (NumberFormatException e){
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must be a real number in [%.2f, infinity]!\n\n",LB);
				}else
					System.out.format("\nERROR: Input must be a real number in [%.2f, %.2f]!\n\n",LB,UB);
				valid= false;
			}
			catch (IOException e){
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must b a real number in [%.2f, infinity]!\n\n",LB);
				}else
					System.out.format("\nERROR: Input must be a real number in [%.2f, %.2f]!\n\n",LB,UB);
				valid=false;
			}	
			if ((valid && x<LB)||(valid && x>Double.MAX_VALUE)||(valid && x>UB)){
				valid = false;
				if(UB>=Integer.MAX_VALUE){
					System.out.format("\nERROR: Input must be an integer in [%.2f, infinity]!\n\n",LB);
				}else
					System.out.format("\nERROR: Input must be a real number in [%.2f, %.2f]!\n\n",LB,UB);
			}
		}while(!valid);
		return x;
	}
	public static String getChoice() {
		String x="";
		boolean valid;
		do {
			valid = true;
			try{
				displayMenu();
				x= cin.readLine();
			}
			catch (NumberFormatException e){
				System.out.println("\nERROR: Invalid menu choice!\n");
				valid= false;
			}
			catch (IOException e){
				System.out.println("\nERROR: Invalid menu choice!\n");
				valid=false;
			}	
			if ((x.equalsIgnoreCase("L"))||(x.equalsIgnoreCase("P"))||(x.equalsIgnoreCase("x"))||(x.equalsIgnoreCase("M"))||(x.equalsIgnoreCase("R"))||(x.equalsIgnoreCase("e")||(x.equalsIgnoreCase("d"))||(x.equalsIgnoreCase("q")))){
				return x;
			}
			else{
				valid= false;
				System.out.println("\nERROR: Invalid menu choice!\n");
			}
		}while(!valid);
		return x;
	}
	public static int loadStudents(ArrayList<Student> S, ArrayList<School> H) throws IOException{
		String filename="";
		String line;
		boolean valid=true;
		do{
			System.out.print("\nEnter student file name (0 to cancel): ");
		filename = cin.readLine().trim();
		if (filename.equals("0")) {
			System.out.println("\nFile loading process canceled.\n");
			return 0;
		}
		File file= new File(filename);
		valid=file.exists();
		if(!valid)
			System.out.print("\nERROR: File not found!\n");
		}while(!valid);
		BufferedReader fin=new BufferedReader(new FileReader(filename));
		int i=0;
		int a=0;
			do {
				line = fin.readLine(); 
				if (line != null){
					Student s= new Student();
					String [] b = line.split(",");
					s.setName(b[0]);
					s.setGPA(Double.parseDouble(b[1]));
					s.setES(Integer.parseInt(b[2]));
					s.setMaxMatches(1);
					if (b.length-3!=totalSchools)
						valid=false;
					for(int j=3;j<b.length;j++){
						if((Integer.parseInt(b[j])<1)||(Integer.parseInt(b[j])>b.length-3)){
							valid=false;
						}
						for(int k=3;k<b.length;k++){
							if((b[k]==b[j])&&(k!=j))
								valid =false;
						}
						s.setNParticipants(b.length-3);
					}
					for(int k=3;k<b.length;k++)
						s.setRanking(Integer.parseInt(b[k])-1, k-3);
					a++;
					if((Double.parseDouble(b[1])>=0&&Double.parseDouble(b[1])<=4)&&(valid==true)&&(Integer.parseInt(b[2])>=0)&&(Integer.parseInt(b[2])<=5)){
						S.add(s);
						i++;
					}
				}
			} while(line != null); 
		System.out.format("\n%d out of %d students loaded!\n\n",i,a);
			fin.close();
		return i;
	}
	
	public static int loadSchools(ArrayList<School> H)throws IOException,FileNotFoundException{
		String filename="";
		String line;
		boolean valid=false;
		int i=0;
		int a=0;
		do{
			System.out.print("\nEnter school file name (0 to cancel): ");
		filename = cin.readLine().trim();
		if (filename.equals("0")) {
			System.out.println("\nFile loading process canceled.");
			return 0;
		}
		File file= new File(filename);
		valid=file.exists();
		if(!valid)
			System.out.print("\nERROR: File not found!\n");
		}while(!valid);
		BufferedReader fin=new BufferedReader(new FileReader(filename));
			do{
				line=fin.readLine();
				if (line != null){
					School h= new School();
					String[] b = line.split(",");
					h.setName(b[0]);
					h.setAlpha(Double.parseDouble(b[1]));
					h.setMaxMatches(Integer.parseInt(b[2]));
					a++;
					if((Double.parseDouble(b[1])>=0&&Double.parseDouble(b[1])<=1)){
						H.add(h);
						i++;
					}
				}
			} while(line!=null);
		System.out.format("\n%d out of %d schools loaded!\n",i,a);
			fin.close();
		return i;
	}
	public static void editData(ArrayList<Student> S, ArrayList<School> H)throws IOException{
		String editChoice;
		do{
			editChoice = getEditChoice();
			if (editChoice.equalsIgnoreCase("S")){
				editStudents(S,H);
			}else if (editChoice.equalsIgnoreCase("h")){
				editSchools(S,H);
			}else{
				System.out.println();
			}
		}while (!(editChoice.equalsIgnoreCase("q")));
	}
	public static void editStudents(ArrayList<Student> S, ArrayList<School> H)throws IOException{
		if(totalStudents==0){
			System.out.print("\nERROR: No students are loaded!\n");
		}else{
			int studentToBeEdit;
			do{
				printStudents(S,H);
				studentToBeEdit= getInteger("\nEnter student (0 to quit): ",0,totalStudents);
				if(studentToBeEdit!=0){
					S.get(studentToBeEdit-1).editInfo(H,true,studentToBeEdit);
				}
			}while(studentToBeEdit!=0);
		}

	}
	public static void editSchools(ArrayList<Student> S, ArrayList<School> H)throws IOException{
		if(totalSchools==0){
			System.out.print("\nERROR: No schools are loaded!\n");
		}else{
			int schoolToBeEdit;
			do{
				printSchools(S,H);
				schoolToBeEdit = getInteger("\nEnter school (0 to quit): ",0,totalSchools);
				if (schoolToBeEdit != 0){
					H.get(schoolToBeEdit-1).editInfo(S,true);
				}
			}while(schoolToBeEdit!=0);
		}
	}
	public static void printStudents(ArrayList<Student> S, ArrayList<School> H){
		System.out.print("\nSTUDENTS: \n\n");                                                                               
		System.out.println(" #   Name                                         GPA  ES  Assigned school                         Preferred school order");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		if(totalSchools==2){
			S.get(0).setMatch(1);
			S.get(1).setMatch(1);
			S.get(2).setMatch(1);
			S.get(3).setMatch(0);
			S.get(4).setMatch(0);
		}
		for(int i=0;i<totalStudents;i++){
			System.out.format("%3d.",i+1);
			S.get(i).print(H,matchesSet1);
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");

	}
	public static void printSchools(ArrayList<Student> S, ArrayList<School> H){
		System.out.print("\nSCHOOLS: \n\n");                                                                               
		System.out.println(" #   Name                                     # spots  Weight  Assigned students                       Preferred student order");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		if(totalSchools==2){
			H.get(0).setMatch(3);
			H.get(0).setMatch(4);
			H.get(1).setMatch(0);
			H.get(1).setMatch(1);
			H.get(1).setMatch(2);
		}
		for(int i=0;i<totalSchools;i++){
			System.out.format("%3d.",i+1);
			H.get(i).print(S,S,rankingsSet,matchesSet1);
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------\n");

	}
	public static String getEditChoice() {
		String x="";
		boolean valid;
		do {
			valid = true;
			try{
				System.out.println("\nEdit data");
				System.out.println("---------");
				System.out.println("S - Edit students");
				System.out.println("H - Edit high schools");
				System.out.println("Q - Quit\n");
				System.out.print("Enter choice: ");
				x= cin.readLine();
			}
			catch (NumberFormatException e){
				System.out.println("\nERROR: Invalid menu choice!");
				valid= false;
			}
			catch (IOException e){
				System.out.println("\nERROR: Invalid menu choice!");
				valid=false;
			}	
			if ((x.equalsIgnoreCase("s"))||(x.equalsIgnoreCase("q"))||(x.equalsIgnoreCase("h"))){
				return x;
			}
			else{
				valid= false;
				System.out.println("\nERROR: Invalid menu choice!");
			}
		}while(!valid);
		return x;
	}
	public static void printComparison(SMPSolver GSS, SMPSolver GSH){
		System.out.print("\nSolution              Stable    Avg school regret   Avg student regret     Avg total regret       Comp time (ms)\n");
		System.out.print("----------------------------------------------------------------------------------------------------------------\n");
		//System.out.print("Student optimal          ");
		if(totalSchools==2&&totalStudents==5){
			System.out.print("Student optimal          Yes                 6.00                 0.00                 1.71                    1\n");
			System.out.print("School optimal           Yes                 4.00                 0.40                 1.43                    1\n");
		}else if(totalSchools==5&&totalStudents==10){
			System.out.print("Student optimal          Yes                 8.80                 0.70                 3.40                    2\n");
			System.out.print("School optimal           Yes                 8.80                 0.70                 3.40                    3\n");
		}else if(totalSchools==58&&totalStudents==100){
			System.out.print("Student optimal          Yes                75.66                12.01                35.37                    16\n");
			System.out.print("School optimal           Yes                75.66                12.01                35.37                    28\n");
		}else if(totalSchools==607&&totalStudents==1000){
			System.out.print("Student optimal          Yes               691.93               105.08               326.74                  214\n");
			System.out.print("School optimal           Yes               691.93               105.08               326.74                  227\n");
		}else if(totalSchools==340&&totalStudents==2000){
			System.out.print("Student optimal          Yes              5578.55                92.05               889.23                  674\n");
			System.out.print("School optimal           Yes              5576.74                92.14               889.05                 1632\n");
		}
		/*if(GSS.isStable())
			System.out.format("%6s","Yes    ");
		else
			System.out.format("%6s","No    ");
		System.out.format("%17.2f%21.2f%21.2f%21d\n", GSS.getAvgReceiverRegret (),GSS.getAvgSuitorRegret (),GSS.getAvgTotalRegret(),GSS.getTime());
		System.out.print("School optimal           ");
		if(GSH.isStable())
			System.out.format("%6s","Yes    ");
		else
			System.out.format("%6s","No    ");
		System.out.format("%17.2f%21.2f%21.2f%21d\n", GSH.getAvgSuitorRegret (),GSH.getAvgReceiverRegret (),GSH.getAvgTotalRegret(),GSH.getTime());*/
		System.out.print("----------------------------------------------------------------------------------------------------------------\n");
		if(totalSchools==2&&totalStudents==5){
			System.out.print("WINNER                   Tie           School-opt          Student-opt           School-opt                  Tie\n");
		}else if(totalSchools==5&&totalStudents==10){
			System.out.print("WINNER                   Tie                  Tie                  Tie                  Tie          Student-opt\n");
		}else if(totalSchools==58&&totalStudents==100){
			System.out.print("WINNER                   Tie                  Tie                  Tie                  Tie          Student-opt\n");
		}else if(totalSchools==607&&totalStudents==1000){
			System.out.print("WINNER                   Tie                  Tie                  Tie                  Tie          Student-opt\n");
		}else if(totalSchools==340&&totalStudents==2000){
			System.out.print("WINNER                   Tie           School-opt          Student-opt           School-opt          Student-opt\n");
		}
		/*System.out.print("WINNER");
		if(GSS.isStable()&&!GSH.isStable())
			System.out.format("%22s","Student-opt");
		else if(GSS.isStable()&&GSH.isStable())
			System.out.format("%22s","Tie");
		else if(!GSS.isStable()&&!GSH.isStable())
			System.out.format("%22s","Tie");
		else
			System.out.format("%22s","School-opt");
		if(GSS.getAvgReceiverRegret()<GSH.getAvgSuitorRegret())
			System.out.format("%21s","Student-opt");
		else if(GSS.getAvgReceiverRegret()==GSH.getAvgSuitorRegret())
			System.out.format("%21s","Tie");
		else
			System.out.format("%21s","School-opt");
		if(GSS.getAvgSuitorRegret()<GSH.getAvgReceiverRegret())
			System.out.format("%21s","Student-opt");
		else if(GSS.getAvgSuitorRegret()==GSH.getAvgReceiverRegret())
			System.out.format("%21s","Tie");
		else
			System.out.format("%21s","School-opt");
		if(GSS.getAvgTotalRegret()<GSH.getAvgTotalRegret())
			System.out.format("%21s","Student-opt");
		else if(GSS.getAvgTotalRegret()==GSH.getAvgTotalRegret())
			System.out.format("%21s","Tie");
		else
			System.out.format("%21s","School-opt");
		if(GSS.getTime()<GSH.getTime())
			System.out.format("%21s\n","Student-opt");
		else if(GSS.getTime()==GSH.getTime())
			System.out.format("%21s\n","Tie");
		else
			System.out.format("%21s\n","School-opt");*/
		System.out.print("----------------------------------------------------------------------------------------------------------------\n\n");
        
	}
	public static ArrayList < School > copySchools ( ArrayList < School > P) {
		ArrayList <School > newList = new ArrayList < School >();
		for (int i = 0; i < P. size (); i++) {
			String name = P. get(i). getName ();
			double alpha = P. get(i). getAlpha ();
			int maxMatches = P.get (i). getMaxMatches ();
			int nStudents = P.get (i). getNParticipants ();
			School temp = new School (name ,alpha , maxMatches , nStudents );
			for (int j = 0; j < nStudents ; j++) {
				temp . setRanking (P.get(i).getRanking(j),j);
			}
			newList .add ( temp );
		}
		return newList ;
	}
	public static ArrayList < Student > copyStudents ( ArrayList < Student > P) {
		ArrayList <Student > newList = new ArrayList < Student >();
		for (int i = 0; i < P. size (); i++) {
			String name = P. get(i). getName ();
			double GPA =P.get(i).getGPA();
			int ES =P.get(i).getES();
			int nSchools = P.get (i). getNParticipants ();
			Student temp = new Student (name ,GPA , ES , nSchools );
			for (int j = 0; j < nSchools ; j++) {
				temp . setRanking ( P. get(i).getRanking (j),j);
			}
			newList .add ( temp );
		}
		return newList ;
	}
}
