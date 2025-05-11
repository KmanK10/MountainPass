
# Mountain Pass

## Overview
Mountain Pass is a Java program designed to analyze topographic elevation data and find a low-elevation-change path across a mountain range. The program reads elevation data from a CSV file, identifies the highest and lowest points, computes a "greedy" path with minimal elevation change from the left to the right side of the map, and visualizes the results as a graphical image.

## Features
- **Input Processing**: Reads a CSV file containing a 2D grid of elevation data, with the first line specifying the width and height of the grid.
- **Elevation Analysis**: Identifies the highest peak and lowest point in the elevation data, resolving ties by prioritizing locations closest to the top-left.
- **Pathfinding**: Uses a greedy algorithm to find a path from the leftmost column to the rightmost column, minimizing elevation changes at each step.
- **Console Output**: Displays the coordinates of the peak, lowest point, the path taken, total elevation change, and the steepest elevation change.
- **Graphical Output**: Generates a visual representation of the elevation data using the `DrawingPanel` class, with the peak in red, the path in green, and other points in grayscale based on elevation.

## Usage
1. **Prerequisites**: Ensure you have Java installed and the `DrawingPanel` class available in your project.
2. **Running the Program**:
   - Compile and run `MountainPass.java`.
   - When prompted, enter the name of a CSV file containing elevation data (example files are available in the repository).
3. **Output**:
   - The console will display the peak, lowest point, path coordinates, and elevation change metrics.
   - A graphical window will show the elevation map with the peak, path, and terrain visualized.

## File Structure
- `MountainPass.java`: Main program that handles input, pathfinding, and output.
- `Location.java`: A class to store and manage x, y coordinates and elevation data for points in the grid.

## Example Input
A sample CSV file might look like:
```
10,5
100,200,100,100,100,200,200,200,100,100
200,100,100,100,100,200,200,200,200,1
1,1,200,200,500,400,300,100,1,100
200,200,1,200,1,200,1,1,300,100
200,200,200,1,100,100,100,100,100,1
```

## Dependencies
- Java Standard Library
- `DrawingPanel` class (included or available from the project repository)
