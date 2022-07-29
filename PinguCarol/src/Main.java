import java.util.Scanner;

import pgdp.MiniJava;

import static pgdp.MiniJava.*;

public class Main extends pgdp.MiniJava {
    public static void main(String[] args) {
        // Reading
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        if (height <= 0 || width <= 0) {
            System.out.println("The width and height of the playing field must be greater than zero.");
            return;
        }
        // Create array for field
        int field[][] = new int[width][height];
        // Fill Array via Randomint, First height.
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = randomInt(-1, 9);
            }
        }

        printPlayground(field);
        // Reading Carols begining information , X,Y Position , Ice block supply .
        System.out.println("Starting position x:");
        int x = sc.nextInt();
        System.out.println("Starting position y:");
        int y = sc.nextInt();
        System.out.println("Direction of view at the beginning:");
        int dir = sc.nextInt();
        System.out.println("Ice blocks at the beginning:");
        int iceblocks = sc.nextInt();

        if (x < 0 || x >= width || y < 0 || y >= height || dir > 3 || dir < 0 || iceblocks < 0 || iceblocks > 10) {
            System.out.println("Invalid start values.");
            return;
        }
        //Create carol coordinator.


        char instr = '0';

        while (instr != 'e') {
            instr = readChar("enter character");
            if (instr == 'a') {
                printPlayground(field, x, y, dir, iceblocks);
            }
            // Instruction R
            else if (instr == 'r') {
                if (dir == 0) {
                    dir = 3;
                } else if (dir == 1) {
                    dir = 0;
                } else if (dir == 2) {
                    dir = 1;
                    ;
                } else if (dir == 3) {
                    dir = 2;
                    ;
                }
            }
            // Instruction L
            else if (instr == 'l') {
                if (dir == 0) {
                    dir = 1;
                } else if (dir == 1) {
                    dir = 2;
                } else if (dir == 2) {
                    dir = 3;
                    ;
                } else if (dir == 3) {
                    dir = 0;
                    ;
                }
            }
            // Instruction S , we have 4 parts here , dir 0 , dir 1 ,dir 2 ,dir 3 ,dir 4.
            else if (instr == 's') {
                // when dir == 0
                if (dir == 0) {
                    if (dir == 0 && x == (field.length - 1)) {   ///////////////////if there will be some error check widht == 0
                        System.out.println("Carol cannot leave the field.");
                    } else if (dir == 0 && Math.abs(field[x + 1][y] - field[x][y]) > 1) {
                        System.out.println("Carol cannot go to the next field because the difference in height is too great.");
                    } else {
                        x++;
                    }
                }

                // when dir == 2
                else if (dir == 2) {
                    if (dir == 2 && x == 0) {   ///////////////////if there will be some error check widht == 0
                        System.out.println("Carol cannot leave the field.");
                    } else if (dir == 2 && Math.abs(field[x - 1][y] - field[x][y]) > 1) {
                        System.out.println("Carol cannot go to the next field because the difference in height is too great.");
                    } else {
                        x--;
                    }
                }
                // When dir == 1
                else if (dir == 1) {
                    if (dir == 1 && y == (field[0].length - 1)) {   ///////////////////if there will be some error check widht == 0
                        System.out.println("Carol cannot leave the field.");
                    } else if (dir == 1 && Math.abs(field[x][y + 1] - field[x][y]) > 1) {
                        System.out.println("Carol cannot go to the next field because the difference in height is too great.");
                    } else {
                        y++;
                    }
                } else if (dir == 3) {
                    if (dir == 3 && y == 0) {   ///////////////////if there will be some error check widht == 0
                        System.out.println("Carol cannot leave the field.");
                    } else if (dir == 3 && Math.abs(field[x][y - 1] - field[x][y]) > 1) {
                        System.out.println("Carol cannot go to the next field because the difference in height is too great.");
                    } else {
                        y--;
                    }
                }
            }
            // Instruction n.
            else if (instr == 'n') {
                //if he has already 10 ice , cant take more.
                if (iceblocks == 10) {
                    System.out.println("Carol can't take a block of ice, she's already carrying ten.");
                } ///if carol is in the water
                else if (field[x][y] == -1) {
                    System.out.println("Carol cannot take blocks of ice in the water.");
                } else if (dir == 0 && x == (field.length - 1)) {
                    System.out.println("Carol cannot leave the field.");

                } else if (dir == 1 && y == (field[0].length - 1)) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 2 && x == 0) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 3 && y == 0) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 0 && field[x + 1][y] == -1) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 1 && field[x][y + 1] == -1) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 2 && field[x - 1][y] == -1) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 3 && field[x][y - 1] == -1) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else {
                    if (dir == 0) {
                        iceblocks++;
                        field[x + 1][y]--;
                    } else if (dir == 1) {
                        iceblocks++;
                        field[x][y + 1]--;
                    } else if (dir == 2) {
                        iceblocks++;
                        field[x - 1][y]--;
                    } else if (dir == 3) {
                        iceblocks++;
                        field[x][y - 1]--;
                    }

                }
            } else if (instr == 'p') {
                //if he has 0 ice , cant put more.
                if (iceblocks == 0) {
                    System.out.println("Carol can't lay a block of ice because she isn't carrying one.");
                } ///if carol is in the water
                else if (field[x][y] == -1) {
                    System.out.println("Carol cannot lay blocks of ice in the water.");
                } else if (dir == 0 && x == (field.length - 1)) {
                    System.out.println("Carol cannot leave the field.");

                } else if (dir == 1 && y == (field[0].length - 1)) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 2 && x == 0) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 3 && y == 0) {   ///////////////////if there will be some error check widht == 0
                    System.out.println("Carol cannot leave the field.");
                } else if (dir == 0 && field[x + 1][y] == 10) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 1 && field[x][y + 1] == 10) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 2 && field[x - 1][y] == 10) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else if (dir == 3 && field[x][y - 1] == 10) {
                    System.out.println("Carol can't take a block of ice, there aren't any left.");
                } else {
                    if (dir == 0) {
                        iceblocks--;
                        field[x + 1][y]++;
                    } else if (dir == 1) {
                        iceblocks--;
                        field[x][y + 1]++;
                    } else if (dir == 2) {
                        iceblocks--;
                        field[x - 1][y]++;
                    } else if (dir == 3) {
                        iceblocks--;
                        field[x][y - 1]++;
                    }
                }
            }
        }
    }
}