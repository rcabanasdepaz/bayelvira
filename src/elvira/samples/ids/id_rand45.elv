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
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
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
num-states = 3;
states = (d1_1 d1_2 d1_3);
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
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
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
pos_x =6;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =5;
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

link D2 V0;

link D2 X3;

link X1 V0;

link X1 X7;

link X10 D1;

link X10 X1;

link X11 D1;

link X11 X4;

link X12 D1;

link X12 X10;

link X12 X13;

link X12 X2;

link X12 X9;

link X13 D1;

link X13 X11;

link X13 X3;

link X4 V0;

link X4 X2;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X4;

link X7 D2;

link X7 X5;

link X8 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X1;

//Network Relationships: 

relation X9 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.935752081687952 0.0 0.06424791831204811 0.0 0.0 1.0 );
}

relation X10 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8035598797103038 1.0 0.19644012028969626 0.0 );
}

relation X11 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9832091079479088 0.13820792344381697 0.01679089205209118 0.5608074808316681 0.0 0.3009845957245148 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.753785374117363 0.24621462588263698 );
}

relation X13 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.5494317267003448 0.0 0.4505682732996552 );
}

relation X5 X7 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.9927803913198443 1.0 0.47961296068320647 0.6599651693851383 1.0 0.4763767502240437 0.0 0.7951582780826488 0.0 1.0 1.0 0.0 0.007219608680155617 0.0 0.5203870393167935 0.3400348306148617 0.0 0.5236232497759562 1.0 0.20484172191735112 1.0 0.0 0.0 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3177049479427892 0.14786320474512998 0.0 0.6822950520572107 0.8521367952548701 1.0 );
}

relation X7 X8 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.682437058452467 0.7506770392551914 0.0 1.0 1.0 0.0 0.31756294154753295 0.2493229607448086 1.0 0.0 0.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20486321301518315 0.7951367869848168 );
}

relation X1 X10 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.005832987840806308 0.8862679540069265 0.08825646235694919 0.48140771656238374 0.5101700952959707 0.7160413380545396 0.41494118522787804 0.14981781744931147 0.41833094355869416 0.0 0.0 1.0 1.0 0.564168272254399 0.0 0.31687206427405673 0.0 0.39078658997853294 0.8562193668414865 0.005842703301689465 0.2683996263746121 0.0 0.05971035812921601 0.28395866194546027 0.506014944144482 0.16799728460312527 0.0 1.0 0.0 0.0 0.0 0.2650518048861115 1.0 0.0 0.8389073799107062 0.6039335493176595 0.13794764531770726 0.10788934269138396 0.6433439112684387 0.5185922834376162 0.4301195465748132 0.0 0.07904387062763997 0.6821848979475632 0.5816690564413058 0.0 1.0 0.0 0.0 0.17077992285948942 0.0 0.6831279357259433 0.1610926200892938 0.005279860703807605 );
}

relation X2 X4 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7622027828910298 0.039556619660762696 0.8025916384841552 0.8053618242501823 0.6463802632238594 0.9388204644026306 0.23779721710897017 0.9604433803392374 0.19740836151584484 0.19463817574981762 0.3536197367761406 0.06117953559736934 );
}

relation X3 X13 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22238610677721252 0.961482892630357 0.5174878747156764 1.0 0.7776138932227875 0.038517107369642964 0.4825121252843237 0.0 );
}

relation X4 X5 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.28236327633823693 0.3542085644108754 0.8402480310341429 0.39250335132129155 0.32919389006101074 0.12248747032322357 1.0 0.0 0.7680778746701317 0.5100221174921357 0.535955159832712 0.1690197587079923 0.6537064926220014 0.0 0.15975196896585703 0.5171985103327565 0.0 0.3677216870068504 0.0 1.0 0.0647205358124219 0.0 0.0 0.8309802412920076 0.06393023103976166 0.6457914355891246 0.0 0.09029813834595186 0.6708061099389893 0.5097908426699259 0.0 0.0 0.16720158951744643 0.4899778825078644 0.4640448401672881 0.0 );
}

relation V0 D1 D2 X1 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (318.58277885924235 346.4037863987895 334.853923921154 239.1470897461906 266.96809728573777 255.41823480810223 0.0 313.9255075199275 302.375645042292 0.0 270.3699631500407 258.82010067240515 163.11326649744177 190.93427403698888 179.38441155935337 210.07067673163152 237.89168427117863 226.34182179354312 256.63990647354365 284.4609140130908 272.9110515354553 177.2042173604919 205.025224900039 0.0 0.0 251.98263513422876 240.43277265659324 180.60608322479482 208.42709076434198 0.0 0.0 0.0 0.0 148.12780434593282 175.94881188547993 164.3989494078444 311.6601978309929 339.48120537054007 0.0 232.22450871794115 0.0 248.49565377985277 0.0 0.0 295.4530640140425 235.62637458224407 263.44738212179124 251.8975196441557 156.19068546919232 184.01169300873943 172.4618305311039 0.0 0.0 219.41924076529367 );
}

}
