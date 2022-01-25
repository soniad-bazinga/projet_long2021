JCC = javac
JFLAGS = -g

default: Writer.class Utilities.class Test.class

Writer.class: Writer.java
		$(JCC) $(JFLAGS) Writer.java

Utilities.class: Utilities.java
		$(JCC) $(JFLAGS) Utilities.java

Test.class: Test.java
		$(JCC) $(JFLAGS) Test.java

clean: 
		$(RM) *.class
