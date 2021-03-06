package Q2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 * Abstraction function:
 * TextInput wraps a text field in which the user will be able to type his messages
 * which will later appear in the chat window.
 * This class wraps the text field with chat-like functionality (such as responding to enter presses)
 * and notifies the chat boxes when a new message is ready.
 *
 * String name - the name of the User (which will be added to the messages he types)
 * List<Observer> Observers - list of chats that will observe this input box for new messages from the user
 * JTextField textField - the text field that the user will use to type his messages
 * String currentMessage - the latest message that has been written by the user.
 *                         after the chat boxes will be notified, they will ask for this value to add it to the chat window.
 *
 * Representation invariant:
 * all fields != null
 */
public class TextInput implements Observable, KeyListener{
	private	String userName;
	private String currMessage;
	private JTextField textField;
	private List<Observer> Observers;

	/**
	 * @requires all params != null && name doesn't contain ':'
	 * @param userName - the name of the user
	 * @param observersToAdd - the chat boxes in the system that observe this chat
	 * @return a new TextInput object
	 */
	public TextInput(String userName, List<Observer> observersToAdd){
		this.userName = userName;
		this.textField = new JTextField();
		this.Observers = new ArrayList<>(observersToAdd);
		this.textField.addKeyListener(this);
		this.currMessage = null;
		checkRep();
	}

	/**
	 * @return the name of the user of the TextInput
	 */
	public String getName(){
		checkRep();
		return this.userName;
	}

	/**
	 * @return the text field used by the TextInput
	 */
	public JTextField getInputTextField(){
		checkRep();
		return this.textField;
	}

	/**
	 * @return the latest message entered by the user
	 */
	public String getCurrentMessage(){
		checkRep();
		return this.currMessage;
	}

	/**
	 * the handler that is called when the user finishes typing a new message with an Enter.
	 * this will get the message he wrote, clear the text field and initiate notifying the listeners
	 * of the new message
	 */
	private void handleNewMessage(){
		// get the text
		checkRep();
		this.currMessage = this.textField.getText();
		// clear the text field
		this.textField.setText("");
		// inform the observers of a new chat message
		this.notifyObservers();
		checkRep();
	}


	// KeyListener Implementation
	/**
	 * will handle the event that happen in the text field.
	 * if an enter was pressed - this will initiate the new message handling method
	 * @param event that happen in the text field
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		checkRep();
		if(event.getKeyCode() == KeyEvent.VK_ENTER){
			// the user has entered a messages and press on the Enter key. lets handle the new message
			this.handleNewMessage();
		}
		checkRep();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	// Observable Implementation
	/**
	 * this function will notify all the observers by calling their update method
	 */
	@Override
	public void notifyObservers() {
		checkRep();
		for(Observer o : this.Observers){
			o.update(this);
		}
		checkRep();
	}

	/**
	 * this method will add a new observer
	 * @param o will be added by the observer
	 */
	@Override
	public void attach(Observer o) {
		checkRep();
		this.Observers.add(o);
		checkRep();
	}


	/**
	 * this method will remove a observer
	 * @param  o will be removed by the observer
	 */
	@Override
	public void detach(Observer o) {
		checkRep();
		if(this.Observers.contains(o)){
			this.Observers.remove(o);
		}
		checkRep();
	}

	/**
	 * will check the object stands in the representation invariant we dictated
	 */
	private void checkRep(){
		assert this.userName != null && this.textField != null
				&&  this.Observers != null  && this.currMessage != null;
	}

}