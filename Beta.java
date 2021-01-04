package battleship;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[][] battleField = new String[10][10];
    private static final String[][] fogField = new String[10][10];
    private static final Scanner scanner = new Scanner(System.in);

    public enum Ships {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP ("Battleship",4),
        SUBMARINE("Submarine",3),
        CRUISER("Cruiser",3),
        DESTROYER("Destroyer",2);

        public final int cells;
        public final String label;
        Ships(String label, int cells) {
            this.label = label;
            this.cells = cells;
        }

        public int getCell(){
            return this.cells;
        }

        public String getLabel() {
            return this.label;
        }

    }

    public static void createField () {
        System.out.print(" 1 2 3 4 5 6 7 8 9 10");
        System.out.println();
        int count = 65;
        for(int i = 0; i<battleField.length; i++) {
            System.out.print((char)count);
            for(int j = 0; j<battleField[i].length; j++) {
                System.out.print(" "+"~");
                battleField[i][j] = "~";
                fogField[i][j] = "~";
            }
            count++;
            System.out.println();
        }
    }

    public static void fillField() {
        System.out.print(" 1 2 3 4 5 6 7 8 9 10");
        System.out.println();
        int count = 65;
        for(int i = 0; i<battleField.length; i++) {
            System.out.print((char)count);
            for(int j = 0; j<battleField[i].length; j++) {
                System.out.print(" "+battleField[i][j]);
            }
            count++;
            System.out.println();
        }
    }

    public static void displayFogField() {
        System.out.print(" 1 2 3 4 5 6 7 8 9 10");
        System.out.println();
        int count = 65;
        for(int i = 0; i<fogField.length; i++) {
            System.out.print((char)count);
            for(int j = 0; j<fogField[i].length; j++) {
                System.out.print(" "+fogField[i][j]);
            }
            count++;
            System.out.println();
        }
    }

    public static boolean checkShot(String shot) {
        boolean hitOrMiss = false;
        int row = (int)shot.charAt(0)-65;
        int col = Integer.parseInt(shot.replaceAll("[^0-9]", ""));

        if(row>9||col>10) {
            System.out.println("Error! You entered the wrong coordinates!\nTry again:\n");
        } else {
            for(int i = 0; i<battleField.length; i++) {
                for(int j = 0; j<battleField[i].length; j++) {
                    if(i == row && j == col-1) {
                        if(battleField[i][j] == "~") {
                            battleField[i][j] = "M";
                            fogField[i][j] = "M";
                            System.out.print(" 1 2 3 4 5 6 7 8 9 10");
                            System.out.println();
                            int count = 65;
                            for(int m = 0; m<fogField.length; m++) {
                                System.out.print((char)count);
                                for(int n = 0; n<fogField[m].length; n++) {
                                    System.out.print(" "+fogField[m][n]);
                                }
                                count++;
                                System.out.println();
                            }
                            System.out.println("\nYou missed!");
                        } else if (battleField[i][j] == "O") {
                            battleField[i][j] = "X";
                            fogField[i][j] = "X";
                            System.out.print(" 1 2 3 4 5 6 7 8 9 10");
                            System.out.println();
                            int count = 65;
                            for(int k = 0; k<fogField.length; k++) {
                                System.out.print((char)count);
                                for(int l = 0; l<fogField[k].length; l++) {
                                    System.out.print(" "+fogField[k][l]);
                                }
                                count++;
                                System.out.println();
                            }
                            System.out.println("\nYou hit a ship!");
                        }
                        hitOrMiss = true;
                        break;
                    }
                }
            }
        }
        return hitOrMiss;
    }


    public static boolean checkShipNeighbours(boolean[] positionChecked, String firstPosition, String secondPosition) {
        boolean nearNeighbour = false;
        if (positionChecked[0] && !positionChecked[1]) {
            int row = (int)firstPosition.charAt(0)-65;
            int colLowLimit = Math.min(Integer.parseInt(firstPosition.replaceAll("[^0-9]", ""))-1, Integer.parseInt(secondPosition.replaceAll("[^0-9]", ""))-1);
            int colHighLimit = Math.max(Integer.parseInt(firstPosition.replaceAll("[^0-9]", ""))-1, Integer.parseInt(secondPosition.replaceAll("[^0-9]", ""))-1);
            for(int i = Math.max(0,row-1); i<= Math.min(9,row+1); i++) {
                for(int j = Math.max(0,colLowLimit-1); j <= Math.min(9,colHighLimit+1); j++) {
                    if(battleField[i][j] != "~") {
                        positionChecked[0] = false;
                    }
                }
            }
            if(positionChecked[0]) {
                for(int j = Math.max(0,colLowLimit); j<=Math.min(9,colHighLimit); j++) {
                    battleField[row][j] = "O";
                }

            }
        } else if(positionChecked[1] && !positionChecked[0]) {
            int col = Integer.parseInt(firstPosition.replaceAll("[^0-9]", ""))-1;
            int rowLowLimit = Math.min((int)firstPosition.charAt(0)-65,(int)secondPosition.charAt(0)-65);
            int rowHighLimit = Math.max((int)firstPosition.charAt(0)-65,(int)secondPosition.charAt(0)-65);
            for(int k = Math.max(0,col-1); k<= Math.min(9, col+1); k++) {
                for(int l = Math.max(0,rowLowLimit-1); l <= Math.min(9,rowHighLimit+1); l++){
                    if(battleField[l][k] != "~") {
                        positionChecked[1] = false;
                    }
                }
            }
            if(positionChecked[1]) {
                for(int m = Math.max(0,rowLowLimit); m <= Math.min(9, rowHighLimit); m++) {
                    battleField[m][col] = "O";
                }
            }
        }
        if(!positionChecked[0] && !positionChecked[1]) {
            nearNeighbour = true;
            System.out.println("Error! You placed it too close to another one. Try again:");
            System.out.println();
        }
        return nearNeighbour;
    }


    public static boolean[] checkShipPosition(String firstPosition, String secondPosition, Ships ship) {
        boolean[] rightLocation;
        rightLocation = new boolean[2];
        rightLocation[0] = false;
        rightLocation[1] = false;
        int length;

        if(firstPosition.charAt(0) == secondPosition.charAt(0)) {
            length = 1 + Math.abs(Integer.parseInt(firstPosition.replaceAll("[^0-9]", "")) - Integer.parseInt(secondPosition.replaceAll("[^0-9]", "")));
            if(ship.getCell() == length) {
                rightLocation[0] = true;
                rightLocation[1] = false;
            } else {
                System.out.println("Error! Wrong length of the "+ship.getLabel()+"! Try again:");
            }
        } else if (firstPosition.charAt(1) == secondPosition.charAt(1)) {
            length = 1 + Math.abs(Character.getNumericValue(firstPosition.charAt(0)) - Character.getNumericValue(secondPosition.charAt(0)));
            if(ship.getCell() == length) {
                rightLocation[1] = true;
                rightLocation[0] = false;
            } else {
                System.out.println("Error! Wrong length of the "+ship.getLabel()+"! Try again:");
            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
        }
        return rightLocation;
    }

    public static void main(String[] args) throws Exception {
        createField();
        System.out.println();
        for (Ships ship: Ships.values()) {
            System.out.println("Enter the coordinates of the "+ship.getLabel()+" ("+ship.getCell()+" cells):");
            System.out.println();
            while(true) {
                String[] location = scanner.nextLine().split("\\s+");
                System.out.println();
                boolean[] check = checkShipPosition(location[0], location[1], ship);
                System.out.println();
                boolean neighbours = false;
                if(!check[0] && check[1] || !check[1] && check[0]) {
                    neighbours = checkShipNeighbours(check,location[0],location[1]);
                    if(!neighbours) {
                        break;
                    }
                }
            }
            System.out.println();
            fillField();
            System.out.println();
        }
        System.out.println("The game starts!\n");
        displayFogField();
        System.out.println("\nTake a shot!\n");
        while(true) {
            String shot = scanner.next();
            System.out.println();
            if(checkShot(shot)) {
                fillField();
                break;
            }
        }
    }
}
