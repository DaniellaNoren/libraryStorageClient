package daniella.iths.se.librarystorageclient.resources;

import java.util.List;

public class ListOfObject<T> {

    private List<T> listOfObjects;

    public ListOfObject(){

    }

    public List<T> getList() {
        return listOfObjects;
    }

        public void setList(List<T> listOfObject) {
        this.listOfObjects = listOfObject;
    }
}
