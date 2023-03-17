package day04_rpg;

public class Monster {
	private String name;
	private int level, maxHp, hp, att, def;

	public String getName() {
		return this.name;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public int getHp() {
		return this.hp;
	}

	public int getAtt() {
		return this.att;
	}

	public int getDef() {
		return this.def;
	}

	public Monster(String name, int level, int maxHp, int hp, int att, int def) {
		this.name = name;
		this.level = level;
		this.maxHp = maxHp;
		this.hp = hp;
		this.att = att;
		this.def = def;
	}
}
