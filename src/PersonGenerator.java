//imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        //Declarations
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");
        ArrayList<String> people = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);

        String ID = " " ;
        String firstName =" ";
        String lastName = " " ;
        String title = " " ;
        String record = " " ;
        int YOB = 0 ;


        do
        {
            //Goes to SafeInput and checks for non-zero length string
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID (6 digits)" );
            firstName = SafeInput.getNonZeroLenString(in, "Please enter the first name" );
            lastName = SafeInput.getNonZeroLenString(in, "Please enter the last name" );
            title = SafeInput.getNonZeroLenString(in, "Please enter the title" );

            //Goes to SafeInput and checks to make sure birth year is 4 digits long
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth (4 digits long )", 1000, 9999 );

            //formats information user input
            record = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB ;

            //records in array list
            people.add(record);

            done = SafeInput.getYNConfirm(in, "Are you done entering information?");

        }
        while(!done);

        for(String p: people)
        {
            System.out.println(p);
        }

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : people) //enhanced for loop
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }

            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }
}