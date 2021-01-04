package battleship;

public class Main {


    public static void main(String[] args) {

        Game game = new Game();
        game.start();


    }

}
src/battleship/Shot.java
package battleship;
import java.util.Scanner;

public class Shot {

    private int raw;
    private int column;

    public Shot() {
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        this.raw = nextLine.charAt(0) - 65;
        this.column = Integer.parseInt(nextLine.substring(1)) - 1;
    }

    public int getRaw() {
        return raw;
    }

    public int getColumn() {
        return column;
    }
}
src/battleship/Position.java
package battleship;

import java.util.Scanner;

public class Position {

    private int startRaw;
    private int endRaw;
    private int startColumn;
    private int endColumn;

    public Position() {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        this.startRaw = input[0].charAt(0) - 65;
        this.endRaw = input[1].charAt(0) - 65;
        this.startColumn = Integer.parseInt(input[0].substring(1)) - 1;
        this.endColumn = Integer.parseInt(input[1].substring(1)) - 1;
    }


    public int getStartRaw() {
        return startRaw;
    }

    public int getEndRaw() {
        return endRaw;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

}
src/battleship/GameField.java
package battleship;

import java.awt.desktop.SystemHotkey;

public class GameField {

    private final int size = 10;
    private final char empty = '~';
    private final char shipSymbol = 'O';
    private final char failedShot = 'M';
    private final char successfulShot = 'X';
    private String error_message;
    private final String letterToPrint = "ABCDEFGHIJ";
    private final char[][] field = new char[size][size];
    private String hitMessage;


