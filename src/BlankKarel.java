/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class BlankKarel extends SuperKarel {

	public void run() {
		int[] xy = calculateArea();
		int x = xy[0], y = xy[1];

		if (x%2 == 1 && y%2 == 1)
			bothOdd(x, y);
		else if (x%2 == 0 && y%2 == 0) {
			bothEven(x, y);
		}else oddEven(x, y);

		println("Beeper count = " + beeperCount);
	}

	private static int moveCounter = 0;

	@Override
	public void move() {
		super.move();
		moveCounter++;
		println(moveCounter);
	}

	private static int beeperCount = 0;

	@Override
	public void putBeeper() {
		if (noBeepersPresent()) {
			super.putBeeper();
			beeperCount++;
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

	private void bothOdd(int x, int y){
		//starting from northeast facing north
		if (x > y && (x-2)%3 == 0) {
			threeChambersX(x);
			return;
		}
		if (y > x && (y-2)%3 == 0){
			threeChambersY(y);
			return;
		}
		if (x != 1){
			divideX(x);
		}
		if (y != 1){
			divideY(y);
		}

	}

	private void bothEven(int x, int y) {
	//starting from northeast facing north
		if (x != 2){
			divideX(x-1);

			turnRight();
			move();
			turnRight();

			putBeeper();
			while (frontIsClear()){
				move();
				putBeeper();
			}
		}
		if (y != 2){
			divideY(y-1);

			turnLeft();
			move();
			turnLeft();
			putBeeper();
			while (frontIsClear()){
				move();
				putBeeper();
			}
		}
	}

	private void oddEven(int x, int y) {
		if (y > x && (y-2)%3 == 0 && y%2 == 1) {
			threeChambersY(y);
			return;
		}
		if (x > y && (x-2)%3 == 0 && x%2 == 1) {
			threeChambersX(x);
			return;
		}
		if (x%2 == 0){
			if (x != 2){
				divideX(x-1);

				turnRight();
				move();
				turnRight();

				putBeeper();
				while (frontIsClear()){
					move();
					putBeeper();
				}
			}
			if (y != 1){
				divideY(y);
			}
		}
		else if (x%2 == 1){
			if (x != 1){
				divideX(x);
			}
			if (y != 2){
				divideY(y-1);

				turnLeft();
				move();
				turnLeft();
				putBeeper();
				while (frontIsClear()){
					move();
					putBeeper();
				}
			}
		}
	}
	private void divideY(int y){
		//starting middleSouth facing south
		turnAround();
		for (int i = 0; i < y/2; i++) {
			move();
		}
		putBeeper();
		turnLeft();
		while (frontIsClear()){
			move();
			putBeeper();
		}
		turnAround();
		while (frontIsClear()) {
			move();
			putBeeper();
		}
	}
	private void divideX(int x){
		//starting from northeast facing north
		turnLeft();
		for (int i = 0; i < x/2; i++) {
			move();
		}
		turnLeft();
		putBeeper();
		while (frontIsClear()){
			move();
			putBeeper();
		}
	}
	private void threeChambersY(int y){
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
			while (frontIsClear()) {
				move();
				putBeeper();
			}
			if (column == 0) {
				turnLeft();
				move();
			}
		}
	}
	private void threeChambersX(int x){
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
			while (frontIsClear()) {
				move();
				putBeeper();
			}
			if (column == 0) {
				turnRight();
				move();
			}
		}
	}
}

