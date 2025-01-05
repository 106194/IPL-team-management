package fahim.fahim22;

import javafx.scene.control.Button;

public class zoomButton
{
    public void zoom(Button login) {
        if (login != null) {
            login.setOnMouseEntered(event -> {
                login.setScaleX(1.2);
                login.setScaleY(1.2);
            });
            login.setOnMouseExited(event -> {
                login.setScaleX(1);
                login.setScaleY(1);
            });
        }
    }
}
