import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LuhnGenerator {
	
	// How often you want the ArrayList to be emptied:
	static int maxArrayListSize = 25;
	static int[] luhnOrder = {0, 5, 1, 6, 2, 7, 3, 8, 4, 9};
	static String[] theFiles = {"AddUpTo0", "AddUpTo1", "AddUpTo2", "AddUpTo3", "AddUpTo4", "AddUpTo5", "AddUpTo6", "AddUpTo7", "AddUpTo8", "AddUpTo9"};

	public static void main(String[] args) {
//fillAddUpToFiles();
		//writeln("List Of Luhn Numbers", "test");
		loopThrough();
		
	}

	public static void loopThrough() {
		int leftOffOdd = -1;
		int leftOffEven = -1;
		/*
		String line = read2ndLastLine("List Of Luhn Numbers");
		String temp1 = ""; 
		for(int i = 0; i < line.length(); i = i + 2) {
			temp1 += line.substring(i,  i+1);
		}
		if(!(temp1.equals(""))) {
			leftOffOdd = Integer.parseInt(temp1);
		}
		*/
		int[] theDigits = new int[16];
		for(int i = leftOffOdd + 1; i < /*Math.pow(10, 8) */ 3; i++) {
			
			int addUpTo = (10 - (addDigits(i) % 10))%10;
			
			for(int j = 0; j < 8; j++) {
			theDigits[2*j] = (int) digitGetter8(i, j + 1);
			}
			
			
		
			readAddUpToFiles(theFiles[addUpTo], theDigits);
			
		}
		
	}
	
	public static int addDigits(long number) {
		int sum = 0;
		for(int i = 1; i <= Math.log10(number) + 1; i++) {
			sum+= digitGetter(number, i);
		}
		return sum;
	}
	public static long digitGetter(long number, int digitPlace) {
		return (long) ((long)(number%Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace) + 1))/(long)(Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace)))); 
	}
	public static long digitGetter8(long number, int digitPlace) {
		if(digitPlace < 8 - Math.log10(number)) {
			return 0;
		}
		else {
			digitPlace = digitPlace - (8 - (int)Math.log10(number) - 1);
		return (long) ((long)(number%Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace) + 1))/(long)(Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace)))); 
	
		}
	}
	public static void writeln(String fileName, String line) 
	{
    try {   
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    	}
    catch(IOException ex) 
    	{
        System.out.println("Error writing to file '" + fileName + "'");
    	}
	}

	public static void writeArrayList(String fileName, ArrayList<String> listHere) 
	{
    try {   
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        for(String line: listHere) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        }
        
        bufferedWriter.close();
    	}
    catch(IOException ex) 
    	{
        System.out.println("Error writing to file '" + fileName + "'");
    	}
	}
	
	public static void fillAddUpToFiles() {
		int leftOffAt = -1;
		
		for(String file: theFiles) {
			int theDig = Integer.parseInt(read2ndLastLine(file));
			if(leftOffAt < theDig) {
				leftOffAt = theDig;
			}
		}
		
		for(int i = leftOffAt + 1; i < Math.pow(10, 8 ); i++) {
			
			writeln(theFiles[addDigits(i)%10], Integer.toString(i));
		}
		
		
	}
	
	
	public static void readLines(String fileName)
	{
		String line = null;
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while ((line = bufferedReader.readLine()) != null)
					{
						// method(line);
						
					}
				
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}

	}
	
	public static String read2ndLastLine(String fileName)
	{
		String line = null;
		String last2 = "";
		String last = "";
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null)
					{
					last2 = last;
					last = line;					
						// method(line);

					}
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}
		return last2;

	}

	public static void readAddUpToFiles(String fileName, int[] theDigits)
	{
		String line = null;
		int temp1 = 0; 
		int counter = 0;
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while ((line = bufferedReader.readLine()) != null && counter < 3)
					{
					
					temp1 = Integer.parseInt(line);
					for(int i = 1; i < 9; i++ ) {
						//System.out.print((int) digitGetter8(temp1, i));
					theDigits[2 * i - 1] = (int) digitGetter8(temp1, i);
					String str = "";
					for(int d: theDigits) {
					str += Integer.toString(d);
					}
					//System.out.println(str);
					printLuhnNumbers(theDigits);
					}
					//System.out.println();
					counter++;
					}
				
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}

	}

	public static void printLuhnNumbers(int[] theDigits) {
		String s = "";
		for(int i = 0; i < 16; i++) {
		if(i%2 == 0) {
			theDigits[i] = luhnOrder[theDigits[i]];
		}	
		
		s+= Integer.toString(theDigits[i]);
		
		}
		System.out.println(s);
		//writeln("List Of Luhn Numbers", s);
	}
	
}

