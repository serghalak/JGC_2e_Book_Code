package org.jgcbook.chapter02.H_wildcard_capture;
// ch02_7_2
import java.util.ArrayList;
import java.util.List;

public class Program_1 {
	public static void reverse(List<?> list) {
		//list.add(null);
	  List<Object> tmp = new ArrayList<>(list);
	  tmp.add(25); // Add a null element to make the size match
	  for (int i = 0; i < list.size(); i++) {
		  System.out.println(tmp.get(list.size()-i-1));
		  Object tmpObject = tmp.get(i);
		   tmp.set(i, tmpObject);
	    //list.set(i, tmp.get(list.size()-i-1));  // compile-time error
	  }
	}

	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		list.add(0);
		list.add("two");
		list.add(3.14);

		reverse(list);
	}
}