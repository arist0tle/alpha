package com.geektcp.alpha.game.hanoi.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class AutoPanel extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private  int amountOfDisc = 3;
    private TowerPoint[] pointA, pointB, pointC;
    private static char[] towerName;
    private Container con;
    private  StringBuffer moveStep;
    private JTextArea showStep;
    private JButton bStart, bStop, bContinue, bClose;
    private Timer time;
    private int i = 0, number = 0;

    public AutoPanel(Container con) {
        setModal(true);
        setTitle("自动演示");
        this.con = con;
        moveStep = new StringBuffer();
        time = new Timer(100, this);
        time.setInitialDelay(10);
        showStep = new JTextArea(10, 12);
        bStart = new JButton("演示");
        bStop = new JButton("暂停");
        bContinue = new JButton("继续");
        bClose = new JButton("关闭");

        bStart.addActionListener(this);
        bStop.addActionListener(this);
        bContinue.addActionListener(this);
        bClose.addActionListener(this);

        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(bStart);
        south.add(bStop);
        south.add(bContinue);
        south.add(bClose);
        add(new JScrollPane(showStep), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        towerName = new char[3];

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                time.stop();
                setVisible(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == time) {
            number++;
            char cStart, cEnd;
            if (i <= moveStep.length() - 2) {
                cStart = moveStep.charAt(i);
                cEnd = moveStep.charAt(i + 1);
                showStep.append("(" + number + ")从" + cStart + "座搬一个盘子到" + cEnd + "座\n");
                autoMoveDisc(cStart, cEnd);
            }
            i = i + 2;
            if (i >= moveStep.length() - 1)
                time.stop();
        } else if (e.getSource() == bStart) {
            autoRun();
        } else if (e.getSource() == bStop) {
            if (time.isRunning()) {
                time.stop();
            }
        } else if (e.getSource() == bContinue) {
            if (!time.isRunning()) {
                time.restart();
            }
        } else if (e.getSource() == bClose) {
            time.stop();
            setVisible(false);
        }

        try{
            Thread.sleep(300);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void setMoveStep(int mountOfDisc, char one, char two, char three) {
        if (mountOfDisc == 1) {
            moveStep.append(one);
            moveStep.append(three);
        } else {
            setMoveStep(mountOfDisc - 1, one, three, two);
            moveStep.append(one);
            moveStep.append(three);
            setMoveStep(mountOfDisc - 1, two, one, three);
        }
    }

    private void autoMoveDisc(char cStart, char cEnd) {
        Disc disc = null;
        if (cStart == towerName[0]) {
            for (TowerPoint point : pointA) {
                if (point.isExistDisc()) {
                    disc = point.getDiscOnPoint();
                    point.setHaveDisc(false);
                    break;
                }
            }
        }
        if (cStart == towerName[1]) {
            for (TowerPoint point : pointB) {
                if (point.isExistDisc()) {
                    disc = point.getDiscOnPoint();
                    point.setHaveDisc(false);
                    break;
                }
            }
        }
        if (cStart == towerName[2]) {
            for (TowerPoint point : pointC) {
                if (point.isExistDisc()) {
                    disc = point.getDiscOnPoint();
                    point.setHaveDisc(false);
                    break;
                }
            }
        }

        TowerPoint endPoint = null;
        int i = 0;
        if (cEnd == towerName[0]) {
            for (i = 0; i < pointA.length; i++) {
                if (pointA[i].isExistDisc()) {
                    if (i > 0) {
                        endPoint = pointA[i - 1];
                        break;
                    } else if (i == 0) {
                        break;
                    }
                }
            }
            if (i == pointA.length)
                endPoint = pointA[pointA.length - 1];
        }

        if (cEnd == towerName[1]) {
            for (i = 0; i < pointB.length; i++) {
                if (pointB[i].isExistDisc()) {
                    if (i > 0) {
                        endPoint = pointB[i - 1];
                        break;
                    } else if (i == 0) {
                        break;
                    }
                }
            }
            if (i == pointB.length)
                endPoint = pointB[pointB.length - 1];
        }

        if (cEnd == towerName[2]) {
            for (i = 0; i < pointC.length; i++) {
                if (pointC[i].isExistDisc()) {
                    if (i > 0) {
                        endPoint = pointC[i - 1];
                        break;
                    } else if (i == 0) {
                        break;
                    }
                }
            }
            if (i == pointC.length)
                endPoint = pointC[pointA.length - 1];
        }

        if (endPoint != null && disc != null) {
            endPoint.putDisc(disc, con);
            endPoint.setHaveDisc(true);
        }

    }

    public void setPointA(TowerPoint[] pointA) {
        this.pointA = pointA;
    }


    public void setPointB(TowerPoint[] pointB) {
        this.pointB = pointB;
    }

    public void setPointC(TowerPoint[] pointC) {
        this.pointC = pointC;
    }

    public void setTowerName(char[] name) {
        if (name[0] == name[1] || name[0] == name[2] || name[1] == name[2]) {
            towerName[0] = 'A';
            towerName[1] = 'B';
            towerName[2] = 'C';
        } else {
            towerName = name;
        }
    }

    public void setAmountOfDisc(int amountOfDisc) {
        this.amountOfDisc = amountOfDisc;
    }

    public void autoRun(){
        showStep.setText("");
        if (moveStep.length() == 0) {
            if (!time.isRunning()) {
                i = 0;
                moveStep = new StringBuffer();
                setMoveStep(amountOfDisc, towerName[0], towerName[1], towerName[2]);
                number = 0;
                time.start();
            }
        }
    }

    public void autoRun(HanoiPanel panel){
        panel.initTower();
        autoRun();
    }
}