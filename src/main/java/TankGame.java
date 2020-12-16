import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        final GameClient gameClient = new GameClient(800, 600);
        frame.add(gameClient);
        frame.setLocation(350, 100);                 //設置視窗開啟位置
        frame.setTitle("坦克大戰");                       //設置視窗名稱
        frame.setVisible(true);                         //啟動時視窗為顯示狀態
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           //設置視窗可手動關閉
        frame.pack();
        gameClient.repaint();                   //放入繪製圖案

        frame.addKeyListener(new KeyAdapter() {         //產生事件監聽類別
            @Override
            public void keyPressed(KeyEvent e) {        //按下按鈕事件
                super.keyPressed(e);
                System.out.println((char)e.getKeyCode());     //取得按鍵代碼 (轉型後取得對應按鍵名稱)
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    }
}
