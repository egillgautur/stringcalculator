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
		/*else if(text.contains(",")){
			return sum(splitNumbers(text));
		}
		else if(text.contains("\n")){
			return sum(splitNumbers(text));
		}*/
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
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
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
}

