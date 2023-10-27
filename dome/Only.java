public class Only {
    //饿汉式
//    private static Only show = new Only();
//
//    private Only (){}
//
//    public static Only geter(){
//        return show;
//    }
    //懒汉式
    private static Only shw = null;

    private  Only(){}
    public static Only getqwe(){
        if (shw == null){
            shw = new Only();
        }
        return shw;
    }
}
