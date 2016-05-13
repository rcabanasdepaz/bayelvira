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
values= table (0.7725035820656544 0.2274964179343457 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.13169771744099767 1.0 0.8683022825590024 );
}

relation X11 X12 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5470078799659396 0.41290151235999856 0.5996332610948478 0.6974577972401125 0.4529921200340605 0.5870984876400015 0.4003667389051522 0.30254220275988747 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X13 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.12628235262411114 0.0 0.8737176473758889 );
}

relation X5 X13 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5009216383168016 0.08932484245504846 0.12692602100242423 0.9475798694298179 0.905280610875141 1.0 0.4350908438756758 1.0 0.4990783616831984 0.9106751575449515 0.8730739789975758 0.052420130570182175 0.09471938912485904 0.0 0.5649091561243242 0.0 );
}

relation X6 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4401146627563969 0.5110628143364145 1.0 0.0 0.5598853372436031 0.4889371856635853 0.0 0.0 );
}

relation X7 X13 X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.34411694099651496 0.785405371787684 0.4658154392684903 1.0 0.0 1.0 0.9871908854468904 0.011418185385103564 0.6558830590034852 0.21459462821231592 0.5341845607315098 0.0 0.0 0.0 0.012809114553109593 0.9885818146148965 );
}

relation X8 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.26303052326474646 0.5857773271893779 0.7369694767352536 0.414222672810622 );
}

relation X1 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.530829017967537 1.0 0.11343470918797825 0.9875232160202265 0.46917098203246294 0.0 0.8865652908120218 0.012476783979773554 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 1.0 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.39922009011299386 0.0 0.6611110781952053 0.0 0.6007799098870061 1.0 0.33888892180479474 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (121.74777108610554 94.44410711225423 0.0 149.52712991147513 );
}

}
