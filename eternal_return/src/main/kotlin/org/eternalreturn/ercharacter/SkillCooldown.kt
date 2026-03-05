package org.eternalreturn.ercharacter

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

    // SkillCooldown.kt 내부에 추가
    fun getLeftInt(skillName: String?): Int {
        val left = getLeft(skillName) // 원래 실수 값 (예: 1.54)
        if (left <= 0) return 0

        // 소수점 첫째 자리까지만 살리고 10을 곱함 (예: 1.54 -> 1.5 -> 15)
        return (left * 10).toInt()
    }
}