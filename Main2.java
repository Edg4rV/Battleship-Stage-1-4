package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String[][] matrix = new String[11][11];

    public static void createMatrix() {

        matrix[0][0] = " ";
        String[] letters = new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int i = 1; i < 11; i++) {
            matrix[0][i] = String.valueOf(i);
        }
        for (int j = 1; j < 11; j++) {
            matrix[j][0] = letters[j];
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                matrix[i][j] = "~";
            }
        }
    }
    public static void createBattlships(String a, String b) {
//        String[] letters = new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
//        String[][] matrix = new String[11][11];
//        matrix[0][0] = " ";
//        for (int i = 1; i < 11; i++) {
//            matrix[0][i] = String.valueOf(i);
//        }
//        for (int j = 1; j < 11; j++) {
//            matrix[j][0] = letters[j];
//        }
//        for (int i = 1; i < 11; i++) {
//            for (int j = 1; j < 11; j++) {
//                matrix[i][j] = "~";
//            }
//        }

        int a_0 = 0;
        int b_0 = 0;
        int a_1 = 0;
        int b_1 = 0;

        if (a.contains("10")) {
            char a0 = a.charAt(0);
            a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
            b_0 = 10;

            char a1 = b.charAt(0);
            a_1 = Integer.valueOf(a1) - 65;

            char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
            b_1 = Character.getNumericValue(b1);
        } else if (b.contains("10")) {
            char a0 = a.charAt(0);
            a_0 = Integer.valueOf(a0) - 65;

            char b0 = a.charAt(1);
            b_0 = Integer.valueOf(b0) - 48;

            char a1 = b.charAt(0);
            a_1 = Integer.valueOf(a1) - 65;

            char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
            b_1 = Character.getNumericValue(b1);
        } else {

            char a0 = a.charAt(0);
            a_0 = Integer.valueOf(a0) - 65;

            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
            b_0 = Character.getNumericValue(b0);

            char a1 = b.charAt(0);
            a_1 = Integer.valueOf(a1) - 65;

            char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
            b_1 = Character.getNumericValue(b1);
        }

//        char a1 = a.charAt(0);
//        String a_1 = String.valueOf(a1);
//        int a__1 = 0;
//
//        char a2 = a.charAt(1);
//        int a__2 = Integer.valueOf(a2) - 48;
//
//        for (int i = 1; i < 10; i++) {
//            if (a_1.equals(letters[i])) {
//                a__1 = i;
//            }
//        }
//
//        char b1 = b.charAt(0);
//        String b_1 = String.valueOf(b1);
//        int b__1 = 0;
//
//
//        for (int i = 1; i < 10; i++) {
//            if (b_1.equals(letters[i])) {
//                b__1 = i;
//            }
//        }
//        char b2 = b.charAt(1);
//        int b__2 = Integer.valueOf(b2) - 48;

//        if (b__2 < a__2 && b__1 == a__1) {
//            System.out.println("Error! Wrong length of the Submarine! Try again:");
//        } else if (b__1 != a__1 && b__2 != a__2) {
//            System.out.println("Error! Wrong ship location! Try again:\n");
//        } else if (matrix[a__1 - 1].equals("O") || matrix[b__1 + 1].equals("O") || matrix[a__2 - 1].equals("O") || matrix[b__2].equals("O")) {
//            System.out.println("Error! You placed it too close to another one. Try again:\n");
//        }

        if (b_0 < b_1 && a_0 == a_1 || b_0 == b_1 && a_0 < a_1 ) {

            for (int i = a_0 + 1; i <= a_1 + 1; i++) {
                for (int j = b_0; j <= b_1; j++) {
                    matrix[i][j] = "O";
                }
            }
        } else if (b_0 > b_1 && a_0 == a_1) {
            for (int i = a_0 + 1; i <= a_1 + 1; i++) {
                for (int j = b_1; j <= b_0; j++) {
                    matrix[i][j] = "O";
                }
            }
        } else if (a_0 > a_1 && b_0 == b_1) {
            for (int i = a_1 + 1; i <= a_0 + 1; i++) {
                for (int j = b_1; j <= b_0; j++) {
                    matrix[i][j] = "O";
                }
            }
        }
//        else {
//            for (int i = a_0 + 1; i <= a_1 + 1; i++) {
//                for (int j = b_0; j <= b_1; j++) {
//                    matrix[i][j] = "O";
//                }
//            }
//        }


        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void checkShip() {
        Scanner scanner = new Scanner(System.in);
        int i = 5;
        boolean flag = true;
        while (flag) {
            if (i == 5) {
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
                String a = scanner.next();
                String b = scanner.next();
                int a_0 = 0;
                int b_0 = 0;
                int a_1 = 0;
                int b_1 = 0;

                if (a.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = 10;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else if (b.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
                    b_0 = Integer.valueOf(b0) - 48;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else {

                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = Character.getNumericValue(b0);

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                }


                if (Math.abs(b_1 - a_0) < 4 && a_0 == b_0) {
                    System.out.println("Error! Wrong length of the Carrier! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }
            }
            if (i == 4) {
                System.out.println("Enter the coordinates of the Battleship (4 cells)");
                String a = scanner.next();
                String b = scanner.next();

                int a_0 = 0;
                int b_0 = 0;
                int a_1 = 0;
                int b_1 = 0;

                if (a.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = 10;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else if (b.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
                    b_0 = Integer.valueOf(b0) - 48;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else {

                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = Character.getNumericValue(b0);

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                }


                boolean b2 = ((Math.abs(b_1 - b_0) < 3 || Math.abs(b_1 - b_0) > 4)  && a_0 == a_1) || (Math.abs(a_1 - a_0) < 3 && b_0 == b_1);
                if (a_0 > 0 && b_0 > 0) {
                    if (matrix[a_0 - 1][b_0].equals("O") || matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0 - 1][b_1].equals("O") || matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                } else if (a_0 == 0 || b_0 == 0) {
                    if (matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                }

            }
            if (i == 3) {
                System.out.println("Enter the coordinates of the Submarine (3 cells)");
                String a = scanner.next();
                String b = scanner.next();

                int a_0 = 0;
                int b_0 = 0;
                int a_1 = 0;
                int b_1 = 0;

                if (a.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = 10;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else if (b.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
                    b_0 = Integer.valueOf(b0) - 48;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else {

                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = Character.getNumericValue(b0);

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                }


                boolean b2 = ((Math.abs(b_1 - b_0) < 2 || Math.abs(b_1 - b_0) > 3) && a_0 == a_1) || (b_0 == b_1 && Math.abs(a_1 - a_0) < 2);
                if (a_0 > 0 && b_0 > 0) {
                    if (matrix[a_0 - 1][b_0].equals("O") || matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0 - 1][b_1].equals("O") || matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                } else if (a_0 == 0 || b_0 == 0) {
                    if (matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                }

            }
            if (i == 2) {
                System.out.println("Enter the coordinates of the Cruiser (3 cells)");
                String a = scanner.next();
                String b = scanner.next();

                int a_0 = 0;
                int b_0 = 0;
                int a_1 = 0;
                int b_1 = 0;

                if (a.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = 10;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else if (b.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
                    b_0 = Integer.valueOf(b0) - 48;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else {

                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = Character.getNumericValue(b0);

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                }



                boolean b2 = ((Math.abs(b_1 - b_0) < 2 || Math.abs(b_1 - b_0) > 3) && a_0 == a_1) || (b_0 == b_1 && Math.abs(a_1 - a_0) < 2);
                if (a_0 > 0 && b_0 > 0) {
                    if (matrix[a_0 - 1][b_0].equals("O") || matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0 - 1][b_1].equals("O") || matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                } else if (a_0 == 0 || b_0 == 0) {
                    if (matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                }

            }
            if (i == 1) {
                System.out.println("Enter the coordinates of the Destroyer (2 cells)");
                String a = scanner.next();
                String b = scanner.next();

                int a_0 = 0;
                int b_0 = 0;
                int a_1 = 0;
                int b_1 = 0;

                if (a.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

//            char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = 10;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else if (b.contains("10")) {
                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
                    b_0 = Integer.valueOf(b0) - 48;

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                } else {

                    char a0 = a.charAt(0);
                    a_0 = Integer.valueOf(a0) - 65;

                    char b0 = a.charAt(1);
//        int b_0 = Integer.valueOf(b0) - 48;
                    b_0 = Character.getNumericValue(b0);

                    char a1 = b.charAt(0);
                    a_1 = Integer.valueOf(a1) - 65;

                    char b1 = b.charAt(1);
//        int b_1 = Integer.valueOf(b1) - 48;
                    b_1 = Character.getNumericValue(b1);
                }


                boolean b2 = ((Math.abs(b_1 - b_0) < 1 || Math.abs(b_1 - b_0) > 2) && a_0 == a_1) || (b_0 == b_1 && Math.abs(a_1 - a_0) < 1);
                if (a_0 > 0 && b_0 > 0) {
                    if (matrix[a_0 - 1][b_0].equals("O") || matrix[a_0 + 2][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_1 - 1][b_0].equals("O") || matrix[a_1 + 1][b_0].equals("O") ||
                            matrix[a_0 - 1][b_1].equals("O") || matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                } else if (a_0 == 0 || b_0 == 0) {
                    if (matrix[a_0 + 1][b_0].equals("O") || matrix[a_0 + 1][b_1].equals("O") ||
                            matrix[a_0][b_0 - 1].equals("O") || matrix[a_0][b_1 + 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                    } else if (b2) {
                        System.out.println("Error! Wrong length of the Battleship! Try again:");
                    } else if (a_0 != a_1 && b_0 != b_1) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else {
                        createBattlships(a, b);
                        i -= 1;
                    }
                }

                if (i == 0) {
                    flag = false;
                }
            }
        }
    }
    public static void main(String[] args) {
        createMatrix();
        checkShip();

        }
    }


