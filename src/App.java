import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOError;
import java.util.ArrayList;

public class App {

    static Boolean readFile(File input,ArrayList<String> allWords){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String temp;
            while((temp = reader.readLine()) != null){
                temp = temp.toLowerCase();
                allWords.add(temp);
            }
            reader.close();
            return(true);
        }catch(Exception e){
            System.out.println("File Could Not Be Read.");
            return(false);
        }
    }
    
    static void checkTypes(ArrayList<String> allWords,ArrayList<String> wordTypes){
        for(int i = 0; i < allWords.size(); i++){
            if(wordTypes.size() > 0){
                boolean included = false;
                for(int k = 0; k < wordTypes.size(); k++){
                    if((allWords.get(i)).equals((wordTypes.get(k)))){
                        included = true;
                    }
                }
                if(!included){
                    wordTypes.add(allWords.get(i));
                    included = false;
                }
            }else{
                wordTypes.add(allWords.get(i));
            }
        }
    }

    static ArrayList<Integer> countWords(ArrayList<String> allWords, ArrayList<String> wordTypes){
        
        ArrayList<Integer> wordCounts = new ArrayList<Integer>();
        int[] tempcount = new int[wordTypes.size()];
        for(int i = 0; i < allWords.size(); i++){
            for(int k = 0; k < wordTypes.size(); k++){
                if(allWords.get(i).equals(wordTypes.get(k))){
                    tempcount[k]++;
                }
            }
        }
        for(int j = 0; j < tempcount.length; j++){
            wordCounts.add(tempcount[j]);
        }
        return(wordCounts);
    }

    static void writeFile(ArrayList<String> wordTypes, ArrayList<Integer> wordCounts){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"));
            for(int i = 0; i < wordTypes.size(); i++){
                writer.write(wordTypes.get(i) + " : " + wordCounts.get(i) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Could Not Write Output File.");
        }
    }
    public static void main(String[] args) throws Exception {
        boolean fileselected = false;
        boolean fileselectloop = false;
        Scanner scnr = new Scanner(System.in);
        ArrayList<String> allWords = new ArrayList<String>();
        ArrayList<String> wordTypes = new ArrayList<String>();
        ArrayList<Integer> wordCounts = new ArrayList<Integer>();
        while(!fileselected){
            if(!fileselectloop){
                System.out.print("Please enter the file that you would like to count: ");
            }else{
                System.out.print("File could not be accessed, please try again: ");
            }
            try {
                File inputFile = new File(scnr.nextLine());
                fileselected = readFile(inputFile , allWords);
            } catch (IOError e) {

            }
            fileselectloop = true;
        }

        checkTypes(allWords, wordTypes);

        wordCounts = countWords(allWords, wordTypes);

        writeFile(wordTypes, wordCounts);
        
        scnr.close();
        
        System.out.println("Word Counting Completed.");
    }
}
