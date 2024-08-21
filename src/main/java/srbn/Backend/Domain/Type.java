package srbn.Backend.Domain;

public class Type {
    private String name = "";
    private String parent="";
    private int type;
    private boolean isArray;
    private int initSize = 0;
    private int finalSize;
    private int memorySize;
    /*
    * @param type - express if symbol is an integer, float, string, etc.
    **/
    public Type(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /*
    * @param type - express if an array type with a final size
    * */
    public Type(String name, int type, int finalSize) {
        this.name = name;
        this.type = type;
        this.isArray = true;
        this.finalSize = finalSize;
        memorySize = finalSize+1;//+1 because of the 0 index
    }

    /*
    * @param type - express if a record type with a parent
    * */
    public Type(String name, int type, String parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    /*
    * @param type - express if an array type with an initial and final size
    * */
    public Type(String name, int type, int initSize, int finalSize) {
        this.name = name;
        this.type = type;
        this.isArray = true;
        this.initSize = initSize;
        this.finalSize = finalSize;
        memorySize = finalSize-initSize+1; //+1 because of the initSize index
    }


    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
