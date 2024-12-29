import stanford.karel.*;
public class BlankKarel extends SuperKarel {
	public static int moveCounter = 0, beeperCount = 0;

	public void run() {
		startPosition();
		int[] xy = calculateArea();
		int x = xy[0], y = xy[1];

		if (x % 2 == 1 && y % 2 == 1) oddAxes(x, y);
		else if (x % 2 == 0 && y % 2 == 0) evenAxes(x, y);
		else oddEvenAxes(x, y);

		println("Beeper count = " + beeperCount);
	}
	@Override
	public void move() {
		super.move();
		println(++moveCounter);
	}
	@Override
	public void putBeeper() {
		if (noBeepersPresent()) {
			super.putBeeper();
			beeperCount++;
		}
	}
	private void oddAxes(int x, int y) {
		if (y > x && (y - 2) % 3 == 0 && x == 1)
			threeChambersY(y);
		else if (x > y && (x - 2) % 3 == 0 && y == 1)
			threeChambersX(x);
		else {
			if (x != 1) divideX(x);
			if (y != 1) divideY(y);
		}
	}
	private void oddEvenAxes(int x, int y) {
		if ((y - 2) % 3 == 0 && x == 2) threeChambersY(y);
		else if ((x - 2) % 3 == 0 && y == 2) threeChambersX(x);
		else {
			if (x % 2 == 0) {
				if (y == 1 && x!= 2 || y == 3 && x!= 2) doubleDivideX(x);
				else if (x != 2) verticalCurve(x, y);
				if (y != 1) divideY(y);
			} else {
				if (x != 1) divideX(x);
				if (x == 1 && y != 2 || x == 3 && y != 2) doubleDivideY(y);
				else if (y != 2) horizontalCurve(x, y);
			}
		}
	}
	private void evenAxes(int x, int y)  {
		if (x == 2 && y == 2) twoByTwo();
		else if (x == 2) doubleDivideY(y);
		else if (y == 2) doubleDivideX(x);
		else if (x >= y) xAxisCurve(x, y);
		else yAxisCurve(x, y);
	}
	public void twoByTwo(){
		putBeeper();
		turnLeft();
		move();
		turnLeft();
		move();
		putBeeper();
	}
	public void verticalCurve(int x, int y){
		turnLeft();
		moveBy(x/2 - 1);
		turnLeft();
		for (int i = 0; i < y/4; i++) {
			putBeeper();
			move();
		}
		if (((y-1) / 2) % 2 == 1)
			putBeeper();
		turnRight();
		move();
		turnLeft();
		for (int i = 0; i < y/2 + 1; i++) {
			putBeeper();
			move();
		}
		if (((y-1) / 2) % 2 == 1)
			putBeeper();
		turnLeft();
		move();
		turnRight();
		moveWhileFrontClearPutBeeper();
	}
	public void horizontalCurve(int x, int y){
		turnAround();
		moveBy(y/2-1);
		turnLeft();
		for (int i = 0; i < x/4 + 1; i++) {
			putBeeper();
			move();
		}
		if (((x-1) / 2) % 2 == 1)
			putBeeper();
		turnRight();
		move();
		turnLeft();
		moveWhileFrontClearPutBeeper();
		turnAround();
		moveBy(x/2);
		turnRight();
		move();
		turnLeft();
		for (int i = 0; i < x/4 + 1; i++) {
			putBeeper();
			move();
		}
		if (((x-1) / 2) % 2 == 1)
			putBeeper();
		turnLeft();
		move();
		turnRight();
		moveWhileFrontClearPutBeeper();
	}
	public void yAxisCurve(int x, int y){
		int curveFactor = (y - x) / 2;
		curveFactor++;
		turnAround();
		moveBy((y-1)/2);
		turnRight();
		putBeeper();
		for (int i = 0; i < x/2 - 1; i++) {
			move();
			putBeeper();
		}
		turnLeft();
		for (int i = 0; i < curveFactor; i++) {
			move();
			putBeeper();
		}
		turnRight();
		move();
		turnRight();
		putBeeper();
		for (int i = 0; i < curveFactor - 1; i++) {
			move();
			putBeeper();
		}
		turnLeft();
		moveWhileFrontClearPutBeeper();
		// x axis
		turnAround();
		moveBy(x/2 - 1);
		turnLeft();
		moveWhileFrontClearPutBeeper();
		turnAround();
		moveBy(y/2 - curveFactor);
		turnLeft();
		move();
		turnRight();
		moveWhileFrontClearPutBeeper();
	}
	public void xAxisCurve(int x, int y) {
		int curveFactor = (x - y) / 2;
		curveFactor++;
		turnLeft();
		moveBy((x-1)/2);
		turnLeft();
		putBeeper();
		for (int i = 0; i < y/2 - 1; i++) {
			move();
			putBeeper();
		}
		turnRight();
		for (int i = 0; i < curveFactor; i++) {
			move();
			putBeeper();
		}
		turnLeft();
		move();
		turnLeft();
		putBeeper();
		for (int i = 0; i < curveFactor - 1; i++) {
			move();
			putBeeper();
		}
		turnRight();
		moveWhileFrontClearPutBeeper();
		// y axis
		turnAround();
		moveBy(y/2 - 1);
		turnRight();
		moveWhileFrontClearPutBeeper();
		turnAround();
		moveBy(x/2 - curveFactor);
		turnRight();
		move();
		turnLeft();
		moveWhileFrontClearPutBeeper();
	}
	public void divideY(int y){
		turnAround();
		moveBy(y/2);
		turnLeft();
		moveWhileFrontClearPutBeeper();
		turnAround();
		moveWhileFrontClearPutBeeper();
	}
	public void divideX(int x){
		turnLeft();
		moveBy(x/2);
		turnLeft();
		moveWhileFrontClearPutBeeper();
	}
	public void doubleDivideY(int y){
		divideY(y-1);
		turnLeft();
		move();
		turnLeft();
		moveWhileFrontClearPutBeeper();
	}
	public void doubleDivideX(int x){
		divideX(x-1);
		turnRight();
		move();
		turnRight();
		moveWhileFrontClearPutBeeper();
	}
	public void threeChambersY(int y){
		turnAround();
		for (int column = 0; column < 2; column++) {
			moveBy((y-2)/3);
			if (column == 0)
				turnRight();
			else turnLeft();
			moveWhileFrontClearPutBeeper();
			if (column == 0) {
				turnLeft();
				move();
			}
		}
	}
	public void threeChambersX(int x){
		turnLeft();
		for (int column = 0; column < 2; column++) {
			moveBy((x-2)/3);
            if (column == 0)
                turnLeft();
            else
                turnRight();
			moveWhileFrontClearPutBeeper();
			if (column == 0) {
				turnRight();
				move();
			}
		}
	}
	public void startPosition(){
		while (notFacingSouth())
			turnLeft();
		while (frontIsClear())
			move();
		if (rightIsClear()) {
			turnRight();
			while (frontIsClear())
				move();
		}
		while (notFacingEast())
			turnLeft();
	}
	private int[] calculateArea(){
		int x = 1,y = 1;
		while (frontIsClear()){
			x++;
			move();
		}
		turnLeft();
		while (frontIsClear()){
			y++;
			move();
		}
		return new int[]{x,y};
	}
	private void moveWhileFrontClearPutBeeper(){
		putBeeper();
		while (frontIsClear()){
			move();
			putBeeper();
		}
	}
	private void moveBy(int length) {
		for (int i = 0; i < length; i++) move();
	}
}