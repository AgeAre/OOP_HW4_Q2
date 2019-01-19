package q2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 * Abstraction Function:
 * FontButtonListener listens on a button and notifies the chatBoxes
 * of the newly requested font when pressed
 * 
 * name - the name of the button. this indicates of the font that is required
 * observers - the list of chatboxes that this button should notify
 * button - the button this class listens on.
 * 
 * Representation Invariant:
 * all fields != null
 * name is either "Default","Bold" or "David"
 */
public class FontButtonListener implements Observable,ActionListener{
	private String name;
	private String userName;
	private List<Observer> observers;
	private JButton button;
	
	/** 
	 * returns a new FontButtonListener
	 * @return a new FontButtonListener 
	 */
	public FontButtonListener(String name,String userName,JButton button,List<Observer> observers){
		this.name = name;
		this.userName = userName;
		this.observers = new ArrayList<>(observers);
		this.button = button;
		this.button.addActionListener(this);
		checkRep();
	}
	
	/**
	 * @return the name of the button listener
	 */
	public String getName(){
		checkRep();
		return this.name;
	}
	
	/**
	 * @return the user name of the button listener
	 */
	public String getUserName(){
		checkRep();
		return this.userName;
	}

	/**
	 * will notify all the observers of the font button that was pressed
	 */
	@Override
	public void notifyObservers() {
		checkRep();
		for(Observer o : this.observers){
			o.update(this);
		}
		checkRep();
	}

	/**
	 * will add a new observer to the observers' list
	 * @param o - the observer to add
	 */
	@Override
	public void attach(Observer o) {
		checkRep();
		if(!this.observers.contains(o)){
			this.observers.add(o);
		}
		checkRep();
	}

	/**
	 * will remove an observer from the observers' list
	 * @param o - the observer to remove
	 */
	@Override
	public void detach(Observer o) {
		checkRep();
		if(this.observers.contains(o)){
			this.observers.remove(o);
		}
		checkRep();
	}

	/**
	 * The action handler that handles pressing event on the button we listen on
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		checkRep();
		// the font button was pressed
		// lets notify all the observers
		this.notifyObservers();
		checkRep();
	}
	
	private void checkRep(){
		assert this.name != null && this.observers != null && this.button != null;
		assert this.name.equals("Default") || this.name.equals("Bold") || this.name.equals("David");
	}
}
