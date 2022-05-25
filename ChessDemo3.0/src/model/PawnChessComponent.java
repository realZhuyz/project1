package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent{
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    /**
     * 车棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 车棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(this.chessColor==ChessColor.BLACK){
            if(source.getY() == destination.getY()&&source.getX()+2 == destination.getX()&&source.getX()==1&&chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                //首次行棋可走两步
                if((chessComponents[source.getX()+1][source.getY()] instanceof EmptySlotComponent)){
                    return true;
                }
            }
            else if(source.getY() == destination.getY()&&source.getX()+1 == destination.getX()&&chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                return true;
            } else if(Math.abs(source.getY()-destination.getY())==1&&source.getX()+1 == destination.getX()&&!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                //打斜吃子
                return true;}
        }
        if(this.chessColor==ChessColor.WHITE){
            if(source.getY() == destination.getY()&&source.getX()-2 == destination.getX()&&source.getX()==6&&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                if((chessComponents[source.getX()-1][source.getY()] instanceof EmptySlotComponent)){
                    return true;
                }
            }
            else if(source.getY() == destination.getY()&&source.getX()-1 == destination.getX()&&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                return true;
            }else if(Math.abs(source.getY()-destination.getY())==1&&source.getX()-1 == destination.getX()&&!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                return true;}
        }
        return false;
    }
    public boolean canGetPromotion(ChessComponent[][] chessComponents){
        if(this.chessColor==ChessColor.BLACK&&getChessboardPoint().getX()==7) {
            return true;
        }else if(this.chessColor==ChessColor.WHITE&&getChessboardPoint().getX()==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
        if (isCanMoveSelected()) { // Highlights the model if selected.
            g.setColor(Color.GREEN);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
