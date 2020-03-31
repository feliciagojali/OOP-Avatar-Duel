class Card{
    private int id;
    private String name;
    private String element;
    private String description;
    private String imagepath;

    public Card(){
        this.id = 0;
        this.name = "";
        this.element = "";
        this.description = "";
        this.imagepath = "";
    }

    public Card(int id, String name, String element, String description, String imagepath){
        this.id = id;
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagepath = imagepath;
    }
}