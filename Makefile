
all: exc
compile: 
	javac Input*.java MWMS.java Main.java Output*.java EMWMS.java SpeedTest.java

exc: compile
	java Main


clean:
	rm -f *.class
