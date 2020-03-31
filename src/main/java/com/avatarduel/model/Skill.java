class Skill extends Card{
    private String type;
    private int attack;
    private int defense;
    private int power;

    public Skill(){
        super();
        this.type = "Skill";
        this.attack = 0;
        this.defense = 0;
        this.power = 0;
    }

    public Skill(int id, String name, String element, String description, String imagepath, String type, int attack, int defense, int power){
        super(id, name, element, description, imagepath);
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    public String getType(){
        return this.type;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public int getPower(){
        return this.power;
    }
}