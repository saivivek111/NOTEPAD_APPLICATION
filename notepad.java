package NOTEPAD_APPLICATION;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.print.PrintException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class notepad extends JFrame implements ActionListener {
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectAll = new JMenuItem("Select All");
    JMenuItem about = new JMenuItem("About");
    JMenuItem exit = new JMenuItem("Exit");
    JTextArea textArea = new JTextArea();

    notepad() {
        setTitle("Notepad App");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("images/notepad.png"));
        setIconImage(icon.getImage());
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        help.add(about);
        JScrollPane scrollpane = new JScrollPane(textArea);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollpane);
        textArea.setFont(new Font("sans serif", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        about.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            textArea.setText(null);
        } else if (e.getSource() == openFile) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only text files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);
            int action = filechooser.showOpenDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                    textArea.read(reader, null);

                } catch (IOException ex) {

                }
            }
        } else if (e.getSource() == saveFile) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only text files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);
            int action = filechooser.showSaveDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                String fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
                if (!fileName.contains(".txt")) {
                    fileName = fileName + ".txt";
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                        textArea.write(writer);
                    } catch (IOException e1) {
                    }
                }
            }
        } else if (e.getSource() == print) {
            try {
              
                    textArea.print();
                } catch (PrinterException e1) {
                     
                    e1.printStackTrace();
                }
             
            

        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == cut) {
            textArea.cut();
        } else if (e.getSource() == copy) {
            textArea.copy();
        } else if (e.getSource() == paste) {
            textArea.paste();
        } else if (e.getSource() == selectAll) {
            textArea.selectAll();
        } else if (e.getSource() == about) {
            JFrame frame = new JFrame();
            frame.setBounds(100, 200, 500, 400);
            frame.setTitle("About Notepad Application");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ImageIcon icon = new ImageIcon(getClass().getResource("images/icons8-notepad-65.png"));
            frame.setIconImage(icon.getImage());
            frame.setLayout(null);
            JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("images/icons8-notepad-65.png")));
            iconLabel.setBounds(100, 50, icon.getIconWidth(), icon.getIconHeight());
            frame.add(iconLabel);
            JLabel textLabel = new JLabel(
                    "<html>Welcome to Notepad<br>Notepad is a simple text editor for Microsoft Windows and a basic text editing program which                 enables computer users to create documents<br>");
            textLabel.setBounds(100, 10, 350, 400);
            textLabel.setFont(new Font("SANS SERIF", Font.PLAIN, 12));
            frame.add(textLabel);
            frame.setVisible(true);
        }
    }

}