package org.eternalreturn.util.dpengine

import org.eternalreturn.util.dpengine.behaviour.MonobehaviourModule
import org.eternalreturn.util.dpengine.command.Command
import org.eternalreturn.util.dpengine.geometry.Cylinder
import org.eternalreturn.util.dpengine.geometry.GeometryModule
import org.eternalreturn.util.dpengine.geometry.InfPlane
import org.eternalreturn.util.dpengine.geometry.InfStraightLine
import org.eternalreturn.util.dpengine.geometry.OrientedBox
import org.bukkit.Location
import org.eternalreturn.erplayer.ERPlayer
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourEvent
import org.eternalreturn.util.dpengine.physics.OrientedBoxSoA
import org.eternalreturn.util.dpengine.physics.TransformSoA
import org.eternalreturn.util.dpengine.physics.UniformGrid
import org.joml.Quaterniond
import java.util.Vector
import java.util.concurrent.ArrayBlockingQueue
import kotlin.reflect.KClass

/**
 * Made by Danpung (TDanfung)
 * 기본적 엔진
 * */
abstract class DPEngine(bufferSize: Int = 512) {

    val geometryModule = GeometryModule(this, bufferSize)

    public fun createOrientedBox(location : Location, halfX: Double, halfY: Double, halfZ: Double): OrientedBox {
        val dir = location.direction;
        return OrientedBox(geometryModule,location.x,location.y, location.z,Quaterniond(dir.x, dir.y, dir.z, 0.0), halfX, halfY, halfZ);
    }

    public val monobehaviourModule = MonobehaviourModule(this)
    
    /**
     * 커맨드 큐
     * */
    public val commandQueue = ArrayBlockingQueue<Command>(512);

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