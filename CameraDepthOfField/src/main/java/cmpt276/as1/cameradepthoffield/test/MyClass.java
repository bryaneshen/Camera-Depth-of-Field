package cmpt276.as1.cameradepthoffield.test;

import cmpt276.as1.cameradepthoffield.model.LensManager;
import cmpt276.as1.cameradepthoffield.ui.CameraTextUI;
//import cmpt276.as1.cameradepthoffield.ui.CameraTextUI;

public class MyClass {
    public static void main(String[] args) {
        // create model
        LensManager manager = LensManager.getInstance();

        // create UI
        CameraTextUI ui = new CameraTextUI(manager);

        // launch
        ui.show();

        /*LensManager manager = LensManager.getInstance();
        CameraTextUI ui = new CameraTextUI(manager);

        System.out.println(manager.getArrayList());*/
    }
}