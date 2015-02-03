import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Editor extends JTabbedPane {
    private ArrayList<EditorTab> tabList = new ArrayList<EditorTab>();

    public Editor() {
        super();
    }

    public void newTab(String title) {
        this.tabList.add(new EditorTab());
        this.add(title, tabList.get(tabList.size() - 1));
    }
}
