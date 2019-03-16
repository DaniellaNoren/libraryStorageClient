package daniella.iths.se.librarystorageclient.menu;

public class MenuItem {

    private String option;
    private DoSomething menuMethod;

    public MenuItem(String option, DoSomething menuMethod){
        this.option = option;
        this.menuMethod = menuMethod;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public DoSomething getMenuMethod() {
        return menuMethod;
    }

    public void setMenuMethod(DoSomething menuMethod) {
        this.menuMethod = menuMethod;
    }

}
