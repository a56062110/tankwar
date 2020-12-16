import javax.swing.*;

public class TankGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameClient gameClient = new GameClient(800,600);
        frame.add(gameClient);
        frame.setLocation(350,100);                 //設置視窗開啟位置
        frame.setTitle("坦克大戰");                       //設置視窗名稱
        frame.setVisible(true);                         //啟動時視窗為顯示狀態
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           //設置視窗可手動關閉
        frame.pack();
        gameClient.repaint();                   //放入繪製圖案
    }
}
