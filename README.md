# ArcAnimator
ArcAnimator helps to create arc transition animation: 14+

<img width="270" height="480" src="http://i.imgur.com/DSiQxbg.gif" />|<img width="270" height="480" src="http://i.imgur.com/aLcLrGk.gif" />

ArcAnimator Demo   |   TransitionLoop Demo*

*TransitionLoop Prototype  by Min-Sang Choi <a href="https://dribbble.com/shots/1828095-Chaining-Animation-with-framerjs">Dribbble</a>

- <a href="https://www.youtube.com/watch?v=ePvGyL-_0DA" target="_blank">YouTube Video for #1 gif</a>
- <a href="https://www.youtube.com/watch?v=jMNkmxqG0zQ" target="_blank">YouTube Video for #2 gif</a>

Usage
===
Add the library to your project:
```groovy
  compile 'com.github.asyl.animation:arcanimator:1.0.0'
```

Then use it in your project code:
```java
  //set up clipView and coordinates where clipView will move
  ArcAnimator.createArcAnimator(clipView, endX, endY, DEGREE, SIDE)
                    .setDuration(500)
                    .start();
  
  //or specify nestView for clipView. Animator will take center x,y coordinates of nestView
  ArcAnimator.createArcAnimator(clipView, nestView, DEGREE, SIDE)
                    .setDuration(500)
                    .start();
```

Download
===

[![JitPack](https://img.shields.io/github/tag/asyl/ArcAnimator.svg?label=maven)](https://jitpack.io/#asyl/ArcAnimator)

To include this library in your project add to your build.gradle:

```gradle
	repositories {
	    maven { url "https://jitpack.io" }
	}
	
	dependencies {
	    compile 'com.github.asyl:ArcAnimator:v1.0.0'
	}
```

Dependency
===
- Ozodrukh's <a href="https://github.com/ozodrukh/CircularReveal">CircularReveal</a> helped to create awesome TransitionLoop Demo

Thanks
===
I want to thank <a href="https://github.com/SherzodAgzamov">Sherzod Agzamov</a> for helping to solve some Geometry problems & <a href="https://github.com/ozodrukh">Ozodrukh</a> for support and tips that help to make this library.

License
--------

    The MIT License (MIT)

    Copyright (c) 2015 Asylbek Isakov
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
