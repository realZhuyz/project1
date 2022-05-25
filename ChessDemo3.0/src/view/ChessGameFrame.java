package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel playerLabel;

    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        this.playerLabel = new JLabel();

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addPlayerLabel();
        addResetButton();
//        addLoadButton();
        addSaveButton();
        addBackground();
    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
        /*JButton button1 = new JButton("Resart");
        button1.addActionListener((e) -> {
            playerLabel.setText("Round "+ChessColor.BLACK.getName());
            chessboard.setCurrentColor(ChessColor.BLACK);
            chessboard.Restart();
            repaint();
        });
        button1.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button1);*/
        /*保存

         */
        /*JButton button2 = new JButton("Save");
        button2.setLocation(HEIGTH, HEIGTH/10 + 360);
        button2.setSize(200,60);
        button2.setFont(new Font("Rockwell",Font.BOLD,20));
        add(button2);
        button2.addActionListener(e -> {
            gameController.saveGame();
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.txt)","txt");
            chooser.setFileFilter(filter);
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();
                String fname = chooser.getName(file);

                if (!fname.contains(".txt")) {
                    file = new File(chooser.getCurrentDirectory(), fname + ".txt");
                    System.out.println("renamed");
                    System.out.println(file.getName());
                }

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    String data = chessboard.getChessboardGraph();
                    fos.write(data.getBytes());
                    fos.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        /*
        加载
         */
      JButton button3 = new JButton("Load");
        button3.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button3.setSize(200, 60);
        button3.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button3);

        button3.addActionListener(e -> {
  //          System.out.println("Click load");
  //          String path = JOptionPane.showInputDialog(this,"Input Path here");
  //          gameController.loadGameFromFile(path);
            JFileChooser jfc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.txt)","txt");
            jfc.setFileFilter(filter);
            int returnvalue = jfc.showOpenDialog(null);
            if (returnvalue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                File file = jfc.getSelectedFile();
                String fname = jfc.getName(file);

                if (!fname.contains(".txt")) {
                    JOptionPane.showMessageDialog(null,"文件格式错误","格式错误",JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        List<String> chessData = Files.readAllLines(Path.of(selectedFile.getPath()));
                        chessboard.loadGame(chessData);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(selectedFile.getAbsoluteFile());
                    setplayerlabel(chessboard.getCurrentColor().getName());
                    repaint();
                }
            }
        });
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addPlayerLabel() {
        setplayerlabel(ChessColor.BLACK.getName());
        playerLabel.setLocation(HEIGTH, HEIGTH / 10);
        playerLabel.setSize(200, 60);
        playerLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        playerLabel.setForeground(Color.WHITE);
        add(playerLabel);
    }

    public static void setplayerlabel(String chessColor){
        playerLabel.setText("Round "+chessColor);
    }


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addResetButton() {
        JButton button1 = new JButton("Restart");
        button1.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        button1.addActionListener((e) -> {
            gameController.restartGame();
      });
        add(button1);
   }

    private void addBackground(){
        JLayeredPane layeredPane = new JLayeredPane();
        ImageIcon imageIcon = new ImageIcon("./images/J.png");
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0,0,WIDTH,HEIGTH);
        add(jLabel);
    }

/*   private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            gameController.loadGameFromFile();
        });
    }*/

    private void addSaveButton(){
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH/10 + 360);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell",Font.BOLD,20));
        add(button);
        button.addActionListener(e -> {
            gameController.saveGame();
        });
    }

}
