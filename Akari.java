/**
 * Akari represents a single puzzle of the game Akari.
 *
 * @author Lyndon While 
 * @version 2021
 */
import java.util.ArrayList; 
import java.util.Arrays;

public class Akari
{
    private String filename; // the name of the puzzle file
    private int size;        // the board is size x size
    private Space[][] board; // the board is a square grid of spaces of various types

    /**
     * Constructor for objects of class Akari. 
     * Creates and initialises the fields with the contents of filename. 
     * The layout of a puzzle file is described on the LMS page; 
     * you may assume the layout is always correct. 
     */
    public Akari(String Filename)
    {
        //TODO 3
        FileIO Board = new FileIO(Filename); //New FilIO object with the filename as refrence
        filename = Filename;
        size = getSize();
        board = new Space[size][size];
        
        for (int i = 0; i < board.length; i ++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = Space.EMPTY;
        //SETS ALL SPACES ON BOARD TO SPACE.EMPTY INITIALLy
                
        ArrayList<String> TextList = Board.getLines();
        for (int k = 1; k < size; k++){ //Starts at 1 to skip first line (Size Variable)
            String CurrentLine = TextList.get(k); 
        
        phrasingloop:
         for (int x = 0; x < CurrentLine.length(); x++){
                int Xcordinates = Integer.parseInt(String.valueOf(CurrentLine.charAt(x)));
                
                for (int y = x+1; true ; y++){ /*This essentially just loops forever, since we want to run code only when the
                                                 loop is complete, and this code can thus*/
                    if ( false == (y < CurrentLine.length())){
                        x = y;
                        break;}
                    
                    if (' ' == CurrentLine.charAt(y)){
                        x = y;
                        break;}

                    int Ycordinates = Integer.parseInt(String.valueOf(CurrentLine.charAt(y)));
                    
                    if (k == 1)
                        board[Xcordinates][Ycordinates] = Space.BLACK;
                    else if (k == 2)
                        board[Xcordinates][Ycordinates] = Space.ZERO;
                    else if (k == 3)
                        board[Xcordinates][Ycordinates] = Space.ONE;
                    else if (k == 4)
                        board[Xcordinates][Ycordinates] = Space.TWO;
                    else if (k == 5)
                        board[Xcordinates][Ycordinates] = Space.THREE;
                    else if (k == 6)
                        board[Xcordinates][Ycordinates] = Space.FOUR;

                }
            }
        }
    }
    
    /**
     * Uses the example file from the LMS page.
     */
    public Akari()
    {
        this("Puzzles/p7-e7.txt");
    }
    
    /**
     * Returns the name of the puzzle file.
     */
    public String getFilename()
    {
        // TODO 1a
        return filename;
    }
    
    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 1b
        FileIO Ref = new FileIO(filename); //New FilIO object with the filename as refrence
        ArrayList<String> SizeList = Ref.getLines(); //Have to attribute arraylist to SizeList Variable, cause can't directly refrence line values.
        return Integer.parseInt(SizeList.get(0)); //Convert to Interger, and get value of first element in the SizeList
    }
    
    /**
     * Returns true iff k is a legal index into the board. 
     */
    public boolean isLegal(int k)
    {
        // TODO 5
        return isLegal(k,k); 
    }
    
    /**
     * Returns true iff r and c are both legal indices into the board. 
     */
    public boolean isLegal(int r, int c)
    {
        // TODO 6
        try{
        Space Test = board [r][c];
        return true; 
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e){
            return false;}
    }
    
    /**
     * Returns the contents of the square at r,c if the indices are legal, 
     * o/w throws an illegal argument exception. 
     */
    public Space getBoard(int r, int c)
    {
        //TODO 7
        if (isLegal(r,c))
        return board[r][c];
        else
        throw new IllegalArgumentException("Value outside of Board, try values within area of the board");
    }
    
    /**
     * Returns the int equivalent of x. 
     * If x is a digit, returns 0 -> 0, 1 -> 1, etc; 
     * if x is an upper-case letter, returns A -> 10, B -> 11, etc; 
     * o/w throws an illegal argument exception. 
     */
    public static int parseIndex(char x)
    {
        // TODO 2
        int a = 0;
        try
        {
            a = Integer.parseInt(String.valueOf(x));
        }
        catch (java.lang.NumberFormatException e)
        {
            a = (int)x; //Converts to ASCII Format
            if (a < 64 || a > 90){
                throw new IllegalArgumentException("Please use an Uppercase Letters");
            }
            a = a - 55; //Taking away 55 since letter starts on 65 in Ascii, but A = 10 so taking only 66
        }
        return a;
    }
    
    /**
     * Performs a left click on the square at r,c if the indices are legal, o/w does nothing. 
     * If r,c is empty, a bulb is placed; if it has a bulb, that bulb is removed.
     */
    public void leftClick(int r, int c)
    {
        // TODO 8
        if (isLegal(r,c)){
        if (board[r][c] == Space.EMPTY)
            board[r][c] = Space.BULB;
        else if (board[r][c] == Space.BULB)
            board[r][c] = Space.EMPTY;
        }
        //Essentially a toggle of Empty -> Bulb or Bulb -> Empty
    }
    
