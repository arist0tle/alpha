package com.geektcp.alpha.game.wuziqi.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by TangHaiyang on 2019/9/20.
 */ // 实现鼠标事件接口
public class QiMouseEvent implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        int x = round(e.getX()), y = round(e.getY());
        if (x >= 45 && x <= 675 && y >= 45 && y <= 675 && AI30.chessBoard[y / 45][x / 45] == 0 && AI30.isBlack == false) {
            AI30.putChess(x, y);
            if (!AI30.isFinished) {
                AI30.isBlack = true;
                AI30.myAI();
            }
            AI30.isFinished = false;
        }
    }

    // 得到鼠标点击点附近的棋盘精准点
    public static int round(int x) {
        return (x % 45 < 22) ? x / 45 * 45 : x / 45 * 45 + 45;
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
}
