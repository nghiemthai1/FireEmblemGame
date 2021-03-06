package v3;


import java.util.Collections; 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The field that the game interacts with.
 * @author Matthew Rodriguez
 *
 */
public class Field
{
    private static final Random rand = new Random();
    
    // The depth and width of the field.
    private int depth, width;
    private Object[][] field;

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }
    
    /**
     * Clears the field.
     */
    public void clear()
    {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    /**
     * Clear the given location.
     * @param location The location to clear.
     */
    public void clear(Location location)
    {
        field[location.getRow()][location.getCol()] = null;
    }
    
    /**
     * Place a character at the given location.
     * If there is already a character at the location it will
     * be lost.
     * @param character The character to be placed.
     * @param row Row coordinate of the location.
     * @param col Column coordinate of the location.
     */
    public void place(Object charcter, int row, int col)
    {
        place(charcter, new Location(row, col));
    }
    
    /**
     * Place a character at the given location.
     * If there is already a character at the location it will
     * be lost.
     * @param character The character to be placed.
     * @param location Where to place the animal.
     */
    public void place(Object character, Location location)
    {
        field[location.getRow()][location.getCol()] = character;
    }
    
    /**
     * Return the character at the given location, if any.
     * @param location Where in the field.
     * @return The character at the given location, or null if there is none.
     */
    public Object getObjectAt(Location location)
    {
        return getObjectAt(location.getRow(), location.getCol());
    }
    
    /**
     * Return the character at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The character at the given location, or null if there is none.
     */
    public Object getObjectAt(int row, int col)
    {
        return field[row][col];
    }
   
    
    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location)
    {
        List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location,1,1);
        for(Location next : adjacent) {
            if(getObjectAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }
    
    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getMoveLocations(Location location, int characterRange){
    	List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location,characterRange,characterRange);
        for(Location next : adjacent) {
            //if(getObjectAt(next) == null) {
                free.add(next);
            //}
        }
        return free;
    }
    
    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getAttackLocations(Location location, int characterRange){
    	List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location,characterRange,characterRange);
        for(Location next : adjacent) {
            free.add(next);
        }
        return free;
    }
    
    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location)
    {
        // The available free ones.
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.size() > 0) {
            return free.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> adjacentLocations(Location location, int rangeX, int rangeY)
    {
     assert location != null : "Null location passed to adjacentLocations";
     // The list of locations to be returned.
     List<Location> locations = new LinkedList<Location>();
     if(location != null) {
      int row = location.getRow();
      int col = location.getCol();
      for(int roffset = rangeX*-1; roffset <= rangeX; roffset++) { 
       int nextRow = row + roffset; 
       if(nextRow >= 0 && nextRow < depth) { 
        int limit = rangeX - Math.abs(roffset);
        for(int coffset = -limit; coffset <= limit; coffset++) { 
         int nextCol = col  + coffset; // Exclude invalid locations and the original location. 
         if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) { 
          locations.add(new Location(nextRow, nextCol));
         }
        }
       }

       // Shuffle the list. Several other methods rely on the list
       // being in a random order.
       // Collections.shuffle(locations, rand);
       }
      }
     return locations; 
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth()
    {
        return width;
    }
}
