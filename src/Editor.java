import javax.swing.*;

import java.io.File;
import java.util.ArrayList;

public class Editor extends JTabbedPane {
    private ArrayList<JScrollPane> tabList = new ArrayList<JScrollPane>();
    private ArrayList<JEditorPane> editList = new ArrayList<JEditorPane>();
    private ArrayList<String> lastSaveDirectory = new ArrayList<String>();

    public Editor() {
        super();
    }

    public void newTab(String title) {
    	this.editList.add(new JEditorPane());
        this.tabList.add(new JScrollPane(this.editList.get(this.editList.size()-1)));
        this.add(title, tabList.get(tabList.size() - 1));
        this.setSelectedIndex(this.tabList.size() - 1);
        this.lastSaveDirectory.add(System.getProperty("user.home")+ File.separator + this.getActiveTabTitle());
        //System.out.println(getLastSaveDirectory(this.tabList.size() - 1));
    }
    
    public JEditorPane getTab(int index) {
        return this.editList.get(index);
    }
    
    public JEditorPane getLastTab() {
        return this.editList.get(this.tabList.size() - 1);
    }
    
    public int getIndexOfLastTab() {
        return this.tabList.size() - 1;
    }
    
    public String getActiveTabTitle(){
		return this.getTitleAt(this.getSelectedIndex());
    }
    public JEditorPane getActiveTab(){
		return this.editList.get(this.getSelectedIndex());
    }

	public String getLastSaveDirectory(int tabIndex) {
		return lastSaveDirectory.get(tabIndex);
	}

	public void setLastSaveDirectory(String lastSaveDirectory,int tabIndex) {
		this.lastSaveDirectory.set(tabIndex, lastSaveDirectory);
	}
}
