package org.eternalreturn.eranimal

import com.google.gson.Gson
import java.nio.file.Files
import java.nio.file.Path

/**
 * Gson으로 animal.json 내의 야생동물 정보를 파싱, load()로 반환한다.
 * 생성 시 JSON파일의 경로가 필요하다.
 */
class ERAJEntityLoader(jsonPath: String) {
    @JvmRecord
    internal data class JsonRoot(
        val location: MutableList<JsonArea>?
    )

    @JvmRecord
    internal data class JsonArea(
        val name: String,
        val table: MutableList<JsonAnimal>?
    )

    @JvmRecord
    internal data class JsonAnimal(
        val name: String,
        val pos: DoubleArray,
        val rot: DoubleArray
    )

    private var root: JsonRoot

    init {
        try {
            val json = Files.readString(Path.of(jsonPath))
            val gson = Gson()
            this.root = gson.fromJson<JsonRoot>(json, JsonRoot::class.java)
        } catch (e: Exception) {
            throw RuntimeException("Failed to load animal.json", e)
        }
    }

    fun load(): MutableList<AreaERAnimalInfo> {
        val areaInfoMap: MutableList<AreaERAnimalInfo> = ArrayList<AreaERAnimalInfo>()

        for (area in root.location!!) {
            val animals: MutableList<ERAnimalInfo> = ArrayList<ERAnimalInfo>()

            for (a in area.table!!) {
                animals.add(
                    ERAnimalInfo(
                        a.name,
                        a.pos[0],
                        a.pos[1],
                        a.pos[2],
                        a.rot[0].toFloat(),
                        a.rot[1].toFloat()
                    )
                )
            }

            areaInfoMap.add(AreaERAnimalInfo(area.name, animals))
        }

        return areaInfoMap
    }

}
