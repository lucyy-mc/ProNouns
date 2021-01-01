package me.lucyy.pronouns.storage;

import me.lucyy.pronouns.set.PronounSet;

import java.util.List;
import java.util.UUID;

public interface Storage {
    List<String> GetPronouns(UUID uuid);
    void SetPronouns(UUID uuid, List<PronounSet> set);
    void ClearPronouns(UUID uuid);
}
