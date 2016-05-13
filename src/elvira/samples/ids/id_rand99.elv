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
pos_x =7;
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

link D1 X2;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X12;

link X11 X8;

link X12 D1;

link X12 X9;

link X2 X4;

link X3 V0;

link X4 X1;

link X4 X3;

link X5 D2;

link X5 X6;

link X5 X7;

link X6 D2;

link X6 X8;

link X7 D2;

link X7 X6;

link X7 X8;

link X7 X9;

link X8 D2;

link X8 X4;

link X9 D1;

link X9 X3;

//Network Relationships: 

relation X9 X7 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9634051337861262 0.039979942221318766 0.46932777188583685 0.9030508616503748 0.03659486621387374 0.9600200577786813 0.5306722281141631 0.09694913834962512 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 1.0 );
}

relation X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5727071625606737 0.30269029401998543 0.4272928374393263 0.6973097059800146 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4981921660153384 0.5018078339846616 );
}

relation X6 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.1103580790918431 0.8510807437570326 0.0989545432382343 1.0 0.5024803222076512 0.42641461444506745 0.6681988251789625 0.0 0.8896419209081569 0.14891925624296742 0.9010454567617656 0.0 0.49751967779234874 0.5735853855549327 0.3318011748210377 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.4512201271118217 0.0 0.5487798728881783 );
}

relation X8 X6 X7 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7516667978562492 0.5902148030134698 0.12187082624897169 0.6014236095650293 0.5753918319681296 0.33383493593080343 0.31746911352731444 0.5937728617156901 0.2483332021437507 0.4097851969865301 0.8781291737510283 0.3985763904349707 0.42460816803187035 0.6661650640691966 0.6825308864726857 0.4062271382843099 );
}

relation X1 D2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.0 0.0 0.0 0.0 0.4162244871490905 1.0 0.0 0.0 1.0 1.0 1.0 1.0 0.5837755128509095 0.0 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.23543485955295126 1.0 0.7645651404470487 );
}

relation X3 X4 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.965745095384401 0.0 0.8098262586047857 1.0 0.7157531969020737 1.0 0.0 1.0 0.034254904615598984 1.0 0.19017374139521434 0.0 0.28424680309792616 0.0 1.0 );
}

relation X4 X8 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6453629785910902 0.16711828770399442 1.0 0.0 0.3546370214089098 0.8328817122960055 0.0 1.0 );
}

relation V0 D1 D2 X3 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 178.42797680148138 0.0 160.96140496694966 118.81924124730364 142.66011422369056 101.35266941277192 125.19354238915881 );
}

}
