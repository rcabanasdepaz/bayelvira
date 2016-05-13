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
pos_y =2;
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
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =1;
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

link D1 X1;

link D1 X11;

link D1 X12;

link D1 X13;

link D1 X14;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X12;

link X10 D1;

link X10 X7;

link X11 D2;

link X11 V0;

link X11 X14;

link X12 D2;

link X12 V0;

link X13 D2;

link X13 X11;

link X14 D2;

link X14 X12;

link X14 X15;

link X14 X5;

link X15 D2;

link X15 V0;

link X15 X1;

link X2 X15;

link X4 X3;

link X4 X9;

link X5 X1;

link X6 X15;

link X6 X2;

link X7 D1;

link X7 X11;

link X8 D1;

link X8 X11;

link X9 D1;

link X9 X11;

//Network Relationships: 

relation X7 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.48627393147052345 1.0 0.5137260685294766 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X9 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4238864763816357 0.0 0.5761135236183643 1.0 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45855153613464356 0.5414484638653564 );
}

relation X11 X13 X7 X8 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.563251481206265 0.6994967336900274 0.33513701289778497 0.2318904500584693 0.9488331381334753 0.0 0.3767529951256094 0.0 1.0 0.5651627785065751 0.386629013068514 0.0 0.9698882962576484 0.0 1.0 0.0 0.0 0.32949045045721814 0.6284817798466815 0.4512261724118577 0.0 1.0 0.9301948538212614 0.3722209102782694 0.792385753891685 0.0 0.8151346522829752 0.0 0.9414977885502507 0.0 0.9570098274585316 1.0 0.436748518793735 0.3005032663099726 0.6648629871022151 0.7681095499415306 0.051166861866524756 1.0 0.6232470048743906 1.0 0.0 0.43483722149342485 0.613370986931486 0.0 0.030111703742351555 1.0 0.0 0.0 0.0 0.6705095495427819 0.3715182201533185 0.5487738275881423 1.0 0.0 0.06980514617873866 0.6277790897217305 0.20761424610831503 1.0 0.18486534771702484 0.0 0.05850221144974928 1.0 0.042990172541468336 );
}

relation X12 X1 X14 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.926273874793628 0.8698896950763686 0.4025104581971137 0.0 0.06109032689197644 0.0 0.0 1.0 0.07372612520637192 0.13011030492363138 0.5974895418028863 1.0 0.9389096731080235 1.0 1.0 );
}

relation X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.766250308820283 1.0 0.23374969117971703 );
}

relation X14 X11 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.2353924072654559 0.5626220616960395 0.26471218720859574 0.0 0.764607592734544 0.43737793830396066 0.7352878127914042 );
}

relation X15 X2 X6 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.17284490446162154 0.3532684485731936 0.19621247047711202 0.0 0.0 1.0 0.6330830782323338 1.0 0.8271550955383785 0.6467315514268064 0.803787529522888 1.0 1.0 0.0 0.36691692176766616 );
}

relation X1 X15 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5469806934285423 0.09240606821192478 1.0 0.18666353809881064 0.0 1.0 0.5370957520633068 0.07601608416778678 0.4530193065714578 0.9075939317880752 0.0 0.8133364619011894 1.0 0.0 0.4629042479366932 0.9239839158322133 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 1.0 );
}

relation X3 D2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 0.0 0.29343671110187397 0.7180956048140376 0.7358707437719207 1.0 0.0 1.0 1.0 0.0 0.706563288898126 0.28190439518596244 0.26412925622807926 0.0 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9945966036497036 0.0054033963502964555 );
}

relation X5 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.0 0.0 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.516414355926395 0.0 0.48358564407360505 1.0 );
}

relation V0 D1 D2 X11 X12 X15 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (202.04223906432154 141.9290075178444 240.0004579626185 179.88722641614135 0.0 114.65736162562838 212.72881207040245 152.61558052392533 0.0 139.19118759629933 0.0 0.0 172.0327732505604 0.0 209.9909921488574 149.87776060238025 0.0 186.42719102592125 284.4986414706953 0.0 219.26877668018238 159.15554513370523 0.0 197.1137640320022 0.0 183.68937110437616 281.76082154915025 221.64759000267313 216.53095675863727 156.41772521216015 0.0 194.37594411045708 );
}

}
