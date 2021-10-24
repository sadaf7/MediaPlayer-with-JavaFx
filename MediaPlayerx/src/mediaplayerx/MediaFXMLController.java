
package mediaplayerx;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class MediaFXMLController implements Initializable {
    
    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    
    @FXML
    Slider volumeSlider;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path=new File("src/media/Vid.mp4").getAbsolutePath();
        me=new Media(new File(path).toURI().toString());
        mp=new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        //mp.setAutoPlay(true);
        
        DoubleProperty width = mv.fitWidthProperty();
        DoubleProperty height=mv.fitHeightProperty();
        
        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
        
        volumeSlider.setValue(mp.getVolume()*100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
               mp.setVolume(volumeSlider.getValue()/100);
            }
        });
        
    }  
    
    public void play(ActionEvent e){
        
        mp.play();
        mp.setRate(1);
    }
    public void pause(ActionEvent e){
        mp.pause();
    }
    public void fast(ActionEvent e){
        mp.setRate(2);
    }
    public void slow(ActionEvent e){
        mp.setRate(.5);
    }
    public void reload(ActionEvent e){
        mp.seek(mp.getStartTime());
        mp.play();
    }
    public void start(ActionEvent e){
        mp.seek(mp.getStartTime());
        mp.play();
    }
    public void last(ActionEvent e){
        mp.seek(mp.getTotalDuration());
        mp.stop();
    } 
    public void stop(ActionEvent e){
        mp.stop();
    }
    
}
