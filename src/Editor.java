import javax.swing.*;
import java.awt.*;
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
        TextLineNumber tln = new TextLineNumber(this.editList.get(this.editList.size() - 1));
        tln.setCurrentLineForeground(new Color(0, 0, 255));
        tln.setMinimumDisplayDigits(2);
        this.tabList.add(new JScrollPane(this.editList.get(this.editList.size() - 1)));
        this.tabList.get(this.tabList.size() - 1).setRowHeaderView(tln);
        this.add(title, tabList.get(tabList.size() - 1));
        this.setSelectedIndex(this.tabList.size() - 1);
        this.lastSaveDirectory.add(System.getProperty("user.home")+ File.separator + this.getActiveTabTitle());
        //System.out.println(getLastSaveDirectory(this.tabList.size() - 1));
    }
    
    public JEditorPane getTab(int index) {
        return this.editList.get(index);
    }
    public JEditorPane getTab(String title) {
    	for(int i = 0; i < this.editList.size(); i++)
    	{
    		if(title.equals(this.getTitleAt(i)));
    		{
    			return this.editList.get(i);
    		}
    	}
    	return null;
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
	public void removeTab(int tabIndex) {
		if(!editList.isEmpty())
		{
			this.editList.remove(tabIndex);
			this.tabList.remove(tabIndex);
			this.removeTabAt(tabIndex);
		}
		if(!editList.isEmpty() && tabIndex > 0)
		{
			this.setSelectedIndex(tabIndex - 1);
		}
		else if(!editList.isEmpty() && tabIndex == 0)
		{
			this.setSelectedIndex(0);
		}
	}
	public void removeTab(String title) {
		for(int i = 0; i < this.editList.size(); i++)
    	{
    		if(title.equals(this.getTitleAt(i)));
    		{
    			this.editList.remove(i);
    			this.tabList.remove(i);
    			this.removeTabAt(i);
    			break;
    		}
    	}
	}
}
