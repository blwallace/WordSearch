package WordSearch;

import java.util.Iterator;

/**
 * Created by brianwallace on 2/18/16.
 */
public class Seeker implements Iterable {

    private int n;
    private int m;
    private int nBound;
    private int mBound;
    private Seeker.Op op;
    private boolean wrap;


    /**
     * Constructor
     *
     * @param n,m the begining position we'll be searching
     * @param op is the operation we're performing for the seeker iterator
     * @param wrap is whether we're wrapping our search
     * @param mBound, nBound are our boundaries
     *
     */

    public Seeker(int n, int m, Op op, boolean wrap, int nBound, int mBound) {
        this.n = n;
        this.m = m;
        this.op = op;
        this.wrap = wrap;
        this.nBound = nBound;
        this.mBound = mBound;
    }

    /**
     *This is a list of all possible directions we can search
     */
    public enum Op {
        TOP_BOTTOM,BOTTOM_TOP,LEFT_RIGHT,RIGHT_LEFT,
        BOTTOMLEFT_TOPRIGHT,BOTTOMRIGHT_TOPLEFT,
        TOPLEFT_BOTTOMRIGHT,TOPRIGHT_BOTTOMLEFT;
    }


    /**
     * This is the heart of the seeker
     * The iterator takes direction, starting point, boundary, wrapping information
     *   and can return the coordinates of the next location.  We can also use this
     *   to determine if there is another value
     */
    @Override
    public Iterator iterator() {

        Iterator<int[]> it = new Iterator<int[]>() {
            private int currentN = n;
            private int currentM = m;
            private int ticker = 0;

            // Determines if there is another character.

            @Override
            public boolean hasNext() {
                if(inSpanBound(nextPosition())){
                    return true;
                }
                else{
                    return false;
                }
            }

            // Returns the position of the next character

            @Override
            public int[] next() {
                if(hasNext()){
                    int[] intArray = nextPosition();
                    currentN = intArray[0];
                    currentM = intArray[1];
                    ticker++;
                    return intArray;
                }
                else{
                    return null;
                }
            }

            // Instructs iterator where to find the next character

            public int[] nextPosition(){
                int seekerN = currentN;
                int seekerM = currentM;

                switch(op){
                    case TOP_BOTTOM:
                        seekerN++;
                        if(!inBounds(seekerN,seekerM) && wrap){
                            seekerN = 0;
                        }
                        return new int[]{seekerN, seekerM};

                    case BOTTOM_TOP:
                        seekerN--;
                        if(!inBounds(seekerN,seekerM) && wrap){
                            seekerN = nBound-1;
                        }
                        return new int[]{seekerN, seekerM};

                    case LEFT_RIGHT:
                        seekerM++;
                        if(!inBounds(seekerN,seekerM) && wrap){
                            seekerM = 0;
                        }
                        return new int[]{seekerN, seekerM};

                    case RIGHT_LEFT:
                        seekerM--;
                        if(!inBounds(seekerN,seekerM) && wrap){
                            seekerM = mBound - 1;
                        }
                        return new int[]{seekerN, seekerM};

                    case BOTTOMLEFT_TOPRIGHT:
                        seekerM++;
                        seekerN--;
                        if(!inMBound(seekerN,seekerM) && wrap) {
                            seekerM = 0;
                        }
                        if(!inNBound(seekerN,seekerM) && wrap){
                            seekerN = nBound - 1;
                        }
                        return new int[]{seekerN, seekerM};

                    case BOTTOMRIGHT_TOPLEFT:
                        seekerM--;
                        seekerN--;
                        if(!inMBound(seekerN,seekerM) && wrap) {
                            seekerM = mBound - 1;
                        }
                        if(!inNBound(seekerN,seekerM) && wrap){
                            seekerN = nBound - 1;
                        }
                        return new int[]{seekerN, seekerM};

                    case TOPLEFT_BOTTOMRIGHT:
                        seekerM++;
                        seekerN++;
                        if(!inMBound(seekerN,seekerM) && wrap) {
                            seekerM = 0;
                        }
                        if(!inNBound(seekerN,seekerM) && wrap){
                            seekerN = 0;
                        }
                        return new int[]{seekerN, seekerM};

                    case TOPRIGHT_BOTTOMLEFT:
                        seekerM--;
                        seekerN++;
                        if(!inMBound(seekerN,seekerM) && wrap) {
                            seekerM = mBound - 1;
                        }
                        if(!inNBound(seekerN,seekerM) && wrap){
                            seekerN = 0;
                        }
                        return new int[]{seekerN, seekerM};

                    default: return new int[]{seekerN, seekerM};

                }
            }

            // Determines if our coordinates are in bounds.
            public boolean inBounds(int seekerN, int seekerM){
                if(seekerM >= mBound || seekerN >=nBound || seekerM < 0 || seekerN < 0){
                    return false;
                }
                else{
                    return true;
                }
            }
            public boolean inMBound(int seekerN, int seekerM){
                if(seekerM >= mBound || seekerM < 0){
                    return false;
                }
                else{
                    return true;
                }
            }
            public boolean inNBound(int seekerN, int seekerM){
                if(seekerN >= nBound || seekerN < 0){
                    return false;
                }
                else{
                    return true;
                }
            }
            // Determines if we have reached our beggining point.
            public boolean inSpanBound(int[] seeker){
                if(!inBounds(seeker[0],seeker[1])){return false;}
                if(seeker[1] == m && seeker[0] == n){return false;}
                else{return true;}
            }
        };

        return it;
    }
}
