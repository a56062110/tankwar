import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

GameClient(){
    this(800,600);          //設置遊戲畫面大小
}
public GameClient(int screenWidth,int screenHeight){
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.setPreferredSize(new Dimension(screenWidth,screenHeight));
}

    @Override
    protected void paintComponent(Graphics g) {                 //繪製功能
        super.paintComponent(g);                        //可有可無
        g.drawImage(new ImageIcon("assets\\images\\iTankD.png")
                .getImage(),400,300,null);                  //繪製圖案
    }


}
