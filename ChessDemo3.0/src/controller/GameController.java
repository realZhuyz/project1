package controller;

import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void saveGame(){
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
    }

    public  void restartGame(){
        ChessGameFrame.setplayerlabel(ChessColor.BLACK.getName());
        chessboard.setCurrentColor(ChessColor.BLACK);
        chessboard.Restart();
        chessboard.repaint();
    }


/*    public List<String> loadGameFromFile() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.txt)", "txt");
        jfc.setFileFilter(filter);
        int returnvalue = jfc.showOpenDialog(null);
        if (returnvalue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            File file = jfc.getSelectedFile();
            String fname = jfc.getName(file);
            try {
                List<String> chessData = Files.readAllLines(Path.of(selectedFile.getPath()));
                chessboard.loadGame(chessData);
                return chessData;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(selectedFile.getAbsoluteFile());
            ChessGameFrame.setplayerlabel(chessboard.getCurrentColor().getName());
            chessboard.repaint();
        }
        return null;
    }*/
}
