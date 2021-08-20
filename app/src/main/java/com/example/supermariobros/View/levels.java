package com.example.supermariobros.View;

public class levels {
    String[][] level_1;
    String[][] level_2;
    String[][] level_3;

    public levels(){
        this.level_1 = new String [100][10];
        this.level_2 = new String [100][10];
        this.level_3 = new String [100][10];
    }
/*Initializing the content into the level's data structure*/
    public void initialize_Level(){
        for(int i=0; i<100; i++){
            for(int j=0; j<10; j++){
                this.level_1[i][j] = "background";
                this.level_2[i][j] = "background";
                this.level_3[i][j] = "background";
            }
        }
        //level 1
        for(int n=0; n<100; n++){
            this.level_1[n][8] = "ground";
            this.level_1[n][9] = "ground";
        }
        this.level_1[5][4] = "star_block";
        this.level_1[7][4] = "block";
        this.level_1[8][4] = "coin_block";
        this.level_1[9][1] = "coin_block";
        this.level_1[9][4] = "block";
        this.level_1[10][4] = "power_block";
        this.level_1[11][4] = "block";
        this.level_1[11][7] = "goomba";
        pipes(15,6,level_1);
        pipes(20,5, level_1);
        this.level_1[20][5] = "plant";
        this.level_1[20][7] = "ground";
        this.level_1[21][7] = "ground";
        pipes(25,4, level_1);
        pipes(25,6, level_1);
        this.level_1[33][4] = "power_block";
        this.level_1[37][8] = "background";
        this.level_1[37][9] = "background";
        this.level_1[38][8] = "background";
        this.level_1[38][9] = "background";
        this.level_1[41][5] = "block";
        this.level_1[42][5] = "coin_block";
        this.level_1[43][5] = "block";
        this.level_1[44][2] = "block";
        this.level_1[45][2] = "block";
        this.level_1[46][2] = "block";
        this.level_1[47][2] = "block";
        this.level_1[48][2] = "block";
        this.level_1[49][2] = "block";
        this.level_1[48][8] = "background";
        this.level_1[49][8] = "background";
        this.level_1[48][9] = "background";
        this.level_1[49][9] = "background";
        this.level_1[50][7] = "ground";
        this.level_1[52][2] = "block";
        this.level_1[53][2] = "block";
        this.level_1[54][2] = "block";
        this.level_1[54][5] = "coin_block";
        this.level_1[56][5] = "block";
        this.level_1[57][5] = "block";
        this.level_1[58][5] = "block";
        this.level_1[57][2] = "power_block";
        this.level_1[61][7] = "goomba";
        this.level_1[64][7] = "block";
        this.level_1[65][6] = "block";
        this.level_1[66][5] = "block";
        this.level_1[68][8] = "background";
        this.level_1[69][8] = "background";
        this.level_1[68][9] = "background";
        this.level_1[69][9] = "background";
        this.level_1[71][5] = "block";
        this.level_1[72][6] = "block";
        this.level_1[73][7] = "block";
        pipes(77,6, level_1);
        this.level_1[81][4] = "block";
        this.level_1[82][4] = "coin_block";
        this.level_1[83][4] = "block";
        this.level_1[84][7] = "goomba";
        this.level_1[86][7] = "block";
        this.level_1[87][6] = "block";
        this.level_1[88][5] = "block";
        this.level_1[89][4] = "block";
        this.level_1[90][3] = "block";
        this.level_1[91][3] = "block";
        this.level_1[92][3] = "block";
        this.level_1[98][5] = "block";
        this.level_1[98][7] = "flag";


        //level 2
        for(int n=0; n<100; n++){
            this.level_2[n][8] = "ground";
            this.level_2[n][9] = "ground";
        }
        this.level_2[2][5] = "star_block";
        this.level_2[5][5] = "power_block";
        this.level_2[7][7] = "block";
        this.level_2[9][6] = "block";
        this.level_2[9][7] = "block";
        this.level_2[11][5] = "block";
        this.level_2[11][6] = "block";
        this.level_2[11][7] = "block";
        this.level_2[13][5] = "block";
        this.level_2[13][6] = "block";
        this.level_2[13][7] = "block";
        this.level_2[15][6] = "block";
        this.level_2[15][7] = "block";
        this.level_2[19][2] ="block";
        this.level_2[19][3] = "block";
        this.level_2[19][4] = "block";
        this.level_2[20][4] = "block";
        this.level_2[21][4] = "block";
        this.level_2[21][3] = "block";
        this.level_2[21][2] ="block";
        this.level_2[22][2] = "block";
        this.level_2[23][2] = "block";
        this.level_2[24][2] = "block";
        this.level_2[24][3] = "block";
        this.level_2[24][4] ="block";
        this.level_2[25][4] = "block";
        this.level_2[26][4] = "block";
        this.level_2[26][3] = "block";
        this.level_2[26][2] = "block";
        this.level_2[21][7] = "goomba";
        this.level_2[30][1] ="block";this.level_2[30][3] = "block";
        this.level_2[30][2] = "block";this.level_2[30][4] = "block";
        this.level_2[31][1] ="block";this.level_2[31][3] = "block";
        this.level_2[31][2] = "block";this.level_2[31][4] = "block";
        this.level_2[32][0] = "block";this.level_2[32][5] = "block";
        this.level_2[32][4] = "block";this.level_2[32][6] = "block";
        this.level_2[33][0] = "block";this.level_2[33][5] = "block";
        this.level_2[33][4] ="block";this.level_2[33][6] = "block";
        this.level_2[36][0] = "block";this.level_2[38][7] = "goomba";
        this.level_2[36][4] ="block";
        this.level_2[37][0] = "block";this.level_2[38][0] = "block";
        this.level_2[37][4] = "block";this.level_2[38][4] = "block";
        this.level_2[39][0] = "block";this.level_2[39][3] = "block";
        this.level_2[39][1] = "block";this.level_2[39][4] = "block";
        this.level_2[39][2] ="block";this.level_2[40][0] = "block";
        this.level_2[40][1] = "block";this.level_2[40][3] = "block";
        this.level_2[40][2] ="block";this.level_2[40][4] = "block";
        this.level_2[43][7] = "ground";
        this.level_2[44][8] = "background";this.level_2[44][9] = "background";
        this.level_2[45][8] = "background";this.level_2[45][9] = "background";
        this.level_2[46][7] = "ground";
        this.level_2[48][4] = "block";this.level_2[48][3] = "coin_block";
        this.level_2[49][4] = "block";this.level_2[49][3] = "coin_block";
        this.level_2[50][4] = "block";this.level_2[50][3] = "coin_block";
        this.level_2[51][4] = "block";this.level_2[51][3] = "coin_block";
        this.level_2[52][4] = "block";this.level_2[52][3] = "coin_block";
        this.level_2[53][7] = "goomba";
        pipes(56,6, level_2);pipes(60,5, level_2);
        pipes(60,7, level_2);pipes(63,7, level_2);
        this.level_2[70][7] = "block";this.level_2[74][4] = "block";
        this.level_2[71][6] ="block";this.level_2[79][5] = "block";
        this.level_2[72][5] = "block";this.level_2[80][5] ="block";
        this.level_2[73][4] = "block";this.level_2[81][5] = "block";
        this.level_2[68][7] = "goomba";
        this.level_2[84][5] ="block";this.level_2[85][5] = "block";this.level_2[86][5] = "block";
        this.level_2[75][8] = "background";this.level_2[75][9] = "background";
        this.level_2[76][8] = "background";this.level_2[76][9] = "background";
        this.level_2[77][8] = "background";this.level_2[77][9] = "background";
        this.level_2[83][8] = "background";this.level_2[83][9] = "background";
        this.level_2[84][8] = "background";this.level_2[84][9] = "background";
        this.level_2[85][8] = "background";this.level_2[85][9] = "background";
        this.level_2[86][8] = "background";this.level_2[86][9] = "background";
        this.level_2[87][8] = "background";this.level_2[87][9] = "background";
        pipes(89,6, level_2);
        this.level_2[91][7] = "block";this.level_2[92][6] = "block";this.level_2[94][5] ="block";
        this.level_2[92][7] = "block";this.level_2[93][6] = "block";this.level_2[95][5] ="block";
        this.level_2[93][7] = "block";this.level_2[94][6] = "block";this.level_2[94][4] = "block";
        this.level_2[94][7] = "block";this.level_2[95][6] = "block";this.level_2[95][4] = "block";
        this.level_2[95][7] = "block";this.level_2[93][5] = "block";
        this.level_2[98][7] = "flag";


        //level 3
        for(int n=0; n<4; n++){
            this.level_3[n][8] = "ground";
            this.level_3[n][9] = "ground";
        }
        this.level_3[6][6] = "leaves";this.level_3[8][6] = "leaves";
        this.level_3[7][6] = "leaves";this.level_3[9][6] = "leaves";
        this.level_3[7][7] = "trunk";this.level_3[7][8] = "trunk";this.level_3[7][9] = "trunk";
        this.level_3[8][7] = "trunk";this.level_3[8][8] = "trunk";this.level_3[8][9] = "trunk";
        this.level_3[15][3] = "leaves";this.level_3[17][3] = "leaves";
        this.level_3[16][3] = "leaves";this.level_3[18][3] = "leaves";
        this.level_3[16][4] = "trunk";
        this.level_3[17][4] = "trunk";this.level_3[17][4] = "trunk";
        this.level_3[13][5] = "leaves";this.level_3[15][5] = "leaves";this.level_3[17][5] = "leaves";
        this.level_3[14][5] = "leaves";this.level_3[16][5] = "leaves";this.level_3[18][5] = "leaves";
        this.level_3[15][6] = "trunk";this.level_3[15][7] = "trunk";this.level_3[15][8] = "trunk";this.level_3[15][9] = "trunk";
        this.level_3[16][6] = "trunk";this.level_3[16][7] = "trunk";this.level_3[16][8] = "trunk";this.level_3[16][9] = "trunk";
        this.level_3[20][8] = "leaves";this.level_3[21][8] = "leaves";this.level_3[22][8] = "leaves";
        this.level_3[21][9] = "trunk";
        this.level_3[24][5] = "leaves";this.level_3[26][5] = "leaves";this.level_3[28][5] = "leaves";
        this.level_3[25][5] = "leaves";this.level_3[27][5] = "leaves";
        this.level_3[25][6] = "trunk";this.level_3[25][7] = "trunk";this.level_3[25][8] = "trunk";this.level_3[25][9] = "trunk";
        this.level_3[26][6] = "trunk";this.level_3[26][7] = "trunk";this.level_3[26][8] = "trunk";this.level_3[26][9] = "trunk";
        this.level_3[27][6] = "trunk";this.level_3[27][7] = "trunk";this.level_3[27][8] = "trunk";this.level_3[27][9] = "trunk";
        this.level_3[30][3] = "leaves";this.level_3[32][3] = "leaves";
        this.level_3[31][3] = "leaves";this.level_3[33][3] = "leaves";
        this.level_3[31][4] = "trunk";this.level_3[31][7] = "trunk";this.level_3[32][4] = "trunk";this.level_3[32][7] = "trunk";
        this.level_3[31][5] = "trunk";this.level_3[31][8] = "trunk";this.level_3[32][5] = "trunk";this.level_3[32][8] = "trunk";
        this.level_3[31][6] = "trunk";this.level_3[31][9] = "trunk";this.level_3[32][6] = "trunk";this.level_3[32][9] = "trunk";
        this.level_3[36][8] = "leaves";this.level_3[38][8] = "leaves";
        this.level_3[37][8] = "leaves";this.level_3[39][8] = "leaves";
        this.level_3[37][9] = "trunk";this.level_3[38][9] = "trunk";
        this.level_3[41][5] = "block";this.level_3[42][5] = "block";this.level_3[43][4] = "block";this.level_3[43][5] = "block";
        this.level_3[45][7] = "block";this.level_3[45][6] = "block";this.level_3[45][5] = "block";
        this.level_3[46][5] = "power_block";
        this.level_3[49][7] = "block";
        this.level_3[45][8] = "leaves";this.level_3[47][8] = "leaves";this.level_3[49][8] = "leaves";
        this.level_3[46][8] = "leaves";this.level_3[48][8] = "leaves";
        this.level_3[47][9] = "trunk";this.level_3[47][9] = "trunk";this.level_3[48][9] = "trunk";
        this.level_3[52][8] = "leaves";this.level_3[54][8] = "leaves";
        this.level_3[53][8] = "leaves";this.level_3[55][8] = "leaves";
        this.level_3[53][9] = "trunk";this.level_3[54][9] = "trunk";
        this.level_3[56][4] = "leaves";this.level_3[57][4] = "leaves";this.level_3[58][4] = "leaves";
        this.level_3[57][5] = "trunk";this.level_3[57][7] = "trunk";this.level_3[57][9] = "trunk";
        this.level_3[57][6] = "trunk";this.level_3[57][8] = "trunk";
        this.level_3[62][6] = "leaves";this.level_3[64][6] = "leaves";this.level_3[66][6] = "leaves";
        this.level_3[63][6] = "leaves";this.level_3[65][6] = "leaves";
        this.level_3[68][5] = "block";
        this.level_3[63][7] = "trunk";this.level_3[64][7] = "trunk";this.level_3[65][7] = "trunk";
        this.level_3[63][8] = "trunk";this.level_3[64][8] = "trunk";this.level_3[65][8] = "trunk";
        this.level_3[63][9] = "trunk";this.level_3[64][9] = "trunk";this.level_3[65][9] = "trunk";
        this.level_3[71][4] = "block";this.level_3[78][5] = "block";this.level_3[77][5] = "block";
        this.level_3[72][4] = "block";this.level_3[79][5] = "block";this.level_3[75][8] = "block";
        this.level_3[74][8] = "block";
        this.level_3[90][7] = "flag";
        for(int n=83; n<100; n++){
            this.level_3[n][8] = "ground";
            this.level_3[n][9] = "ground";
        }

    }
    /*initializing pipes*/
    private void pipes(int x, int y, String[][] level){
        level[x][y] = "pipe";
        level[x][y+1] = "pipe";
        level[x+1][y] = "pipe";
        level[x+1][y+1] = "pipe";
    }
}/*EOF*/
