package battleship;

import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] shipName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] shipLength = {5, 4, 3, 3, 2};

        //making and printing scheme
        char[][] scheme = makeScheme(10, 10);
        printScheme(scheme);

        //asking for coordinates and positioning of warships
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter the coordinates of the " + shipName[i] + " (" + shipLength[i] + " cells):");
            positioningScheme(scheme, shipName[i], shipLength[i]);
            printScheme(scheme);
        }

        //starting the game
        System.out.println("The game starts!");
        printScheme(scheme);
        shot(scheme);
        printScheme(scheme);
    }


    public static char[][] makeScheme(int rows, int seatsInRow) {

        char[][] cinemaHall = new char[rows + 1][seatsInRow + 2];
        int rowsCounter = 65;
        cinemaHall[0][0] = ' ';

        for (int i = 0; i < rows; i++) {
            cinemaHall[i][0] = (char) rowsCounter;
            rowsCounter++;
            for (int j = 1; j < seatsInRow + 1; j++) {
                cinemaHall[i][j] = '~';
            }
        }
        return cinemaHall;
    }

    public static void printScheme(char[][] cinemaHall) {

        System.out.print("\n  ");
        for (int i = 1; i < cinemaHall.length; i++) {
            if (i < 10) {
                System.out.print(i + " ");
            } else {
                System.out.print(i);
            }
        }
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.println();
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(cinemaHall[i][j] + " ");
            }
            //System.out.println();
        }
        System.out.println("\n");
    }


    public static void positioningScheme(char[][] scheme, String shipName, int shipLength) {

        String coordinates = "";
        //System.out.println("Enter the coordinates of the " + shipName + " (" + shipLength + " cells):");
        coordinates = scanner.nextLine().toUpperCase();
        String[] parts = coordinates.split("\\s+");
        int realShipLength = 1;
        boolean horizontalPosition = true;

        int int1 = Integer.parseInt(parts[0].replaceAll("\\D+", ""));
        int int2 = Integer.parseInt(parts[1].replaceAll("\\D+", ""));

        char char1 = (parts[0].replaceAll("[0-9]", "")).charAt(0);
        char char2 = (parts[1].replaceAll("[0-9]", "")).charAt(0);

        if (char1 == char2) {
            realShipLength = Math.abs(int1 - int2) + 1;
        } else if (int1 == int2) {
            realShipLength = Math.abs(char1 - char2) + 1;
            horizontalPosition = false;
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            positioningScheme(scheme, shipName, shipLength);
            return;
        }

        if (realShipLength != shipLength) {
            System.out.println(realShipLength + " " + shipLength);
            System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
            positioningScheme(scheme, shipName, shipLength);
            return;
        }

        for (int i = 0; i < realShipLength; i++) {
            if (horizontalPosition) {
                if (checkClosedPosition(scheme, char1 - 65, Math.min(int1, int2) + i)) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    positioningScheme(scheme, shipName, shipLength);
                    return;
                }
            } else {
                if (checkClosedPosition(scheme, Math.min(char1, char2) - 65 + i, int1)) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    positioningScheme(scheme, shipName, shipLength);
                    return;
                }
            }
        }

        if (horizontalPosition) {
            for (int i = 0; i < realShipLength; i++) {
                scheme[char1 - 65][Math.min(int1, int2) + i] = 'O';
            }
        } else {
            for (int i = 0; i < realShipLength; i++) {
                scheme[Math.min(char1, char2) - 65 + i][int1] = 'O';
            }
        }
    }

    public static boolean checkClosedPosition(char[][] scheme, int int1, int int2) {
        boolean result = false;

        if (int1 == 0) {
            if (scheme[int1 + 1][int2] == 'O' ||
                scheme[int1][int2 - 1] == 'O' ||
                scheme[int1][int2 + 1] == 'O' ||
                scheme[int1 + 1][int2 + 1] == 'O' ||
                scheme[int1 + 1][int2 - 1] == 'O') {
                result = true;
            }
        } else {
            if (scheme[int1 - 1][int2] == 'O' ||
                scheme[int1 + 1][int2] == 'O' ||
                scheme[int1][int2 + 1] == 'O' ||
                scheme[int1][int2 - 1] == 'O' ||
                scheme[int1 - 1][int2 - 1] == 'O' ||
                scheme[int1 + 1][int2 + 1] == 'O' ||
                scheme[int1 + 1][int2 - 1] == 'O') {
                result = true;
            }
        }
        return result;
    }

    public static void shot(char[][] scheme) {
        boolean error;
        String coordinates = "";
        int int1 = 0;
        int int2 = 0;

        System.out.println("Take a shot!");
        do {
            error = false;
            coordinates = scanner.nextLine().toUpperCase();

            try {
                int1 = coordinates.charAt(0) - 65;
                int2 = Integer.parseInt(coordinates.substring(1));
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                error =true;
            }

            if (int1 > 10 || int1 < 0 || int2 > 10 || int2 < 0) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                error = true;
            }
        } while (error);

        if (scheme[int1][int2] == 'O') {
            scheme[int1][int2] = 'X';
            printScheme(scheme);
            System.out.println("You hit a ship!");
        } else {
            scheme[int1][int2] = 'M';
            printScheme(scheme);
            System.out.println("You missed!");
        }
    }
}
