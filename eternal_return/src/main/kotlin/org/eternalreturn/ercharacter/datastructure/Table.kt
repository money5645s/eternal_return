package org.eternalreturn.ercharacter.datastructure

class CoolTableSeconds(val getLevel : ()->Int, vararg argTicks : Long) {
    val table : LongArray
    init{
        val length = argTicks.size;
        table = LongArray(argTicks.size);

        for(i in 0 until length){
            table[i] = argTicks[i] * 20;
        }
    }

    fun get() : Long{
        return table[getLevel()];
    }

}

class DamageTable(val getLevel : ()->Int, vararg args : Double) {
    val table : DoubleArray
    init{
        val length = args.size;
        table = DoubleArray(args.size);

        for(i in 0 until length){
            table[i] = args[i];
        }
    }

    fun get() : Double{
        return table[getLevel()];
    }

}