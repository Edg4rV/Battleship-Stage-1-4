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
        String[] letters = new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
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

        char a1 = a.charAt(0);
        String a_1 = String.valueOf(a1);
        int a__1 = 0;

        char a2 = a.charAt(1);
        int a__2 = Integer.valueOf(a2) - 48;

        for (int i = 1; i < 10; i++) {
            if (a_1.equals(letters[i])) {
                a__1 = i;
            }
        }

        char b1 = b.charAt(0);
        String b_1 = String.valueOf(b1);
        int b__1 = 0;


        for (int i = 1; i < 10; i++) {
            if (b_1.equals(letters[i])) {
                b__1 = i;
            }
        }
        char b2 = b.charAt(1);
        int b__2 = Integer.valueOf(b2) - 48;

        if (b__2 < a__2 && b__1 == a__1) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
        } else if (b__1 != a__1 && b__2 != a__2) {
            System.out.println("Error! Wrong ship location! Try again:\n");
        } else if (matrix[a__1 - 1].equals("O") || matrix[b__1 + 1].equals("O") || matrix[a__2 - 1].equals("O") || matrix[b__2].equals("O")) {
            System.out.println("Error! You placed it too close to another one. Try again:\n");
        }

        for (int i = a__1; i <= b__1; i++) {
            for (int j = a__2; j <= b__2; j++) {
                matrix[i][j] = "O";
            }
        }


        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void checkShip() {
        Scanner scanner = new Scanner(System.in);
        int i = 6;
        boolean flag = true;
        while (flag) {
            if (i == 6) {
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
                String a = scanner.next();
                String b = scanner.next();

                char a0 = a.charAt(0);
                int a_0 = Integer.valueOf(a0) - 65;
                char b0 = b.charAt(0);
                int b_0 = Integer.valueOf(b0) - 48;
                System.out.println(b_0);

                char a1 = a.charAt(1);
                int a2 = Integer.valueOf(a1) - 65;
                char b1 = b.charAt(1);
                int b2 = Integer.valueOf(b1) - 48;

//                for (int k = a_0; k <= a2; k++) {
//                    for (int x = b_0; x <= b2; x++) {
//                        if ((matrix[k - 1][x - 1].contains("O")) || (matrix[k + 1][x + 1].contains("O"))) {
//                            System.out.println("Error! You placed it too close to another one. Try again:\n");
//                            break;
//                        } else {
//                            createBattlships(a, b);
//                            i -= 1;
//                        }
//                    }
//                }

//                if (matrix[a__1 - 1].equals("O") || matrix[b__1 + 1].equals("O") || matrix[a__2 - 1].equals("O") || matrix[b__2].equals("O")) {
//                    System.out.println("Error! You placed it too close to another one. Try again:\n");
//                }

                if (b2 - a2 < 4 && a.contains(b)) {
                    System.out.println("Error! Wrong length of the Carrier! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }
            }
            if (i == 5) {
                System.out.println("Enter the coordinates of the Battleship (4 cells)");
                String a = scanner.next();
                String b = scanner.next();

                char a0 = a.charAt(0);
                int a_0 = Integer.valueOf(a0) - 65;
                char b0 = a.charAt(1);
                int b_0 = Integer.valueOf(b0) - 48;

                char a1 = b.charAt(0);
                int a2 = Integer.valueOf(a1) - 65;
                char b1 = b.charAt(1);
                int b2 = Integer.valueOf(b1) - 48;

//                for (int k = a_0; k < a2; k++) {
//                    for (int x = b_0; x < b2; x++) {
//                        if ((matrix[k - 1][x - 1].equals("O")) || (matrix[k + 1][x + 1].equals("O"))) {
//                            System.out.println(matrix[k - 1][x - 1] + " " + matrix[k + 1][x + 1]);
//                            System.out.println("Error! You placed it too close to another one. Try again:\n");
//                            break;
//                        } else {
//                            createBattlships(a, b);
//                            i -= 1;
//                        }
//                    }
//                }

                if ((matrix[a_0 - 1][b_0].equals("O")) || (matrix[a2 + 1][b2].equals("O")) || (matrix[a_0][b_0 - 1].equals("O")) || matrix[a2][b2 + 1].equals("O")) {
//                            System.out.println(matrix[k - 1][x - 1] + " " + matrix[k + 1][x + 1]);
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                }
                if (b2 - a2 < 3 && a.contains(b)) {
                    System.out.println("Error! Wrong length of the Battleship! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }

            }

            if (i == 4) {
                System.out.println("Enter the coordinates of the Submarine (3 cells)");
                String a = scanner.next();
                String b = scanner.next();

                char a0 = a.charAt(0);
                int a_0 = Integer.valueOf(a0) - 48;
                char b0 = a.charAt(1);
                int b_0 = Integer.valueOf(b0) - 48;

                char a1 = b.charAt(0);
                int a2 = Integer.valueOf(a1) - 48;
                char b1 = b.charAt(1);
                int b2 = Integer.valueOf(b1) - 48;

//                for (int k = a_0; k <= a2; k++) {
//                    for (int x = b_0; x <= b2; x++) {
//                        if ((matrix[k - 1][x - 1].equals("O")) || (matrix[k + 1][x + 1].equals("O"))) {
//                            System.out.println("Error! You placed it too close to another one. Try again:\n");
//                            break;
//                        } else {
//                            createBattlships(a, b);
//                            i -= 1;
//                        }
//                    }
//                }

                if (b2 - a2 < 2 && a.contains(b)) {
                    System.out.println("Error! Wrong length of the Submarine! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }

            }
            if (i == 3) {
                System.out.println("Enter the coordinates of the Cruiser (3 cells)");
                String a = scanner.next();
                String b = scanner.next();

                char a0 = a.charAt(0);
                int a_0 = Integer.valueOf(a0) - 48;
                char b0 = a.charAt(1);
                int b_0 = Integer.valueOf(b0) - 48;

                char a1 = b.charAt(0);
                int a2 = Integer.valueOf(a1) - 48;
                char b1 = b.charAt(1);
                int b2 = Integer.valueOf(b1) - 48;

                if (b2 - a2 < 2) {
                    System.out.println("Error! Wrong length of the Cruiser! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }

            }

            if (i == 2) {
                System.out.println("Enter the coordinates of the Destroyer (3 cells)");
                String a = scanner.next();
                String b = scanner.next();

                char a0 = a.charAt(0);
                int a_0 = Integer.valueOf(a0) - 48;
                char b0 = b.charAt(0);
                int b_0 = Integer.valueOf(b0) - 48;

                char a1 = a.charAt(1);
                int a2 = Integer.valueOf(a1) - 48;
                char b1 = b.charAt(1);
                int b2 = Integer.valueOf(b1) - 48;
                if (b2 - a2 < 1 && a.contains(b)) {
                    System.out.println("Error! Wrong length of the Destroyer! Try again:");
                } else {
                    createBattlships(a, b);
                    i -= 1;
                }

            }
            if (i == 1) {
                flag = false;
            }


        }


    }

    public static void main(String[] args) {
        createMatrix();
        checkShip();

        }
    }


