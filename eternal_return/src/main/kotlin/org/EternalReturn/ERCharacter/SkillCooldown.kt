package org.EternalReturn.ERCharacter

class SkillCooldown {
    private val cooldowns: MutableMap<String?, Long?> = HashMap<String?, Long?>()

    // 쿨타임 설정
    fun set(skillName: String?, seconds: Long) {
        cooldowns.put(skillName, System.currentTimeMillis() + (seconds * 1000))
    }

    // 쿨타임 여부 확인
    fun isWaiting(skillName: String?): Boolean {
        if (!cooldowns.containsKey(skillName)) return false
        return System.currentTimeMillis() < cooldowns[skillName]!!
    }

    // 남은 시간 반환
    fun getLeft(skillName: String?): Double {
        if (!isWaiting(skillName)) return 0.0
        return (cooldowns.get(skillName)!! - System.currentTimeMillis()) / 1000.0
    }
}