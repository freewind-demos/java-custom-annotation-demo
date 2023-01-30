Java Custom Annotation Demo
=====================

在Java中可以定义一个annotation来标注在class/method/parameter上面，提供一些信息供代码在运行时获取。

但是只是仅仅如此，它们就像是注释一样，只是提供了信息，但没有提供标准快捷的方法（像js那样）定义处理逻辑。

所以我们需要自己写代码去找到需要处理的类或方法，拿到其annotation中的信息，自己处理。

这样做的问题在于：如果我们的代码是由框架调用，而不是我们自己调用，则我们可能没有机会插入自己的处理逻辑。
所以我们必须要依赖框架提供的相应的接口，让我们把处理逻辑提交上去，让框架调用。
所以我们需要研究框架是否支持。如果没有的话，那就几乎没办法做了。

说是“几乎”，是因为因为一定要做，我们也许需要使用一些修改字节码的库，在编译期修改字节码，插入逻辑。这样就超复杂了。

好在一个框架如果自己提供了annotation，它基本上同时也会提供接口让用户自定义。

如何运行，提供了三种方法，可以直接使用:

1. Run `Hello.java` in your IDE.
2. Use maven plugin `exec-maven-plugin`:
```
mvn clean compile exec:java
```
3. Use maven plugin `maven-dependency-plugin` and `maven-jar-plugin`
   ```
   mvn clean package; java -jar target/demo.jar
   ```
