//imports
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.util.Scanner;




public class PersonReader
{
    public static void main(String[] args)
    {
        //Declarations
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        Scanner con = new Scanner(System.in);
        boolean start = true;

        start = SafeInput.getYNConfirm(con, "Would you like to select a file?");

        if (start)
        {

            try {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();


                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    System.out.println(String.format("%-8s%-12s%-12s%-5s%7s", "ID#", "Firstname", "Lastname", "Title", "YOB"));
                    System.out.println("==================================================");

                    while (reader.ready())
                    {
                        rec = reader.readLine();
                    System.out.println(rec);

                    }


                } else {
                    System.out.println("You must choose a file. Program terminating.");
                    System.exit(0);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if (!start)
        {
            start = false;
            System.out.println("You did not want to select a file. Goodbye.");
            System.exit(0);
        }
    }


}


