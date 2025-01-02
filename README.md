# Map Division Program for Karel  

_By Ahmad Emad_  
- **YouTube Video**: [Watch Here](https://youtu.be/wqUGu_8BF90)  
- **Email**: [ahmademad995.ae@gmail.com](mailto:ahmademad995.ae@gmail.com)  

---

## Objectives
- Divide any given map into four equal chambers.
- If four chambers are not possible, divide the map into the highest number of chambers achievable.
- Minimize the number of code lines.
- Reduce the number of moves required.
- Use the smallest number of beepers possible.

---

## Step-by-Step Solution

### 1. **Calculate Map Dimensions**
Determine the lengths of the x-axis and y-axis.

### 2. **Categorize Axes**
Invoke the appropriate method based on axis properties:
- **Odd Axes**
- **Even Axes**
- **Odd-Even Axes**

### 3. **Axis-Specific Division Methods**
Analyze axes further to call appropriate division methods and handle indivisible axes.

---

## Main Division Methods

### Y-Axis Division
- **`divideY(int y)`**
  Divides the y-axis into two equal halves with one wall of beepers. Suitable for odd y-axis lengths.
- **`doubleDivideY(int y)`**
  Divides the y-axis into two equal halves with two walls of beepers. Suitable for even y-axis lengths.
- **`threeChambersY(int y)`**
  Divides the y-axis into three parts using two walls of beepers. Suitable for y-axis lengths divisible by 3, especially when x-axis ≤ 2.

### X-Axis Division
- **`divideX(int x)`**
  Divides the x-axis into two equal halves with one wall of beepers. Suitable for odd x-axis lengths.
- **`doubleDivideX(int x)`**
  Divides the x-axis into two equal halves with two walls of beepers. Suitable for even x-axis lengths.
- **`threeChambersX(int x)`**
  Divides the x-axis into three parts using two walls of beepers. Suitable for x-axis lengths divisible by 3, especially when y-axis ≤ 2.

---

## Curving Methods

### Y-Axis Curves
- **`yAxisCurve(int x, int y)`**
  Creates an overlapping curved vertical wall of beepers to divide the map into four chambers. Suitable when both axes are even, and the y-axis is taller.

### X-Axis Curves
- **`xAxisCurve(int x, int y)`**
  Creates an overlapping curved horizontal wall of beepers to divide the map into four chambers. Suitable when both axes are even, and the x-axis is wider.

### Dynamic Curves
- **`verticalCurve(int x, int y)`**
  Dynamically adjusts to create a curved vertical wall of beepers. Suitable for even x-axis lengths and odd y-axis lengths.
- **`horizontalCurve(int x, int y)`**
  Dynamically adjusts to create a curved horizontal wall of beepers. Suitable for even y-axis lengths and odd x-axis lengths.

---

## Special Case Method
- **`twoByTwo()`**
  Handles the specific case where both the x-axis and y-axis lengths are 2, dividing the map diagonally into two chambers.

---

## Analysis Helper Methods

### Axis Categorization
- **`oddAxes(int x, int y)`**
  Determines the best method for dividing the map when both axes are odd, such as using `threeChambersX()` or `divideY()` and `divideX()`.
- **`evenAxes(int x, int y)`**
  Determines the best method for dividing the map when both axes are even, such as using `yAxisCurve()` or `xAxisCurve()`.
- **`oddEven(int x, int y)`**
  Determines the best method for dividing the map when one axis is odd and the other is even, such as using `verticalCurve()` and `divideY()`.

### Area Calculation
- **`calculateArea()`**
  Calculates the lengths of the x-axis and y-axis.

---

## Reusable Helper Methods

### Movement and Beeper Placement
- **`moveWhileFrontClearPutBeeper()`**
  Repeatedly moves forward and places a beeper while the path ahead is clear.
- **`moveBy(int steps)`**
  Moves a specified number of steps forward.

---

## Overridden Methods

### Movement Tracking
- **`move()`**
  Tracks the number of moves made by incrementing a move counter every time this method is called.

### Beeper Placement
- **`putBeeper()`**
  Checks if no beeper is present before placing one, preventing overlap. Also increments a beeper counter to track usage.

---

## Conclusion
This solution employs a top-down approach, categorizing map axes into three main categories: even-even, odd-odd, and odd-even. Based on the category, the system determines the appropriate methods to divide the map.

### Optimization Highlights
- **Less Redundancy:**
  - Overridden methods eliminate repetitive condition checks.
  - Reusable methods like `moveWhileFrontClearPutBeeper()` (16 usages) and `moveBy()` (13 usages) promote clean and efficient code.

- **Efficient Beeper Use:**
  - Curving methods like `yAxisCurve()` and `xAxisCurve()` reduce the number of beepers by overlapping walls where possible.

- **Maximized Chambers:**
  - The map is ensured to be divided into four equal chambers or the highest possible number of chambers.

At **only 277 lines of code**, this solution effectively balances functionality, efficiency, and clarity.

