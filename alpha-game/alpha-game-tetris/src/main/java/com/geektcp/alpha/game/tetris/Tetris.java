package com.geektcp.alpha.game.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Tetris extends JPanel {

    private static final long serialVersionUID = -807909536278284335L;
    private static final int BLOCK_SIZE = 10;
    private static final int BLOCK_WIDTH = 16;
    private static final int BLOCK_HEIGHT = 26;
    private static final int TIME_DELAY = 1000;

    private static final String[] AuthorInfo = {
            "�����ˣ�", "HelloClyde"
    };

    // ����Ѿ��̶��ķ���
    private boolean[][] blockMap = new boolean[BLOCK_HEIGHT][BLOCK_WIDTH];

    // ����
    private int score = 0;

    //�Ƿ���ͣ
    private boolean isPause = false;

    // 7����״
    static boolean[][][] shape = BlockV4.Shape;

    // ���䷽���λ��,���Ͻ�����
    private Point nowBlockPos;

    // ��ǰ�������
    private boolean[][] nowBlockMap;
    // ��һ���������
    private boolean[][] nextBlockMap;
    /**
     * ��Χ[0,28) 7�֣�ÿ����4����ת״̬����4*7=28 %4��ȡ��ת״̬ /4��ȡ��״
     */
    private int nextBlockState;
    private int nowBlockState;

    //��ʱ��
    private Timer timer;

    private Tetris() {
        this.initial();
        timer = new Timer(Tetris.TIME_DELAY, this.TimerListener);
        timer.start();
        this.addKeyListener(this.KeyListener);
    }

    private void setMode(String mode) {
        if (mode.equals("v6")) {
            Tetris.shape = BlockV6.Shape;
        } else {
            Tetris.shape = BlockV4.Shape;
        }
        this.initial();
        this.repaint();
    }

    /**
     * �µķ�������ʱ�ĳ�ʼ��
     */
    private void getNextBlock() {
        // ���Ѿ����ɺõ���һ�η��鸳����ǰ����
        this.nowBlockState = this.nextBlockState;
        this.nowBlockMap = this.nextBlockMap;
        // �ٴ�������һ�η���
        this.nextBlockState = this.CreateNewBlockState();
        this.nextBlockMap = this.getBlockMap(nextBlockState);
        // ���㷽��λ��
        this.nowBlockPos = this.calNewBlockInitPos();
    }

    /**
     * �ж���������ķ����ǽ���Ѿ��̶��ķ����Ƿ��нӴ�
     *
     * @return
     */
    private boolean isTouch(boolean[][] srcNextBlockMap, Point srcNextBlockPos) {
        for (int i = 0; i < srcNextBlockMap.length; i++) {
            for (int j = 0; j < srcNextBlockMap[i].length; j++) {
                if (srcNextBlockMap[i][j]) {
                    if (srcNextBlockPos.y + i >= Tetris.BLOCK_HEIGHT || srcNextBlockPos.x + j < 0 || srcNextBlockPos.x + j >= Tetris.BLOCK_WIDTH) {
                        return true;
                    } else {
                        if (srcNextBlockPos.y + i < 0) {
                            continue;
                        } else {
                            if (this.blockMap[srcNextBlockPos.y + i][srcNextBlockPos.x + j]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * �̶����鵽��ͼ
     */
    private boolean fixBlock() {
        for (int i = 0; i < this.nowBlockMap.length; i++) {
            for (int j = 0; j < this.nowBlockMap[i].length; j++) {
                if (this.nowBlockMap[i][j])
                    if (this.nowBlockPos.y + i < 0) {
                        return false;
                    } else {
                        this.blockMap[this.nowBlockPos.y + i][this.nowBlockPos.x + j] = this.nowBlockMap[i][j];
                    }
            }
        }
        return true;
    }

    /**
     * �����´����ķ���ĳ�ʼλ��
     *
     * @return ��������
     */
    private Point calNewBlockInitPos() {
        return new Point(Tetris.BLOCK_WIDTH / 2 - this.nowBlockMap[0].length / 2, -this.nowBlockMap.length);
    }

    /**
     * ��ʼ��
     */
    public void initial() {
        //���Map
        for (int i = 0; i < this.blockMap.length; i++) {
            for (int j = 0; j < this.blockMap[i].length; j++) {
                this.blockMap[i][j] = false;
            }
        }
        //��շ���
        this.score = 0;
        // ��ʼ����һ�����ɵķ������һ�����ɵķ���
        this.nowBlockState = this.CreateNewBlockState();
        this.nowBlockMap = this.getBlockMap(this.nowBlockState);
        this.nextBlockState = this.CreateNewBlockState();
        this.nextBlockMap = this.getBlockMap(this.nextBlockState);
        // ���㷽��λ��
        this.nowBlockPos = this.calNewBlockInitPos();
        this.repaint();
    }

    public void setPause(boolean value) {
        this.isPause = value;
        if (this.isPause) {
            this.timer.stop();
        } else {
            this.timer.restart();
        }
        this.repaint();
    }

    /**
     * ��������·���״̬
     */
    private int CreateNewBlockState() {
        int Sum = Tetris.shape.length * 4;
        return (int) (Math.random() * 1000) % Sum;
    }

    private boolean[][] getBlockMap(int BlockState) {
        int Shape = BlockState / 4;
        int Arc = BlockState % 4;
        System.out.println(BlockState + "," + Shape + "," + Arc);
        return this.RotateBlock(Tetris.shape[Shape], Arc);
    }

    /**
     * ԭ�㷨
     *
     * ��ת����Map��ʹ�ü�����任,ע��Դ���󲻻ᱻ�ı�
     * ʹ��round���doubleת����int���ȶ�ʧ���½������ȷ������
     *
     * @param blockMap
     *            ��Ҫ��ת�ľ���
     * @param angel
     *            rad�Ƕȣ�Ӧ��Ϊpi/2�ı���
     * @return ת����ɺ�ľ�������

    private boolean[][] RotateBlock(boolean[][] blockMap, double angel) {
    // ��ȡ������
    int Heigth = blockMap.length;
    int Width = blockMap[0].length;
    // �¾���洢���
    boolean[][] ResultBlockMap = new boolean[Heigth][Width];
    // ������ת����
    float CenterX = (Width - 1) / 2f;
    float CenterY = (Heigth - 1) / 2f;
    // ������任���λ��
    for (int i = 0; i < blockMap.length; i++) {
    for (int j = 0; j < blockMap[i].length; j++) {
    //�����������ת���ĵ�����
    float RelativeX = j - CenterX;
    float RelativeY = i - CenterY;
    float ResultX = (float) (Math.cos(angel) * RelativeX - Math.sin(angel) * RelativeY);
    float ResultY = (float) (Math.cos(angel) * RelativeY + Math.sin(angel) * RelativeX);
    // ������Ϣ
    //System.out.println("RelativeX:" + RelativeX + "RelativeY:" + RelativeY);
    //System.out.println("ResultX:" + ResultX + "ResultY:" + ResultY);

    //��������껹ԭ
    Point OrginPoint = new Point(Math.round(CenterX + ResultX), Math.round(CenterY + ResultY));
    ResultBlockMap[OrginPoint.y][OrginPoint.x] = blockMap[i][j];
    }
    }
    return ResultBlockMap;
    }
     **/

    /**
     * @param shape 7��ͼ��֮һ
     * @param time  ��ת����
     * @return https://blog.csdn.net/janchin/article/details/6310654  ��ת����
     */

    private boolean[][] RotateBlock(boolean[][] shape, int time) {
        if (time == 0) {
            return shape;
        }
        int heigth = shape.length;
        int width = shape[0].length;
        boolean[][] resultMap = new boolean[heigth][width];
        int tmpH = heigth - 1, tmpW = 0;
        for (int i = 0; i < heigth && tmpW < width; i++) {
            for (int j = 0; j < width && tmpH > -1; j++) {
                resultMap[i][j] = shape[tmpH][tmpW];
                tmpH--;
            }
            tmpH = heigth - 1;
            tmpW++;
        }
        for (int i = 1; i < time; i++) {
            resultMap = RotateBlock(resultMap, 0);
        }
        return resultMap;
    }

    /**
     * ���Է�����������ת����
     *
     * @param args
     */
    public static void main(String... args) {
        boolean[][] srcMap = Tetris.shape[3];
        Tetris.ShowMap(srcMap);
		/*
		for (int i = 0;i < 7;i ++){
			System.out.println(i);
			Tetris.ShowMap(Tetris.shape[i]);
		}
		*/

        Tetris tetris = new Tetris();
        boolean[][] result = tetris.RotateBlock(srcMap, 1);
        Tetris.ShowMap(result);

    }

    /**
     * ���Է�������ʾ����
     *
     * @param SrcMap
     */
    static private void ShowMap(boolean[][] SrcMap) {
        System.out.println("-----");
        for (int i = 0; i < SrcMap.length; i++) {
            for (int j = 0; j < SrcMap[i].length; j++) {
                if (SrcMap[i][j])
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    /**
     * ������Ϸ����
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ��ǽ
        for (int i = 0; i < Tetris.BLOCK_HEIGHT + 1; i++) {
            g.drawRect(0 * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            g.drawRect((Tetris.BLOCK_WIDTH + 1) * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                    Tetris.BLOCK_SIZE);
        }
        for (int i = 0; i < Tetris.BLOCK_WIDTH; i++) {
            g.drawRect((1 + i) * Tetris.BLOCK_SIZE, Tetris.BLOCK_HEIGHT * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                    Tetris.BLOCK_SIZE);
        }
        // ����ǰ����
        for (int i = 0; i < this.nowBlockMap.length; i++) {
            for (int j = 0; j < this.nowBlockMap[i].length; j++) {
                if (this.nowBlockMap[i][j])
                    g.fillRect((1 + this.nowBlockPos.x + j) * Tetris.BLOCK_SIZE, (this.nowBlockPos.y + i) * Tetris.BLOCK_SIZE,
                            Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            }
        }
        // ���Ѿ��̶��ķ���
        for (int i = 0; i < Tetris.BLOCK_HEIGHT; i++) {
            for (int j = 0; j < Tetris.BLOCK_WIDTH; j++) {
                if (this.blockMap[i][j])
                    g.fillRect(Tetris.BLOCK_SIZE + j * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE,
                            Tetris.BLOCK_SIZE);
            }
        }
        //������һ������
        for (int i = 0; i < this.nextBlockMap.length; i++) {
            for (int j = 0; j < this.nextBlockMap[i].length; j++) {
                if (this.nextBlockMap[i][j])
                    g.fillRect(190 + j * Tetris.BLOCK_SIZE, 30 + i * Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
            }
        }
        // ����������Ϣ
        g.drawString("��Ϸ����:" + this.score, 190, 10);
        for (int i = 0; i < Tetris.AuthorInfo.length; i++) {
            g.drawString(Tetris.AuthorInfo[i], 190, 100 + i * 20);
        }

        //������ͣ
        if (this.isPause) {
            g.setColor(Color.white);
            g.fillRect(70, 100, 50, 20);
            g.setColor(Color.black);
            g.drawRect(70, 100, 50, 20);
            g.drawString("PAUSE", 75, 113);
        }
    }

    /**
     * @return
     */
    private int ClearLines() {
        int lines = 0;
        for (int i = 0; i < this.blockMap.length; i++) {
            boolean IsLine = true;
            for (int j = 0; j < this.blockMap[i].length; j++) {
                if (!this.blockMap[i][j]) {
                    IsLine = false;
                    break;
                }
            }
            if (IsLine) {
                for (int k = i; k > 0; k--) {
                    this.blockMap[k] = this.blockMap[k - 1];
                }
                this.blockMap[0] = new boolean[Tetris.BLOCK_WIDTH];
                lines++;
            }
        }
        return lines;
    }

    // ��ʱ������
    ActionListener TimerListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO �Զ����ɵķ������
            if (Tetris.this.isTouch(Tetris.this.nowBlockMap, new Point(Tetris.this.nowBlockPos.x, Tetris.this.nowBlockPos.y + 1))) {
                if (Tetris.this.fixBlock()) {
                    Tetris.this.score += Tetris.this.ClearLines() * 10;
                    Tetris.this.getNextBlock();
                } else {
                    JOptionPane.showMessageDialog(Tetris.this.getParent(), "GAME OVER");
                    Tetris.this.initial();
                }
            } else {
                Tetris.this.nowBlockPos.y++;
            }
            Tetris.this.repaint();
        }
    };

    //��������
    java.awt.event.KeyListener KeyListener = new java.awt.event.KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO �Զ����ɵķ������
            if (!isPause) {
                Point DesPoint;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x, Tetris.this.nowBlockPos.y + 1);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        boolean[][] TurnBlock = Tetris.this.RotateBlock(Tetris.this.nowBlockMap, 1);
                        if (!Tetris.this.isTouch(TurnBlock, Tetris.this.nowBlockPos)) {
                            Tetris.this.nowBlockMap = TurnBlock;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x + 1, Tetris.this.nowBlockPos.y);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        DesPoint = new Point(Tetris.this.nowBlockPos.x - 1, Tetris.this.nowBlockPos.y);
                        if (!Tetris.this.isTouch(Tetris.this.nowBlockMap, DesPoint)) {
                            Tetris.this.nowBlockPos = DesPoint;
                        }
                        break;
                }
                //System.out.println(Tetris.this.nowBlockPos);
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO �Զ����ɵķ������

        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO �Զ����ɵķ������

        }

    };
}
