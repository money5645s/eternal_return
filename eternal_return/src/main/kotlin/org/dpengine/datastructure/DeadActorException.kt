package org.dpengine.datastructure

class DeadActorException(val classString : String) : RuntimeException("You just did deal with dead actor(referenceCount == 0) on$classString") {
}