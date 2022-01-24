/*Radhika Banerjea
 * November 9, 2021
 * This is a user login system for a program using a GUI. 
 * The application has the hawthorn logo, a field for the username and a field for the password to tbe inputted
The user will write a username and password in the fields designated for them. 
Once the user presses “Login”, the program checks whether the user is registered in a preexisting file and 
whether the password provided is valid.
 */

//importing what is necessary
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import exceptionbasics.EmptyStringException;

public class Assignment4 {
	//variables for the text inputs for username and password
	private static Text text;
	private static Text text_1;

	public static void main(String[] args) throws FileNotFoundException {
		//variable for the file
		File handle = new File("LoginInfo.txt");

		//setting the display, the shell, and naming and sizing the shell
		Display display = Display.getDefault();
		Shell shlLoginApp = new Shell();
		shlLoginApp.setImage(SWTResourceManager.getImage("C:\\Users\\radhi\\eclipse-workspace\\Assignment 4 \u2013 File IO and GUI\\images\\site-logo.png"));
		shlLoginApp.setText("Login App");
		shlLoginApp.setSize(457, 354);
		
		//making a verrtical line down the center
		Label label = new Label(shlLoginApp, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(229, 41, 2, 224);
		
		//labels that say username and password
		Label lblUsername = new Label(shlLoginApp, SWT.NONE);
		lblUsername.setBounds(22, 41, 81, 25);
		lblUsername.setText("Username:");
		
		Label lblPassword = new Label(shlLoginApp, SWT.NONE);
		lblPassword.setBounds(22, 137, 81, 25);
		lblPassword.setText("Password:");
		
		//text boxes for the username and password
		text = new Text(shlLoginApp, SWT.BORDER);
		text.setBounds(22, 72, 187, 31);
		
		text_1 = new Text(shlLoginApp, SWT.BORDER);
		text_1.setBounds(22, 168, 187, 31);
		
		//creating login button
		Button btnLogin = new Button(shlLoginApp, SWT.NONE);
		btnLogin.setBounds(66, 230, 105, 35);
		btnLogin.setText("Login");
		
		//getting the hawthorn logo, setting it as a variable and putting it on the GUI.
		Label lblNewLabel = new Label(shlLoginApp, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("C:\\Users\\radhi\\eclipse-workspace\\Assignment 4 \u2013 File IO and GUI\\images\\site-logo.png"));
		lblNewLabel.setBounds(245, 53, 169, 183);
		
 
	
		//selection listener for the button
		
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//try and catch for errors
				try {
					//variable for the file scanner, the message box, the inputed username, and the inputed password
					Scanner inputFile = new Scanner(handle);
					String username = text.getText();
					String password = text_1.getText();
					MessageBox messageBox = new MessageBox(shlLoginApp, SWT.ICON_WARNING);
					
					//throws an exception if the username or password has nothing in it.
					if (username.equals("")||password.equals("")) {
						throw new EmptyStringException();
					}
					//boolean variable that says whether there is a next line.
					boolean statement = inputFile.hasNextLine();
					//while there is a next line
					while (statement) {
						//splits the file line that its on into an array, ignoring the ;
						String[]tokens = (inputFile.nextLine()).split(";");
						//if the first spot of the array is the username inputed, and the second spot is the password inputed, then a message box opens and the boolean variable is set to false to exit the while loop
						if (tokens[0].equals(username)&&tokens[1].equals(password)) {
							messageBox.setMessage("Successful Logging In.");
							messageBox.open();
							statement = false;
						}
						//if the first spot of the array isn't the inputed username and the second spot of the array isnt the inputed password it is the last spot of the file, then the unsuccessful message box pops up.
						else if(!(inputFile.hasNextLine())&&(!(tokens[0].equals(username)||tokens[1].equals(password)))){
								messageBox.setMessage("Unsuccessful Logging In.");
								messageBox.open();

						}
					}	
					//closing scanner
					inputFile.close();
				//if there is an error it catches the error
				}catch(EmptyStringException e1) {
					
				}
				catch(IOException e1) {
				}
				catch(Exception e1) {
				}
				
			}
		});
		
		shlLoginApp.open();
		shlLoginApp.layout();
		while (!shlLoginApp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
