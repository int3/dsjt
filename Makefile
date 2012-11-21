all:
	javac -cp asm-4.1.jar *.java
	jar cvfm Tracer.jar MANIFEST.MF *.class
