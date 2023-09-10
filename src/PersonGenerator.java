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
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.csv");
        ArrayList<Person> people = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);

        String ID = " " ;
        String firstName =" ";
        String lastName = " " ;
        String title = " " ;
        String csvRec = " " ;
        Person folk;
        int YOB = 0 ;
        int a = 0;


        do
        {
            //Goes to SafeInput and checks for non-zero length string
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID (6 digits)" );
            firstName = SafeInput.getNonZeroLenString(in, "Please enter the first name" );
            lastName = SafeInput.getNonZeroLenString(in, "Please enter the last name" );
            title = SafeInput.getNonZeroLenString(in, "Please enter the title" );

            //Goes to SafeInput and checks to make sure birth year is 4 digits long
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth (4 digits long )", 1000, 9999 );

            //creates input to object
            folk = new Person(ID, firstName, lastName, title, YOB);

            folk.setFirstName(firstName);

            //records in array list
            people.add(folk);

            //Asks user for more information using getYN method
            done = SafeInput.getYNConfirm(in, "Are you done entering information?");


        }
        while(!done);

        //Prints out objects
        for(Person p:people)
        {
            System.out.println(p);
        }

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(Person rec : people) //enhanced for loop to write CSV file
            {
                csvRec = rec.toCSVDataRecord(); //changes person object toCSVData
                writer.write(csvRec, 0, csvRec.length());
                writer.newLine();  // adds the new line

                System.out.println(csvRec);

            }

            writer.close();


            System.out.println();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }










    }


}