    /**
     * GameField constructor
     * create a blank gameField array with specified size
     */
    public GameField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.field[i][j] = empty;
            }
        }
    }


    /**
     * InitGameField Method
     * lunch the placing ship method for all the enum ships
     */
    public void InitGameField() {
        //place each ship
        DisplayGameField();
        for (KindShip kindShip : KindShip.values()) {
            PlaceShipToGameField(kindShip);
        }
    }


    /**
     * @param position (defined by the user input)
     * @param kindShip (different kind of ship)
     * @return return false if the position is incorrect, else, return true
     */
    public boolean checkIfShipValid(Position position, KindShip kindShip) {

        int startRaw = position.getStartRaw();
        int endRaw = position.getEndRaw();
        int startColumn = position.getStartColumn();
        int endColumn = position.getEndColumn();

        //out of the field!
        if (startRaw < 0 || endRaw > 10 || startColumn < 0 || endColumn > 10) {
            error_message = "Error! wrong ship location! Try again";
            return false;
        }

        //wrong length for the current ship! && wrong ship location!
        if (startRaw == endRaw) {
            if (Math.abs(endColumn - startColumn) + 1 != kindShip.getSize()) {
                error_message = String.format("Error! wrong length for the %s! Try again:", kindShip.getName());
                return false;
            }
        } else if (startColumn == endColumn) {
            if (Math.abs(endRaw - startRaw) + 1 != kindShip.getSize()) {
                error_message = String.format("Error! wrong length for the %s! Try again:", kindShip.getName());
                return false;
            }
        } else {
            error_message = "Error! wrong ship location! Try again";
            return false;
        }

        //obstruct another ship! (Horizontal)
        if (startRaw == endRaw) {
            for (int i = Math.min(startColumn, endColumn); i <= Math.max(startColumn, endColumn); i++) {
                //on another ship
                if (field[startRaw][i] == shipSymbol) {
                    error_message = "Error! to close to another one! Try again";
                    return false;
                }
                //to close to another ship
                if (startRaw > 0) {
                    if (field[startRaw - 1][i] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (startRaw < 9) {
                    if (field[startRaw + 1][i] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (Math.min(startColumn, endColumn) > 0) {
                    if (field[startRaw][Math.min(startColumn, endColumn) - 1] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (Math.max(startColumn, endColumn) < 9) {
                    if (field[startRaw][Math.max(startColumn, endColumn) + 1] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
            }
        }
        //obstruct another ship! (vertical)
        if (startColumn == endColumn) {
            for (int i = Math.min(startRaw, endRaw); i <= Math.max(startRaw, endRaw); i++) {
                //on another ship
                if (field[i][startColumn] == shipSymbol) {
                    error_message = "Error! to close to another one! Try again";
                    return false;
                }
                //to close to another ship
                if (startColumn > 0) {
                    if (field[i][startColumn - 1] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (startColumn < 9) {
                    if (field[i][startColumn + 1] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (Math.min(startRaw, endRaw) > 0) {
                    if (field[Math.min(startRaw, endRaw) - 1][startColumn] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
                //to close to another ship
                if (Math.max(startRaw, endRaw) < 9) {
                    if (field[Math.max(startRaw, endRaw) + 1][startColumn] == shipSymbol) {
                        error_message = "Error! to close to another one! Try again";
                        return false;
                    }
                }
            }
        }
        //default return
        return true;
    }


    /**
     * ship placing UI
     * @param kindShip
     */
    public void PlaceShipToGameField(KindShip kindShip) {
        while (true) {
            System.out.println(String.format("Enter the coordinates of the %s (%d cells):" + "\n> ", kindShip.getName(), kindShip.getSize()));
            Position position = new Position();
            if (!checkIfShipValid(position, kindShip)) {
                System.out.println(error_message);
                continue;
            } else {
                drawShip(position);
                DisplayGameField();
                break;
            }
        }
    }

    /**
     * draw ships on the field
     * @param position
     */
    public void drawShip(Position position) {

        int startRaw = position.getStartRaw();
        int endRaw = position.getEndRaw();
        int startColumn = position.getStartColumn();
        int endColumn = position.getEndColumn();

        if (startRaw == endRaw) {
            for (int i = Math.min(startColumn, endColumn); i <= Math.max(startColumn, endColumn); i++) {
                field[startRaw][i] = shipSymbol;
            }
        }
        if (startColumn == endColumn) {
            for (int i = Math.min(startRaw, endRaw); i <= Math.max(startRaw, endRaw); i++) {
                field[i][startColumn] = shipSymbol;
            }
        }

    }

    /**
     * draw the GameField
     */
    public void DisplayGameField() {
        String firstLine = "  1 2 3 4 5 6 7 8 9 10\n";
        System.out.print(firstLine);
        for (int i = 0; i < size; i++) {
            System.out.print(letterToPrint.charAt(i) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void PlaceShotToGameField(GameField gameField) {
        GameField hiddenGameField = new GameField();
        System.out.println("\nThe game starts!");
        hiddenGameField.DisplayGameField();
        while (true) {
            System.out.println("\nTake a shot!");
            Shot shot = new Shot();
            if (!CheckIfShotValid(shot)) {
                System.out.println(error_message);
                continue;
            } else {
                DrawShot(shot, gameField, hiddenGameField);
                hiddenGameField.DisplayGameField();
                System.out.println(hitMessage);
                DisplayGameField();
            }
        }
    }

    public void DrawShot(Shot shot, GameField gameField, GameField hiddenGameField) {
        int raw = shot.getRaw();
        int column = shot.getColumn();
        if (gameField.field[raw][column] == shipSymbol) {
            hiddenGameField.field[raw][column] = successfulShot;
            gameField.field[raw][column] = successfulShot;
            hitMessage = "You hit a ship!";
        } else {
            hiddenGameField.field[raw][column] = failedShot;
            gameField.field[raw][column] = failedShot;
            hitMessage = "You missed!";
        }
    }

    public boolean CheckIfShotValid(Shot shot) {
        int raw = shot.getRaw();
        int column = shot.getColumn();
        System.out.println(raw);
        System.out.println(column);
        if (raw > 9 || raw < 0 || column > 9 || column < 0) {
            error_message = "Error! you entered the wrong coordinates! Try again: ";
            return false;
        }
        //default return
        return true;
    }



}
src/battleship/Game.java
package battleship;

public class Game {

    /**
     * start method
     * create a new GameField and initializes it
     * place shot
     */
    public void start() {

        //create the first gameField
        GameField gameField = new GameField();
        //initializes it
        gameField.InitGameField();
        //start the shot part
        gameField.PlaceShotToGameField(gameField);
    }

}
src/battleship/KindShip.java
package battleship;

public enum KindShip {
    AIRCRAFT("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2),
    ;

    private String name;
    private Integer size;

    /**
     * KindShip constructor
     * @param name
     * @param size
     */
    KindShip(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }
}
 Correct.
