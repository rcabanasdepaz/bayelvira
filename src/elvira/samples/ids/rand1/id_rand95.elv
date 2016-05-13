// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
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

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
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

link D1 X1;

link D1 X5;

link D1 X6;

link D1 X7;

link D2 V0;

link D2 X3;

link D2 X4;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X5;

link X13 X7;

link X13 X8;

link X2 X4;

link X3 X1;

link X5 D2;

link X5 X6;

link X6 D2;

link X6 X7;

link X7 D2;

link X8 D2;

link X8 X5;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.26182821433552445 0.7381717856644756 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5313307907048723 0.43551956156457033 0.4686692092951276 0.5644804384354297 );
}

relation X11 X10 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4367950977768043 0.5623681982209444 0.06724575544217079 0.5078272948628354 0.5632049022231956 0.4376318017790555 0.9327542445578293 0.49217270513716466 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22204033869305137 0.7779596613069485 );
}

relation X13 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4979776004549435 0.46879438524236666 0.5020223995450565 0.5312056147576334 );
}

relation X5 D1 X13 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7897031690564811 0.03063549850722376 0.4251476030065321 0.38126647033860633 0.863786279825245 0.7368082122408713 0.8825610843852876 0.4922572911103264 0.21029683094351884 0.9693645014927762 0.574852396993468 0.6187335296613936 0.13621372017475505 0.26319178775912855 0.11743891561471237 0.5077427088896735 );
}

relation X6 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7835164009880046 0.45784343249875825 0.3552454099351917 0.39931118129748483 0.2164835990119954 0.5421565675012419 0.6447545900648083 0.6006888187025152 );
}

relation X7 D1 X13 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.505458488390095 0.7313200722941124 0.5646923094618981 0.18875903984858344 0.44675447259490425 0.5205340750244054 0.7509344307499719 0.9720328235669422 0.4945415116099051 0.2686799277058875 0.4353076905381019 0.8112409601514166 0.5532455274050958 0.4794659249755947 0.24906556925002815 0.027967176433057787 );
}

relation X8 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7693958739736688 0.09539579771761837 0.23060412602633118 0.9046042022823817 );
}

relation X1 D1 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6019312346303904 0.32702145925320963 0.5699870220969794 0.5017649960024044 0.39806876536960956 0.6729785407467904 0.43001297790302073 0.4982350039975955 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.27342428591833406 0.7265757140816659 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4585772778533996 0.5203168120574461 0.5414227221466004 0.479683187942554 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7005571279297971 0.6019296606115055 0.038694792867646766 0.4457472592743429 0.2994428720702029 0.3980703393884945 0.9613052071323531 0.554252740725657 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
