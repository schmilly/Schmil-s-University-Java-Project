/**
 * AkariViewer represents an interface for a player of Akari.
 *
 * @author Lyndon While
 * @version 2021
 */
import java.awt.*;
import java.awt.event.*; 

public class AkariViewer implements MouseListener
{    
    private Akari puzzle;    // the internal representation of the puzzle
    private SimpleCanvas sc; // the display window
    private int windowsize;
    private int displacment;
    private int size;
    /**
     * Constructor for objects of class AkariViewer.
     * Sets all fields and displays the initial puzzle.
     */
    public AkariViewer(Akari puzzle)
    {
        //TODO 10
        this.puzzle = puzzle;
        windowsize = 1000; //DO not change will break if changed
        size = puzzle.getSize();
        sc = new SimpleCanvas ("Akari Puzzle", windowsize, windowsize,Color.white);
        sc.addMouseListener(this);
        displacment = 100; //Eseentialy Margins, but displacment from Window Edge, Should've really called it Margins tho
        displayPuzzle();
    }
    
    /**
     * Selects from among the provided files in folder Puzzles. 
     * The number xyz selects pxy-ez.txt. 
     */
    public AkariViewer(int n)
    {
        this(new Akari("Puzzles/p" + n / 10 + "-e" + n % 10 + ".txt"));
    }
    
    /**
     * Uses the provided example file on the LMS page.
     */
    public AkariViewer()
    {
        this(77);
    }
    
    /**
     * Returns the internal state of the puzzle.
     */
    public Akari getPuzzle()
    {
        // TODO 9a
        return puzzle;
    }
    
    /**
     * Returns the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        // TODO 9b
        return sc;
    }
    
    /**
     * Displays the initial puzzle; see the LMS page for a suggested layout. 
     */
    private void displayPuzzle()
    {
        // TODO 11
        int evener = (windowsize - (displacment*2))/size; 
        sc.drawRectangle(0,0,windowsize,windowsize,Color.white); //ITs a hackey way of erasing the board, but I cannot get any erase commands to work
                                                                 //So just blanking a rectange the size of the canvas is good enough and it works        

        //THIS THEN FILLS IN THE SQUARES
        for (int y = 0; y < size; y++){   
            for (int x = 0; x < size; x++){
            Space currentspace = puzzle.getBoard(x,y);
            if (currentspace == Space.EMPTY){
                if (puzzle.canSeeBulb(x,y)){
                    sc.drawRectangle(displacment + (evener * x), displacment + (evener * y),displacment + (evener * (1+x)) ,displacment + (evener * (1+y)) , Color.yellow);
                }
            }
            else if (currentspace == Space.BLACK)
            sc.drawRectangle(displacment + (evener * x), displacment + (evener * y),displacment + (evener * (1+x)) ,displacment + (evener * (1+y)) , Color.black);
            else{
                int Halfwayx = (((displacment + (evener * x))+ (displacment + (evener * (1+x))))/2); // WE USE THESE HALFWAY POINT CALCULATIONS TO FIND THE CENTRES OF THESE GRIDS
                int HalfwayY = (((displacment + (evener * y))+ (displacment + (evener * (1+y))))/2); // SO THE TEXT AND STUFF CAN BE CENTRED
                if (currentspace == Space.BULB){
                sc.drawRectangle(displacment + (evener * x), displacment + (evener * y),displacment + (evener * (1+x)) ,displacment + (evener * (1+y)) , Color.yellow);
                    if (puzzle.canSeeBulb(x,y))
                        sc.drawString("💡", Halfwayx, HalfwayY, Color.red);
                    else
                        sc.drawString("💡", Halfwayx, HalfwayY, Color.black);
                }
                //THIS IS FOR NON BULB ITEMS, WE RUN IT FIRST SOW WE DON'T HAVE TO WORRY ABOUT BLACK SQUARES BLOCKING THE BULB ICON
                else{sc.drawRectangle(displacment + (evener * x), displacment + (evener * y),displacment + (evener * (1+x)) ,displacment + (evener * (1+y)) , Color.black); 
                if (currentspace == Space.ZERO)
                sc.drawString("0", Halfwayx, HalfwayY, Color.white);
                else if (currentspace == Space.ONE)
                sc.drawString("1", Halfwayx, HalfwayY, Color.white);
                else if (currentspace == Space.TWO)
                sc.drawString("2", Halfwayx, HalfwayY, Color.white);
                else if (currentspace == Space.THREE)
                sc.drawString("3", Halfwayx, HalfwayY, Color.white);
                else if (currentspace == Space.FOUR)
                sc.drawString("4", Halfwayx, HalfwayY, Color.white);}                    
                }
            }
        }
        //Clear Button
        sc.drawRectangle((windowsize/3)-20, (windowsize-(displacment/2))-20, (windowsize/3)+60, (windowsize-(displacment/2))+20, Color.black);
        sc.drawString("CLEAR", windowsize/3, windowsize-(displacment/2), Color.white);
        
        //Check Solution Button
        sc.drawRectangle((windowsize/3*2)-20, (windowsize-(displacment/2))-20, (windowsize/3*2)+120, (windowsize-(displacment/2))+20, Color.black);
        sc.drawString("CHECK SOLUTION",  windowsize/3*2, windowsize-(displacment/2), Color.white);
        
        //THIS GENERATES THE GRID OF THE GAME, USING JUST LINES   
        for (int i = 0; i <= size; i++){
            sc.drawLine(displacment, displacment + (evener * i) , windowsize - displacment , displacment + (evener * i), Color.black);
            sc.drawLine(displacment + (evener * i), displacment , displacment + (evener * i) , windowsize - displacment, Color.black);}
        
        // Happens after All other stuff so one can still see lines
        puzzle.isSolution();
        if (puzzle.isSolution() == ("\u2713\u2713\u2713"))
            sc.drawString("CONGRATULATIONS, YOU HAVE WON!!!", (windowsize/2) , (windowsize/2), Color.white);
            System.out.println("Congrats You have won!!, many celebrations!");
    }
    
    /**
     * Performs a left click on the square at r,c if the indices are legal, o/w does nothing. 
     * Updates both puzzle and the display. 
     */
    public void leftClick(int r, int c)
    {
        // TODO 12
        puzzle.leftClick(r,c);
        displayPuzzle();
    }
    
    // TODO 13
    public void mousePressed (MouseEvent e) {
        int MouseX = e.getX();
        int MouseY= e.getY();
        String Check= puzzle.isSolution();
        if (MouseY < displacment){}
        if (MouseY > windowsize - displacment){
            if (MouseX >= windowsize/3-20 && MouseX <= (windowsize/3)+60)
                puzzle.clear();
            else if(MouseX >= (windowsize/3*2)-20 && MouseX <= (windowsize/3*2)+120)
                System.out.println(Check);
        } 
        if (MouseX < displacment || MouseX > windowsize - displacment){} //DO NOTHING SINCE ITS OUTSIDE THE GIRID
        else { 
            double Distribution = (windowsize - (displacment*2))/size; 
            double WndownSize = windowsize;
            double RowX = Math.floor((MouseX + displacment)/Distribution);
            double RowY = Math.floor((MouseY + displacment)/Distribution);
            int ROWX = ((int) RowX) -1;
            int ROWY = ((int) RowY) -1;
            leftClick(ROWX,ROWY);
        } }
    public void mouseClicked (MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited  (MouseEvent e) {}
}
