package com.example.supermariobros.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.supermariobros.R;

import java.util.ArrayList;
import java.util.Collections;

public class CustomView extends View {
    private static final int SQUARE_SIZE_DEF = 30;
    private static final int max_limit = 11, min_limit = 3;
    private Rect RectSquare;
    private Paint PaintSquare, PaintMario;
    private int SquareSize, level_count=1;

    levels level;
    String [][] current_level = new String[102][12];
    int [][] map = new int[102][12];
    public Point pt, mush_pt = new Point(0,0), star_pt = new Point(0,0), goomba_pt= new Point(0,0);
    public Point plant_pt = new Point(0,0);
    public Point[] Pieces = new Point[2];

    public int Score = 0, lives=3;
    protected int width_limit=0, time;
    boolean move_atm = false, jmp_atm = false, grav_atm = false, invincible=false, alive;
    boolean mush_active=false, star_active=false, goomba_active=false, plant_active=false, plant_alive=false;
    public boolean big=false;
    boolean direction = true;

    private Rect frameToDraw = new Rect(0,0,240,240);
    private Rect frameMario;
    int sprite, sprite_upper, sprite_lower;

    public CustomView(Context context) {
        super(context);
        init(null);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set){
        RectSquare = new Rect();
        PaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintMario = new Paint(Paint.ANTI_ALIAS_FLAG);
        if(set==null)
            return;
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        SquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE_DEF);

