import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.AttributeList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "TestData.txt";
		Scanner scan = null;
		List<Integer> AttributeList = new ArrayList<Integer>();
		List<Integer>  matchList = new ArrayList<Integer>();
		List<Integer>  sameraceList = new ArrayList<Integer>();
		List<Integer>  age_oList = new ArrayList<Integer>();
		List<Integer>  race_oList = new ArrayList<Integer>();
		List<Integer>  sinc_oList = new ArrayList<Integer>();
		List<Integer>  attr_oList = new ArrayList<Integer>();
		List<Integer>  intel_oList = new ArrayList<Integer>();
		List<Integer>  fun_oList = new ArrayList<Integer>();
		List<Integer>  amb_oList = new ArrayList<Integer>();
		List<Integer>  shar_oList = new ArrayList<Integer>();
		List<Integer>  met_oList = new ArrayList<Integer>();
		int total = 0;

		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String attribute = scan.nextLine();
		ParseString parse = new ParseString();		
		while(scan.hasNext()){
			//AttributeList.add(Integer.parseInt(scan.nextLine()));
			total++;
			parse.makeList(scan.nextLine(),matchList, sameraceList, age_oList, race_oList, sinc_oList,
										   attr_oList, intel_oList, fun_oList, amb_oList, shar_oList, met_oList);
		
		}
		

		System.out.println("total is "+ total);
		//System.out.println("Match  " + matchList.get(i));
		System.out.println("Match = 0 has "+parse.Count_match[0]);
		System.out.println("Match = 1 has "+parse.Count_match[1]);
		
		System.out.println("same race = 0 has "+parse.Count_sameRace[0]);
		System.out.println("same race = 1 has "+parse.Count_sameRace[1]);
		
		System.out.println("age = 0 has "+parse.Count_age[0]);
		System.out.println("age = 1 has "+parse.Count_age[1]);
		
		System.out.println("race = 1 has "+parse.Count_race[0]);
		System.out.println("race = 2 has "+parse.Count_race[1]);
		System.out.println("race = 3 has "+parse.Count_race[2]);
		System.out.println("race = 4 has "+parse.Count_race[3]);
		System.out.println("race = 5 has "+parse.Count_race[4]);
		System.out.println("race = 6 has "+parse.Count_race[5]);
		
		
		System.out.println("sincerely = 0 has "+parse.Count_sinc[0]);
		System.out.println("sincerely = 1 has "+parse.Count_sinc[1]);
		
		System.out.println("attr = 0 has "+parse.Count_attr[0]);
		System.out.println("attr = 1 has "+parse.Count_attr[1]);
		
		System.out.println("intel = 0 has "+parse.Count_intel[0]);
		System.out.println("intel = 1 has "+parse.Count_intel[1]);
		
		System.out.println("fun = 0 has "+parse.Count_fun[0]);
		System.out.println("fun = 1 has "+parse.Count_fun[1]);
		
		System.out.println("amb = 0 has "+parse.Count_amb[0]);
		System.out.println("amb = 1 has "+parse.Count_amb[1]);
		
		System.out.println("shared = 0 has "+parse.Count_shared[0]);
		System.out.println("shared = 1 has "+parse.Count_shared[1]);
		
		System.out.println("met = 0 has "+parse.Count_met[0]);
		System.out.println("met = 1 has "+parse.Count_met[1]);
		
	}

}
