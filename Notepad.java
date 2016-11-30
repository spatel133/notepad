package notepadtest;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;    //imported to include basic function for text edits
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.awt.AWTKeyStroke;


public class Notepad extends JFrame implements ActionListener {
    private static JTextArea textArea = new JTextArea();
    private JMenuBar JMenuBar = new JMenuBar();
    private JMenu file = new JMenu(); 
    private JMenu edit = new JMenu();
    private JMenu shortcut = new JMenu();
    private JMenu format = new JMenu();
    private String Deletevalue = new String ("");
    
    
    //UNDO/REDO//
    private JMenuItem redo = new JMenuItem();
    private JMenuItem undo = new JMenuItem();
    private UndoManager manager;
   
    //file items, new,open,save,close,cut,copy,paste,setbackround,fontformat//
    private JMenuItem newFile = new JMenuItem();
    private JMenuItem openFile = new JMenuItem();
    private JMenuItem saveFile = new JMenuItem();
    private JMenuItem closeFile = new JMenuItem();
    private JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());     //These constructors set up not only a menu item
    private JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());	//but they include the proper function for cut, copy
    private JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());//and paste allowing them to have no methods
    private JMenuItem setBackground = new JMenuItem();
    private JMenuItem fontformat = new JMenuItem();
    
    //Select All and delete buttons added to the notepad//
    private JMenuItem SelectAll = new JMenuItem();
    private JMenuItem Delete = new JMenuItem();  

    //dimentions of the notepad//
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    public static final String TITLE = "Montclair Notepad"; //what application is called on the top bar//
    


    public Notepad() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        
        //Undomanger Is used to be call for the Undo Method. 
        //UndoEditListener helps give the command to action perform
        manager= new UndoManager();
        textArea.getDocument().addUndoableEditListener(manager);
        this.getContentPane().setLayout(new BorderLayout()); 
        
        
        
        
        this.getContentPane().add(textArea);
        //file bar//
        this.setJMenuBar(this.JMenuBar);
        this.JMenuBar.add(this.file);
        //menu bar//
        this.setJMenuBar(this.JMenuBar);
        this.JMenuBar.add(this.shortcut);
        //EDIT BAR//
        this.setJMenuBar(this.JMenuBar);
        this.JMenuBar.add(this.edit);
        //FORMAT BAR//
        this.setJMenuBar(this.JMenuBar);
        this.JMenuBar.add(this.format);
        //BARS//
        this.edit.setText("Edit");
        this.shortcut.setText("Shortcuts");
        this.file.setText("File");
        this.format.setText("Format");
        //NEW//
        //This sets the button up along with incorporating a button shortcut with the event
        this.newFile.setText("New");
        this.newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.newFile.addActionListener(this);
        this.newFile.setMnemonic(KeyEvent.VK_N);
        this.file.add(this.newFile);
        //OPEN//
        //This sets the button up along with incorporating a button shortcut with the event
        this.openFile.setText("Open");
        this.openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        this.openFile.addActionListener(this);
        this.openFile.setMnemonic(KeyEvent.VK_O); 
        this.file.add(this.openFile); 
        //SAVE//
        //This sets the button up along with incorporating a button shortcut with the event
        this.saveFile.setText("Save");
        this.saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.saveFile.addActionListener(this);
        this.saveFile.setMnemonic(KeyEvent.VK_S);
        this.file.add(this.saveFile);
        //CLOSE//
        //This sets the button up along with incorporating a button shortcut with the event
        this.closeFile.setText("Close");
        this.closeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        this.closeFile.addActionListener(this);
        this.closeFile.setMnemonic(KeyEvent.VK_Q);
        this.file.add(this.closeFile);
        //CUT//
        //This sets the button up along with incorporating a button shortcut with the event
        this.cut.setText("Cut");
        this.cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.cut.addActionListener(this);
        this.cut.setMnemonic(KeyEvent.VK_X);
        this.shortcut.add(this.cut);
        //COPY//
        //This sets the button up along with incorporating a button shortcut with the event
        this.copy.setText("Copy");
        this.copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        this.copy.addActionListener(this);
        this.copy.setMnemonic(KeyEvent.VK_C);
        this.shortcut.add(this.copy);
        //PASTE//
        //This sets the button up along with incorporating a button shortcut with the event
        this.paste.setText("Paste");
        this.paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        this.paste.addActionListener(this);
        this.paste.setMnemonic(KeyEvent.VK_V);
        this.shortcut.add(this.paste);
        //REDO//
        //This sets the button up along with incorporating a button shortcut with the event
        this.redo.setText("Redo");
        this.redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        this.redo.addActionListener(this);
        this.redo.setMnemonic(KeyEvent.VK_R);
        this.edit.add(this.redo);
        //UNDO//
        //This sets the button up along with incorporating a button shortcut with the event
        this.undo.setText("Undo");
        this.undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        this.undo.addActionListener(this);
        this.undo.setMnemonic(KeyEvent.VK_Z);
        this.edit.add(this.undo);
        //Select All//
        //This sets the button up along with incorporating a button shortcut with the event
        this.SelectAll.setText("Select All");
        this.SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        this.SelectAll.addActionListener(this);
        this.SelectAll.setMnemonic(KeyEvent.VK_A);
        this.edit.add(this.SelectAll);
        //Delete//
        //This sets the button up along with incorporating a button shortcut with the event
        this.Delete.setText("Delete");
        this.Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
        this.Delete.addActionListener(this);
        this.Delete.setMnemonic(KeyEvent.VK_DELETE);
        this.edit.add(this.Delete);
        //BACKGROUD//
        //This sets the button up along with incorporating a button shortcut with the event
        this.setBackground.setText("Set Background");
        this.setBackground.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        this.setBackground.addActionListener(this);
        this.setBackground.setMnemonic(KeyEvent.VK_B);
        this.format.add(this.setBackground);
        //FONT//
        //This sets the button up along with incorporating a button shortcut with the event
        this.fontformat.setText("Font Formatting");
        this.fontformat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        this.fontformat.addActionListener(this);
        this.fontformat.setMnemonic(KeyEvent.VK_F);
        this.format.add(this.fontformat);
        }

       public void actionPerformed(ActionEvent e) {
    	//Select All Method is calling text area to focus on the window and select the document.
    	textArea.requestFocusInWindow();
        textArea.selectAll();
        if (e.getSource() == this.openFile) {
        JFileChooser open = new JFileChooser(); 
        int option = open.showOpenDialog(this); 
        	if (option == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText(""); 
                try { Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext())
                        this.textArea.append(scan.nextLine() + "\n");}
               catch (Exception ex) { 
                   System.out.println(ex.getMessage());
                }
            }
        }
        //close file//
        else if (e.getSource() == this.closeFile){
                this.dispose();
        }
        //Save file//
        else if (e.getSource() == this.saveFile) {
        JFileChooser save = new JFileChooser(); 
        int option = save.showSaveDialog(this); 
        if (option == JFileChooser.APPROVE_OPTION) {
        	// create a buffered writer to write to a file//

        	try { BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                  out.write(this.textArea.getText());
                  out.close(); } //close file //
            catch (Exception ex) { 
                    System.out.println(ex.getMessage());
                }
            }
        }
    	///Undo Method this is the method to call undo manager and do the action performed
        else if (e.getSource() == this.undo) {
        	try {
                manager.undo();
              } catch (Exception exc) {
                Toolkit.getDefaultToolkit().beep();
              }
        }
    	///Redo Method this is the method to call redo manager and do the action performed
        else if (e.getSource() == this.redo) {
        	try {
            manager.redo();
          } catch (Exception exc) {
            Toolkit.getDefaultToolkit().beep();
          }
    }
    	///Delete Method calls the value field and updates the text area to blank
        else if (e.getSource()== this.Delete) {
        	textArea.setText(Deletevalue);
        }
        
        //set backgroud method//
        else if (e.getSource() == this.setBackground) {
        	//JFrame f = new JFrame ("Display Color");
    		//f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    		JPanel cp = new JPanel();
    		cp.setPreferredSize(new Dimension (350, 150));
    		Color s = Color.white;		//must use jcolorchooser with color 's' object to change background
    		s = JColorChooser.showDialog(cp, "Choose Your Color", s);
    		textArea.setBackground(s);
        }
			 
        //font format method//	
        else if (e.getSource() == this.fontformat) {		// obtain new font from font format
        	FontFormat ff = new FontFormat(this);
           	this.textArea.setFont(ff.getNewFont());
        }
    	//new file method//
        else if (e.getSource() == this.newFile){
        JOptionPane jp = new JOptionPane();
        Object[] options = { "Save", "No", "Cancel" }; // options for new file opener//
        //verification of openeing a new file//
        int button = jp.showOptionDialog(null, "Do you want to save the changes to your Notepad",  null, 
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (button == 0) {
        	// file opener//
        JFileChooser newFile = new JFileChooser(); 
        int option = newFile.showSaveDialog(this);
        	if (option == JFileChooser.APPROVE_OPTION) {
                try { BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getSelectedFile().getPath()));
                      out.write(this.textArea.getText());
                      out.close();}
                catch (Exception ex) {
                      System.out.println(ex.getMessage());
                     }
                 }
             } else if (button == 1) {
                 textArea.setText(null);
             } else if (button == 2) {
  
             }
          }
      }
      
   
    public static void main(String args[]) {
        Notepad app = new Notepad();
        app.setVisible(true);
    }
}