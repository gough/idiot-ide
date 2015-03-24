import javax.swing.*;

import java.util.ArrayList;

public class Editor extends JTabbedPane {
    private ArrayList<JScrollPane> tabList = new ArrayList<JScrollPane>();
    private ArrayList<JEditorPane> editList = new ArrayList<JEditorPane>();

    public Editor() {
        super();
    }

    public void newTab(String title) {
    	this.editList.add(new JEditorPane());
        this.tabList.add(new JScrollPane(this.editList.get(this.editList.size()-1)));
        this.add(title, tabList.get(tabList.size() - 1));
        this.setSelectedIndex(this.tabList.size() - 1);
    }
    
    public JEditorPane getTab(int index) {
        return this.editList.get(index);
    }
    
    public JEditorPane getLastTab() {
        return this.editList.get(this.tabList.size() - 1);
    }
    
    public String getActiveTabTitle(){
		return this.getTitleAt(this.getSelectedIndex());
    }
    public JEditorPane getActiveTab(){
		return this.editList.get(this.getSelectedIndex());
    }
}
