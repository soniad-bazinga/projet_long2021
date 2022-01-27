JCC = javac
JFLAGS = -g

default: Writer.class Convert.class Utilities.class Test.class Main.class
		java Main

Writer.class: Writer.java
		$(JCC) $(JFLAGS) Writer.java

Convert.class: Convert.java
		$(JCC) $(JFLAGS) Convert.java

Utilities.class: Utilities.java
		$(JCC) $(JFLAGS) Utilities.java

Test.class: Test.java
		$(JCC) $(JFLAGS) Test.java

Main.class: Main.java
		$(JCC) $(JFLAGS) Main.java

clean: 
		$(RM) *.class
