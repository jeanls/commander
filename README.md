# Commander

Command pattern applied for building code in blocks.

<p align="center">
  <img src="assets/military.png" style="width: 250px;display: block;margin-left: auto;margin-right: auto" alt="commander">
</p>


## 1. Getting started.

``pom.xml``

````xml

<dependency>
    <groupId>io.github.jeanls</groupId>
    <artifactId>commander</artifactId>
    <version>1.0.2</version>
</dependency>
````

``build.gradle``

````groovy
implementation 'io.github.jeanls:commander:1.0.2'
````

## 2. Using non typed commander.


``Executor`` help to build a group of code blocks.

````java
public class NumberExecutor extends Executor {

    @Override
    public void exec(final Context context) {
        SumCommand sumCommand = new SumCommand();
        MultiplyCommand multiplyCommand = new MultiplyCommand();

        start(context)
                .add(sumCommand)
                .add(multiplyCommand)
                .run();
    }
}
````

Example of ``SumCommand``.

````java
public class SumCommand implements Commander {

    @Override
    public void doProcess(final Context context) {
        final Integer numberA = context.get("numberA", Integer.class);
        final Integer numberB = context.get("numberB", Integer.class);
        context.put("sum", numberA + numberB);
    }

    @Override
    public boolean canProcess(Context context) {
        return true;
    }
}
`````

Example of ``MultiplyCommand``.

````java
public class MultiplyCommand implements Commander {

    @Override
    public void doProcess(final Context context) {
        Integer result = context.get("sum", Integer.class);
        context.put("result", result * 10);
    }

    @Override
    public boolean canProcess(Context context) {
        return true;
    }
}
````

Running ``NumberExecutor``.

````java
    @Test
    void successTest() {
        NumberExecutor numberExecutor = new NumberExecutor();
        final Context context = new Context();
        context.put("numberA", 10);
        context.put("numberB", 20);
        numberExecutor.exec(context);

        assertEquals(300, context.get("result", Integer.class));
    }
````

## 3. Using typed commander.

``ExecutorTyped<T>`` help to build a group of code blocks.

````java
public class NumberExecutorTyped extends ExecutorTyped<Integer> {

    @Override
    public Integer exec(Integer initalValue) {
        SumCommandTyped sumCommandTyped = new SumCommandTyped();
        MultiplyCommandTyped multiplyCommandTyped = new MultiplyCommandTyped();

        return start(initalValue)
                .add(sumCommandTyped)
                .add(multiplyCommandTyped)
                .run();
    }
}
````

Example of ``SumCommandTyped``.

````java
public class SumCommandTyped implements CommanderTyped<Integer> {

    @Override
    public Integer doProcess(Integer input) {
        return input + 10;
    }

    @Override
    public boolean canProcess(Integer input) {
        return true;
    }
}
````

Example of ``MultiplyCommandTyped``.

````java
public class MultiplyCommandTyped implements CommanderTyped<Integer> {

    @Override
    public Integer doProcess(Integer input) {
        return input * 100;
    }

    @Override
    public boolean canProcess(Integer input) {
        return true;
    }
}
````

Running ``NumberExecutorTyped``.

````java
    @Test
    void successTest() {
        NumberExecutorTyped numberExecutorTyped = new NumberExecutorTyped();
        Integer result = numberExecutorTyped.exec(0);
        assertEquals(1000, result);
    }
````
