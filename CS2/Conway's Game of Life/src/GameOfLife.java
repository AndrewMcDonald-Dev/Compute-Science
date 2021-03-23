public class GameOfLife {
    public static void main(String[] args) {
        int l = 10, w = 10;
        int interations = 10;

        int[][] grid = {
                {0,0,0,0,0,0,0,0,1,1},
                {0,0,0,1,0,0,0,0,1,1},
                {0,0,0,0,1,0,0,0,0,1},
                {0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,1,1,1},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,1,0,0,0,0,0},
                {0,0,1,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };

        System.out.println("Input");
        for (int i = 0; i < l; i++){
            for (int j = 0; j < w; j++){
                if(grid[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print("# ");
            }
            System.out.println();
        }
        for(int i = 0; i < l+2;i++)
            System.out.print("_ ");
        System.out.println();
        for(int i = 0; i < interations; i++) {
            grid = nextIteration(grid, l, w);
        }
    }

    static int[][] nextIteration(int grid[][], int l, int w){
        int[][] next = new int[l][w];

        for (int i = 1; i < (l -1); i++){
            for (int j = 1; j < (w - 1); j++){

                int aliveNeighbors = 0;
                for(int n = -1; n <= 1; n++)
                    for(int k = -1; k <= 1; k++)
                        aliveNeighbors += grid[i + n][j + k];

                aliveNeighbors -= grid[i][j];

                if((grid[i][j] == 1) &&(aliveNeighbors < 2))
                    next[i][j]=0;

                else if((grid[i][j] == 1) &&(aliveNeighbors > 3))
                    next[i][j] = 0;

                else if((grid[i][j] == 0) && (aliveNeighbors == 3))
                    next[i][j]= 1;

                else
                    next[i][j] = grid[i][j];
            }
        }
        System.out.println("Next Iteration");
        for(int i = 0; i < l; i++){
            for(int j = 0;j < w; j++){
                if(next[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print("# ");
            }
            System.out.println();
        }

        for(int k = 0; k < l+2;k++)
            System.out.print("_ ");
        System.out.println();
        return next;
    }
}