import javax.swing.*;

public class EditorTab extends JScrollPane {
    private static JEditorPane editorPane = new JEditorPane();
    
    public EditorTab() {
        super(editorPane);
    }
    
    public void setText(String text) {
        this.editorPane.setText(text);
    }
}
