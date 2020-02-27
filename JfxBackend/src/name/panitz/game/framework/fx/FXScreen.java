package name.panitz.game.framework.fx;

import javax.sound.sampled.AudioInputStream;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import name.panitz.game.framework.GameLogic;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.KeyCode;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.swing.JavaSoundTool;

public class FXScreen extends Canvas {
    GameLogic<Image, AudioInputStream> logic;

    private JavaSoundTool soundTool = new JavaSoundTool();

    public FXScreen(GameLogic<Image, AudioInputStream> gl) {
        this.logic = gl;
        this.setWidth(logic.getWidth());
        this.setHeight(logic.getHeight());

        setFocusTraversable(true);

        GraphicsContext gc = this.getGraphicsContext2D();
        GraphicsTool<Image> gct = new FXContextTool(gc);
        gct.loadSprites(gct);
        logic.paintTo(gct);
        logic.setupPlayer();


        setOnKeyPressed((ev) -> {
            logic.keyPressedReaction(KeyCode.fromCode(ev.getCode().getCode()));
            ev.consume();
        });

        setOnKeyReleased((ev) -> {
            logic.keyReleasedReaction(KeyCode.fromCode(ev.getCode().getCode()));
            ev.consume();
        });


        timer.start();
    }

    AnimationTimer timer = new AnimationTimer() {
        private long lastUpdate = 0;

        @Override
        public void handle(long now) {
            setFocused(true);
            logic.move();
            logic.doChecks();

            if (now - lastUpdate >= 28_000_000) {
                GraphicsContext gc = getGraphicsContext2D();
                setHeight(getScene().getWindow().getHeight());
                setWidth(getScene().getWindow().getWidth());
                logic.updateScreenSize(getScene().getWindow().getWidth(),getScene().getWindow().getHeight());
                gc.clearRect(0, 0, getWidth(), getHeight());
                gc.translate(logic.moveField(false),logic.moveField(true) );
                logic.paintTo(new FXContextTool(gc));
                lastUpdate = now;
            }
            for (SoundObject<AudioInputStream> so : logic.getSoundsToPlayOnce()) {
                so.playSound(soundTool);
            }
            logic.getSoundsToPlayOnce().clear();

        }
    };
}

