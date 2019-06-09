/**
 * 
 */
package branch_practice_project.debD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Desc : 
 * @author Debu-PC_2
 * Date : 24-Sep-2018 10:28:03 PM
 */
public class MySingleton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2221157384789936828L;
	//Serialization
	//private static MySingleton myObj = new MySingleton();

	private static MySingleton myObj = new MySingleton();
	/**
	 * Create private constructor
	 * Private Constructor prevents any other class from instantiating 
	 */
	private MySingleton() {
		//Prevent reflection(prevents creation of object if an object already present)
		if (myObj != null)
			//			throw new IllegalStateException("Singleton" +" instance already created.");
			System.out.println("Hashcode :"+this.hashCode());
	}

	public static MySingleton getInstance() {
		/* Lazy initialization, creating object on first use */
		if (myObj == null)
			myObj = new MySingleton();
		return myObj;
	}

	public Runnable getSomeThing() {
		// if hashcodes are same that means Singleton class is implemented correctly
		System.out.println("the current object hashcode is..." + this.hashCode() );
		return null;
	}

	/* Prevent cloning */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return myObj;
	}

	/* Prevent Multiple object via Serialization */
	protected Object readResolve() {
		return myObj;
	}


	public static void main(final String a[]) throws CloneNotSupportedException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException, IllegalArgumentException,
	InvocationTargetException, FileNotFoundException, IOException {

		/**Singleton breaking test cases**/

		// factory method(Default)

		MySingleton st = MySingleton.getInstance();
		System.out.println("first original object");
		st.getSomeThing();

		// cloning

		MySingleton st2 = (MySingleton) st.clone();
		System.out.println(" ");
		System.out.println("Cloning");
		st2.getSomeThing();


		// serilaization/deserilization

		// serailize from file to object
		MySingleton instance1 = MySingleton.myObj;
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.txt"));
		out.writeObject(instance1);
		out.close();

		// deserailize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("file.txt"));

		MySingleton instance2 = (MySingleton) in.readObject();
		in.close();
		System.out.println(" ");
		System.out.println("serilaization/deserilization :-");
		System.out.println("instance1 object hashcode is... " + instance1.hashCode());
		System.out.println("instance2 object hashcode is..." + instance2.hashCode());

		// thread

		/** SingletonThreadBreak**/

		// reflection
		System.out.println(" ");
		System.out.println("reflection");
		MySingleton stbRef = null;
		Class<?> c = Class.forName("sopra.MySingleton");
		stbRef = (MySingleton) c.newInstance();
		stbRef.getSomeThing();

	}
}
