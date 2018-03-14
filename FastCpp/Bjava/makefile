JC		=	javac
JVM		=	java
MAIN    =   Main
SIZE	= 	7516192768

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = A.java B.java Main.java

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) -Xmx$(SIZE) $(MAIN)

clean:
	$(RM) *.class