package Main.Game;

public abstract class GameObjects {
    final private int playerSize = 25;
    protected int locationInY = 25;
    protected int locationInX = 25;

    boolean pastAble;



    public int getLocationInY() {
        return locationInY;
    }

    public void setLocationInY(int locationInY) {
        this.locationInY = locationInY;
    }

    public int getLocationInX() {
        return locationInX;
    }

    public void setLocationInX(int locationInX) {
        this.locationInX = locationInX;
    }


    public int getPlayerSize() {
        return this.playerSize;
    }
    public static boolean canMoveUp(int y , int x){
        int[][] arr = GameObjects.arrOfIndex;
        int  y1, x1, x2;

        y1 = y  / 25;
        if (x % 25 != 0){
            x1 = x / 25;
            x2 = x / 25 + 1;

            return arr[y1][x1] != 0 && arr[y1][x2] != 0;
        }
        else x1 = x / 25;
        return arr [y1][x1] != 0;
    }
    public static boolean canMoveDown(int y , int x){
        int[][] arr = GameObjects.arrOfIndex;
         int y1, x1, x2;

         if ( y % 25 != 0){
             y1 = y / 25 + 1;
         }
         else y1 = y / 25;
         if (x % 25 != 0){
             x1 = x / 25;
             x2 = x / 25 + 1;
             return arr[y1][x1] != 0 && arr[y1][x2] != 0;
         }
         else x1 = x / 25;
        return arr[y1][x1] != 0;
    }

    public static boolean canMoveLeft(int y , int x){
        int[][] arr = GameObjects.arrOfIndex;
        int y1, y2, x1;

        x1 = x / 25;
        if (y % 25 != 0){
            y1 = y / 25 + 1;
            y2 = y / 25;
            return arr[y1][x1] != 0 && arr[y2][x1] != 0;
        }
        else y1 = y / 25;
        return arr[y1][x1] != 0;
    }
    public static boolean canMoveRight(int y , int x){
        int[][] arr = GameObjects.arrOfIndex;
        int y1, y2, x1;

        if (x % 25 != 0){
            x1 = x /25 + 1;
        }
        else x1 = x / 25;
        if (y % 25 != 0){
            y1 = y / 25 + 1;
            y2 = y / 25;
            return arr[y1][x1] != 0 && arr[y2][x1] != 0;
        }
        else y1 = y / 25;
        return arr[y1][x1] != 0;
    }
    public static void isEaten(int y, int x){
        GameObjects.arrOfIndex[y][x] = 1;
    }


    static int[][] level_1 =
            {
                    // 0 present wall
                    // 1 present way
                    // 2 present fruit
                    // 3 present ghost
                    // 4 ghost killer
                    // 5 present chery

                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 1, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0},
                    {0, 2, 0, 2, 2, 4, 0, 2, 0, 1, 1, 1, 0, 2, 0, 4, 2, 2, 0, 2, 0},
                    {0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0},
                    {0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0},
                    {0, 2, 0, 2, 0, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 0},
                    {0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 5, 0, 2, 0, 2, 0},
                    {0, 2, 0, 4, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 4, 0, 2, 0},
                    {0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };


    static int [][] level_2 =
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0},
                    {0, 2, 2, 2, 2, 4, 2, 2, 0, 1, 1, 1, 0, 2, 2, 4, 2, 2, 2, 2, 0},
                    {0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0},
                    {0, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0},
                    {0, 2, 0, 4, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 4, 0, 2, 0},
                    {0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0},
                    {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

    static int[][] arrOfIndex = level_1;

}

