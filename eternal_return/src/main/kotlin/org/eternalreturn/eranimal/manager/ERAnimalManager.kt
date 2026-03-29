package org.eternalreturn.eranimal.manager

import com.google.gson.Gson
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Display
import org.bukkit.entity.EntityType
import org.bukkit.entity.TextDisplay
import org.eternalreturn.area.AreaNode
import org.eternalreturn.eranimal.AreaERAnimalInfo
import org.eternalreturn.eranimal.ERAJEntity
import org.eternalreturn.eranimal.ERAJEntityLoader
import org.eternalreturn.eranimal.DPAnimal
import org.eternalreturn.eranimal.manager.behavs.*
import org.eternalreturn.system.EREngine
import org.eternalreturn.system.PluginInstance
import org.eternalreturn.util.AJEntity.AJEntityManager
import org.dpengine.DPEngine
import org.dpengine.monobehaviour.MonobehaviourActor
import java.io.File

/**
 * animal.json 정보를 토대로 ERAnimal을 관리하는 클래스.
 * MonobehaviourActor이다.
 */
class ERAnimalManager(
    ajEntityManager: AJEntityManager,
    private val engine: DPEngine,
    /**
     * 실제 ERAnimalManager가 갖는 필드들
     */
    val world: World, info: AreaERAnimalInfo
) : MonobehaviourActor(engine) {
    var isAllowedToSummonAlpha: Boolean = false
        private set

    fun allowToSummonAlpha(b: Boolean) {
        this.isAllowedToSummonAlpha = b
    }

    var isAllowedToSummonOmega: Boolean = false
        private set

    fun allowToSummonOmega(b: Boolean) {
        this.isAllowedToSummonOmega = b
    }

    @JvmField
    val areaName: String = info.name

    val entities = ArrayList<ERAJEntity>()

    val textDisplayList = ArrayList<TextDisplayer>()

    val erAnimalMap = HashMap<ERAJEntity, DPAnimal>()

    var px: Double = 0.0
    var py: Double = 0.0
    var pz: Double = 0.0

    public override fun remove() {
        super.remove()
        for (textDisplayer in textDisplayList) {
            textDisplayer.remove()
        }
    }

    init {
        for (animalInfo in info.animals) {
            val animal = ERAJEntity(
                animalInfo.name,
                Location(world, animalInfo.x, animalInfo.y, animalInfo.z, animalInfo.yaw, animalInfo.pitch)
            )
            AJEntityManager.registerAJEntity(animal) //register하지 않으면 돌아가지 않아요...
            entities.add(animal)
        }

        for (erAJAnimal in entities) {
            val loc = Location(
                world,
                erAJAnimal.spawnLocation.x(),
                erAJAnimal.spawnLocation.y() + 3.0,
                erAJAnimal.spawnLocation.z()
            )

            val textDisplay = this.world.spawnEntity(loc, EntityType.TEXT_DISPLAY) as TextDisplay
            textDisplay.billboard = Display.Billboard.CENTER //어느 방향에서 봐도 똑같이 보인다.

            val textDisplayEREntity =
                TextDisplayer(textDisplay, dpEngine as EREngine) //어차피 생성되면서 MonobehaviourModule내에 들어가게 됨.
            this.textDisplayList.add(textDisplayEREntity)
        }

        registerMonobehaviour(InitializeManager(128.0))
        registerMonobehaviour(WaitForSummoning())
        registerMonobehaviour(ManageERAnimals(this.entities.size))
        registerMonobehaviour(DetectingPlayersInRange())


        this.submitEvent(InitializeEvent())
    }

    public override fun lateinit() {
    }

    //static 한 친구들
    companion object {
        /**
         * Initialized from ERAJEntityLoader.load()
         */
        private var areaInfoList: MutableList<AreaERAnimalInfo>? = null

        private val erAnimalLoader = ERAJEntityLoader("plugins/animal.json")

        /**
         * 얘네들 기준으로 쿼리하기 위해 따로 모아둔 리스트
         */
        val animalManagers: MutableList<ERAnimalManager?> = ArrayList<ERAnimalManager?>()

        val animalMap : Map<String, AnimalStat> = loadFromFile(File("plugins/animal_stat.json"));

        /**
         * ERAnimalManager들은 MonobehaviourActor이므로, dpEngine의 MonobehaviourModule에 register해야 함.
         */
        @JvmStatic
        fun initERAnimalManagers(ajEntityManager: AJEntityManager, engine: EREngine) {
            areaInfoList = erAnimalLoader.load()
            val world: World = PluginInstance.getServerInstance().server.worlds.first()

            val areaSystem = engine.areaSystem

            /** areaInfoList를 순회하며 해당하는 ERAnimalManager들을 생성, 등록 */
            for (info in areaInfoList!!) {
                val vertex = areaSystem.getVertex(AreaNode(info.name)).getData()

                println(vertex.name + " <-> " + info.name)

                val animalManager = ERAnimalManager(ajEntityManager, engine, world, info)
                vertex.manager = animalManager
                animalManagers.add(animalManager)
            }
        }

        @JvmStatic
        fun loadMobStats(json: String): Map<String, AnimalStat> {
            val type = object : com.google.gson.reflect.TypeToken<Map<String, AnimalStat>>() {}.type
            return Gson().fromJson(json, type)
        }

        @JvmStatic
        fun loadFromFile(file: File): Map<String, AnimalStat> {
            println("Reading file : $file")
            val json = file.readText()
            return loadMobStats(json)
        }

    }
}

data class AnimalStat(
    val maxLevel: Int,
    val hp0: Double,
    val hp: Double,
    val hpMax : Double,
    val hpInc: Double,
    val damage0: Double,
    val damage: Double,
    val damageInc: Double,
    val attackTicks: IntArray,
    val summonCooldownSeconds : IntArray,
)

