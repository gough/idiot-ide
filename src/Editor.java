import javax.swing.*;

import java.util.ArrayList;

public class Editor extends JTabbedPane {
    private ArrayList<JEditorPane> tabList = new ArrayList<JEditorPane>();

    public Editor() {
        super();
    }

    public void newTab(String title) {
        this.tabList.add(new JEditorPane());
        this.add(title, tabList.get(tabList.size() - 1));
    }
    
    public JEditorPane getTab(int index) {
        return this.tabList.get(index);
    }
    
    public JEditorPane getLastTab() {
        return this.tabList.get(this.tabList.size() - 1);
    }
}
