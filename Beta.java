package battleship;
import java.util.Scanner;

public class Beta {

    public static void main(String[] args) {
        // Declaring sea array
        String[][] sea = new String[11][11];

        //Setting 5 types of ships
        shipType Aircraft = new shipType();
        Aircraft.setShipName("Aircraft Carrier");
        Aircraft.setShipLength(5);

        shipType Battleship = new shipType();
        Battleship.setShipName("Battleship");
        Battleship.setShipLength(4);

        shipType Submarine = new shipType();
        Submarine.setShipName("Submarine");
        Submarine.setShipLength(3);

        shipType Cruiser = new shipType();
        Cruiser.setShipName("Cruiser");
        Cruiser.setShipLength(3);

        shipType Destroyer = new shipType();
        Destroyer.setShipName("Destroyer");
        Destroyer.setShipLength(2);


        // Creating a starting battlefield
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {

                if (row == 0 && col == 0) {
                    sea[row][col] = " ";
                } else if (row == 0) {
                    sea[row][col] = String.format("%d", col);
                } else if (col == 0) {
                    sea[row][col] = String.format("%c", (char) (64 + row));
                } else {
                    sea[row][col] = "~";
                }
            }
        }

        // Printing a starting battlefield
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }

        //Stage 1/5: Take position!

        setUnit(sea, Aircraft);

        // printing a starting battlefield
        System.out.println();
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }

        setUnit(sea, Battleship);

        // printing a starting battlefield
        System.out.println();
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }

        setUnit(sea, Submarine);

        // printing a starting battlefield
        System.out.println();
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }

        setUnit(sea, Cruiser);

        // printing a starting battlefield
        System.out.println();
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }

        setUnit(sea, Destroyer);

        // printing a starting battlefield
        System.out.println();
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }
    }


    public static void setUnit(String[][] sea, shipType unit) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", unit.getShipName(), unit.getShipLength());


        Scanner scanner = new Scanner(System.in);


        for (; ; ) {
            // Entering input values
            String startCoordinates = scanner.next();
            String endCoordinates = scanner.next();

            // Starting Coordinates parsing process
            int rowStart = startCoordinates.charAt(0);
            int colStart = 0;
            if(startCoordinates.length() == 3) {
                if((int)(startCoordinates.charAt(1)) == 49 && (int)(startCoordinates.charAt(2)) == 48)
                    colStart = 10;
            } else
            colStart = startCoordinates.charAt(1);

            // Ending Coordinates parsing process
            int rowEnd = endCoordinates.charAt(0);
            int colEnd = 0;
            if(endCoordinates.length() == 3) {
                if((int)(endCoordinates.charAt(1)) == 49 && (int)(endCoordinates.charAt(2)) == 48)
                    colEnd = 10;
            } else
            colEnd = endCoordinates.charAt(1);



            //Converting variables
            int rowStartInt = rowStart - 64;
            int rowEndInt = rowEnd - 64;

            int colStartInt;
            if(colStart == 10)
                colStartInt = 10;
            else
                colStartInt = colStart - 48;

            int colEndInt;
            if(colEnd == 10)
                colEndInt = 10;
            else
                colEndInt = colEnd - 48;

            //Replacing variables if condition is true
            if(rowEndInt < rowStartInt) {
                int temp = rowStartInt;
                rowStartInt = rowEndInt;
                rowEndInt = temp;
            }
            if(colEndInt < colStartInt) {
                int temp = colStartInt;
                colStartInt = colEndInt;
                colEndInt = temp;
            }


            // Error checking
            if ((rowStartInt != rowEndInt && colStartInt != colEndInt)) {
                System.out.println("\nError! Wrong ship location! Try again:\n\n");
                continue;
            }
            if ((rowEndInt - rowStartInt != unit.getShipLength() - 1 && colStartInt == colEndInt)
                    || (colEndInt - colStartInt != unit.getShipLength() - 1) && rowStartInt == rowEndInt) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", unit.getShipName());
                continue;
            }
            //Ship placing Errors
            boolean errorFlag = false;
            if (rowStartInt == rowEndInt) {
                for (int j = colStartInt; j <= colEndInt; j++) {
                    //1st Error
                    if ("O".equals(sea[rowStartInt][j])) {
                        System.out.println("\nError! This place is already taken!\n\n");
                        errorFlag = true;
                        break;
                    }
                    //2nd Error
                    if((rowStartInt < 10 && rowEndInt < 10 && colStartInt < 10 && colEndInt < 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                            || ("O".equals(sea[rowStartInt - 1][j]))
                            || ("O".equals(sea[rowStartInt - 1][j + 1]))
                            || ("O".equals(sea[rowStartInt][j + 1]))
                            || ("O".equals(sea[rowStartInt + 1][j + 1]))
                            || ("O".equals(sea[rowStartInt + 1][j]))
                            || ("O".equals(sea[rowStartInt + 1][j - 1]))
                            || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //3d Error
                    else if((rowStartInt == 10 && colEndInt < 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt - 1][j + 1]))
                                    || ("O".equals(sea[rowStartInt][j + 1]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //4th Error
                    else if((rowStartInt == 10 && colEndInt == 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //5th Error
                    else if((rowStartInt < 10 && colEndInt == 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                                    || ("O".equals(sea[rowStartInt+1][j - 1]))
                                    || ("O".equals(sea[rowStartInt+1][j]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                    }
                }
            } else if (colStartInt == colEndInt) {
                for (int i = rowStartInt; i <= rowEndInt; i++) {
                    //1 Error
                    if ("O".equals(sea[i][colStartInt])) {
                        System.out.println("\nError! This place is already taken!\n\n");
                        errorFlag = true;
                        break;
                    }
                    //2nd Error
                    if ((rowStartInt < 10 && rowEndInt < 10 && colStartInt < 10 && colEndInt < 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i + 1][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i + 1][colStartInt]))
                            || ("O".equals(sea[i - 1][colStartInt + 1]))
                            || ("O".equals(sea[i + 1][colStartInt + 1]))
                            || ("O".equals(sea[i][colStartInt + 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //3rd Error
                    if ((colStartInt == 10 && rowEndInt < 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i + 1][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i + 1][colStartInt]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //4th Error
                    if ((colStartInt == 10 && rowEndInt == 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //5th Error
                    if ((colStartInt < 10 && rowEndInt == 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i - 1][colStartInt + 1]))
                            || ("O".equals(sea[i][colStartInt + 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                }
            }
            if (errorFlag) {
                continue;
            }


            // Setting the Aircraft

            if (rowStartInt == rowEndInt) {
                for (int j = colStartInt; j <= colEndInt; j++) {
                    sea[rowStartInt][j] = "O";
                }
                break;
            } else if (colStartInt == colEndInt) {
                for (int i = rowStartInt; i <= rowEndInt; i++) {
                    sea[i][colStartInt] = "O";
                }
                break;
            }

        }
    }
}

 class shipType {
    String shipName;
    int shipLength;

    public void setShipName(String name) {
        this.shipName = name;
    }

    public void setShipLength(int len) {
     this.shipLength = len;
    }

    public String getShipName() {
        return this.shipName;
    }
    public int getShipLength() {
        return this.shipLength;
    }
}
