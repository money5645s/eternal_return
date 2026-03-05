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
import java.util.concurrent.ArrayBlockingQueue
import kotlin.reflect.KClass

/**
 * Made by Danpung (TDanfung)
 * 기본적 엔진
 * */
abstract class DPEngine(bufferSize: Int = 512) : Runnable {

    val geometryModule = GeometryModule(this, bufferSize)

    public fun createOrientedBox(location : Location, halfX: Double, halfY: Double, halfZ: Double): OrientedBox {
        val dir = location.direction;
        return OrientedBox(geometryModule,location.x,location.y, location.z,Quaterniond(dir.x, dir.y, dir.z, 0.0), halfX, halfY, halfZ);
    }

    public val monobehaviourModule = MonobehaviourModule(this)

    protected abstract fun update();
    
    /**
     * 커맨드 큐
     * */
    public val commandQueue = ArrayBlockingQueue<Command>(128);

    public fun appendCommandQueue(cmd : Command){
        commandQueue.add(cmd);
    }

    public fun flushCommandQueue(){
        while(!commandQueue.isEmpty()){
            val cmd = commandQueue.poll();
            cmd.run();
        }
    }

    /**
     * In main thread
     * */
    override fun run() {
        monobehaviourModule.consumeEvents();
        monobehaviourModule.updateMonobehaviours();
        monobehaviourModule.monobehaviourActorList.update();
        update();
        flushCommandQueue();
    }

}