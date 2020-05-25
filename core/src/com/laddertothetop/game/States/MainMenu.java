package com.laddertothetop.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.laddertothetop.game.LadderToTheTop;
import com.badlogic.gdx.math.Rectangle;
import com.laddertothetop.game.States.Levels.First;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainMenu extends State{
    class MyInputProtector implements InputProcessor{
        SpriteBatch sb;


        public MyInputProtector(SpriteBatch sb){
            this.sb = sb;
        }

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if ((Touchrect.overlaps(Playrect))&&(k==0)) {
                gms.set(new First(gms,200,21,4,false,0,false));
                k++;
            }
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

    public static final int BtnX = 160;
    public static final int BtnY = 200;
    public static final int WidthBtn = 320;
    public static final int HeightBtn = 65;

    private Texture bg,play,settings,achieve;
    private Texture plays,settingss,achieves;
    private Vector3 touchPos;
    private Rectangle Playrect,Setrect,Achrect,Touchrect;
    private MyInputProtector inputProtector;
    int k=0;


    public MainMenu(GameStateManager gms) {
        super(gms);
        camera.setToOrtho(false, LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);

//        try{
//            System.out.println("kavo");
//            FileReader reader = new FileReader("Savee.txt");
//            int c;
//            while((c=reader.read())!=-1){
//                System.out.print((char)c);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("pizdec");
//        }
//        catch(IOException ex){
//            System.out.println("pizdec");
//        }

        bg = new Texture("background2.jpg");
        play = new Texture("PlayBtnS.png");
        settings = new Texture("SetBtnS.png");
        achieve = new Texture("AchievBtnS.png");
        plays = new Texture("PlayBtn1.png");
        settingss = new Texture("SetBtn.png");
        achieves = new Texture("AchievBtn.png");



        Playrect = new Rectangle(BtnX,BtnY,WidthBtn,HeightBtn);
        Setrect = new Rectangle(BtnX,BtnY-70,WidthBtn,HeightBtn);
        Achrect = new Rectangle(BtnX,BtnY-140,WidthBtn,HeightBtn);

        touchPos = new Vector3();
    }

    @Override
    protected void handleInput() {
      //  public boolean touuchdown;


    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        inputProtector = new MyInputProtector(sb);
        Gdx.input.setInputProcessor(inputProtector);


        sb.setProjectionMatrix(camera.combined);
        sb.begin();
            sb.draw(bg,0,0,LadderToTheTop.WIDTH/2,LadderToTheTop.HEIGHT/2);
            sb.draw(play,BtnX,BtnY,WidthBtn,HeightBtn);
            sb.draw(settings,BtnX,BtnY-75,WidthBtn,HeightBtn);
            sb.draw(achieve,BtnX,BtnY-150,WidthBtn,HeightBtn);
        if (Gdx.input.isTouched()) {  // в методе render
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Touchrect = new Rectangle(touchPos.x,touchPos.y,1,1);
            if (Touchrect.overlaps(Playrect)){
                sb.draw(plays,BtnX,BtnY,WidthBtn,HeightBtn);
            }
            if (Touchrect.overlaps(Setrect)){
                sb.draw(settingss,BtnX,BtnY-75,WidthBtn,HeightBtn);
            }
            if (Touchrect.overlaps(Achrect)){
                sb.draw(achieves,BtnX,BtnY-150,WidthBtn,HeightBtn);
            }
        }
        sb.end();

    }

    @Override
    public void dispose() {
        play.dispose();
        settings.dispose();
        achieve.dispose();

        bg.dispose();

        plays.dispose();
        settingss.dispose();
        achieves.dispose();
    }
}
