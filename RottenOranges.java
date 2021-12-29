import java.util.LinkedList;
import java.util.Queue;

// TC - O(m*n)
// SC - O(m*n) for the queue, say if all are 2's in the matrix
public class RottenOranges {

     int m ;
     int n;
     int mins;
     int [][] dirs;
     Queue <int []> q;
     int rotCount;
    public int orangesRotting(int[][] grid) {

        if(grid == null || grid.length ==0) {
            return -1;
        }

        m = grid.length;
        n = grid[0].length;
        mins = 0;
        dirs = new int[][]{{-1,0}, {1,0}, {0, -1}, {0,1}};
        int freshCount =0;
        rotCount =0;
        q = new LinkedList<>();
        for(int i =0; i < m; i++) {
            for (int j =0; j< n;j++) {
                if(grid[i][j] == 2) {
                    q.add(new int[] {i,j}); // Adding all the rotten oranges in the queue
                } else if(grid[i][j] == 1) {
                    freshCount++; // calculating the fresh count
                }
            }
        }

        if(freshCount ==0) {
            return 0; // dont move ahead, if there are no fresh oranges
        }

        bfs(grid); // bfs on the queue

        if(freshCount == rotCount) {
            return mins-1; // if the fresh count is equal to the rot count, that means all are rotten
            // giving -1, because the initial state is when we added rotten oranges and when we iterate over the rotten oranges we did +1
            // we added 2's first, then we added all 1's, so subtracting the time when we added 2's
        } else {
            return -1;
        }
    }

    private void bfs(int [][] grid) {
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i =0; i< size; i++) {
                int [] elem = q.poll();
                for(int [] dir : dirs) {
                    // going over all the 4 directions
                    int nr = elem[0] + dir[0];
                    int nc = elem[1] + dir[1];

                    //checking if that is Valid or not
                    if(isValid(nr, nc, grid)) {
                        grid[nr][nc]=2;
                        q.add(new int[]{nr, nc});
                        rotCount++; // increasing the rot count
                    }
                }
            }
            mins++;
        }
    }

    private boolean isValid(int row, int col, int [][]grid) {
        return row >=0 && col >=0 && row<m && col <n && grid[row][col]==1;
    }
}
