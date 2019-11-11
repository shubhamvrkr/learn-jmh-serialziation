# Serialization Benchmarking
Project contains usage of different serialization libraries in java and learning JMH usage to microbenchmark.<br>
<b>Note:</b> Default configuration options are being used for all external serialization libraries.

### Project structure
 - <b>src/main/java:</b> contains source code for each serializer.
 - <b>src/test/java:</b> contains test cases for each serializer.
  -  <b>/models:</b> contains model object used for serialization and deserialization.
 - <b>src/jmh/java:</b> contains performance benchmarking  for each serializer.

### Steps to Execute
 - Run `git clone <repo_url>` to clone the repository on ypur local machine.
 - Run `cd <repo_folder_name> && ./gradlew jmh` task to execute the benchmarking clases.

### JMH Examples
- http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/

### Some cool notes on writing benchmarking to avoid mistakes
-  Benchmarking function not returning returned computation or not using Blackhole for consuming the computed value. This can lead to dead code elimination.<br>
<b>Example:</b>http://hg.openjdk.java.net/code-tools/jmh/file/c8f9f5b85cd9/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_08_DeadCode.java
- Using accumulation to consume values inside a loop. This can lead to loop optimization/ Loop unrolling and OSR.<br>
<b>Example:</b>
http://hg.openjdk.java.net/code-tools/jmh/file/c8f9f5b85cd9/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_34_SafeLooping.java
- Using final primitive for benchmarking input can lead to Constant folding.
- False sharing.<br><b>Example:</b> http://hg.openjdk.java.net/code-tools/jmh/file/c8f9f5b85cd9/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_22_FalseSharing.java
- JMH overhead Configuring benchmarks with zero forks can lead to profile-guided optimization. SOlution is to run benchmark with atleast 3 forks.
- Class loading: Classes are loaded during their first use. This operation involves complex operations like disk i/o, parsing and verifying classes. Run some warm up rounds before measuring.
- Caching: A simple example is file-system caching, which can take place in hardware or the OS. If you are benchmarking how long it takes to read the bytes from a file, but your benchmark code reads the same file many times (or you perform the same benchmark multiple times), then the I/O time can fall dramatically after the first read. If you want to benchmark random file reads, you likely need to ensure that different files are read to avoid caching.
<br>
<b>Always use JvmArgs such as +XX:PrintCompilation and make sure after warmup iterations JIT compiler doesnt kicks off than can mislead the result</b>

### Static Analysis Plugin for JMH to detect bad practices
- SpotJMHBugs : https://github.com/DiegoEliasCosta/spotjmhbugs

### Additional Reads
- https://stackoverflow.com/questions/504103/how-do-i-write-a-correct-micro-benchmark-in-java
- https://www.ibm.com/developerworks/java/library/j-benchmark1/j-benchmark1-pdf.pdf
- https://www.ibm.com/developerworks/library/j-jtp12214/
- More on printing JIT generated assembly :  http://psy-lob-saw.blogspot.com/2013/01/java-print-assembly.html
