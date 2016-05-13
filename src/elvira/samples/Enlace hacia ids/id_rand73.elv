// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X2 V0;

link X2 X1;

link X3 X2;

link X4 D2;

link X4 V0;

link X4 X6;

link X5 D2;

link X5 V0;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X2;

link X8 D1;

link X8 V0;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 0.4462674476096484 0.0 1.0 1.0 0.5537325523903516 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6074668985697912 0.3925331014302088 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8356725929329603 0.23739984578521872 1.0 0.5086403950317271 1.0 0.06891717009679318 0.0 0.6042946583721562 0.6947428170713973 0.1643274070670398 0.7626001542147812 0.0 0.4913596049682729 0.0 0.9310828299032068 1.0 0.39570534162784377 0.30525718292860266 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9469200302273716 0.027629311332088725 0.025450658440539613 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.3860724296321114 0.0 0.8880161529513925 0.0 0.0 1.0 0.6139275703678885 0.0 0.11198384704860753 1.0 0.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9907520867101275 0.5064435616212125 0.008740105693336983 0.9948925401038698 0.009247913289872432 0.49355643837878743 0.910743249210327 0.005107459896130155 0.0 0.0 0.08051664509633606 0.0 );
}

relation X2 D2 X3 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8011694219585818 0.35103457347418165 1.0 0.0 0.0 0.0 0.0 1.0 0.5548692157189256 1.0 0.18440852020727752 0.0 0.6557593652851431 1.0 0.0 0.0 0.5844708074045722 0.5209338476588817 0.0 0.0 0.8245020464136654 0.0 0.7238015590558393 1.0 0.19883057804141824 0.6489654265258185 0.0 0.0 1.0 1.0 0.0 0.0 0.4451307842810745 0.0 0.8155914797927225 0.0 0.34424063471485683 0.0 1.0 1.0 0.41552919259542775 0.47906615234111827 1.0 1.0 0.17549795358633452 1.0 0.2761984409441607 0.0 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5238679596421966 1.0 0.6851156416116437 0.4761320403578033 0.0 0.3148843583883564 );
}

relation V0 D1 D2 X2 X4 X5 X8 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (284.2294982629053 274.13228291072085 331.56850695419627 321.47129160201183 0.0 275.3988094769414 237.20413812097416 227.10692276878973 284.54314681226515 274.4459314600807 238.4706646871947 228.37344933501026 262.45397442097664 252.3567590687922 309.7929831122676 299.6957677600832 0.0 253.62328563501273 215.42861427904552 0.0 262.7676229703365 252.67040761815207 216.69514084526605 0.0 319.47040788701133 309.3731925348269 0.0 0.0 320.73693445323187 0.0 272.4450477450802 262.3478323928958 319.7840564363712 309.68684108418677 273.71157431130075 263.6143589591163 297.6948840450827 287.59766869289825 345.0338927363737 334.93667738418924 298.9614106113032 288.8641952591188 250.66952390315157 0.0 0.0 287.9113172422581 251.9360504693721 241.83883511718767 269.3063662355731 0.0 316.64537492686406 306.54815957467963 0.0 260.4756774496092 222.28100609364193 0.0 269.62001478493295 259.5227994327485 223.54753265986247 213.45031730767803 247.53084239364438 237.43362704145994 294.86985108493536 0.0 248.7973689598649 0.0 200.50548225171326 190.40826689952883 247.84449094300425 0.0 0.0 191.67479346574936 304.5472758596791 0.0 0.0 341.7890691987856 305.8138024258996 0.0 257.52191571774796 247.42470036556352 304.86092440903894 0.0 258.7884422839685 0.0 282.77175201775043 272.674536665566 330.1107607090414 320.013545356857 284.03827858397096 273.94106323178653 0.0 225.64917652363488 283.0854005671103 272.98818521492586 237.01291844203985 226.9157030898554 226.6300498849517 0.0 0.0 0.0 227.89657645117222 0.0 179.60468974302057 169.50747439083614 0.0 216.84648308212712 0.0 0.0 0.0 194.7573106908386 252.19353473431403 242.0963193821296 206.12105260924358 196.02383725705914 0.0 147.73195054890746 0.0 0.0 0.0 148.998477115128 0.0 251.7737441568733 309.2099682003487 0.0 263.1374860752783 0.0 0.0 0.0 262.1846080584176 252.08739270623317 0.0 0.0 240.09543566712907 229.99822031494463 287.4344443584201 277.33722900623565 241.3619622333496 231.26474688116517 193.07007552519792 182.9728601730135 0.0 230.3118688643045 194.33660209141846 184.23938673923402 );
}

}
