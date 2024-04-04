compile: app/App.java
	@javac -d ../bin/ app/App.java

run: ../bin/app/App.class
	@java --class-path ../bin app.App

clean:
	@rm -r classes/*

test: junit-platform-console-standalone-1.9.3.jar
	@javac -d classes -cp junit-platform-console-standalone-1.9.3.jar:app:. tests/TestData.java
	@java -jar junit-platform-console-standalone-1.9.3.jar --class-path classes --select-class tests.TestData
