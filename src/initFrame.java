import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class initFrame extends JFrame {
    String[] items = {"день", "неделя", "месяц", "год"};
    private JTextField field1, field2;
    public initFrame()throws Exception{

            JLabel label = new JLabel("Даты вводить в формате: dd.mm.yyyy");
            JComboBox a = new JComboBox(items);
            Container content = getContentPane();
            JButton butt = new JButton("Apply");
            JCheckBox check = new JCheckBox("SMA");
            butt.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    boolean sma = check.isSelected();
                    if(!field1.getText().isEmpty() && !field2.getText().isEmpty()) {
                        try {
                            SiteAPI api = new SiteAPI();
                            api.getCurr().clear();
                            int tmp = (int) a.getSelectedIndex();
                            System.out.println(tmp);
                            api.setPeriod(field1.getText(), field2.getText());
                            new FrameChart(tmp, api, sma);

                        } catch (Exception ee) {
                            System.out.println(ee.toString());
                        }
                    }
                    else if(field1.getText().isEmpty() && field2.getText().isEmpty()){
                        try {
                            SiteAPI api = new SiteAPI();
                            api.getCurr().clear();
                            int tmp = (int) a.getSelectedIndex();
                            //System.out.println(tmp);
                            String tmp1 = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                            String tmp2 = LocalDate.now().minusYears(10).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                            System.out.println(tmp1 + "      " +tmp2);
                            api.setPeriod(tmp2, tmp1);
                            new FrameChart(tmp, api, sma);

                        } catch (Exception ee) {
                            System.out.println(ee.toString());
                        }
                    }
                }
            });
            field1 = new JTextField();
            field2 = new JTextField();

            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.add(a);
            content.add(label);
            content.add(field1);
            content.add(field2);
            content.add(check);
            content.add(butt);

            setSize(800,500);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



}
