import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import javax.swing.JOptionPane;

public class GUI implements ActionListener {
    public GUI(String title, int width, int height) {
        initGUI(title);
    }
    
    public void initGUI(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.getInputMap().put(KeyStroke.getKeyStroke("F2"), "new");
        //panel.getActionMap().put(this, "new");

        JEditorPane filePane = new JEditorPane();
        filePane.setBorder(new TitledBorder("Files"));
        filePane.setPreferredSize(new Dimension(200, 0));

        JEditorPane editorPane = new JEditorPane(); // multiple files?
        JEditorPane consolePane = new JEditorPane();
        JEditorPane errorPane = new JEditorPane();

        JScrollPane fileScrollPane = new JScrollPane(filePane);
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        JScrollPane consoleScrollPane = new JScrollPane(consolePane);
        JScrollPane errorScrollPane = new JScrollPane(errorPane);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Console", consoleScrollPane);
        tabbedPane.add("Errors", errorScrollPane);
        tabbedPane.setPreferredSize(new Dimension(0, 200));

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane2.add(editorScrollPane);
        splitPane2.add(tabbedPane);
        splitPane2.setOneTouchExpandable(true);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane1.add(fileScrollPane);
        splitPane1.add(splitPane2);
        splitPane1.setOneTouchExpandable(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newMenuItem.setActionCommand("new");
        newMenuItem.addActionListener(this);
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        JMenuItem printMenuItem = new JMenuItem("Print");
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(quitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(selectAllMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
        viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        JMenuItem aboutMenuItem = new JMenuItem("About my IDE");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(aboutMenuItem);

        JToolBar toolBar = new JToolBar("Tool Bar");

        JButton newToolBarButton = new JButton();
        newToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_add.png"));

        JButton saveToolBarButton = new JButton();
        saveToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_save.png"));

        JButton cutToolBarButton = new JButton();
        cutToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/cut.png"));

        JButton copyToolBarButton = new JButton();
        copyToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_copy.png"));

        JButton pasteToolBarButton = new JButton();
        pasteToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_paste.png"));

        JButton printToolBarButton = new JButton();
        printToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/printer.png"));

        JButton runToolBarButton = new JButton();
        runToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/resultset_next.png"));

        JButton helpToolBarButton = new JButton();
        helpToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/help.png"));

        JButton closeToolBarButton = new JButton();
        closeToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/cancel.png"));

        toolBar.add(newToolBarButton);
        toolBar.add(saveToolBarButton);
        toolBar.add(cutToolBarButton);
        toolBar.add(copyToolBarButton);
        toolBar.add(pasteToolBarButton);
        toolBar.add(printToolBarButton);
        toolBar.add(runToolBarButton);
        toolBar.add(helpToolBarButton);
        toolBar.add(closeToolBarButton);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        panel.add(splitPane1);
        panel.setSize(new Dimension(800, 600));
        frame.add(panel);

        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH);

        frame.setMinimumSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("new")) {
            JOptionPane.showMessageDialog(null, "test");
            String action2 = e.getActionCommand();
        }
    }
}

