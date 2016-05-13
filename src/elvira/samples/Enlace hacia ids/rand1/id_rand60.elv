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
values= table (0.5938818753096625 0.4061181246903375 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5091736097501179 0.9003854880272615 0.490826390249882 0.09961451197273846 );
}

relation X11 X10 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5840353315020702 0.3238808617234026 0.2557250714752071 0.30728315775322274 0.41596466849792985 0.6761191382765973 0.7442749285247928 0.6927168422467773 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.13173504037632489 0.8682649596236751 );
}

relation X13 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9240350952128643 0.21758694318978158 0.0759649047871357 0.7824130568102184 );
}

relation X5 D1 X13 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.523883913376241 0.2690508878553087 0.011138257326362451 0.5524253334945595 0.12398068564104782 0.12765153989886427 0.49608709531336115 0.6697954561904002 0.47611608662375904 0.7309491121446913 0.9888617426736376 0.44757466650544053 0.8760193143589522 0.8723484601011356 0.5039129046866389 0.33020454380959985 );
}

relation X6 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8705841692720122 0.5117392389703099 0.7937687503191091 0.4042945085503562 0.12941583072798787 0.4882607610296902 0.206231249680891 0.5957054914496438 );
}

relation X7 D1 X13 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7216149011084912 0.9262454857423628 0.819134135488868 0.08434675064488277 0.10429779546634571 0.5175506400047688 0.5216890708445548 0.4074452446209351 0.2783850988915088 0.07375451425763721 0.180865864511132 0.9156532493551173 0.8957022045336543 0.48244935999523125 0.4783109291554451 0.5925547553790649 );
}

relation X8 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.41040756217700886 0.4973716611207282 0.5895924378229912 0.5026283388792718 );
}

relation X1 D1 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.10215987903083798 0.4713866196590626 0.2625255509952459 0.7149467116367184 0.8978401209691621 0.5286133803409375 0.7374744490047541 0.28505328836328153 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.49366993068031906 0.5063300693196809 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.46029435240602024 0.19709216301923685 0.5397056475939797 0.8029078369807631 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3021090056074181 0.9729427731950865 0.5675356917836835 0.22327081497378684 0.6978909943925818 0.027057226804913567 0.43246430821631643 0.7767291850262132 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
