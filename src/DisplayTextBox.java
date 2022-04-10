import javax.swing.*;
import java.awt.*;

public class DisplayTextBox extends JPanel {
    String text = "";

    public DisplayTextBox() {
        super();
        this.setPreferredSize(new Dimension(SimSettings.DIMENSION, 15));
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.drawString(text, this.getWidth()/2 - text.length()*3, this.getHeight());
    }

    public void setText(String text) {
        this.text = text;
    }
}
