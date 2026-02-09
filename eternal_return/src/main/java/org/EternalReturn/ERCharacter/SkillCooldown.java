package org.EternalReturn.ERCharacter;

import java.util.HashMap;
import java.util.Map;

public class SkillCooldown {
    private final Map<String, Long> cooldowns = new HashMap<>();

    // 쿨타임 설정
    public void set(String skillName, long seconds) {
        cooldowns.put(skillName, System.currentTimeMillis() + (seconds * 1000));
    }

    // 쿨타임 여부 확인
    public boolean isWaiting(String skillName) {
        if (!cooldowns.containsKey(skillName)) return false;
        return System.currentTimeMillis() < cooldowns.get(skillName);
    }

    // 남은 시간 반환
    public double getLeft(String skillName) {
        if (!isWaiting(skillName)) return 0.0;
        return (cooldowns.get(skillName) - System.currentTimeMillis()) / 1000.0;
    }
}