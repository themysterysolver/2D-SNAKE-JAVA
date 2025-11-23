import javax.swing.*;
import java.util.*;

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
        head.add(size/2);
        head.add(size/2);

        generateFood();

        snake.add(head);
        /*Thought of adding  a zero body since i have uppdated updateDirection*/
//        System.out.println(snake);

        dirMap = new HashMap<>();
        dirMap.put("UP",new ArrayList<>(Arrays.asList(-1,0)));
        dirMap.put("DOWN",new ArrayList<>(Arrays.asList(1,0)));
        dirMap.put("LEFT",new ArrayList<>(Arrays.asList(0,-1)));
        dirMap.put("RIGHT",new ArrayList<>(Arrays.asList(0,1)));


    }

    //console display
    void display(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    /*The idea here is beautiful with differnet scenarios. I always thought moving everything while coding
    * for example if we have a snake of (0,0),(0,1),(0,2) then if I want to move eerything I thought I
    * should make track of turns or move the head seperately from the body.Like 2 different things.I thought
    * I should shift each other,this is a real drag. Then out of blue I got an SUPERB idea Adding a head front
    * is moving forward and removing a tail from backward is technicall shifting all blocks ahead.*/
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
            if(haveWon()){
                JOptionPane.showMessageDialog(null, "Won!!");
                return false;
            }
            if(IsgameOver(nx,ny)) {
                JOptionPane.showMessageDialog(null, "Game over!!");
                return false;
            }
            board[hx][hy] = 1; //making the snake head as a body(green) still in snake list(body)


            if(board[nx][ny]==3){ //food
                /*If food exists then what we do is that we don't remove tail but just move head forward
                * if food doens't exist we pop it to simulate the moving idea.*/
                ArrayList<Integer> tail = snake.getLast();
                board[tail.get(0)][tail.get(1)] = 1;

                //generating food
                if(!generateFood()){
                    return false;
                }

            }else{
                ArrayList<Integer> tail = snake.removeLast();
                board[tail.get(0)][tail.get(1)] = 0; //making it blank
            }
            //This was before the if block making a lot of confusion due to overriding,thx to GPT for debugging.
            board[nx][ny] = 2; //red
            snake.add(0,new ArrayList<>(Arrays.asList(nx,ny)));
            /*Board is for display purpose and snake is for handling the game logic!*/


            return true;
    }

    private boolean haveWon() {
        ArrayList<ArrayList<Integer>> empty = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == 0){
                    empty.add(new ArrayList<>(Arrays.asList(i,j)));
                }
            }
        }
        if(empty.size()==0) {
            return true;
        }
        return false;
    }

    private boolean generateFood() {
        ArrayList<ArrayList<Integer>> empty = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == 0){
                    empty.add(new ArrayList<>(Arrays.asList(i,j)));
                }
            }
        }
        if(empty.size()==0){
            return false;
        }else{
            Random r = new Random();
            int idx = r.nextInt(empty.size());
            board[empty.get(idx).get(0)][empty.get(idx).get(1)] = 3;
        }
        return true;
    }

    private boolean IsgameOver(int nx, int ny) {
        /*Stupid snake ends over if it tries to go back the same direction on which it is
         moving on,so going to fix that up here*/
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



//    public static void main(String[] args) {
//
//    }

    public void updateDirection(String dir) {
        ArrayList<Integer> currentDir = dirMap.get(this.direction);
        ArrayList<Integer> newDir = dirMap.get(dir);
        if(snake.size()==1 || currentDir.get(0)+newDir.get(0)!=0 || currentDir.get(1)+newDir.get(1)!= 0){
            this.direction = dir; // only update if not opposite
        }
    }
}
