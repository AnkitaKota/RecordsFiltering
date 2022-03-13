package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Read any file and get the last number found in record sales
* Example recordSales_CA_1202_1221 -> get 1221
* In short: Find all words start with recordSales and get the last number */
public class ReceiptsFilter {

    public static void main(String[] args)  {
        String file = "src/receipts.txt";
        String outputFile = "src/output.csv";
        FileWriter outputfile = null;


        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            outputfile = new FileWriter(outputFile);
            for(String line; (line = br.readLine()) != null; ) {
                System.out.println(line);
                String regex = "\\BrecordSales|recordSales\\B\\w+";
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find())
                {

                    String recordSales = matcher.group();
                    String[] id = recordSales.split("_");
                    try {
                        outputfile.append(System.lineSeparator());
                        outputfile.append(id[3] );
                        outputfile.flush();
                    } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                }

            }

            outputfile.close();
            br.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


}
