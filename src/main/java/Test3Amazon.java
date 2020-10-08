public class Test3Amazon {

    /*
    Amazon plans to open Treasure Truck Pop-Ups in the park areas of Technicia. Technicia is represented as a grid of M*N blocks. Each block represents either a park area, denoted by 1, or a commercial area, denoted by 0.
    Adjacent blocks with value 1 are considered a contiguous park. Diagonal blocks with value 1 are not considered part of the same contiguity.
    Write an algorithm to find the number of Treasure Truck Pop-Ups that Amazon can open in the area of Technicia.

    Input
    The input to the function/method consists of three arguments:-
    rows, an integer representing the number of rows in the grid;
    column, an integer representing the number of columns in the grid;
    grid, a two-dimensional integer array representing Technicia.

    Output
    Return an integer representing the number of Treasure Truck Pop-Ups that Amazon can open in the area of Technicia.

    Exmple
    Input
        rows = 5
        column = 4
        grid
            1 1 0 0
            0 0 1 0
            0 0 0 0
            1 0 1 1
            1 1 1 1
     output = 3
     The first cluster is the two adjacent 1's in row one.
     The second cluster is the 1 on row two, which is not adjacent to any other 1's (diagonal blocks are not considered part of the same cluster).
     The third cluster is the set of seven adjacent 1's in rows four and five.
     The total number of clusters of open areas = 3.

    Testcase 1
    Input
    4,4
        1 1 0 0
        0 0 0 0
        0 0 1 1
        0 0 0 0
    expected result 2

    Testcase 2
    input
    7,7
        1 0 0 0 0 0 0
        0 1 0 0 0 0 0
        0 0 1 0 0 0 0
        0 0 0 1 0 0 0
        0 0 0 0 1 0 0
        0 0 0 0 0 1 0
        0 0 0 0 0 0 1
     */
    public static void main(String[] args) {
        int testCase = 0;

        //Testcase 0
        printTestCase(++testCase, 5, 4, new int[][]{{1,1,0,0}, {0,0,1,0}, {0,0,0,0}, {1,0,1,1}, {1,1,1,1}}, 3);

        //Testcase 1
        printTestCase(++testCase, 4, 4, new int[][]{{1,1,0,0}, {0,0,0,0}, {0,0,1,1}, {0,0,0,0}}, 2);

        //Testcase 2
        printTestCase(++testCase, 7, 7, new int[][]{{1,0,0,0,0,0,0}, {0,1,0,0,0,0,0}, {0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0}, {0,0,0,0,1,0,0}, {0,0,0,0,0,1,0}, {0,0,0,0,0,0,1}}, 7);

        //Testcase 3
        printTestCase(++testCase, 3, 4, new int[][]{{1,0,0,0}, {1,0,1,1}, {1,0,0,0}}, 2);

        //Testcase 4
        printTestCase(++testCase, 4, 4, new int[][]{{1,0,0,1}, {1,0,0,1}, {1,0,0,1}, {1,1,1,1}}, 1);

        //Testcase 5
        printTestCase(++testCase, 4, 6, new int[][]{{1,0,1,0,0,0}, {1,0,1,0,0,1}, {1,1,1,1,0,1}, {0,0,1,0,1,1}}, 2);

        //Testcase 6
        printTestCase(++testCase, 2, 2, new int[][]{{1,1}, {1,1}}, 1);

        //Testcase 7
        printTestCase(++testCase, 3, 3, new int[][]{{0,1,0}, {1,1,1}, {0,1,0}}, 1);
    }

    //Miramos cuántas son las mínimas conexiones necesarias para conectar todos los parques
    //que pertenecen a una zona de parque
    //Contamos los parques que están conectados y se los quitamos al número total de parques
    public static int countParkZones(int rows, int columns, int[][] grid) {

        int parks = 0;
        int links = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(grid[i][j] == 1) {
                    parks++;

                    //Comprobamos si hay un parque a la derecha o debajo
                    boolean hayDerecha = j+1 < columns && grid[i][j+1] == 1;
                    boolean hayDebajo = i+1 < rows && grid[i+1][j] == 1;

                    //Comprobamos que no hay parque a arriba y en la diagonal
                    //Si lo hay, el parque que tengamos a la derecha ya lo abremos contado
                    //Y sólo miramos los parques que están conectados una vez
                    boolean hayArriba = i-1 >= 0 && grid[i-1][j] == 1;
                    boolean hayDiagonal = i-1 >= 0 && grid[i-1][j] == 1 && j+1 < columns && grid[i-1][j+1] == 1;

                    if(hayDebajo) {
                        links++;
                    }

                    if(hayDerecha && !(hayArriba && hayDiagonal)) {
                        links++;
                    }
                }
            }
        }

        System.out.println("Parks: " + parks + " - Links: " + links);

        return parks - links;
    }

    public static void printTestCase(int testCase, int rows, int columns, int[][] grid, int expected) {
        System.out.println("Testcase " + testCase + ":");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("--------------");
        System.out.println("Found: " + countParkZones(rows, columns, grid));
        System.out.println("Expected: " + expected);
        System.out.println();
    }
}
