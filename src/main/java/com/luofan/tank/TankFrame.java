package com.luofan.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author luofan
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 200, Dir.DOWN);
    Bullet myBullet = new Bullet(300, 300, Dir.DOWN);
    private final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        this.addKeyListener(new MykeyListen());
        // 窗口监听 当窗口按x 可以关闭窗口，不加只能小程序停止，可以注销下列内部类进行规避验证
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }

    /**
     * 防止 子弹一直频闪 ---  游戏双缓冲问题
     * update方法在paint之前进行加载
     * 原理 先给屏幕给块内存画布offScreeenImage，然后先直接画在内存位置，然后把内存画布直接交给系统画笔g
     * 即就是先做内存处理  然后一次画布同步；
     */
    Image offScreeenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreeenImage == null) {
            offScreeenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreeenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreeenImage, 0, 0, null);

    }

    @Override
    public void paint(Graphics graphics) {
        myTank.paint(graphics);
        myBullet.paint(graphics);
    }

    /**
     * 处理键盘事件的类
     */
    class MykeyListen extends KeyAdapter {
        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) {
                    myTank.setDir(Dir.LEFT);
                }
                if (bU) {
                    myTank.setDir(Dir.UP);
                }
                if (bR) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (bD) {
                    myTank.setDir(Dir.DOWN);
                }
            }

        }
    }

}
