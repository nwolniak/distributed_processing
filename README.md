# distributed_processing
#### [AGH-LAB] Distributed processing class homework using gRPC and Ice.

For each technology there were Client implemented in Java and Server implemented in Python. <br>

### gRPC aka chat gRPC
- Clients can join chat group and chat with each other.
- Client sends messages which may contain text or image.
- Server is responsible for chat groups member logic.
- Server sends messages to relevant group members.

### Ice (invocations examples)
- Clients can invoke methods like calculate statistics on doubles sequence, group by author on books sequence and convert image to grayscale.
- Server is responsible for operations on data and responses.
