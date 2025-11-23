import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class game {
    int board[][];
    public int size = 16;
    ArrayList<ArrayList<Integer>> snake;
    String direction = "DOWN";
    Map<String,ArrayList<Integer>> dirMap;


    public game(){
        board = new int[size][size];
        snake = new ArrayList<>();
        ArrayList<Integer> head = new ArrayList<>();
        head.add(8);
        head.add(8);
        snake.add(head);
        System.out.println(snake);

        dirMap = new HashMap<>();
        dirMap.put("UP",new ArrayList<>(Arrays.asList(-1,0)));
        dirMap.put("DOWN",new ArrayList<>(Arrays.asList(1,0)));
        dirMap.put("LEFT",new ArrayList<>(Arrays.asList(0,-1)));
        dirMap.put("RIGHT",new ArrayList<>(Arrays.asList(0,1)));


    }

    void display(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean makeMove(){
        int hx = snake.get(0).get(0);
        int hy = snake.get(0).get(1);
        board[hx][hy] = 2;

            //display();
            ArrayList<Integer> xy = dirMap.get(direction);
            int x = xy.get(0);
            int y = xy.get(1);
            int nx = hx+x;
            int ny = hy+y;

            if(IsgameOver(nx,ny)){
                System.out.println("Game over!");
                return false;
            }
            board[hx][hy] = 1;
            board[nx][ny] = 2;
            ArrayList<Integer> tail = snake.removeLast();
            board[tail.get(0)][tail.get(1)] = 0;
            snake.add(0,new ArrayList<>(Arrays.asList(nx,ny)));
            return true;
    }

    private boolean IsgameOver(int nx, int ny) {
        if(nx<0 || ny<0 || nx>=size || ny>=size){
            return true;
        }
        for(int i=0;i<snake.size();i++){
            int x = snake.get(i).get(0);
            int y = snake.get(i).get(1);
            if(x==nx && y == ny){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {

    }

    public void updateDirection(String dir) {
        direction = dir;
    }
}
