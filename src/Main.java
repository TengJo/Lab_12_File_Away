import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec= "";
        ArrayList<String> lines = new ArrayList<>();
        int lineCounter = 0;
        int words=0;
        char  myChar;
        int charCount=0;

        try
        {
          File workingDirectory = new File(System.getProperty("user.dir"));
          chooser.setCurrentDirectory(workingDirectory);
          if (chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
          {
              selectedFile = chooser.getSelectedFile();
              Path file = selectedFile.toPath();
              InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
              BufferedReader reader = new BufferedReader(new InputStreamReader(in));
              while (reader.ready())
              {
                  rec = reader.readLine();
                  rec = rec + "\n";
                  lines.add(rec);
                  lineCounter++;
                  System.out.printf("\n Line %2d %-60s", lineCounter, rec);
              }
              for (String l :lines)
              {
                for (int i =0; i < l.length();i++ )
                {
                    myChar = l.charAt(i);
                    if (myChar == ' ' || myChar == '\n')
                    {
                        words++;
                    }
                    else
                    {
                        charCount++;
                    }
                }
              }
              System.out.println("\nThe amount of words is: " + words);
              System.out.println("\nThe amount of character is: " + charCount);

              reader.close(); // must close the file to seal it and flush buffer
              System.out.println("\n\nData file read!");
          }
          else
          {
              System.out.println("You did not choose a file to process, run the program again.");
              System.exit(0);
          }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
