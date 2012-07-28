import java.util.ArrayList;


public class aa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> date = new ArrayList<String>();
		
		date.add("2012-07");
		date.add("2012-07");
		date.add("2012-07");
		date.add("2012-08");
		date.add("2012-09");
		
		for(int i=0; i<date.size(); i++){
			System.out.println(date.get(i).charAt(i));
		}
	}

}
