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
values= table (0.4928309610646315 0.0 0.5071690389353687 1.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7721201210833699 0.22787987891663017 );
}

relation X9 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.08205178529723679 0.0 0.9179482147027632 1.0 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X11 X13 X7 X8 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.33583653322034246 0.0 0.0 0.0 0.3756923067136453 1.0 0.7275519554822649 1.0 0.0 0.0 1.0 0.0 0.0 1.0 1.0 0.9800222283697103 0.0 0.382644152629697 0.9744004309428974 0.0 0.9006538155227889 0.10134284222252225 0.616762553704075 0.40131535835016147 0.0 0.39380635813669584 1.0 0.0 0.8222856774806631 0.0 0.0 1.0 0.6641634667796574 0.0 1.0 0.0 0.6243076932863547 0.0 0.272448044517735 0.0 1.0 0.0 0.0 1.0 1.0 0.0 0.0 0.01997777163028966 1.0 0.6173558473703029 0.025599569057102558 1.0 0.0993461844772111 0.8986571577774778 0.38323744629592504 0.5986846416498386 1.0 0.6061936418633042 0.0 1.0 0.17771432251933691 1.0 1.0 );
}

relation X12 X1 X14 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2691523084520807 0.8604978253958193 0.8558244777220526 0.06182044084417192 0.8800549641775672 0.32000971301466746 0.515650221883047 0.5451880213696713 0.7308476915479192 0.13950217460418057 0.14417552227794736 0.9381795591558281 0.11994503582243275 0.6799902869853325 0.48434977811695307 0.45481197863032874 );
}

relation X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 1.0 0.0 );
}

relation X14 X11 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22576902466155796 1.0 0.48322499840387995 0.9276028262586239 0.774230975338442 0.0 0.51677500159612 0.07239717374137603 );
}

relation X15 X2 X6 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.733303327957217 0.0 1.0 0.6331160176437693 0.0 1.0 0.9430301607542682 1.0 0.26669667204278297 1.0 0.0 0.3668839823562307 1.0 0.0 0.05696983924573186 );
}

relation X1 X15 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.06923717217598878 0.9261249704649014 0.5021206908788725 0.1198127953051237 1.0 0.010999623148947474 0.0 0.6106009852616744 0.9307628278240112 0.07387502953509857 0.49787930912112754 0.8801872046948763 0.0 0.9890003768510526 1.0 0.3893990147383256 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.26456193423105695 0.8612384314296766 0.7354380657689431 0.13876156857032343 );
}

relation X3 D2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42633948069300937 0.0 0.4431030127083311 0.0 0.5630975989218195 1.0 0.41673283743702005 0.7751563974497344 0.5736605193069907 1.0 0.5568969872916688 0.0 0.43690240107818057 0.0 0.58326716256298 0.22484360255026573 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X5 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5182239981109583 0.45498359507703096 0.4817760018890417 0.5450164049229691 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9320894901595679 1.0 0.06791050984043218 0.0 );
}

relation V0 D1 D2 X11 X12 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (242.51180423054012 216.42133502462394 203.69605560930898 177.6055864033928 263.7172750615613 237.62680585564516 224.9015264403302 198.81105723441402 205.62403433219185 179.53356512627568 0.0 140.7178165050446 226.82950516321307 200.7390359572969 188.01375654198193 161.9232873360658 );
}

}
