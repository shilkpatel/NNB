import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class dataset
{
    String[] header;
    double[][] data;
    public dataset(String filepath)
    {
        try {
            Path file = Paths.get(filepath);
            int lines =(int)Files.lines(file).count();
            File datafile = new File(filepath);
            Scanner reader = new Scanner(datafile);
            header = reader.nextLine().split(",");
            data = new double[lines][];
            int n=0;
            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                String[] line_arr = line.split(",");
                data[n] = new double[line_arr.length];
                for (int i=0;i<line_arr.length;i++)
                {
                    data[n][i] = Double.parseDouble(line_arr[i]);
                }
                n++;

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e){}
    }
}
