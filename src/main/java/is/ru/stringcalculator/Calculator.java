package is.ru.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains("//[")){
			String temp = "";

			for(int i = 0 ; i < text.length() ; i++){
				if(at(text, i) != '/' && at(text, i) != '['){
					temp += at(text, i);
				}
			}

			String temp2 = temp.substring(0, temp.indexOf(']'));
			String delim = "";

			for(int i = 0 ; i < temp2.length() ; i++){
				delim += "\\" + at(temp2, i);
			}

			delim = "(" + delim + ")";
			String numbers = temp.substring(temp.indexOf(']') + 2, temp.length());
			
			return sum(splitNumbers2(numbers, delim));
		}
		else{
			String[] numbers = size(text);

			if(negative(numbers))
				throw new RuntimeException();

			return sum(numbers);
		}
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",|\n");
	}
    
    private static String[] splitNumbers2(String numbers, String numbers2){
	    return numbers.split(numbers2);
	}  

    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	if(toInt(number) <= 1000){
		    total += toInt(number);
		    }
		}
		return total;
    }

    private static String[] customDelimeter(String text) {
		Matcher match = Pattern.compile("//(.)\n(.*)").matcher(text);
		match.matches();
		String cstmDelimeter = match.group(1);
		String numbers = match.group(2);

		return numbers.split(Pattern.quote(cstmDelimeter));
	}

    private static boolean negative(String[] numbers){
    	for(String number : numbers){
    		int neg = toInt(number);
    		if(neg < 0){
    			return true;
    		}
    	}

    	return false;
    }

    private static String[] size(String text){
		if(text.startsWith("//"))
			return customDelimeter(text);
		else
			return splitNumbers(text);
	}

	private static char at(String s, int iter) {
		return s.charAt(iter);
	}
}

