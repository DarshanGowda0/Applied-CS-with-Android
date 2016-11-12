package com.google.engedu.puzzle8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PuzzleBoardView extends View {
    public static final int NUM_SHUFFLE_STEPS = 40;
    private Activity activity;
    private PuzzleBoard puzzleBoard;
    private ArrayList<PuzzleBoard> animation;
    private Random random = new Random();

    public PuzzleBoardView(Context context) {
        super(context);
        activity = (Activity) context;
        animation = null;
    }

    public void initialize(Bitmap imageBitmap) {
        int width = getWidth();
        puzzleBoard = new PuzzleBoard(imageBitmap, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (puzzleBoard != null) {
            if (animation != null && animation.size() > 0) {
                puzzleBoard = animation.remove(0);
                puzzleBoard.draw(canvas);
                if (animation.size() == 0) {
                    animation = null;
                    puzzleBoard.reset();
                    Toast toast = Toast.makeText(activity, "Solved! ", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    this.postInvalidateDelayed(500);
                }
            } else {
                puzzleBoard.draw(canvas);
            }
        }
    }

    public void shuffle() {
        if (animation == null && puzzleBoard != null) {
            // Do something. Then:
            for (int i = 0; i < NUM_SHUFFLE_STEPS; i++) {
                //get the valid adjacent tiles
                ArrayList<PuzzleBoard> validMoves = puzzleBoard.neighbours();

                //select a random tile in that
                puzzleBoard = validMoves.get(random.nextInt(validMoves.size()));
            }

            //to redraw/refresh the puzzleBoardView which is .this view
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (animation == null && puzzleBoard != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (puzzleBoard.click(event.getX(), event.getY())) {
                        invalidate();
                        if (puzzleBoard.resolved()) {
                            Toast toast = Toast.makeText(activity, "Congratulations!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        return true;
                    }
            }
        }
        return super.onTouchEvent(event);
    }

    public void solve() {

        //get a comparator object
        Comparator<PuzzleBoard> comparator = new BoardComparator();

        //all the visited boards
//        ArrayList<PuzzleBoard> visited = new ArrayList<>();

        //priority queue of boards
        PriorityQueue<PuzzleBoard> queue = new PriorityQueue<>(100, comparator);

        //set initial board's previousBoard to null(termination condition)
        puzzleBoard.previousBoard = null;

        //set the steps to zero
        puzzleBoard.steps = 0;

        //add the present board to the queue
        queue.add(puzzleBoard);


        /*
        *  while the queue is not empty:
        * Remove from the priority queue the PuzzleBoard with the lowest priority
        * If the removed PuzzleBoard is not the solution,
           -> insert onto the PriorityQueue all neighbouring states (reusing the neighbours method).
        * If it is the solution,
           -> create an ArrayList of all the PuzzleBoards leading to this solution
            (you will need to create a getter for PuzzleBoard.previousBoard).
            Then use Collections.reverse to turn it into an in-order sequence of all the steps to solving the puzzle.
            If you copy that ArrayList to PuzzleBoardView.animation,
            the given implementation of onDraw will animate the sequence of steps to solve the puzzle
        */

//        int i = 0;
        while (!queue.isEmpty()) {

//            Log.d(TAG, "solve() called with: " + i++);

            //get the board with lowest priority(manhattan distance+steps)
            PuzzleBoard lowestPriorityPuzzleBoard = queue.poll();

            //add it to the visited boards
//            visited.add(lowestPriorityPuzzleBoard);

            //if the board is not the solution(solved board)
            if (!lowestPriorityPuzzleBoard.resolved()) {
                //get all its unvisited neighbours and add them to the queue
                for (PuzzleBoard pb : lowestPriorityPuzzleBoard.neighbours()) {
//                    if (!pb.equals(lowestPriorityPuzzleBoard.getPreviousBoard()) && !(visited.contains(pb)))
                    if (!pb.equals(lowestPriorityPuzzleBoard.getPreviousBoard()))
                        queue.add(pb);
                }
            }
            //if the board is the solution, get all the boards leading to this solution and add them to a list till u get the present board (null as previous board,which is termination condition )
            else {

                ArrayList<PuzzleBoard> sequence = new ArrayList<>();
                sequence.add(lowestPriorityPuzzleBoard);
                while (lowestPriorityPuzzleBoard.getPreviousBoard() != null) {
                    lowestPriorityPuzzleBoard = lowestPriorityPuzzleBoard.getPreviousBoard();
                    sequence.add(lowestPriorityPuzzleBoard);
                }

                //remove one extra board
                sequence.remove(sequence.size() - 1);

                //reverse the collection -> from present board to the solved board
                Collections.reverse(sequence);

                //add the sequence to animations
                animation = sequence;

                //refresh the screen
                invalidate();

                return;
            }
        }
    }

    //comparator class
    class BoardComparator implements Comparator<PuzzleBoard> {

        @Override
        public int compare(PuzzleBoard lhs, PuzzleBoard rhs) {

            return lhs.priority() - rhs.priority();

        }
    }
}
