compile: app/App.java app/Controller.java app/Flight.java app/Booking.java app/Data.java
	@javac -d classes/ app/App.java

run: classes/app/App.class
	@java --class-path classes app.App

clean:
	@rm -r classes/*

test: junit-platform-console-standalone-1.9.3.jar
	@javac -d classes -cp junit-platform-console-standalone-1.9.3.jar:app:. tests/*.java
	@java -jar junit-platform-console-standalone-1.9.3.jar --class-path classes --select-class tests.TestData
