package server.domain;

import common.dto.UserProfileStructure;
import platform.domain.IUser;
import server.common.ProfileState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profile")

public class UserProfile implements IUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final int id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private int level;

    @Column(name = "experience")
    private int experience;

    @Column(name = "energy")
    private int energy;

    @Column(name = "rating")
    private int rating;

    @Column(name = "money")
    private int money;

    @Column(name = "backpack")
    private List<BackpackItem> backpack;

    @Column(name = "inventory")
    private List<InventoryItem> inventory;

    @Column(name = "friends")
    private Set<Integer> friends;

    @Column(name = "state")
    private ProfileState state = ProfileState.MAIN_MENU;

    @Column(name = "change_name_timer")
    private LocalDateTime changeNameTimer;

    public UserProfile(int id) {
        this.id = id;
    }

    public UserProfile(int id, String name, int level, int experience, int energy, int rating, int money, List<BackpackItem> backpack, List<InventoryItem> inventory, Set<Integer> friends) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.energy = energy;
        this.rating = rating;
        this.money = money;
        this.backpack = backpack;
        this.inventory = inventory;
        this.friends = friends;
    }

    public UserProfileStructure serialize() {
        var dto = new UserProfileStructure();
        dto.id = id;
        dto.name = name;
        dto.level = level;
        dto.experience = experience;
        dto.energy = energy;
        dto.rating = rating;
        dto.money = money;
        dto.backpack = backpack.toArray(new BackpackItem[0]);
        dto.inventory = inventory.toArray(new InventoryItem[0]);
        dto.friends = friends.stream().mapToInt(i -> i).toArray();
        dto.changeNameTimer = changeNameTimer;
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<BackpackItem> getBackpack() {
        return backpack;
    }

    public void setBackpack(List<BackpackItem> backpack) {
        this.backpack = backpack;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }

    public ProfileState getState() {
        return state;
    }

    public void setState(ProfileState state) {
        this.state = state;
    }

    public LocalDateTime getChangeNameTimer() {
        return changeNameTimer;
    }

    public void setChangeNameTimer(LocalDateTime changeNameTimer) {
        this.changeNameTimer = changeNameTimer;
    }

    @Override
    public int id() {
        return id;
    }
}
