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

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
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

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =2;
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

link D1 X12;

link D1 X13;

link D1 X15;

link D1 X3;

link D2 V0;

link D2 X1;

link D2 X3;

link D2 X5;

link X10 D1;

link X10 X12;

link X11 D1;

link X11 X10;

link X12 D2;

link X12 V0;

link X12 X13;

link X13 D2;

link X13 V0;

link X14 D2;

link X14 X15;

link X15 D2;

link X15 X12;

link X15 X16;

link X16 D2;

link X16 V0;

link X16 X2;

link X2 X12;

link X3 X4;

link X3 X5;

link X5 X4;

link X6 X2;

link X6 X5;

link X7 D1;

link X7 X11;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X7 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6805218242093369 0.03260805014306703 0.319478175790663 0.9673919498569329 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9526536306268772 0.04734636937312278 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X10 X9 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.6132554520893349 0.37018992373610243 1.0 1.0 0.3867445479106651 0.6298100762638975 0.0 );
}

relation X11 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4871160326127219 0.396378048871159 0.512883967387278 0.603621951128841 );
}

relation X12 X10 X15 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 1.0 1.0 0.015782104091876855 0.23644379857251413 1.0 0.9735102960022083 0.17194291078713322 0.656564143046329 0.5178350512100109 0.7446296753251304 0.052884092992753796 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.984217895908123 0.7635562014274858 0.0 0.026489703997791686 0.8280570892128668 0.34343585695367096 0.48216494878998917 0.25537032467486964 0.9471159070072462 0.0 0.0 );
}

relation X13 X12 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3524717490658944 0.8529612221600237 0.9939375844395785 1.0 0.6475282509341056 0.1470387778399763 0.006062415560421566 0.0 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X15 X14 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8181782857163502 0.39472946254894103 1.0 0.0 0.1818217142836498 0.605270537451059 0.0 0.0 );
}

relation X16 X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5326463104051519 0.4591370905811621 0.4673536895948481 0.5408629094188379 );
}

relation X1 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.058916193449006324 1.0 1.0 1.0 0.9410838065509937 0.0 );
}

relation X2 X6 X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6416894649384499 0.0 1.0 0.5099297635385711 0.3583105350615501 1.0 0.0 0.4900702364614288 );
}

relation X3 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5206060761101847 1.0 0.0 1.0 0.47939392388981533 0.0 1.0 );
}

relation X4 X5 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.7855113216040301 0.0 0.0 1.0 0.21448867839596986 1.0 );
}

relation X5 X3 X6 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5003724245158424 0.37066779072189215 0.8562429087278922 0.8585323435123705 0.0 0.1508441390012841 0.6439468995780377 1.0 0.49962757548415765 0.6293322092781078 0.14375709127210778 0.14146765648762946 1.0 0.8491558609987159 0.35605310042196214 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation V0 D1 D2 X12 X13 X16 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (228.4023135993305 0.0 307.8112845204259 259.6972737542265 242.04924042342492 193.9352296572255 321.4582113445203 0.0 231.9808536673373 183.8668429011379 311.3898245884327 263.27581382223326 0.0 197.5137697252323 325.0367514125271 276.92274064632767 0.0 186.07385289255723 313.59683457985204 265.4828238136526 247.83479048285105 199.72077971665163 327.24376140394645 279.129750637747 0.0 189.652392960564 317.1753746478588 269.06136388165936 251.4133305508578 203.2993197846584 0.0 282.70829070575377 );
}

}
