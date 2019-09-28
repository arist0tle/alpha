package com.geektcp.alpha.game.wuziqi;


import com.geektcp.alpha.game.wuziqi.panel.AI;
import com.geektcp.alpha.game.wuziqi.panel.DrawingPanel;
import com.geektcp.alpha.game.wuziqi.panel.QiMouseEvent;

/**
 * Created by TangHaiyang on 2019/9/20.
 */
public class Application {

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(700, 700);
        QiMouseEvent qiMouseEvent = new QiMouseEvent();
        panel.addMouseListener(qiMouseEvent);
        AI.init(panel);
        AI.initChessBoard();
    }
}
