# Map Division Program for Karel  
_By Ahmad Emad_  

- **YouTube Video**: [Watch Here](https://youtu.be/z6CUp96oPaM)  
- **Email**: [ahmademad995.ae@gmail.com](mailto:ahmademad995.ae@gmail.com)  

---

## **Objectives**  
- Divide any given map into **four equal chambers**.  
- If four chambers are not possible, divide into the **highest number possible**.  
- **Minimize the number of code lines**.  
- **Minimize the number of moves**.  
- Use the **lowest possible number of beepers**.  

---

## **Step-by-Step Solution**  

1. Ensure Karel is at the starting position.  
2. Calculate the x-axis and y-axis lengths.  
3. Divide the map into four chambers based on:  
   - Odd-odd axes.  
   - Even-even axes.  
   - Odd-even axes.  

---

## **Main Methods**  

### **Public Methods**  

- **`DivideY(int)`**  
  - Divides the y-axis into two halves using one wall of beepers.  

- **`DoubleDivideY(int)`**  
  - Divides the y-axis into two halves using two walls of beepers.  

- **`ThreeChambersY(int)`**  
  - Divides the y-axis into three parts using two walls of beepers.  

- **`yAxisCurve(int, int)`**  
  - Divides the map vertically into four chambers using a curved wall of beepers.  

- **`DivideX(int)`**  
  - Divides the x-axis into two halves using one wall of beepers.  

- **`DoubleDivideX(int)`**  
  - Divides the x-axis into two halves using two walls of beepers.  

- **`ThreeChambersX(int)`**  
  - Divides the x-axis into three parts using two walls of beepers.  

- **`XAxisCurve(int, int)`**  
  - Divides the map horizontally into four chambers using a curved wall of beepers.  

---

### **Helper Methods**  

- **`bothOdd(int, int)`**  
  - Handles odd x and y parameters and decides the optimal division strategy.  
  - Example: Calls `ThreeChambersX()` if y-axis length is not divisible, and x-axis length allows three chambers.  

- **`bothEven(int, int)`**  
  - Handles even x and y parameters and selects the best division strategy.  
  - Example: Calls `yAxisCurve()` if y-axis length > x-axis length, and both are >2.  

- **`oddeven(int, int)`**  
  - Handles odd-even or even-odd axes and selects an appropriate method.  
  - Example: Calls `DoubleDivideX()` and `DivideY()` if x-axis is even and >2, and y-axis is odd and >1.  

- **`calculateArea()`**  
  - Calculates x-axis and y-axis lengths.  

- **`moveWhileFrontClearPutBeeper()`**  
  - Moves while the front is clear, placing a beeper at each step.  

---

### **Overridden Methods**  

- **`move()`**  
  - Adds a move counter, incrementing with each call to track the total number of moves.  

- **`putBeeper()`**  
  - Prevents beeper overlap by checking if a beeper is already present.  
  - Adds a beeper counter to track usage.  

---

## **Conclusion**  

### **Approach**  
- A **top-down approach**:  
  - Categorize axes into three cases: **even-even, odd-odd, odd-even**.  
  - Select appropriate methods based on the scenario.  

### **Efficiency**  
- Overridden methods eliminate repetitive checks.  
- Reusable code follows clean coding principles.  
  - Example: `moveWhileFrontClearPutBeeper()` has 13 usages; `divideX()` and `divideY()` have 3 usages each.  
- Methods like `yAxisCurve()` and `XAxisCurve()` minimize beeper usage by curving walls.  

### **Results**  
- Divides the map into **4 equal chambers** (or the next best number).  
- Achieved in just **300 lines of code**!  
- Tracks both **move count** and **beeper usage**.  

---
