package java8inaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortingProblems {
	
	/**
	 * @param map
	 */
	private static void sortMapValues(Map<String, Integer> map) {
		Map<Integer, String> sortedMap = new TreeMap<>();
		for(Entry<String, Integer> entry : map.entrySet()) {
			sortedMap.put(entry.getValue(),entry.getKey());
		}
		System.out.println("After Sorting\n"+sortedMap);
	}
	
	/**
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue7(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
        result.put(entry.getKey(), entry.getValue());
    }
    return result;
}
	
	/**
	 * @param map
	 * @return
	 */
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue8(Map<K, V> map) {
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(e1, e2) -> e1, 
						LinkedHashMap::new
						));
	}
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("Nana", 5);
		map.put("Nana", 6);
		map.put("Bana", 11);
		map.put("Gana", 1);
		map.put("Lana", 67);
		map.put("Pana", 27);
		map.put("Aana", 6);
		System.out.println("Before Sorting\n"+map);
		
		sortMapValues(map);
		System.out.println(sortByValue8(map));
		System.out.println(sortByValue7(map));
		
		
	}

}
