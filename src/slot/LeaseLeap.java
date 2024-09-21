//package slot;
//
//import java.util.List;
//
//public class LeaseLeap {
//    private long currentTime;
//
//    private int windowTime;
//
//    private Window[] windows;
//
//
//
//
//    public Window currentWindow(){
//        long currentTimeMillis = System.currentTimeMillis();
//
//        int idx = (int) (windowTime % currentTimeMillis);
//
//        Window window = windows[idx];
//
//        if(window == null){
//            window = new Window();
//        }
//
//        return window;
//    }
//
//    public List<Window> list(){
//
//    }
//
//
//}
