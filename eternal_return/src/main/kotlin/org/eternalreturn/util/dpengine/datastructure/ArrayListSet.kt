package org.eternalreturn.util.dpengine.datastructure


import kotlin.system.measureTimeMillis
import java.util.*

class ArrayListSet<T> {
    val arrayList = ArrayList<T>()
    val hashMap = HashMap<T, Int>()
    val size: Int get() = arrayList.size

    fun contains(obj: T) : Boolean{
        return hashMap.contains(obj);
    }

    operator fun get(idx : Int) : T{
        return arrayList[idx];
    }

    fun isEmpty() : Boolean{
        return arrayList.isEmpty();
    }

    fun add(obj : T) : Boolean{
        val isAlreadyContained = hashMap.contains(obj)
        if(!isAlreadyContained){
            val lastIdx = arrayList.size;
            arrayList.add(obj);
            hashMap.put(obj, lastIdx);
        }
        return !isAlreadyContained;
    }

    fun remove(index : Int) : Boolean{
        val size = arrayList.size;
        if(index >= size){
            return false;
        }
        val obj = arrayList[index];
        val overwriteObj = arrayList[size - 1];
        arrayList[index] = overwriteObj
        arrayList.removeLast();
        hashMap[overwriteObj] = index;
        hashMap.remove(obj);
        return true;
    }

}
