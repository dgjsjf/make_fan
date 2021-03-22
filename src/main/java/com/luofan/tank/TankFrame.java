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
    int x = 200;
    int y = 200;

    public TankFrame() {
        setSize(800, 600);
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

    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
        x += 10;
//        y += 10;
    }

    /**
     * 处理键盘事件的类
     */
    class MykeyListen extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            x += 20;
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
        }
    }

}
