import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FinalProject extends JFrame {

	private JPanel contentPane;
	protected FinalProject Notepad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalProject frame = new FinalProject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinalProject() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JButton btnNotepad = new JButton("Notepad");
		btnNotepad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnNotepad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		    });*/
		
		
		JButton btnNotepad = new JButton("Notepad");
		btnNotepad.setBounds(10, 62, 414, 23);
		contentPane.add(btnNotepad);
		btnNotepad.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            try {
	               FinalProject.main(new String[0]);   //This actionlistener takes the user input from pressing 
	            } catch (Exception e) {			   //the selected button, and open the realated app
	                e.printStackTrace();
	            }
	        }
	    });
		
		btnNotepad.setBounds(31, 73, 390, 29);
		contentPane.add(btnNotepad);
		
		JLabel lblTitle = new JLabel("Notepad Application With Clock GUI");
		lblTitle.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(6, 6, 438, 38);
		contentPane.add(lblTitle);
	}

	protected void Notepad(String[] strings) {
		// TODO Auto-generated method stub
		
	}
}