    /**
     * Sets all mutable squares on the board to empty.
     */
    public void clear()
    {
        //TODO 4
        for (int i = 0; i < board.length; i ++)
            for (int j = 0; j < board[i].length; j++)
                if (board [i][j] == Space.BULB || board [i][j] == Space.EMPTY) 
                    board[i][j] = Space.EMPTY;
        //This is essentially the origonal generation command seen in the Akari Class
        // But with more conditonals
    }
    
    /**
     * Returns the number of bulbs adjacent to the square at r,c. 
     * Throws an illegal argument exception if r,c are illegal coordinates.
     */
    public int numberOfBulbs(int r, int c)
    {
        // TODO 14
        int adjbulbcounter = 0;
        try 
        {
        if (r > 0)
           if (board[r-1][c] == Space.BULB) {adjbulbcounter++;}
        if (c > 0)
            if (board[r][c-1] == Space.BULB) {adjbulbcounter++;}
        if (r < (getSize()-1))
            if (board[r+1][c] == Space.BULB){adjbulbcounter++;}
        if (c < (getSize()-1))
            if (board[r][c+1] == Space.BULB){adjbulbcounter++;}
        //All these if statments make sure we aren't calling for variables outside puzzle, IDK why but getsize command over estimates size of board
        //for some reason so we gotta minus 1.
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e)
        {   
             throw new IllegalArgumentException("Error! the co-ordinates you entred are not on the Board!");
        }
        return adjbulbcounter;
    }
    
    /**
     * Returns true iff the square at r,c is lit by a bulb elsewhere. 
     * Throws an illegal argument exception if r,c are illegal coordinates.
     */
    public boolean canSeeBulb(int r, int c)
    {
        //TODO 15
        boolean BulbSee = false;
        try{
        for (int x = 0; x < board.length; x++){
            if (board[x][c] == Space.BULB)
                if (x == r) {} //This is so we don't return false for the can see bulb we are testing
                else BulbSee = true;
            if (board[x][c] != Space.EMPTY && board[x][c] != Space.BULB){
                if (x < r) {BulbSee = false;}
                if (x > r) {break;}
            }
        }
        if (BulbSee) {}
        else {
        for (int y = 0; y < board.length; y++){
            if (board[r][y] == Space.BULB)
                if (y == c) {}
                else BulbSee = true;
            if (board[r][y] != Space.EMPTY && board[r][y] != Space.BULB){
                if (y < c) {BulbSee = false;}
                if (y > c) {break;}
            }
        }
        }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Error! the co-ordinates you entred are not on the Board!");
        }
        return BulbSee;
    }
    
    /**
     * Returns an assessment of the state of the puzzle, either 
     * "Clashing bulb at r,c", 
     * "Unlit square at r,c", 
     * "Broken number at r,c", or
     * three ticks, as on the LMS page. 
     * r,c must be the coordinates of a square that has that kind of error. 
     * If there are multiple errors on the board, you may return any one of them. 
     */
    public String isSolution()
    {
    // TODO 16
    String Solution = ("");
    
    //TESTING FOR CLASHING BULBS
    Clashing_Bulbs:
    for (int i = 0; i < board.length; i ++)
            for (int j = 0; j < board[i].length; j++)
                if (board [i][j] == Space.BULB)
                    if (canSeeBulb(i,j)){
                        Solution = ("Clashing bulb at " + i + "," + j );
                        break Clashing_Bulbs;}
                        
    //TESTING FOR UNLIT SQUARES
    Unlit_Squares:
    for (int i = 0; i < board.length; i ++)
        for (int j = 0; j < board[i].length; j++)
            if (board [i][j] == Space.EMPTY)
                if (false == (canSeeBulb(i,j))){
                    Solution = Solution + ("Unlit square at " + i + "," + j);
                    break Unlit_Squares;}

    //TESTING FOR BROKEN NUMBERS
    Broken_Numbers:
    for (int i = 0; i < board.length; i ++)
        for (int j = 0; j < board[i].length; j++)
            if (board [i][j] == Space.ZERO){
                if (numberOfBulbs(i,j) != 0)
                    Solution = Solution + ("Broken number at " + i + "," + j);}
            else if(board [i][j] == Space.ONE){
                if (numberOfBulbs(i,j) != 1)
                    Solution = Solution + ("Broken number at " + i + "," + j);}
            else if(board [i][j] == Space.TWO){
                if (numberOfBulbs(i,j) != 2)
                    Solution = Solution + ("Broken number at " + i + "," + j);}
            else if(board [i][j] == Space.THREE){
                if (numberOfBulbs(i,j) != 3)
                    Solution = Solution + ("Broken number at " + i + "," + j);}
            else if(board [i][j] == Space.FOUR){
                if (numberOfBulbs(i,j) != 4)
                    Solution = Solution + ("Broken number at " + i + "," + j);}
    if (Solution == "")
        Solution = ("\u2713\u2713\u2713");
    return Solution;
    }
}