package secret;

import utils.Colors;
import utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.Random;

public class Snake {        
    // Original source code:
    // https://github.com/FernandoSC18/Snake_Java_using_jni/blob/main/src/snakepackage/Snake.java
    // This code is a modified version of the original code.
    // The original code was modified to work with the Solidereviews project.

    private static final String UP_DOWN = "─";
    private static final String LEFT_RIGHT = "│";
    private static final String TOP_LEFT = "┌";
    private static final String TOP_RIGHT = "┐";
    private static final String BOTTOM_LEFT = "└";
    private static final String BOTTOM_RIGHT = "┘";
    private static final String SNAKE_HEAD = Colors.GREEN + "█" + Colors.RESET;
    private static final String SNAKE = Colors.GREEN_BRIGHT + "█" + Colors.RESET;;
    private static final String FOOD = Colors.RED + "█" + Colors.RESET;

    private int HEIGHT = 20;
    private int WIDTH = 40;
    private String BOARD_DATA [][] = new String[HEIGHT][WIDTH];
    private int FOOD_DATA [] = new int[2];
    private static ArrayList<int[]> PLAYER_DATA = new ArrayList<int[]>();

    private static int direction;
    private int gameUpdateTime = 100;
    private int gameSpeed = 8;

    private Thread updateGui = null;
    private Thread speedGame = null;

    private boolean isUpdateGui = true;
    private boolean isSpeedGame = true;

    public Snake() {
        resetGame();
        drawBoard();

        updateGui = new Thread(new Runnable(){ 
        @Override
        public void run() { 
            try {
                while (isUpdateGui){
                    GlobalFunctions.clearScreen();
        
                    drawBoard();
                    Thread.sleep(gameUpdateTime);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }  
        });

        speedGame = new Thread(new Runnable() { 
            @Override
            public void run() {
                long speed = (1000 - (gameSpeed * 100));

                try{
                    while (isSpeedGame){
                        for (int [] body : PLAYER_DATA) {
                            BOARD_DATA[body[0]][body[1]] = " ";
                        }

                        updatePlayer();
                        
                        insertPlayer();
                        insertFood();

                        Thread.sleep(speed > 50 ? speed : 50);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        updateGui.start();
        speedGame.start();
    }

    private void drawBoard() {
        for (int i = 0; i < HEIGHT; i++){
        for (int j = 0; j < WIDTH; j++){
                System.out.print(BOARD_DATA[i][j]);
            }
            System.out.println("");
        }
    }

    private void resetGame() {
        direction = 57421;
        PLAYER_DATA = new ArrayList<int[]>();

        //player position
        int middleColumns = (int) (WIDTH / 2);
        int middleRows = (int) (HEIGHT / 2);

        PLAYER_DATA.add( new int[]{middleRows, middleColumns});
        PLAYER_DATA.add( new int[]{middleRows, middleColumns - 1});
        PLAYER_DATA.add( new int[]{middleRows, middleColumns - 2});
        PLAYER_DATA.add( new int[]{middleRows, middleColumns - 3});

        createRandomFood();

        //fill table with a space
        for (int i = 1; i < HEIGHT - 1; i++){
            for (int j = 1; j < WIDTH - 1; j++){
                BOARD_DATA[i][j] = " ";
            }
        }

        //fill up and down walls 
        for (int i = 0; i < WIDTH; i++){
            BOARD_DATA[0][i] = UP_DOWN;
            BOARD_DATA[HEIGHT - 1][i] = UP_DOWN;
        }

        //fill right and left walls
        for (int i = 0; i < HEIGHT; i++){
            BOARD_DATA[i][0] = LEFT_RIGHT;
            BOARD_DATA[i][WIDTH - 1] = LEFT_RIGHT;
        }

        //fill corners
        BOARD_DATA[0][0] = TOP_LEFT;
        BOARD_DATA[0][WIDTH - 1] = TOP_RIGHT;
        BOARD_DATA[HEIGHT - 1][0] = BOTTOM_LEFT;
        BOARD_DATA[HEIGHT - 1][WIDTH - 1] = BOTTOM_RIGHT;

        insertPlayer();
        insertFood();
    }

    private void insertFood() {
        BOARD_DATA[FOOD_DATA[0]][FOOD_DATA[1]] = FOOD;
    }

    private void insertPlayer() {
        try {
            for (int [] pos : PLAYER_DATA){
                BOARD_DATA[pos[0]][pos[1]] = SNAKE;
            }

            int [] head = PLAYER_DATA.get(0);
            BOARD_DATA[head[0]][head[1]] = SNAKE_HEAD;
        }catch (ArrayIndexOutOfBoundsException err){
            System.out.println(err);
            resetGame();
        }
    }

    private void createRandomFood() {
        Random random = new Random();

        int x = random.nextInt(WIDTH - 2) + 1;
        int y = random.nextInt(HEIGHT - 2) + 1;

        for (int [] body : PLAYER_DATA){
            if(body[0] == y && body[1] == x){
                createRandomFood();
                return;
            }
        }

        FOOD_DATA[0] = y;
        FOOD_DATA[1] = x;
    }

    public static void changeDir(int dir) {
        if (direction == 57416 && dir == 57424 )
            return;
        else if (direction == 57424 && dir == 57416 )
            return;
        else if (direction == 57419 && dir == 57421 )
            return;
        else if (direction == 57421 && dir == 57419 )
            return;

        direction = dir;
    }

    private void updatePlayer() {

        int [] head = PLAYER_DATA.get(0);

        switch (direction) { 
            case 57416: //up
                PLAYER_DATA.add(0, new int[] {head[0] - 1, head[1]});
            break;
            case 57421: //right          
                PLAYER_DATA.add(0, new int[] {head[0], head[1] + 1});
            break;
            case 57424: //down
                PLAYER_DATA.add(0, new int[] {head[0] + 1, head[1]});
            break;
            case 57419: //left
                PLAYER_DATA.add(0, new int[] {head[0], head[1] - 1});            
            break;
            default: break;
        }

        if (head[0] == FOOD_DATA[0] && head[1] == FOOD_DATA[1]){
            createRandomFood();
        }else{
            PLAYER_DATA.remove(PLAYER_DATA.size() - 1);
        }

        int [] newHead = PLAYER_DATA.get(0);

        //Head Collition with any wall
        if (newHead[0] <= 0 || newHead[0] >= HEIGHT -1 || newHead[1] <= 0 || newHead[1] >= WIDTH -1){
            resetGame();
        }

        //Head Collition with body 
        for (int [] body : PLAYER_DATA.subList(1, PLAYER_DATA.size())){
            if(body[0] == newHead[0] && body[1] == newHead[1]){
                resetGame();
                break;
            }
        }
    } 
}