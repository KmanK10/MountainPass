import java.awt.*;
import java.util.*;
import java.io.*;

public class MountainPass {
    public static void main(String[] args) throws FileNotFoundException {
        int width, height;
        ArrayList<ArrayList<Location>> data = new ArrayList<>();
        int[] max = {0, 0};
        int[] min = {0, 0};
        int maxVal, minVal;

        // Request the file to use
        Scanner console = new Scanner(System.in);

        System.out.print("File name? ");
        Scanner file = new Scanner(new File(console.nextLine()));

        console.close(); // Free up resources

        file.useDelimiter(",|\\s+"); // The elevation file data is seperated by ","

        // Get the width and height
        width = file.nextInt();
        height = file.nextInt();
        System.out.println("Width: " + width + ", Height: " + height);

        // populate the data array from the grid
        for (int i = 0; i < height; i++) {
            data.add(new ArrayList<>());

            for (int j = 0; j < width; j++) {
                // Skip empty tokens caused by consecutive delimiters
                while (file.hasNext() && !file.hasNextInt()) {
                    file.next();
                }

                data.get(i).add(new Location(file.nextInt()));
            }
        }
        
        file.close(); // Free up resources

        // Find max and min
        maxVal = data.get(0).get(0).elevation;
        minVal = data.get(0).get(0).elevation;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maxVal < data.get(i).get(j).elevation) {
                    max [0] = j;
                    max [1] = i;
                    maxVal = data.get(i).get(j).elevation;
                }
                
                if (minVal > data.get(i).get(j).elevation) {
                    min [0] = j;
                    min [1] = i;
                    minVal = data.get(i).get(j).elevation;
                }
            }
        }

        // Print max and min
        System.out.println("Mountain Peak: (" + max[0] + ", " + max[1] + ")\nLowest Point: (" + min[0] + ", " + min[1] + ")");

        // Populate the colors
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data.get(i).get(j).setColor(maxVal, minVal);
            }
        }

        data.get(max[1]).get(max[0]).color = new Color(255, 0, 0); // Find max elevation in GUI

        // Calculate path
        int totalElevation = 0;
        int steepestElevation = 0;
        int y = (int) (Math.random() * height); // Start at a random row

        data.get(y).get(0).color = new Color(0, 255, 0); // Set the first step of the path to green
        System.out.print("Lowest Elevation Change Route: (0, " +  y + ")");

        int elevation;
        int temp;
        int pos;
        for (int x = 0; x < width - 1; x++) {
            elevation = data.get(y).get(x).elevation;
            temp = Math.abs(elevation - data.get(y).get(x + 1).elevation); // Get the change in elevation of going east
            pos = 0; // Default forward

            // Calculate optimal move forward
            if ((y > 0) && (temp > Math.abs(elevation - data.get(y - 1).get(x + 1).elevation))) { // Check if going northeast would result in a smaller change than going east, assuming were not all the way north
                if ((y < height - 1) && (Math.abs(elevation - data.get(y - 1).get(x + 1).elevation) == Math.abs(elevation - data.get(y + 1).get(x + 1).elevation)) && (Math.random() < 0.5)) { // Check if going northeast or southeast would be the same change, assuming were not at either limit of the map, then toss a coin to see which one to use
                    temp = Math.abs(elevation - data.get(y + 1).get(x + 1).elevation);
                    pos = 1;
                }
                else {
                    temp = Math.abs(elevation - data.get(y - 1).get(x + 1).elevation);
                    pos = -1;
                }
            } else if ((y < height - 1) && (temp > Math.abs(elevation - data.get(y + 1).get(x + 1).elevation))) { // Check if going southeast would result in an even smaller change, assuming were not all the way south
                temp = Math.abs(elevation - data.get(y + 1).get(x + 1).elevation);
                pos = 1;
            }

            y += pos; // New optimal y;

            data.get(y).get(x + 1).color = new Color(0, 255, 0); // Set the next step of the path to green
            System.out.print(", (" + (x + 1) + ", " + y + ")");

            totalElevation += temp;
            steepestElevation = Math.max(steepestElevation, temp);
        }

        System.out.println("\nTotal Elevation Change: " + totalElevation + "\nSteepest Elevation Change: " + steepestElevation);

        // Create the panel
        DrawingPanel panel = new DrawingPanel(width, height);
        
        // Get a brush to draw on the panel with
        Graphics g = panel.getGraphics();
        
        // Populate the panel
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                g.setColor(data.get(i).get(j).color); // Set the color of our brush
                g.fillRect(j, i, 1, 1); // Create the pixel on the panel
            }
        }
    }
}
