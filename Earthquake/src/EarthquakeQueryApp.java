import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EarthquakeQueryApp
{
    private static String[] dataTimes;
    private static double[] magnitudes;
    private static String[] places;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Runner
     * 
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException
    {

        File file = new File(
            "/Users/ahmedabdelhady/Documents/csv_reader/all_month.csv");
        recordData(file);
    }


    /**
     * Reads over file and extracts required data for arrays
     * 
     * @throws FileNotFoundException
     */
    public static void recordData(File file)
        throws FileNotFoundException
    {

        // Counts lines to initialize arrays
        Scanner count = new Scanner(file);
        count.useDelimiter(",");
        count.nextLine();

        int earthquakes = 0;
        while (count.hasNextLine())
        {
            count.nextLine();
            earthquakes++;
        }
        count.close();

        // Re-reads to parse data into arrays
        Scanner reader = new Scanner(file);
        reader.useDelimiter(",");
        dataTimes = new String[earthquakes];
        magnitudes = new double[earthquakes];
        places = new String[earthquakes];

        reader.nextLine();

        for (int i = 0; i < earthquakes; i++)
        {
            String time = reader.next();
            dataTimes[i] = time;

            double magnitude = reader.nextDouble();
            magnitudes[i] = magnitude;

            String place = reader.nextLine();
            place = place.replaceAll(",", "");
            places[i] = place.replaceAll("\"", "");

        }
        reader.close();

        // Take user input to search through arrays
        Scanner input = new Scanner(System.in);
        System.out.print("Place:");
        String place = input.nextLine();
        int i = -1;

        int numOfEarthquakes = 0;

        System.out.print("Magnitude:");

        double magnitude = input.nextDouble();

        System.out.println("Search Results:");
        System.out.println();

        if (place.equals("-"))
        {
            place = "";
            for (double target : magnitudes)
            {

                i++;

                if (target == magnitude)
                {
                    numOfEarthquakes++;
                    System.out.println("Date and Time:" + dataTimes[i]);
                    System.out.println("Place:" + places[i]);
                    System.out.println("Magnitude:" + target);

                    System.out.println("__________");
                    System.out.println();

                }
            }
        }
        else
        {
            for (String target : places)
            {

                i++;

                if (target.contains(place))
                {
                    if (magnitudes[i] == magnitude)
                    {
                        numOfEarthquakes++;
                        System.out.println("Date and Time:" + dataTimes[i]);
                        System.out.println("Place:" + target);
                        System.out.println("Magnitude:" + magnitudes[i]);

                        System.out.println("__________");
                        System.out.println();
                    }
                }
            }
        }

        if (numOfEarthquakes > 0)
        {
            System.out.println(numOfEarthquakes + " earthquakes...");
        }
        else
        {
            System.out.println(numOfEarthquakes + " No earthquakes found");
        }

    }
}
