JCC = javac
JFLAGS = -g

default: Exception Writer.class Convert.class Utilities.class Test.class Main.class 
		java Main

Exception : RangeNoteNull.class PatternNotExhaustive.class

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

RangeNoteNull.class: CustomExc.java
		$(JCC) $(JFLAGS) CustomExc.java

PatternNotExhaustive.class: CustomExc.java
		$(JCC) $(JFLAGS) CustomExc.java

clean: 
		$(RM) *.class
