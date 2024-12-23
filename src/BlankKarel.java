import stanford.karel.*;
public class BlankKarel extends SuperKarel {
	public void run() {
		startPosition();

		int[] xy = calculateArea();
		int x = xy[0], y = xy[1];

		if (x%2 == 1 && y%2 == 1)
			bothOdd(x, y);
		else if (x%2 == 0 && y%2 == 0)
			bothEven(x, y);
		else oddEven(x, y);

		println("Beeper count = " + beeperCount);
	}
	public static int moveCounter = 0;
	@Override
	public void move() {
		super.move();
		moveCounter++;
		println(moveCounter);
	}
	public static int beeperCount = 0;
	@Override
	public void putBeeper() {
		if (noBeepersPresent()) {
			super.putBeeper();
			beeperCount++;
		}
	}
	private void bothOdd(int x, int y){
		//starting from northeast facing north
		if (y > x && (y-2)%3 == 0 && x == 1) {
			threeChambersY(y);
			return;
		}
		if (x > y && (x-2)%3 == 0 && y == 1) {
			threeChambersX(x);
			return;
		}
		if (x != 1){
			divideX(x);
		}
		if (y != 1){
			divideY(y);
		}
	}
	private void oddEven(int x, int y) {
		if ((y-2)%3 == 0 && x == 2) {
			threeChambersY(y);
			return;
		}
		if ((x-2)%3 == 0 && y == 2) {
			threeChambersX(x);
			return;
		}
		if (x%2 == 0){
			if (x != 2) {
				doubleDivideX(x);
			}
			if (y != 1){
				divideY(y);
			}
		} else if (x%2 == 1){
			if (x != 1){
				divideX(x);
			}
			if (y != 2){
				doubleDivideY(y);
			}
		}
	}
	private void bothEven(int x, int y) {
		//starting from northeast facing north
		if (y == 2 && x == 2){
			return;
		}
		if (x == 2){
			doubleDivideY(y);
			return;
		}
		if (y == 2){
			doubleDivideX(x);
			return;
		}
		if (x >= y)
			xAxisCurve(x, y);
		else
			yAxisCurve(x, y);
	}
	public void yAxisCurve(int x, int y){
		int curveFactor = (y - x) / 2;
		curveFactor++;

		turnAround();
		for (int i = 0; i < y/2 - 1; i++) {
			move();
		}
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
		for (int i = 0; i < x/2 - 1; i++) {
			move();
		}
		turnLeft();
		moveWhileFrontClearPutBeeper();
		turnAround();
		for (int i = 0; i < y/2 - curveFactor ; i++) {
			move();
		}
		turnLeft();
		move();
		turnRight();
		putBeeper();
		moveWhileFrontClearPutBeeper();
	}
	public void xAxisCurve(int x, int y) {
		//starting northeast facing north
		int curveFactor = (x - y) / 2;
		curveFactor++;

		turnLeft();
		for (int i = 0; i < x/2 - 1; i++) {
			move();
		}
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
		for (int i = 0; i < y/2 - 1; i++) {
			move();
		}
		turnRight();
		moveWhileFrontClearPutBeeper();
		turnAround();
		for (int i = 0; i < x/2 - curveFactor; i++) {
			move();
		}
		turnRight();
		move();
		turnLeft();
		putBeeper();
		moveWhileFrontClearPutBeeper();
	}
	public void divideY(int y){
		//starting middleSouth facing south
		turnAround();
		for (int i = 0; i < y/2; i++) {
			move();
		}
		turnLeft();
		putBeeper();
		moveWhileFrontClearPutBeeper();
		turnAround();
		moveWhileFrontClearPutBeeper();
	}
	public void doubleDivideY(int y){
		divideY(y-1);

		turnLeft();
		move();
		turnLeft();
		putBeeper();
		moveWhileFrontClearPutBeeper();
	}
	public void divideX(int x){
		//starting from northeast facing north
		turnLeft();
		for (int i = 0; i < x/2; i++) {
			move();
		}
		turnLeft();
		putBeeper();
		moveWhileFrontClearPutBeeper();
	}
	public void doubleDivideX(int x){
		divideX(x-1);

		turnRight();
		move();
		turnRight();

		putBeeper();
		moveWhileFrontClearPutBeeper();
	}
	public void threeChambersY(int y){
		//starting from northeast facing north
		turnAround();
		for (int column = 0; column < 2; column++) {
			for (int i = 0; i < (y-2)/3; i++) {
				move();
			}
			if (column == 0)
				turnRight();
			else turnLeft();

			putBeeper();
			moveWhileFrontClearPutBeeper();
			if (column == 0) {
				turnLeft();
				move();
			}
		}
	}
	public void threeChambersX(int x){
		//starting from northeast facing north
		turnLeft();
		for (int column = 0; column < 2; column++) {
			for (int i = 0; i < (x-2)/3; i++) {
				move();
			}
			if (column == 0)
				turnLeft();
			else turnRight();

			putBeeper();
			moveWhileFrontClearPutBeeper();
			if (column == 0) {
				turnRight();
				move();
			}
		}
	}
	public void startPosition(){
		while (notFacingSouth()) {
			turnLeft();
		}
		while (frontIsClear()){
			move();
		}
		if (rightIsClear()) {
			turnRight();
			while (frontIsClear()) {
				move();
			}
		}
		while (notFacingEast()) {
			turnLeft();
		}
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
		while (frontIsClear()){
			move();
			putBeeper();
		}
	}
}