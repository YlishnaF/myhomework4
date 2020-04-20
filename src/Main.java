
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SIZE = 3;
   static final int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, DOTS_TO_WIN)) {
                System.out.println("Ты выйграл!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья...");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O, DOTS_TO_WIN)) {
                System.out.println("Компьютер победил!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья...");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;

    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;

    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char c, int DOTS_TO_WIN) {
        int checkHorizontal = 0;
        int checkDiagonal1 = 0;
        int checkDiagonal2 = 0;
        int k = 0;
        int checkVertical = 0;
        for (int i = 0; i < SIZE; i++) {

            while (k<SIZE){
                if (map[i][k]==c) {
                    checkVertical++;
                }
                k++;
                if(checkVertical==DOTS_TO_WIN){
                    return true;
                }

            }

            for (int j = 0; j < SIZE; j++) {

                if (map[i][j]==c){
                    checkHorizontal++;
                } if(checkHorizontal==3){
                    return true;
                }

                if (i==j){
                    if(map[i][j]==c){
                        checkDiagonal1++;
                    }
                    if(checkDiagonal1==3){
                        return true;
                    }
                }

                if (i + j == SIZE - 1) {
                    if (map[i][j] == c) {
                        checkDiagonal2++;
                    }
                    if (checkDiagonal2 == 3) {
                        return true;
                    }
                }

            }
            checkHorizontal = 0;


            k=0;


        }


        return false;
    }
}
