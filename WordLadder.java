import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordLadder {
	private static String start;
	private static String end;
	private static StringMap T; // This map stores the dictionary of words.
	private static StringMap R; // This map keeps track of all the words 
                                // that are visited during breadth-first-search.
	// The key field is the word that is visited, 
    // and its value field can hold the predecessor pointer.
	private static Queue Q;		// A queue to perform the breadth-first-search.
	private static QNode startNode;

	public static boolean bfs(QNode start) {
		R = new StringMap();
		Q = new Queue();

 		Q.enqueue(start);
 		start.setDist(0);
		// mark start node as visited
		R.insert(start.getWord(), null);
		
		// visit all nodes
		QNode state = Q.dequeue();
		while(state != null) {
			// visit all of node's neighbors

			String oldword = state.getWord();
			String newword;
			char[] wordarr;

			// for each char in array
 			for(int c = 0; c < 4; c++) {

				// convert oldword into array of chars
				wordarr = new char[4];
				for(int ch = 0; ch < 4; ch++) {
					wordarr[ch] = oldword.charAt(ch);
				}

				// for each letter in alphabet
				for(int a = 97; a < 123; a++) {

					// change character at index c to letter a
					wordarr[c] = (char)a;

					// convert wordarr back to string
					newword = new String(wordarr);

					// if not yet visited, and word is valid,
					// then this is a neighboring node
 					if (R.find(newword) == null && T.find(newword) != null) {
						QNode neighbor = new QNode(state.getDist() + 1, newword);

						// mark word as visited, set predecessor
						R.insert(newword, oldword);

						// add new neighbor to queue
 						Q.enqueue(neighbor);
						
						// if newword is endword, return true
						if (newword.equals(end)) {
							return true;
						}
					}
				}
			}
			// get next node
			state = Q.dequeue();
		}
		return false;
	}

	public static void print(String word) {
		if (word != start) {
			StringNode predSN = R.find(word);
			String pred = predSN.getValue();

			print(pred);
			System.out.println(word);
		}
		else {
			System.out.println(word);
			return;
		}
	}

	public static void main(String [] args) throws IOException {
		// Loading the dictionary of words into the StringMap T.
		T = new StringMap();
		File file = new File("dictionary4");
		Scanner f = new Scanner(file);
		while (f.hasNext()) {
			String word = f.nextLine();
			T.insert(word, "");
		}
		f.close();

		Scanner kb = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		start = kb.nextLine();
		System.out.print("Enter the end word: ");
		end = kb.nextLine();
		kb.close();

		startNode = new QNode(0, start);

		if (bfs(startNode)) {
			System.out.println("\nYay! A word ladder is possible.");
			print(end);
			System.out.println();
		}
		else {
			System.out.println("\nDuh! Impossible.\n");
		}
	}
}
