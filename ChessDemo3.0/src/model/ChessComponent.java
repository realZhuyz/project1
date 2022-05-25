package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 这个类是一个抽象类，主要表示8*8棋盘上每个格子的棋子情况，当前有两个子类继承它，分别是EmptySlotComponent(空棋子)和RookChessComponent(车)。
 */
public abstract class ChessComponent extends JComponent {

    /**
     * CHESSGRID_SIZE: 主要用于确定每个棋子在页面中显示的大小。
     * <br>
     * 在这个设计中，每个棋子的图案是用图片画出来的（由于国际象棋那个棋子手动画太难了）
     * <br>
     * 因此每个棋子占用的形状是一个正方形，大小是50*50
     */

//    private static final Dimension CHESSGRID_SIZE = new Dimension(1080 / 4 * 3 / 8, 1080 / 4 * 3 / 8);
    private static final Color[] BACKGROUND_COLORS = {Color.decode("#EEE9BF"), Color.decode("#8B6914")};
    /**
     * handle click event
     */
    private ClickController clickController;

    /**
     * chessboardPoint: 表示8*8棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0), (0, 7),(7, 7)等等
     * <br>
     * chessColor: 表示这个棋子的颜色，有白色，黑色，无色三种
     * <br>
     * selected: 表示这个棋子是否被选中
     */
    private ChessboardPoint chessboardPoint;
    protected final ChessColor chessColor;
    private boolean selected;
    private int moveTimes=0;
    private boolean canMoveSelected;

    public boolean isCanMoveSelected() {
        return canMoveSelected;
    }

    public void setCanMoveSelected(boolean canMoveSelected) {
        this.canMoveSelected = canMoveSelected;
    }

    public int getMoveTimes() {
        return moveTimes;
    }

    public void addMoveTimes(){
        moveTimes++;
    }
    protected ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
    }
    public ChessComponent(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(ChessComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用所有监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
            clickController.onClick(this);
        }
    }

    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 7)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false
     */
    public abstract boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination);

    /**
     * 这个方法主要用于加载一些特定资源，如棋子图片等等。
     *
     * @throws IOException 如果一些资源找不到(如棋子图片路径错误)，就会抛出异常
     */
    public abstract void loadResource() throws IOException;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        System.out.printf("repaint chess [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
        Color squareColor = BACKGROUND_COLORS[(chessboardPoint.getX() + chessboardPoint.getY()) % 2];
        g.setColor(squareColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (isCanMoveSelected()) { // Highlights the model if selected.
            g.setColor(Color.MAGENTA);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    //↓该方法以List的形式返回所有canMoveTo的点
    public ArrayList<ChessboardPoint> getCanMoveTo(ChessComponent[][] chessComponents){
        ChessboardPoint source= this.getChessboardPoint();
        ArrayList<ChessboardPoint> canMovePoint = new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) {
                ChessboardPoint destination = new ChessboardPoint(i, j);
                if (canMoveTo(chessComponents, destination) && (chessComponents[i][j].getChessColor() != this.getChessColor())) {
                    canMovePoint.add(destination);
                }
            }
        }
        return canMovePoint;
    }
    public void printCanMoveTo(ChessComponent[][] chessboard){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for(int m=0;m<getCanMoveTo(chessboard).size();m++){
                    if(getCanMoveTo(chessboard).get(m).getX()==i&&getCanMoveTo(chessboard).get(m).getY()==j){
                        chessboard[i][j].setCanMoveSelected(true);
                        chessboard[i][j].repaint();
                    }
                }
            }
        }
    }
    //↓该方法判断：棋子moveTo的destination是否会收到对方棋子的攻击（移动后位于对方某一棋子的canMoveTo中）
    public boolean NotGetAttacked(ChessComponent[][] chessComponents,ChessboardPoint destination){
        ChessboardPoint source=getChessboardPoint();
        ChessColor thisColor=getChessColor();
        ChessComponent[][] initialChessComponents=new ChessComponent[8][8];
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                initialChessComponents[i][j] = chessComponents[i][j];//储存数组
            }
        }
        ChessComponent Test0 =new KingChessComponent(thisColor);
        ChessComponent Test1 =new EmptySlotComponent(ChessColor.NONE);
        initialChessComponents[destination.getX()][destination.getY()] = Test0;
        initialChessComponents[source.getX()][source.getY()] = Test1;
        //这里我直接新写了两个构造方法，但目的只是改变ChessComponent[][]中的棋子种类属性，其余属性不改变。
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                ChessComponent x= initialChessComponents[i][j];
                if(x.getChessColor()!=thisColor){
                    for(int m=0;m<x.getCanMoveTo(initialChessComponents).size();m++){
                        if(x.getCanMoveTo(initialChessComponents).get(m).getX()==destination.getX()&&x.getCanMoveTo(initialChessComponents).get(m).getY()==destination.getY()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
