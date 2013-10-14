Renderscripting
===
What is this?
---
Just a project for myself (but others may be interested as well) to learn some Renderscript for Android. This is intended to be a demo app in order to see what can be done with RS.

What does it do?
---
For now, the demo app shows different options in the overflow menu that and allows you to:

* Apply a grayscale filter to the current image. This is done with a RS script (see [rs/grayscale.rs](https://github.com/xrigau/renderscripting/tree/master/app/src/main/rs/grayscale.rs)), using it from the Java code (see [GrayscaleEffect.java](https://github.com/xrigau/renderscripting/tree/master/app/src/main/java/com/xrigau/renderscripting/effects/GrayscaleEffect.java)).
* Blur the current image. This effect is an already built-in RS Intrinsic, so we don't need to create a script for this, we can do everything from Java (see [BlurEffect.java](https://github.com/xrigau/renderscripting/tree/master/app/src/main/java/com/xrigau/renderscripting/effects/BlurEffect.java)).
* Display the next image.
* Reset the current image to its original state.

Goals
---
```java
// TODO
```

Author
---
Xavi Rigau · [@xrigau](http://twitter.com/xrigau) · [+Xavi Rigau](http://gplus.to/xrigau)