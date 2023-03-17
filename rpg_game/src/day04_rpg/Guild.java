package day04_rpg;

import java.util.ArrayList;

public class Guild {
	public final int PARTY_SIZE = 4;
	public ArrayList<Unit> guildList = new ArrayList<>();
	public Unit[] partyList;

	public void setGuild() {
		this.partyList = new Unit[this.PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < this.guildList.size(); i++) {
			if (this.guildList.get(i).getParty() == true) {
				this.partyList[n] = this.guildList.get(i);
				n += 1;
			}
		}
	}

	public Unit getGuildUnit(int num) {
		return this.guildList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + guildList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + guildList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + guildList.get(i).getHp());
			System.out.println(" / " + guildList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + guildList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + guildList.get(i).getDef() + "]");
			System.out.println(" [파티중 : " + guildList.get(i).getParty() + "]");
			System.out.println("");
		}
		System.out.println("=================================");
	}

	public void printUnitStaus(int num) {
		this.guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		this.guildList.get(num).printEquitedItem();
	}

	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.guildList.add(temp);
		Player.money -= 5000;
	}

	public void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		if (this.guildList.get(sel - 1).getParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + this.guildList.get(sel - 1).getName() + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			this.guildList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean printParty() {
		boolean isRun = true;
		if (partyList[0] != null) {
			System.out.println("================ [파티원] ===============");
			for (int i = 0; i < PARTY_SIZE; i++) {
				if (partyList[i] != null) {
					System.out.print("[" + (i + 1) + "번]");
					System.out.print(" [이름 : " + partyList[i].getName() + "]");
					System.out.print(" [레벨 : " + partyList[i].getLevel() + "]");
					System.out.print(" [체력 : " + partyList[i].getHp());
					System.out.println(" / " + partyList[i].getMaxHp() + "]");
					System.out.print("[공격력 : " + partyList[i].getAtt() + "]");
					System.out.print(" [방어력 : " + partyList[i].getDef() + "]");
					System.out.println(" [파티중 : " + guildList.get(i).getParty() + "]");
					System.out.println("");
				}
			}
			System.out.println("=====================================");
		} else {
			isRun = false;
		}
		return isRun;
	}

	private void partyPlus() {
		int count = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).getParty() == true) {
				count++;
			}
		}

		if (count < PARTY_SIZE) {
			printAllUnitStaus();
			System.out.println("참가할 번호를 입력하세요 ");
			int guildNum = MainGame.scan.nextInt() - 1;

			if (guildNum >= 0 && guildNum < guildList.size()) {
				if (guildList.get(guildNum).getParty() != true) {
					guildList.get(guildNum).setParty(true);
				} else {
					System.out.println("이미 등록된 파티원입니다.");
				}
			}
			setGuild();
		} else {
			System.out.println("만석입니다.");
		}
	}

	public void partyChange() {
		if (printParty()) {
			System.out.println("교체할 번호를 입력하세요 ");
			int partyNum = MainGame.scan.nextInt();
			printAllUnitStaus();
			System.out.println("참가할 번호를 입력하세요 ");
			int guildNum = MainGame.scan.nextInt();

			this.partyList[partyNum - 1].setParty(false);
			this.guildList.get(guildNum - 1).setParty(true);

			System.out.println("====================================");
			System.out.print("[이름 : " + this.partyList[partyNum - 1].getName() + "]");
			System.out.print("에서 ");
			System.out.print("[이름 : " + this.guildList.get(guildNum - 1).getName() + "]");
			System.out.println("으로 교체 합니다. ");
			System.out.println("====================================");

			int n = 0;
			for (int i = 0; i < this.guildList.size(); i++) {
				if (this.guildList.get(i).getParty()) {
					this.partyList[n] = this.guildList.get(i);
					n += 1;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("파티원이 없습니다.");
		}
	}

	private void partyDelete() {
		int count = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).getParty() == true) {
				count++;
			}
		}

		if (count > 0) {
			printAllUnitStaus();
			System.out.println("삭제할 파티원 번호를 입력하세요 ");
			int guildNum = MainGame.scan.nextInt() - 1;

			if (guildNum >= 0 && guildNum < guildList.size()) {
				if (guildList.get(guildNum).getParty() == true) {
					guildList.get(guildNum).setParty(false);
				} else {
					System.out.println("등록된 파티원이 아닙니다.");
				}
			}
			setGuild();
		} else {
			System.out.println("파티원이 없습니다");
		}
	}

	private void partySort() {
		String temp = "";
		for (int i = 0; i < this.guildList.size(); i++) {
			for (int j = 0; j < this.guildList.size(); j++) {
				if (this.guildList.get(i).getName().compareTo(this.guildList.get(j).getName()) < 0) {
					temp = this.guildList.get(i).getName();
					this.guildList.get(i).setName(this.guildList.get(j).getName());
					this.guildList.get(j).setName(temp);
				}
			}
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.길드목록] [2.길드원추가] [3.길드원삭제]\n" + "[4.파티원선택] [5.파티원교체] [6.피티원삭제] [7.정렬] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				partyPlus();
			} else if (sel == 5) {
				partyChange();
			} else if (sel == 6) {
				partyDelete();
			} else if (sel == 7) {
				partySort();
			} else if (sel == 0) {
				break;
			}
		}
	}

}