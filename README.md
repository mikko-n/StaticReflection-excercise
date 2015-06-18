# StaticReflection-excercise
Static field behaviour demo with small reflection excercise.

##What?
Small demo program to highlight the differences between static and non-static
field behavior in not-so-obvious situation where the field is private
to a class.
 
##Why? 
Static fields are visible per classloader and thus, not related to a particular 
class instance at all. In other words, you can use the static field in 
class A without ever creating any instances of it.
 
When class itself is not static and we are dealing with private static/non-static
fields, there is a catch: static fields inside a (non-static) class are still
shared between all instances of given class, when they are loaded with 
the same JVM classloader.

In certain situations this might give some headaches, especially when classes
with private static fields get re-used.
 
Another reason to write this was to refresh my java reflection superpowers.
 
##tl;dr; 
Static fields are per class, not per class instance
