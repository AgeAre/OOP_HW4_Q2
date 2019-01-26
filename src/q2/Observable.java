package Q2;


public interface Observable {
	void notifyObservers();
	void attach(Observer o);
	void detach(Observer o);
}
