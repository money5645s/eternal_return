package org.dpengine

import org.dpengine.command.Command
import org.dpengine.geometry.MatVecCalculator
import org.dpengine.monobehaviour.MonobehaviourModule
import java.util.concurrent.ArrayBlockingQueue

/**
 * Made by Danpung (TDanfung)
 * 기본적 엔진
 * */
abstract class DPEngine(bufferSize: Int = 512) {

    val matVecCalculator = MatVecCalculator(bufferSize)

    val monobehaviourModule = MonobehaviourModule(this)

    /**
     * 커맨드 큐
     * */
    val commandQueue = ArrayBlockingQueue<Command>(512);

    init{
        println("     _ ____\n" +
                "  __| |  _ \\\n" +
                " / _` | |_) |\n" +
                "| (_| |  __/\n" +
                " \\__,_|_|ENGINE\n\n" +
                "The high-performance plugin engine with multi-threading\n" +
                "Fast, modular, and built for experienced developers\n" +
                "Powering the next generation of plugins\n" +
                "\nMADE BY Danpung (TDanfung)\n\n")
    }



    /**
     * 커맨드 큐에 커맨드를 제출한다.
     * Thread-safe 하다.
     * */
    public fun appendCommand(cmd : Command){
        //println("appendCommand : ${cmd.javaClass.simpleName}")
        commandQueue.add(cmd);
    }

    /**
     * 큐에 쌓인 모든 커맨드를 실행 후 비운다
     * */
    public open fun flushCommandQueue(){
        while(commandQueue.isNotEmpty()){
            val cmd = commandQueue.remove();
            //println("${cmd.javaClass.simpleName} is running")
            cmd.run();
        }
    }

    fun free(){
        monobehaviourModule.free();
    }

    /**
     * In main thread
     * */
    open fun update() {
        monobehaviourModule.consumeEvents();
        monobehaviourModule.updateMonobehaviours();
        monobehaviourModule.monobehaviourActorList.update();
        flushCommandQueue();
    }

}