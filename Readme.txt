The project consists of four Java files: G_CourseClient.java, G_CourseServer.java, G_CourseServant.java, G_CourseGInterface.java. and other text files: Cources.txt, CourseDetails.txt, CoursesOffered.txt, Timmings.txt, and Users.txt.

In command prompt, we need to go to the path where the files are located using cd command.

We need to compile G_CourseInterface.java as follows.
Compiling G_CourseInterface.java
>javac G_CourseInterface.java

We need to compile G_CourseServant.java as follows.
Compiling G_CourseServant.java
>javac G_CourseServant.java
The G_CourseServant implements remote object. So, it should be compiled with rmic. This is to generate G_CourseServant_Stub.class file. We need to copy this stub file to client side.
>rmic G_CourseServant

Compiling G_CourseServer.java as follows.
>javac G_CourseServer.java

Compiling G_CourseClient.java as follows.
>javac G_CourseClient.java

We need to run the rmiregistry program as follows in the command prompt.
>start rmiregistry

We need to run the server program as follows.
>java G_CourseServer

We need to run the client program as follows.
>java G_CourseClient

The client needs to enter username. The user can be existing user in the Users.txt file or new user and they can enroll or drop courses accordingly. The courses that are offered will be stored in CoursesOffered.txt file. Once the capacity of course reaches 5 the object will be rebind in the rmiregistry and his course will be added to courses offered file.
