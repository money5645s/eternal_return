package org.eternalreturn.system


import org.bukkit.Bukkit
import org.bukkit.Chunk
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scoreboard.Objective
import org.dpengine.DPhysicsEngine
import org.dpengine.datastructure.DeadActorException
import org.dpengine.datastructure.UpdateView
import org.eternalreturn.area.ERAreaSystem
import org.eternalreturn.erentity.DPEntity
import org.eternalreturn.erplayer.DPlayer



class EREngine(val plugin : Plugin, bufferSize : Int = 512) : DPhysicsEngine(bufferSize) {


    /**
     * 플레이어들을 따로 업데이트하기 위한 리스트
     * 뷰로써 동작한다.
     */
    val players = UpdateView<DPlayer>();
    val playerList : List<DPlayer> get() = players.curQueue;

    init{
        val scheduler = Bukkit.getScheduler();
        this.monobehaviourModule.registerUpdateView(players);
        scheduler.runTaskTimer(plugin, Runnable{this.update()}, 0, 1);
    }

    val areaSystem: ERAreaSystem = ERAreaSystem()

    @Volatile
    private var day = 0;
    val curDay : Int
        get(){
            return day;
        }
    private var halfday = 0;

    var dayScoreboard : Objective? = null;
    var summonAlpha = false;
    var summonOmega = false;
    override fun update(){

        if(dayScoreboard == null){
            dayScoreboard = Bukkit.getScoreboardManager().mainScoreboard.getObjective("time");
        }

        if(dayScoreboard != null){
            day = dayScoreboard!!.getScore("day").score;
            halfday = dayScoreboard!!.getScore("halfday").score;
        }

        if(day == 2 && halfday == 1 && !summonAlpha){
            println("Summoning alpha...")
            summonAlpha = true;
            areaSystem.allowToSummonAlphaOnDay2()
        }

        if(day == 3 && halfday == 1 && !summonOmega){
            println("Summoning omega...")
            summonOmega = true;
            areaSystem.allowToSummonOmegaOnDay3()
        }

        super.update();

    }

    /**
     * Entity를 통해서 MonobehaviourActor를 접근하기 위해 필요한 함수
     *
     * 그냥 객체 생성 시에는 getEREntity()를 호출하여도 값은 얻을 수 없음.
     *
     * Actor의 레퍼런스 카운터를 올리지 않음.
     */
    override fun registerBukkitActor(entity: Entity, actor: DPEntity) {
        if(dpEntityMap.contains(entity)){
            val oldEREntity = dpEntityMap[entity]!!;
            oldEREntity.remove();
        }

        dpEntityMap[entity] = actor;
        try {
            entities.add(actor);
            if (entity is Player && actor is DPlayer) {
                players.add(actor);
            }
        } catch (e: DeadActorException) {
            e.printStackTrace()
        }

    }





}