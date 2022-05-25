package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetBackground extends JPanel {
    private int width;
    private int height;
    private BufferedImage image = null;
    public SetBackground(int width, int height, String Path){
        super();
        this.width = width;
        this.height = height;
        try{
            image = ImageIO.read(new File(Path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(image,0,0,width,height,this);
    }
}
