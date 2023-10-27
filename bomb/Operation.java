import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Operation {

    private static List<String> msgArr=new ArrayList();
    public void testRandom2() {
        double random = Math.random();
    }
    public static void main(String[] args) throws AWTException {
        //轰炸内容
        String str = "你脑子有坑吧";

        Robot robot = new Robot();
        robot.delay(5000);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = str.split("[,]");
        //循环轰炸
        for (int j = 0; j < 100000; j++){
            for (int i = 0; i < authors.length; i++) {
                String str1 = authors[i];
                Transferable text = new StringSelection(str1);
                clip.setContents(text, null);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(100);
                robot.keyPress(KeyEvent.VK_ENTER);
            }
        }
    }
}
