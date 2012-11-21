# Dead Simple Java Tracer

Prints a text stream of the non-natived called methods to standard output.

I hacked this together because most of the Java method tracers that I found
online wouldn't build for me (on OS X) or wouldn't work with the latest versions
of Java.

Largely based off the code in [this PDF.][1]

## Usage

    make
    # ensure asm-4.1.jar is in the same directory as Tracer.jar
    java -javaagent:Tracer.jar Foo

[1]:http://www.cs.helsinki.fi/u/pohjalai/k05/okk/seminar/Aarniala-instrumenting.pdf