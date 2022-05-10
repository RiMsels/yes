import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputAdapter;



public class App {
    public static JFrame main;
    
    public static void main(String[] args) throws Exception {
        displayFrame();
        getItems();
        main.setVisible(true);
    }
    
    public static void displayFrame(){
        main = new JFrame("Catalogue");
        main.setSize(1024, 576);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(null);
        main.setResizable(false);
        //main.setLocationRelativeTo(null);
        //main.setVisible(true);
    }

    public static void getItems() throws FileNotFoundException{
        DefaultListModel<String> l1 = new DefaultListModel<>();
        File dir = new File("src/items");
        File[] directoryListing = dir.listFiles();
        for(File child : directoryListing){
            l1.addElement(child.getPath());
            System.out.println(child);
        }
        JList<String> list = new JList<>(l1);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list); 
        scrollPane.getViewport().add(list);
        //list.setBounds(100, 100, 300, 100);
        scrollPane.setBounds(100, 100, 300, 100);
        //main.add(list);
        main.add(scrollPane);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(500, 100, 400, 400);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        MouseInputAdapter mouseListener = new MouseInputAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 1){
                    System.out.println(list.getSelectedValue());
                    String path = list.getSelectedValue();
                    try {

                        String content = Files.readString(Paths.get(path));
                        System.out.println(content);
                        textArea.setText(content);

                    } catch (IOException b) {
                        b.printStackTrace();
                    }

                }
            }
        };
                
        list.addMouseListener(mouseListener);
        main.add(textArea);
    }

}
