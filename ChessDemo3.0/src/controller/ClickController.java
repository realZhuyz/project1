package controller;


import model.*;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)&& chessboard.isKingSurvive()) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
                first.printCanMoveTo(chessboard.getChessComponents());
            }
        } else {
            chessboard.disPrintCanMove(chessboard.getChessComponents());
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                first.addMoveTimes();
                chessboard.swapChessComponents(first, chessComponent);
                first.setSelected(false);
                if(chessboard.isKingSurvive()){
                if(chessboard.KingGetAttacked(chessboard.getChessComponents(),chessboard.getCurrentColor())&&chessboard.getCurrentColor()== ChessColor.WHITE) {
                    JOptionPane.showMessageDialog(null, "Black King get attacked!", "Attention!", JOptionPane.WARNING_MESSAGE);
                }
                if(chessboard.KingGetAttacked(chessboard.getChessComponents(),chessboard.getCurrentColor())&&chessboard.getCurrentColor()== ChessColor.BLACK) {
                    JOptionPane.showMessageDialog(null, "White King get attacked!", "Attention!", JOptionPane.WARNING_MESSAGE);
                }
                }
                chessboard.swapColor();
                first = null;
            } else if(handleThird(chessComponent)){
                //王车易位
                int X;
                if(chessboard.getCurrentColor()==ChessColor.BLACK){
                     X=0;
                }else{ X=7;}
                if(first instanceof KingChessComponent&&chessComponent.getX()==0){
                    first.addMoveTimes();
                    chessComponent.addMoveTimes();
                    ChessComponent EmptyKing=chessboard.getChessComponents()[X][2];
                    ChessComponent EmptyRook=chessboard.getChessComponents()[X][3];
                    chessboard.swapChessComponents(first, EmptyKing);
                    chessboard.swapChessComponents(chessComponent, EmptyRook);
                }else if(first instanceof KingChessComponent){
                    first.addMoveTimes();
                    chessComponent.addMoveTimes();
                    ChessComponent EmptyKing=chessboard.getChessComponents()[X][6];
                    ChessComponent EmptyRook=chessboard.getChessComponents()[X][5];
                    chessboard.swapChessComponents(first, EmptyKing);
                    chessboard.swapChessComponents(chessComponent, EmptyRook);
                }else if(first instanceof RookChessComponent&&first.getX()==0){
                    first.addMoveTimes();
                    chessComponent.addMoveTimes();
                    ChessComponent EmptyKing=chessboard.getChessComponents()[X][2];
                    ChessComponent EmptyRook=chessboard.getChessComponents()[X][3];
                    chessboard.swapChessComponents(chessComponent, EmptyKing);
                    chessboard.swapChessComponents(first, EmptyRook);
                }else{
                    first.addMoveTimes();
                    chessComponent.addMoveTimes();
                    ChessComponent EmptyKing=chessboard.getChessComponents()[X][6];
                    ChessComponent EmptyRook=chessboard.getChessComponents()[X][5];
                    chessboard.swapChessComponents(chessComponent, EmptyKing);
                    chessboard.swapChessComponents(first, EmptyRook);
                }
                first.setSelected(false);
                if(chessboard.isKingSurvive()){
                    if(chessboard.KingGetAttacked(chessboard.getChessComponents(),chessboard.getCurrentColor())&&chessboard.getCurrentColor()== ChessColor.WHITE) {
                        JOptionPane.showMessageDialog(null, "Black King get attacked!", "Attention!", JOptionPane.WARNING_MESSAGE);
                    }
                    if(chessboard.KingGetAttacked(chessboard.getChessComponents(),chessboard.getCurrentColor())&&chessboard.getCurrentColor()== ChessColor.BLACK) {
                        JOptionPane.showMessageDialog(null, "White King get attacked!", "Attention!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                chessboard.swapColor();
                first = null;
            }
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }
    private boolean handleThird(ChessComponent chessComponent){
        if(chessComponent instanceof RookChessComponent&&first instanceof KingChessComponent&&chessComponent.getChessColor()==first.getChessColor()){
            if(chessboard.changeKingWithRook(chessboard.getChessComponents(), chessComponent.getChessboardPoint())){
                return true;
            }
        }else if(chessComponent instanceof KingChessComponent&&first instanceof RookChessComponent&&chessComponent.getChessColor()==first.getChessColor()){
            if(chessboard.changeKingWithRook(chessboard.getChessComponents(), first.getChessboardPoint())){
                return true;
            }
        }
        return false;
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
//        下面这行会使棋子不主动走到受攻击的地方(吃子除外)，俗称不白给
//               && (first.NotGetAttacked(chessboard.getChessComponents(),chessComponent.getChessboardPoint())||Eat(chessComponent));
    }
    private boolean Eat(ChessComponent chessComponent){
        return !(chessboard.getChessComponents()[chessComponent.getChessboardPoint().getX()][chessComponent.getChessboardPoint().getY()] instanceof EmptySlotComponent);
    }
}