        level = new levels();
        level.initialize_Level();
        current_level = level.level_1;
        ConvertMap(level.level_1, map);
        //call a function to load a new map
        new_mario();
        ta.recycle();
    }
    /*adds the new mario to the board that will spawn in the initial location*/
    public void new_mario() {
        pt = new Point(3,7);
        Pieces[0] = pt;
        PaintMario.setColor(hue.red);
    }
    /*makes mario big by adding another mario piece*/
    public void SuperMario(){
        if(big){
            return;
        }
        Pieces[1] = new Point(pt.x,pt.y-1);
        big=true;
    }
    /*initializes a new map*/
    public void ConvertMap(String[][] array, int[][] map){
        for(int i=0; i<100 ; i++){
            for(int j=0; j<10; j++){
                switch(array[i][j]) {
                    case "background":
                        map[i][j] = hue.lblue;
                        break;
                    case "ground":
                        map[i][j] = hue.gray;
                        break;
                    case "block":
                        map[i][j] = hue.lbrown;
                        break;
                    case "star_block":
                        map[i][j] = hue.star_block;
                        break;
                    case "power_block":
                        map[i][j] = hue.purple;
                        break;
                    case "coin_block":
                        map[i][j] = hue.yellow;
                        break;
                    case "pipe":
                        map[i][j] = hue.green;
                        break;
                    case "plant":
                        map[i][j] = hue.green2;
                        break;
                    case "goomba":
                        map[i][j] = hue.orange;
                        break;
                    case "leaves":
                        map[i][j] = hue.dark_green;
                        break;
                    case "trunk":
                        map[i][j] = hue.brown;
                        break;
                    case "flag":
                        map[i][j] = hue.blue;
                        break;
                    default: break;
                }
            }
        }
    }
    /*Returns true if there is a collision, false if no collision*/
    public boolean Collide(int x, int y){
        if(x==width_limit||x==24+width_limit||y==0||y==10){
            if(y==10){
                if(goomba_active){ //means that if goomba is active falling to the pit wont kill mario
                    goomba_active=false;
                    return true;
                }
                death();
            }
            return true;
        }else if(map[x][y] == hue.yellow) { //coins
            map[x][y] = hue.lblue;
            Score += 200;
            return false;
        }else if(map[x][y] == hue.blue) { //flag
            Score += 5000;
            change_level();
            return false;
        }else if(map[x][y] == hue.lbrown){ //blocks
            if(map[x][y-1] == hue.orange){//checks for goomba above block
                map[x][y-1] = hue.lblue;
                Score += 100;
                postInvalidate();
            }
            if(big){
                if(y+1 != Pieces[1].y){
                    return true;
                }
                map[x][y] = hue.lblue;
                postInvalidate();
                Score += 10;
                return true;
            }
            return true;
        }else if(map[x][y] == hue.purple){ //super mushroom
            if(big){
                if(y+1!=Pieces[1].y) {
                    return true;
                }
            }else{
                if(y+1!=Pieces[0].y){
                    return true;
                }
            }
            map[x][y]= hue.dpurple;
            mushroom(x, y);
            return true;
        }else if(map[x][y] == hue.star_block){ //starman
            if(big){
                if(y+1!=Pieces[1].y) {
                    return true;
                }
            }else{
                if(y+1!=Pieces[0].y){
                    return true;
                }
            }
            map[x][y]= hue.star_block_darkened;
            star(x, y);
            return true;
        }else if(map[x][y] == hue.orange){ //goomba
            if(y-1==Pieces[0].y||invincible){ //if mario hits goomba from above
                map[x][y] = hue.lblue;
                alive=false;
                Score += 100;
                return false;
            }else if(big){
                big=false;
                gravity(Pieces[1]);
                return true;
            }else{
                death();
                return true;
            }
        }
        else if(map[x][y] == hue.lblue || map[x][y]==hue.red){return false;}
        else{ return true;}
    }
    /*resets the level if mario dies*/
    public void death(){
        if(mush_active||star_active){
            return;
        }
        lives--;
        if(lives==0){
            System.out.println("Game Over");
            System.exit(0); //end of game
        }
        ConvertMap(current_level, map);
        width_limit=0;
        big=false;
        goomba_active=false;
        new_mario();
    }
    /*this changes the map based on the current level*/
    public void change_level(){
        level_count++;
        System.out.println(level_count);
        switch(level_count) {
            case 2:
                ConvertMap(level.level_2, map);
                current_level = level.level_2;
                break;
            case 3:
                ConvertMap(level.level_3,map);
                current_level = level.level_3;
                break;
            default:
                //end of level_3
                level_count=1;
                ConvertMap(level.level_1,map);
                current_level = level.level_1;
        }
        width_limit=0;
        new_mario();
    }
    /*Will move the object if there is no collision*/
    public void move(int i, int j, boolean jumping, Point obj){
        if(big && obj==Pieces[0]){
            if(Collide(Pieces[1].x+i, Pieces[1].y+j)){
                return;
            }
        }
        if(!Collide(obj.x + i, obj.y+j)){
            obj.x+=i;
            obj.y+=j;
            move_atm = true;
        }
        postInvalidate();
        if(!jumping){
            gravity(obj);
        }
    }
    //Returns false from Game_Activity after moving stops
    public void moving (boolean moves) {
        this.move_atm = moves;
    }
    //Checks in Game_Activity if directional buttons make Mario face left or right
    public void direction_facing (boolean forward) {
        this.direction = forward;
    }
    /*runs a thread to do the jumping motion for mario*/
    public void jumping() {
        final ArrayList<Integer> jump_list = new ArrayList<>();
        Collections.addAll(jump_list, -1, -1, -1, -1);
        if(jmp_atm){ return; }
        time = 50;
        if(big){
            pt = Pieces[1]; //higher piece
        }else{ pt = Pieces[0];} //lower piece
        Thread jmp = new Thread(new Runnable(){
            @Override
            public void run(){
                jmp_atm=true;
                while(!jump_list.isEmpty()){
                    if(Collide(pt.x, pt.y-1)){ break; } //stops the jump animation if collides with block
                    try {
                        move(0, jump_list.get(0),true, pt);
                        jump_list.remove(0);
                        Thread.sleep(time);
                        time +=25;
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gravity(Pieces[0]);
                jmp_atm=false;
            }
        });
        jmp.start();
    }
    /*this will make an object fall down to the ground if there's nothing below the object*/
    public void gravity(final Point obj){
        final ArrayList<Integer> grav_list = new ArrayList<>();
        Collections.addAll(grav_list, 1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        if(grav_atm){ return; }
        time=150;
        Thread gravity_t = new Thread(new Runnable(){
            @Override
            public void run(){
                grav_atm=true;
                jmp_atm=true;
                while(!Collide(obj.x,obj.y+1)){
                    try {
                        if(time<0){ time=25;}
                        Thread.sleep(time);
                        move(0, grav_list.get(0), false, obj);
                        grav_list.remove(0);
                        time -= 15;
                        //change time to increase momentum of fall
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                grav_atm=false;
                jmp_atm=false;
            }
        });
        gravity_t.start();
    }
    /*Items: the mushroom has a thread that runs left or right and if eaten makes mario super*/
    public void mushroom(int x, int y){
        final ArrayList<Integer> move_list = new ArrayList<>();
        mush_active = true;
        mush_pt.x = x;
        mush_pt.y = y-1;
        move_list.add(+1); //direction of the mushroom
        //run a thread to move the mushroom
        Thread mush_run = new Thread(new Runnable(){
            @Override
            public void run(){
                int timer = 300;
                while(mush_active){
                    try {
                        if(big){
                            if(Pieces[1].x == mush_pt.x && Pieces[1].y == mush_pt.y) { //if upper mario eats the mushroom
                                SuperMario();
                                Score += 1000;
                                break;
                            }
                        }
                        if(Pieces[0].x == mush_pt.x && Pieces[0].y == mush_pt.y) { //if lower mario eats the mushroom
                            SuperMario();
                            Score += 1000;
                            break;
                        }
                        if(Collide(mush_pt.x+move_list.get(0),mush_pt.y)) {  //switches the direction of the mushroom if there is a collision
                            if(move_list.get(0)==1){
                                move(-1, 0, false, mush_pt);
                                move_list.remove(0);
                                move_list.add(-1);
                            }else{
                                move(1, 0, false, mush_pt);
                                move_list.remove(0);
                                move_list.add(1);
                            }

                        }else{
                            move(move_list.get(0), 0 ,false, mush_pt);
                        }

                        Thread.sleep(timer);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mush_active=false;
            }
        });
        mush_run.start();
    }
    /*Item: starman will make mario invulnerable to enemies for 8 seconds if he eats it*/
    public void star(int x, int y){
        final ArrayList<Integer> move_list = new ArrayList<>();
        star_active = true;
        star_pt.x = x;
        star_pt.y = y-1;
        move_list.add(+1); //direction of the star
        Thread star_run = new Thread(new Runnable(){
            @Override
            public void run(){
                int timer = 300;
                while(star_active){
                    try {
                        if(big){
                            if(Pieces[1].x == star_pt.x && Pieces[1].y == star_pt.y) { //if upper mario eats the star
                                Invincible();
                                Score += 1000;
                                break;
                            }
                        }
                        if(Pieces[0].x == star_pt.x && Pieces[0].y == star_pt.y) { //if bottom mario eats the star
                            Invincible();
                            Score += 1000;
                            break;
                        }
                        if(Collide(star_pt.x+move_list.get(0),star_pt.y)) {  //switches the direction of the star if there is a collision
                            if(move_list.get(0)==1){
                                move(-1, 0, false, star_pt);
                                move_list.remove(0);
                                move_list.add(-1);
                            }else{
                                move(1, 0, false, star_pt);
                                move_list.remove(0);
                                move_list.add(1);
                            }
                        }else{
                            move(move_list.get(0), 0 ,false, star_pt);
                        }
                        Thread.sleep(timer);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                star_active=false;
            }
        });
        star_run.start();
    }
    /*this checks the current viewable board if there is an enemy and allows that enemy to be active*/
    public void check_for_enemies(){
        for(int i=width_limit; i<25+width_limit; i++){
            for( int j=0; j<11;j++){
                if(map[i][j]==hue.orange && !goomba_active){
                    map[i][j] = hue.lblue;
                    alive=true;
                    Goomba(i,j);
                }
                if(map[i][j]==hue.green2){
                    if(!plant_alive) {
                        Pirana_plant(i, j);
                    }
                }
            }
        }

    }
    /*Enemies: Goomba this guy moves left or right and dies when mario stomps on its head*/
    public void Goomba(int x, int y){
        final ArrayList<Integer> move_list = new ArrayList<>();
        goomba_active = true;
        goomba_pt.x = x;
        goomba_pt.y = y;
        move_list.add(+1); //direction of the goomba
        Thread goomba_run = new Thread(new Runnable(){
            @Override
            public void run(){
                int timer = 500;
                while(goomba_active) {
                    try {
                        if(!alive){
                            break;
                        }
                        map[goomba_pt.x][goomba_pt.y]=hue.lblue;
                        if (Collide(goomba_pt.x + move_list.get(0), goomba_pt.y)) {  //switches the direction of the goomba if there is a collision
                            if (move_list.get(0) == 1) {
                                move(-1, 0, false, goomba_pt);
                                move_list.remove(0);
                                move_list.add(-1);
                            } else {
                                move(1, 0, false, goomba_pt);
                                move_list.remove(0);
                                move_list.add(1);
                            }

                        } else {
                            move(move_list.get(0), 0, false, goomba_pt);
                        }
                        map[goomba_pt.x][goomba_pt.y]= hue.orange;
                        Thread.sleep(timer);
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                goomba_active=false;
            }
        });
        goomba_run.start();
    }
    /*Enemies: Pirana-Plant comes up every 1.5 seconds and if mario gets caught in its vines for more than 1 sec then mario dies.*/
    private void Pirana_plant(final int x,final int y){
        plant_active = true;
        plant_alive = true;
        plant_pt.x = x;
        plant_pt.y = y-1;
        //run a thread to move the plant
        Thread plant_run = new Thread(new Runnable(){
            @Override
            public void run(){
                int timer = 1500;
                while(plant_alive){
                    try {
                        System.out.println(level_count);
                        if(level_count!=1){
                            plant_active=false;
                            break;
                        }
                        if ((Pieces[0].y == plant_pt.y && Pieces[0].x == plant_pt.x) || (Pieces[0].y == plant_pt.y && Pieces[0].x == plant_pt.x+1)){
                            if(invincible){
                                map[plant_pt.x][plant_pt.y+1]=hue.green;
                                plant_active=false;
                                break;
                            }else if(big){
                                big=false;
                                gravity(Pieces[1]);
                            }else{
                                death();
                            }
                        }
                        plant_active=false;
                        postInvalidate();
                        Thread.sleep(timer);
                        if((plant_pt.x-3 < Pieces[0].x) && (Pieces[0].x < plant_pt.x+3)){
                            plant_active=false;
                        }else{
                            plant_active=true;
                        }
                        postInvalidate();
                        Thread.sleep(timer+500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                plant_alive=false;
                postInvalidate();
            }
        });
        plant_run.start();
    }
    /*This makes mario immune to enemies for 8 seconds*/
    private void Invincible(){
        Thread invincible_time = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    invincible=true;
                    Thread.sleep(8000); //8 secs of star man power
                    invincible=false;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        invincible_time.start();
    }
    /******************** bitmap drawing part of the canvas ******************/
    private void drawGoomba(Canvas canvas){
        PaintSquare.setColor(hue.orange);
        RectSquare.left = (goomba_pt.x-width_limit)*SquareSize;
        RectSquare.top = (goomba_pt.y)*SquareSize;
        RectSquare.right = RectSquare.left+SquareSize;
        RectSquare.bottom = RectSquare.top+SquareSize;
        Bitmap bitmapGoomba = BitmapFactory.decodeResource(this.getResources(), R.drawable.gmb_1);
        canvas.drawBitmap(bitmapGoomba, frameToDraw, RectSquare, PaintSquare);
    }
    private void drawPlant(Canvas canvas){
        if(plant_pt.y==5){
            PaintSquare.setColor(hue.green);
        }else{
            PaintSquare.setColor(hue.plant_green);
        }
        RectSquare.left = (plant_pt.x-width_limit)*SquareSize+40;
        RectSquare.top = (plant_pt.y)*SquareSize;
        RectSquare.right = RectSquare.left+SquareSize;
        RectSquare.bottom = RectSquare.top+SquareSize;
        Bitmap bitmapPlant = BitmapFactory.decodeResource(this.getResources(), R.drawable.plt_1);
        canvas.drawBitmap(bitmapPlant, frameToDraw, RectSquare, PaintSquare);
    }
    private void drawStar(Canvas canvas){
        PaintSquare.setColor(hue.supa_star);
        RectSquare.left = (star_pt.x-width_limit)*SquareSize;
        RectSquare.top = (star_pt.y)*SquareSize;
        RectSquare.right = RectSquare.left+SquareSize;
        RectSquare.bottom = RectSquare.top+SquareSize;
        Bitmap bitmapStar = BitmapFactory.decodeResource(this.getResources(), R.drawable.starman);
        canvas.drawBitmap(bitmapStar, frameToDraw, RectSquare, PaintSquare);
    }
    private void drawMushroom(Canvas canvas){
        PaintSquare.setColor(hue.pink);
        RectSquare.left = (mush_pt.x-width_limit)*SquareSize;
        RectSquare.top = (mush_pt.y)*SquareSize;
        RectSquare.right = RectSquare.left+SquareSize;
        RectSquare.bottom = RectSquare.top+SquareSize;
        Bitmap bitmapShroom = BitmapFactory.decodeResource(this.getResources(), R.drawable.mushroom);
        canvas.drawBitmap(bitmapShroom, frameToDraw, RectSquare, PaintSquare);
    }
    //Bitmap draws Mario sprites based on Super Mario, Invincible Mario, both, or normal
    private void drawMario(Canvas canvas){
        RectSquare.left = (pt.x - width_limit) * SquareSize;
        RectSquare.top = (pt.y) * SquareSize;
        RectSquare.right = RectSquare.left + SquareSize;
        RectSquare.bottom = RectSquare.top + SquareSize;
        if(big){
            if(jmp_atm&&!grav_atm){
                Pieces[0].y = Pieces[1].y+1;}
            else{
                Pieces[1].y =Pieces[0].y-1;
            }
            Pieces[1].x=Pieces[0].x;

            if (invincible) {
                if (move_atm) {
                    if (direction) {sprite_upper = R.drawable.rb_run1_ru;
                        sprite_lower = R.drawable.rb_run1_rl;}
                    else {sprite_upper = R.drawable.rb_run1_lu;
                        sprite_lower = R.drawable.rb_run1_ll;}
                } else if (jmp_atm) {
                    if (direction) {sprite_upper = R.drawable.rb_jump_ru;
                        sprite_lower = R.drawable.rb_jump_rl;}
                    else {sprite_upper = R.drawable.rb_jump_lu;
                        sprite_lower = R.drawable.rb_jump_ll;}
                } else {
                    sprite_lower = R.drawable.rb_stand_l;
                    if (direction) {sprite_upper = R.drawable.rb_stand_ru; }
                    else {sprite_upper = R.drawable.rb_stand_lu; }
                }
            } else {
                if (move_atm) {
                    if (direction) {sprite_upper = R.drawable.b_run1_ru;
                        sprite_lower = R.drawable.b_run1_rl;}
                    else {sprite_upper = R.drawable.b_run1_lu;
                        sprite_lower = R.drawable.b_run1_ll;}
                } else if (jmp_atm) {
                    if (direction) {sprite_upper = R.drawable.b_jump_ru;
                        sprite_lower = R.drawable.b_jump_rl;}
                    else {sprite_upper = R.drawable.b_jump_lu;
                        sprite_lower = R.drawable.b_jump_ll;}
                } else {
                    sprite_lower = R.drawable.b_stand_l;
                    if (direction) {sprite_upper = R.drawable.b_stand_ru; }
                    else {sprite_upper = R.drawable.b_stand_lu; }
                }
            }
            RectSquare.left = (Pieces[1].x - width_limit) * SquareSize;
            RectSquare.top = (Pieces[1].y) * SquareSize;
            RectSquare.right = RectSquare.left + SquareSize;
            RectSquare.bottom = RectSquare.top + SquareSize+10;
            Bitmap bitmapSUMario = BitmapFactory.decodeResource(this.getResources(), sprite_upper);
            canvas.drawBitmap(bitmapSUMario, frameToDraw, RectSquare, PaintSquare);

            RectSquare.left = (Pieces[0].x - width_limit) * SquareSize;
            RectSquare.top = (Pieces[0].y) * SquareSize;
            RectSquare.right = RectSquare.left + SquareSize;
            RectSquare.bottom = RectSquare.top + SquareSize+10;
            Bitmap bitmapSLMario = BitmapFactory.decodeResource(this.getResources(), sprite_lower);
            canvas.drawBitmap(bitmapSLMario, frameToDraw, RectSquare, PaintSquare);

        }else if (invincible){
            frameMario = new Rect(0, 0, 240, 240);
            if (move_atm) {
                if (direction) {sprite = R.drawable.rs_run1_r; }
                else { sprite = R.drawable.rs_run1_l; }
            } else if (jmp_atm) {
                if (direction) { sprite = R.drawable.rs_jump_r; }
                else { sprite = R.drawable.rs_jump_l; }
            } else {
                if (direction) { sprite = R.drawable.rs_stand_r; }
                else { sprite = R.drawable.rs_stand_l; }
            }
            Bitmap bitmapIMario = BitmapFactory.decodeResource(this.getResources(), sprite);
            canvas.drawBitmap(bitmapIMario, frameMario, RectSquare, PaintSquare);
        } else {
            frameMario = new Rect(0,0,240,240);
            if (move_atm) {
                if (direction) { sprite = R.drawable.s_run1_r; }
                else { sprite = R.drawable.s_run1_l; }
            } else if (jmp_atm) {
                if (direction) { sprite = R.drawable.s_jump_r; }
                else { sprite = R.drawable.s_jump_l; }
            } else {
                if (direction) { sprite = R.drawable.s_stand_r; }
                else { sprite = R.drawable.s_stand_l; }
            }
            Bitmap bitmapMario = BitmapFactory.decodeResource(this.getResources(), sprite);
            canvas.drawBitmap(bitmapMario, frameMario, RectSquare, PaintSquare);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        //
        if(pt.x > width_limit+max_limit){
            width_limit++;
        }else if(pt.x < width_limit+min_limit){
            width_limit--;
        }
        if(width_limit >76){
            width_limit=76;
        }else if(width_limit < 1){
            width_limit=0;
        }

        for(int i=0; i<25; i++){ //max screen block is 24
            for(int j=0; j<11; j++){
                if(j==0||j==10||i==0||i==24){
                    PaintSquare.setColor(hue.background);
                    RectSquare.left = i* SquareSize;
                    RectSquare.top = j* SquareSize;
                    RectSquare.right = RectSquare.left + SquareSize;
                    RectSquare.bottom = RectSquare.top + SquareSize;
                    canvas.drawRect(RectSquare, PaintSquare);
                }
                else {
                    PaintSquare.setColor(map[i+width_limit][j]);
                    RectSquare.left = i * SquareSize+1;
                    RectSquare.top = j * SquareSize+1;
                    RectSquare.right = RectSquare.left + SquareSize-2;
                    RectSquare.bottom = RectSquare.top + SquareSize-2;
                    canvas.drawRect(RectSquare, PaintSquare);
                }
            }
        }
        drawMario(canvas);
        if(mush_active) {
            drawMushroom(canvas);
        }if(star_active){
            drawStar(canvas);
        }if(goomba_active){
            drawGoomba(canvas);
        }if(plant_active){
            drawPlant(canvas);
        }
    }
}/*EOF*/