import java.util.List;

public class ParseString {
	public int Count_match[] = {0,0};
	public int Count_sameRace[] = {0,0};
	public int Count_age[]= {0,0};
	public int Count_race[]= {0,0,0,0,0,0};
	public int Count_sinc[] = {0,0};
	public int Count_attr[] = {0,0};
	public int Count_intel[] = {0,0};
	public int Count_fun[] = {0,0};
	public int Count_amb[] = {0,0};
	public int Count_shared[] = {0,0};
	public int Count_met[] = {0,0};
	
	

	

	public void makeList(String str, List<Integer> a1,List<Integer> b1,List<Integer> c1,List<Integer> d1,List<Integer> e1,List<Integer> f1,
			List<Integer> g1,List<Integer> h1,List<Integer> i1,List<Integer> j1,List<Integer> k1){
		String[] myarr = str.split("\\s+");
		

		a1.add(roundNum(myarr[0],Count_match));
		b1.add(roundNum(myarr[1],Count_sameRace));		
		c1.add(ConvertToBinary(Count_age,myarr[2], 25));
		//race
		d1.add(roundNum(myarr[3],Count_race,6));
			
		e1.add(ConvertToBinary(Count_sinc,myarr[4], 5));
		f1.add(ConvertToBinary(Count_attr,myarr[5], 5));
		g1.add(ConvertToBinary(Count_intel,myarr[6], 5));
		h1.add(ConvertToBinary(Count_fun,myarr[7], 5));
		i1.add(ConvertToBinary(Count_amb,myarr[8], 5));	
		j1.add(ConvertToBinary(Count_shared,myarr[9], 5));	
		k1.add(ConvertToBinary(Count_met,myarr[10], 1));
	}

	public int ConvertToBinary(int[] arr,String s, int mid){
		if((int)Math.round(Double.parseDouble(s)) >= mid){
			arr[1]++;
			return 1;
		}
		else {
			arr[0]++;
			return 0;
		}
	}
	public int roundNum(String s,int[] arr){
		int result = Integer.valueOf(String.valueOf((int)Math.round(Double.parseDouble(s))));
		if(result == 1) arr[1]++;
		else {
			arr[0]++;
		}
		return result;
	}
	public int roundNum(String s,int[] arr,int max){
		int result = Integer.valueOf(String.valueOf((int)Math.round(Double.parseDouble(s))));
		for(int i =0 ; i<max;i++){
			if(result-1==i) arr[i]++;
		}
		return result;
	}
}
