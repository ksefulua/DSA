all: exc

compile: 
	javac -cp '.:log4j-1.2.17.jar:perf4j-0.9.16.jar' Input*.java MWMS.java Main.java Output*.java EMWMS.java SpeedTest.java 

exc: compile
    mkdir testFiles
    mkdir storage
    mkdir output
	rm -f testFiles/*
	java -cp '.:log4j-1.2.17.jar:perf4j-0.9.16.jar' Main


clean:
	rm -f *.class
