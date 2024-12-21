import stanford.karel.SuperKarel;
public class Homework extends SuperKarel {
    int width, height, midWidth, midHeight, totalMoves;
    public static final int nonStop =9666666;
    public void run() {
        totalMoves = 0;
        backToStart();
        setBeepersInBag(999);
        width = findWidth();
        height = findHeight();
        divideWorld();
        backToStart();
        System.out.println("Total moves of Karel = " + totalMoves +"width  "+ width +"height   " +height );
    }
    private int findWidth(){
        width = 1;
        while(moveIfClear()){
            width++;
            totalMoves++;
        }
        midWidth =(int)(Math.ceil(width/2.0)-1);
        return width;
    }
    private int findHeight(){
        height =1;
        faceNorth();
        while(moveIfClear()){
            height++;
            totalMoves++;
        }
        midHeight =(int)(Math.ceil(height /2.0)-1);
        faceWest();
        return height;
    }
    private void divideWorld() {
        if(Math.min(height,width) > 2) {
            karelMove(midWidth, false);
            faceSouth();
        }
        if(height == 2 && width == 2) {
            placeBeeperIfAbsent();
            backToStart();
            placeBeeperIfAbsent();
        }
        if( height < 3 || width < 3 )
            restOfSpecialCases();
        else if( width%2 == 0 && height %2 == 0 )
            evenXEven();
        else if( width%2 != 0 && height %2 != 0 )
            oddXOdd();
        else if( width%2 == 0 )
            evenXOdd();
        else
            oddXEven();
    }
    private void backToStart() {
        faceWest();
        karelMove(nonStop,false);
        faceSouth();
        karelMove(nonStop,false);
        faceEast();
    }
    private void restOfSpecialCases() {
        if(height >= width)
            faceSouth();
        if( height < 3 || width < 3 ){
            int longestSide = Math.max(height, width) - 3 ;
            int chambersLength = Math.max( 1 , longestSide/4 );
            for(int i = 1; i <= 4 ; i++){
               karelMove(chambersLength,false);
                if (frontIsClear())
                    wallOfBeepers();
                if ( i != 4 )
                    moveIfClear();
            }
            while (moveIfClear())
                wallOfBeepers();
            if (longestSide % 4 == 1)
                wallOfBeepers();
        }
    }
    private void evenXEven(){
        if(height == width){
            walkTurnThenWalk(midHeight +1,'L', midWidth,true,true);
            karelMove(width,false);
            faceNorth();
            karelMove(1,false);
            faceEast();
            walkTurnThenWalk(midWidth ,'R', midHeight+1,true,true);
        }else {  // when they are not equal
            curvedWallOfBeepers();
            faceNorth();
            walkTurnThenWalk(midHeight, 'R', midWidth, false, true);
            horizontalDoubleBeepers();
        }
    }
    private void oddXOdd(){
        karelMove(height,true);
        turnAround();
        walkTurnThenWalk(midHeight,'R',midWidth,true,true);
        karelMove(width,true);
    }
    private void evenXOdd(){
        curvedWallOfBeepers();
        faceNorth();
        walkTurnThenWalk(midHeight,'R', midWidth,false,false);
        karelMove(width,true);
    }
    private void oddXEven(){
        karelMove(height,true);
        faceNorth();
        walkTurnThenWalk(midHeight,'R', midWidth,false,false);
        curvedWallOfBeepers();
    }
    private void curvedWallOfBeepers(){
        int curvedWallHeight=height;
        if(height%2!=0){  // if height is odd
            curvedWallHeight=height-1;
        } else if (width%2!=0) { // if width is odd
            curvedWallHeight=width-1;
        } else{    // if both are even but not equal
            curvedWallHeight=height-2;
        }
        int newMidHeight=curvedWallHeight/2;
        if((curvedWallHeight/2)%2!=0  ){   // if new height's quarter is not even
            karelMove((newMidHeight/2),true);
            turnRight();  // going to right corner
            karelMove(1,true);
            turnLeft();
            karelMove(1,true);
            karelMove((newMidHeight/2),true);
            karelMove((newMidHeight/2),true);
            if(height%2==0 && width%2==0 ){ // if  both  dimensions are even we walk twice since we will use a double wall
                karelMove(2,true);
            }
            else{        // walk through single beeper wall
                karelMove(1,true);
            }
            turnLeft();
            karelMove(1,true);
            turnRight();
            karelMove(nonStop,true);
        }
        else{ // new heights quarter is even
            karelMove((newMidHeight/2),true);
            turnRight();
            makeSquareCurve();
            turnAround();
            karelMove(1,true);
            karelMove((newMidHeight/2),true);
            if(curvedWallHeight==8) // ##RARE CASE##
            {
                karelMove(((newMidHeight/2)-1),true);}
            else{
                karelMove((newMidHeight/2),true);
            }
            turnLeft();
            makeSquareCurve();
            karelMove(nonStop,true);
        }
    }
    private void makeSquareCurve(){
        karelMove(1,true);
        turnRight();
        karelMove(1,true);
        turnRight();
        karelMove(1,true);
        turnRight();
        karelMove(1,true);
        turnRight();
        karelMove(1,true);
        turnRight();
    }
    private void karelMove(int steps, boolean karelPutBeepers) {
        if (karelPutBeepers) {
            placeBeeperIfAbsent();
        }
        for (int i = 0; i < steps; i++) {
            if (!moveIfClear()) {
                break;
            }
            if (karelPutBeepers) {
                placeBeeperIfAbsent();
            }
        }
    }
    private boolean moveIfClear() {
        if (frontIsClear()) {
            move();
            totalMoves++;
            return true;
        }
        return false;
    }
    private void wallOfBeepers(){
        turnRight();
        moveIfClear();
        placeBeeperIfAbsent();
        turnAround();
        moveIfClear();
        placeBeeperIfAbsent();
        turnRight();
    }
    private void walkTurnThenWalk(int walk1,char turn,int walk2,boolean beepers1,boolean beepers2){
        karelMove(walk1,beepers1);
        if(turn == 'L') turnLeft();
        else turnRight();
        karelMove(walk2,beepers2);
        faceWest();
    }
    private void placeBeeperIfAbsent(){
        if(noBeepersPresent())
            putBeeper();
    }
    private void faceWest(){
        while(notFacingWest())
            turnLeft();
    }
    private void faceEast() {
        while(notFacingEast())
            turnLeft();
    }
    private void faceSouth(){
        while(notFacingSouth())
            turnLeft();
    }
    private void faceNorth() {
        while(notFacingNorth())
            turnLeft();
    }
    private void horizontalDoubleBeepers(){
        turnRight();
        karelMove(1,true);
        turnLeft();
        karelMove(width, true);
        turnLeft();
        karelMove(1,true);
        turnLeft();
        karelMove(width,true);
    }
}