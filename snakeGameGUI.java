import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class snakeGameGUI extends JFrame implements KeyListener {

    int size;
    game g;
    JButton[][] board;
    String dir = "DOWN";
    snakeGameGUI(){

        g = new game();
        size = g.size;


        setSize(g.size*40,g.size*40);
        setResizable(false);
        setTitle("Snake game");
        setLayout(new GridLayout(g.size,g.size));

        board = new JButton[g.size][g.size];
        for(int i=0;i<g.size;i++){
            for(int j=0;j<g.size;j++){
                board[i][j] = new JButton();

                board[i][j].setBackground(new Color(255, 255, 255));
                board[i][j].setEnabled(false);
                board[i][j].setBorderPainted(false);
                add(board[i][j]);
            }
        }

//        g.gameStart();
        displayBoard();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    void displayBoard() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(g.board[i][j]==2){ //head of the snake
                    board[i][j].setBackground(new Color(251, 3, 3));
                }else if(g.board[i][j]==3){ // food
                    board[i][j].setBackground(new Color(60, 0, 0));
                }else if(g.board[i][j]==0){ // blank space
                    board[i][j].setBackground(new Color(255, 255, 255));
                }else if(g.board[i][j]==1){ //snake body
                    board[i][j].setBackground(new Color(32, 204, 105));
                }
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
//        System.out.println(code);
        if(code == 37){
            dir="LEFT";
        }
        else if(code == 38){
            dir="UP";
        }else if(code == 39){
            dir="RIGHT";
        }else{
            dir="DOWN";
        }
        System.out.println(dir);
        g.updateDirection(dir);

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        snakeGameGUI s = new snakeGameGUI();
        s.startGame();
    }

    private void startGame() {
        int delay = 300;
        Timer[] tholder = new Timer[1];

        tholder[0] = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!g.makeMove()){
                    tholder[0].stop();
                }
                displayBoard();
            }
        });
        tholder[0].start();
    }
}
