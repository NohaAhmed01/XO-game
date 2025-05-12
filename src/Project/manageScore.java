
package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

public class manageScore {
    List<Integer> numbers;
    File jk;
    public manageScore(List numbers,File file){
        this.numbers=numbers;
        this.jk=file;
    }
    public void initializeFile() {
        try {
            if (!jk.exists()) {
                PrintWriter out = new PrintWriter(jk);
                out.println("X Score\t\tTie Score\tO Score");
                out.println("0\t\t0\t\t0");
                out.close();
            }
        } catch (IOException ex) {
            System.out.println("error");
        }
    }
    public void saveScore(int scoreX,int scoreTie,int scoreO) {
        
        try {
            FileWriter fw = new FileWriter(jk, true);
            PrintWriter out = new PrintWriter(fw);
            if (numbers.get(0) == 0 && numbers.get(1) == 0 && numbers.get(2) == 0) {
                System.out.println("save score functions scores x as " + scoreX + " and tie as " + scoreTie + " and o as " + scoreO);
                out.append(String.valueOf(scoreX) + "\t\t" + String.valueOf(scoreTie) + "\t\t" + String.valueOf(scoreO) + "\n");
            } else {
                out.append(String.valueOf(numbers.get(0)) + "\t\t" + String.valueOf(numbers.get(1)) + "\t\t" + String.valueOf(numbers.get(2)) + "\n");
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("error");
        }
    }

    public List displayScore(Label lblScoreX,Label lblScoreTie,Label lblScoreO) {
        //  display score
        String lastLine = "";
        numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("score.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
            if (!lastLine.isEmpty()) {
                // Split the last line by spaces (or any other delimiter)
                String[] parts = lastLine.split("\\s+"); // Splitting by whitespace
                // Parse each part as an integer
                for (String part : parts) {
                    try {
                        int number = Integer.parseInt(part);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.out.print(" Skipping non-integer value: " + part);
                    }
                }
            }
            if (!numbers.isEmpty()) {
                System.out.println("Last line as integers: " + numbers);
                lblScoreX.setText(String.valueOf(numbers.get(0)));
                lblScoreTie.setText(String.valueOf(numbers.get(1)));
                lblScoreO.setText(String.valueOf(numbers.get(2)));
            } else {
                lblScoreTie.setText(String.valueOf(0));
                lblScoreX.setText(String.valueOf(0));
                lblScoreO.setText(String.valueOf(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

}
