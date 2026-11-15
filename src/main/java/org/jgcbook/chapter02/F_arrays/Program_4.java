package org.jgcbook.chapter02.F_arrays;
// ch02_5_3a
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Program_4 {
	public static void storeIntegers(Collection<? super Integer> ints, int n) {
	  for (int i = 0; i < n; i++) {
		  ints.add(i);
	  }

	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		//storeIntegers(Arrays.asList(0), 10);
		storeIntegers(list, 10);
		list.stream().forEach(System.out::println);
	}
}