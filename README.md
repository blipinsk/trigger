Trigger
=======

Simple command pattern implementation.

Let's be honest... that's just a simple class, but it can sometimes tidy up your code quite a bit. It uses java generics and intuitive class naming, so you can easily create a method reference or just a callback, just like you wanted.

Usage
=====
*For a working implementation of this library see the `sample/` folder.*

  1. Create a `Trigger` object (one of 4 types: `Trigger.NoParam.NoResult`, `Trigger.NoParam.WithResult`, `Trigger.WithParam.NoResult`or `Trigger.WithParam.WithResult`)
  
  ```java
  final Trigger.WithParam.WithResult<Boolean, String> trigger =
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

Developed by
============
 * Bartosz Lipiński

License
=======

    Copyright 2016 Bartosz Lipiński
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
