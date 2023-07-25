compile:
	javac QNode.java
	javac Queue.java
	javac StringMap.java
	javac StringNode.java
	javac WordLadder.java

run:
	java WordLadder

demo:
	make compile
	make run

clean:
	rm -f *.class