Usage
=====
*For a working implementation of this library see the `sample/` folder.*

  1. Create a `Trigger` object (one of 4 types: `Trigger.NoParam.NoResult`, `Trigger.NoParam.WithResult`, `Trigger.WithParam.NoResult`or `Trigger.WithParam.WithResult`)

  ```java
  Trigger.WithParam.WithResult<Boolean, String> trigger =
          Trigger.WithParam.WithResult.create(new Trigger.WithParam.WithResult.OnTriggered<Boolean, String>() {
              @Override
              public String onTriggered(Boolean param) {
                  return executeCommandWithResult(param);
              }
          });
  ```

  2. Pull the `trigger` to execute the `onTriggered` block.

  ```java
  String result = trigger.pull(true);
  ```

Depending on which version of `Trigger` you've chosen, `pull(...)` will behave differently.
It:

 1. Will return a value for `WithResult` triggers.
 2. Won't return anything for `NoResult` triggers.
 3. Will take a parameter for `WithParam` triggers.
 4. Won't take anything for `NoParam` triggers.

Including In Your Project
-------------------------
You can grab the library via Maven Central. Just add a proper dependency inside your `build.gradle`. Like this:

```xml
dependencies {
    compile 'com.bartoszlipinski:trigger:1.0.0'
}
```
