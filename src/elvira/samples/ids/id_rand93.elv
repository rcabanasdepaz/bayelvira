// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
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

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
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

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X10;

link X10 D2;

link X10 X9;

link X11 D1;

link X11 X2;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 V0;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X2;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.47359858557703094 0.7837233555754554 0.5264014144229691 0.21627664442454475 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X13 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24652909144925375 0.7005377680205868 0.7534709085507462 0.29946223197941324 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7818816098534188 1.0 0.21811839014658121 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6294689569075033 0.0 0.37053104309249674 1.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.19421098024790714 0.8057890197520928 );
}

relation X7 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7438709817805449 0.4057720473098167 1.0 0.6024864246442418 0.25612901821945516 0.5942279526901832 0.0 0.3975135753557582 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16839939534079 0.980863743748035 0.8316006046592099 0.019136256251964915 );
}

relation X9 X5 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 0.0 0.0 1.0 1.0 1.0 );
}

relation X10 X1 X14 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5401398804683076 1.0 0.2258453378688046 0.0 0.04322857763713048 0.27488287541669465 1.0 1.0 1.0 1.0 0.0 0.19219302045635947 0.0 1.0 0.494713001330693 1.0 0.45986011953169237 0.0 0.7741546621311954 1.0 0.9567714223628695 0.7251171245833054 0.0 0.0 0.0 0.0 1.0 0.8078069795436406 1.0 0.0 0.5052869986693069 );
}

relation X1 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.14716454268054346 0.8572672288617429 0.0 0.0 0.8528354573194565 0.1427327711382571 );
}

relation X2 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4026988696733073 1.0 0.05747666358871575 0.0 0.5973011303266927 0.0 0.9425233364112843 1.0 );
}

relation X3 D2 X2 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.9569351293851488 0.05067610243178882 1.0 1.0 0.0 0.5065047741694944 1.0 1.0 0.0 1.0 0.7919453665184082 1.0 0.45588483529439533 0.0 0.0 0.0 0.04306487061485122 0.9493238975682111 0.0 0.0 1.0 0.49349522583050565 0.0 0.0 0.0 0.0 0.20805463348159178 0.0 0.5441151647056046 1.0 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.4907693465990577 0.0 0.0 1.0 0.5092306534009423 1.0 1.0 );
}

relation V0 D1 D2 X2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 0.0 172.31282987615324 0.0 134.85240722012176 0.0 0.0 0.0 );
}

}
