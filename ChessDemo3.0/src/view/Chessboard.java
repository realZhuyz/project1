package view;


import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.BLACK;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private boolean KingSurvive=true;

    public boolean isKingSurvive() {
        return KingSurvive;
    }

    public void setKingSurvive(boolean kingSurvive) {
        KingSurvive = kingSurvive;
    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initKingOnBoard(0,4,ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE-1,4,ChessColor.WHITE);
        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        initPawnOnBoard(1,0,ChessColor.BLACK);
        initPawnOnBoard(1,1,ChessColor.BLACK);
        initPawnOnBoard(1,2,ChessColor.BLACK);
        initPawnOnBoard(1,3,ChessColor.BLACK);
        initPawnOnBoard(1,4,ChessColor.BLACK);
        initPawnOnBoard(1,5,ChessColor.BLACK);
        initPawnOnBoard(1,6,ChessColor.BLACK);
        initPawnOnBoard(1,7,ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,0,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,1,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,2,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,3,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,4,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,5,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,6,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,7,ChessColor.WHITE);
        initQueenOnBoard(0,3,ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1,3,ChessColor.WHITE);
        initBishopOnBoard(0,2,ChessColor.BLACK);
        initBishopOnBoard(0,CHESSBOARD_SIZE - 3,ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1,2,ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1,CHESSBOARD_SIZE - 3,ChessColor.WHITE);
    }
    public void Restart(){
        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initKingOnBoard(0,4,ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE-1,4,ChessColor.WHITE);
        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        initPawnOnBoard(1,0,ChessColor.BLACK);
        initPawnOnBoard(1,1,ChessColor.BLACK);
        initPawnOnBoard(1,2,ChessColor.BLACK);
        initPawnOnBoard(1,3,ChessColor.BLACK);
        initPawnOnBoard(1,4,ChessColor.BLACK);
        initPawnOnBoard(1,5,ChessColor.BLACK);
        initPawnOnBoard(1,6,ChessColor.BLACK);
        initPawnOnBoard(1,7,ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,0,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,1,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,2,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,3,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,4,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,5,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,6,ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2,7,ChessColor.WHITE);
        initQueenOnBoard(0,3,ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1,3,ChessColor.WHITE);
        initBishopOnBoard(0,2,ChessColor.BLACK);
        initBishopOnBoard(0,CHESSBOARD_SIZE - 3,ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1,2,ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1,CHESSBOARD_SIZE - 3,ChessColor.WHITE);
    }

    public String getChessboardGraph(){
        String[] chessboard = new String[9];
        for (int i = 0; i < 8; i++) {
            chessboard[i] = "";
            for (int j = 0; j < 8; j++) {
                if (this.chessComponents[i][j] instanceof KingChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'K';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'k';
                    }
                }
                if (this.chessComponents[i][j] instanceof QueenChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'Q';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'q';
                    }
                }
                if (this.chessComponents[i][j] instanceof RookChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'R';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'r';
                    }
                }
                if (this.chessComponents[i][j] instanceof BishopChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'B';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'b';
                    }
                }
                if (this.chessComponents[i][j] instanceof KnightChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'N';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'n';
                    }
                }
                if (this.chessComponents[i][j] instanceof PawnChessComponent) {
                    if (this.chessComponents[i][j].getChessColor() == ChessColor.BLACK) {
                        chessboard[i] = chessboard[i] + 'P';
                    }
                    else {
                        chessboard[i] = chessboard[i] + 'p';
                    }
                }
                if (this.chessComponents[i][j] instanceof EmptySlotComponent){
                    chessboard[i] = chessboard[i] + '_';
                }
            }
        }
        if (this.currentColor == ChessColor.WHITE){
            chessboard[8] = "w";
        }
        else {
            chessboard[8] = "b";
        }
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",chessboard[0],chessboard[1],chessboard[2],chessboard[3],chessboard[4],chessboard[5],chessboard[6],chessboard[7],chessboard[8]);
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if((chess2 instanceof KingChessComponent)){
            ChessColor chessColor=chess2.getChessColor();
            setKingSurvive(false);
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
            chess1.swapLocation(chess2);
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            chessComponents[row1][col1] = chess1;
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            chessComponents[row2][col2] = chess2;
            chess1.repaint();
            chess2.repaint();
            if(chessColor==ChessColor.BLACK){
                JOptionPane.showMessageDialog(null, "White Player Win!!!", "Game Over!", JOptionPane.WARNING_MESSAGE);
            }if(chessColor==ChessColor.WHITE){
                JOptionPane.showMessageDialog(null, "Black Player Win!!!", "Game Over!", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
            }
            chess1.swapLocation(chess2);
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            chessComponents[row1][col1] = chess1;
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            chessComponents[row2][col2] = chess2;
            chess1.repaint();
            chess2.repaint();
        }
        if(chess1 instanceof PawnChessComponent){
            if(((PawnChessComponent) chess1).canGetPromotion(chessComponents)){
                remove(chess1);
                String [] options = {"马(Knight)","象(Bishop)","车(Rook)","后(Queen)"};
                int n=JOptionPane.showOptionDialog(null,"请选择兵的升变类型：","升变",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if(n==0){add(chess1 = new KnightChessComponent(chess1.getChessboardPoint(), chess1.getLocation(),chess1.getChessColor(), clickController, CHESS_SIZE));}
                else if(n==1){add(chess1 = new BishopChessComponent(chess1.getChessboardPoint(), chess1.getLocation(),chess1.getChessColor(), clickController, CHESS_SIZE));}
                else if(n==2){add(chess1 = new RookChessComponent(chess1.getChessboardPoint(), chess1.getLocation(),chess1.getChessColor(), clickController, CHESS_SIZE));}
                else if(n==3){add(chess1 = new QueenChessComponent(chess1.getChessboardPoint(), chess1.getLocation(),chess1.getChessColor(), clickController, CHESS_SIZE));}
                int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                chessComponents[row1][col1] = chess1;
                chess1.repaint();
            }
        }
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initEmptyOnBoard(int row, int col) {
        ChessComponent chessComponent = new EmptySlotComponent(new ChessboardPoint(row, col), calculatePoint(row, col), clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
        int m = 0;
        int n = 1;
        int o = 0;
        if (chessData.size()==9){
            m=1;
            for (int i = 0; i < 8; i++) {
                if (chessData.get(i).length() != 8){
                    m = 0;
                    break;
                }
            }
        }
        if (chessData.size() == 8){
            m = 1;
            for (int i = 0; i < 8; i++) {
                if (chessData.get(i).length() != 8){
                    m = 0;
                    break;
                }
            }
        }
        if ( m == 0){
            JOptionPane.showMessageDialog(null,"棋盘大小错误","棋盘错误",JOptionPane.ERROR_MESSAGE);
        }
        int k = chessData.size();
        if ((chessData.get(k-1).charAt(0) != 'w' || chessData.get(k-1).charAt(0) != 'b') && chessData.get(k-1).length() != 1){
            n = 0;
        }
        if (n == 0) {
            JOptionPane.showMessageDialog(null,"缺少行棋方","行棋方错误",JOptionPane.ERROR_MESSAGE);
        }
        if ( n == 1) {
            for (int i = 0; i < chessData.size()-1; i++) {
                for (int j = 0; j < chessData.get(i).length(); j++) {
                    if (chessData.get(i).charAt(j) != 'K' && chessData.get(i).charAt(j) != 'k' && chessData.get(i).charAt(j) != 'Q' && chessData.get(i).charAt(j) != 'q' && chessData.get(i).charAt(j) != 'R' && chessData.get(i).charAt(j) != 'r' && chessData.get(i).charAt(j) != 'B' && chessData.get(i).charAt(j) != 'b' && chessData.get(i).charAt(j) != 'N' && chessData.get(i).charAt(j) != 'n' && chessData.get(i).charAt(j) != 'P' && chessData.get(i).charAt(j) != 'p' && chessData.get(i).charAt(j) != '_'){
                        o = 0;
                        break;
                    }
                    else {
                        o = 1;
                    }
                }
                if (o == 0){
                    break;
                }
            }
        }
        if (n == 0){
            for (int i = 0; i < chessData.size(); i++) {
                for (int j = 0; j < chessData.get(i).length(); j++) {
                    if (chessData.get(i).charAt(j) != 'K' && chessData.get(i).charAt(j) != 'k' && chessData.get(i).charAt(j) != 'Q' && chessData.get(i).charAt(j) != 'q' && chessData.get(i).charAt(j) != 'R' && chessData.get(i).charAt(j) != 'r' && chessData.get(i).charAt(j) != 'B' && chessData.get(i).charAt(j) != 'b' && chessData.get(i).charAt(j) != 'N' && chessData.get(i).charAt(j) != 'n' && chessData.get(i).charAt(j) != 'P' && chessData.get(i).charAt(j) != 'p' && chessData.get(i).charAt(j) != '_'){
                        o = 0;
                        break;
                    }
                    else {
                        o = 1;
                    }
                }
                if ( o == 0){
                    break;
                }
            }
        }
        if ( o == 0 ){
            JOptionPane.showMessageDialog(null,"棋子并非六种棋子","棋子错误",JOptionPane.ERROR_MESSAGE);
        }
        if(m == 1 && n == 1 && o == 1) {
            initiateEmptyChessboard();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (chessData.get(i).charAt(j) == 'K') {
                        initKingOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'Q') {
                        initQueenOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'R') {
                        initRookOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'B') {
                        initBishopOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'N') {
                        initKnightOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'P') {
                        initPawnOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (chessData.get(i).charAt(j) == 'k') {
                        initKingOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == 'q') {
                        initQueenOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == 'r') {
                        initRookOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == 'b') {
                        initBishopOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == 'n') {
                        initKnightOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == 'p') {
                        initPawnOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (chessData.get(i).charAt(j) == '_') {
                        initEmptyOnBoard(i, j);
                    }
                }
                if (chessData.get(8).charAt(0) == 'w') {
                    this.currentColor = ChessColor.WHITE;
                } else {
                    this.currentColor = ChessColor.BLACK;
                }
            }
        }
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }
    public boolean KingGetAttacked(ChessComponent[][] chessComponents, ChessColor CurrentPlayer){
        ChessColor anotherColor;
        if(CurrentPlayer==ChessColor.BLACK){anotherColor=ChessColor.WHITE;}
        else{anotherColor=ChessColor.BLACK;}
        ChessboardPoint KingSource=sourceOfKing(chessComponents,anotherColor);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                ChessComponent x= chessComponents[i][j];
                if(x.getChessColor()==CurrentPlayer){
                    for(int m=0;m<x.getCanMoveTo(chessComponents).size();m++){
                        if(x.getCanMoveTo(chessComponents).get(m).getX()==KingSource.getX()&&x.getCanMoveTo(chessComponents).get(m).getY()==KingSource.getY()){
                            System.out.println(x.getChessboardPoint());
                            if(CurrentPlayer==ChessColor.WHITE){System.out.println("Attention!, Black King get attacked!");}
                            if(CurrentPlayer==ChessColor.BLACK){System.out.println("Attention!, White King get attacked!");}
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public ChessboardPoint sourceOfKing(ChessComponent[][] chessComponents,ChessColor currentPlayer){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chessComponents[i][j]instanceof KingChessComponent&&chessComponents[i][j].getChessColor()==currentPlayer){
                    ChessboardPoint KingPoint=new ChessboardPoint(i,j);
                    return KingPoint;
                }
            }
        }
        return null;
    }
    public ArrayList<ChessboardPoint> sourceOfRook(ChessComponent[][] chessComponents,ChessColor currentPlayer){
        ArrayList<ChessboardPoint> sourceOfRook=new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chessComponents[i][j]instanceof RookChessComponent&&chessComponents[i][j].getChessColor()==currentPlayer){
                    ChessboardPoint RookPoint=new ChessboardPoint(i,j);
                    sourceOfRook.add(RookPoint);
                }
            }
        }
        return sourceOfRook;
    }
    public boolean changeKingWithRook(ChessComponent[][] chessComponents,ChessboardPoint destination){
        ChessColor anotherColor;
        if(getCurrentColor()==ChessColor.BLACK){
            anotherColor=ChessColor.WHITE;
        }else{anotherColor=ChessColor.BLACK;}
        for(int i=0;i<sourceOfRook(chessComponents,getCurrentColor()).size();i++){
            if(destination.getX()==sourceOfRook(chessComponents,getCurrentColor()).get(i).getX()&&destination.getY()==sourceOfRook(chessComponents,getCurrentColor()).get(i).getY()&&!KingGetAttacked(chessComponents,anotherColor)){
                if(destination.getX()==sourceOfKing(chessComponents,getCurrentColor()).getX()&&(Math.abs(sourceOfKing(chessComponents,getCurrentColor()).getY()-destination.getY())==3||Math.abs(sourceOfKing(chessComponents,getCurrentColor()).getY()-destination.getY())==4)&&chessComponents[sourceOfKing(chessComponents,getCurrentColor()).getX()][sourceOfKing(chessComponents,getCurrentColor()).getY()].getMoveTimes()==0&&chessComponents[destination.getX()][destination.getY()].getMoveTimes()==0) {
                    for(int j=Math.min(sourceOfKing(chessComponents,getCurrentColor()).getY(),destination.getY())+1;j<Math.max(sourceOfKing(chessComponents,getCurrentColor()).getY(),destination.getY());j++) {
                        if(!(chessComponents[destination.getX()][j]instanceof EmptySlotComponent)){
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
            return false;
    }
    public void disPrintCanMove(ChessComponent[][] chessboard){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) {
                chessboard[i][j].setCanMoveSelected(false);
                chessboard[i][j].repaint();
            }
        }
    }
}
