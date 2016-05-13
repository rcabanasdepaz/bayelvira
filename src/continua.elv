// Bayesian Network
// Elvira format 

bnet  Continuous1 { 

// Network Properties

version = 1.0;
default node states = (absent , present);


// Network Variables 

node X1(continuous) {
title = "NodeX1";
kind-of-node = chance;
type-of-variable = continuous;
pos_x =201;
pos_y =118;
relevance = 7.0;
min = 0;
max = 1;
}

node X2(continuous) {
title = "NodeX2";
kind-of-node = chance;
type-of-variable = continuous;
pos_x =201;
pos_y =128;
relevance = 7.0;
min = 0;
max = 1;
}

node X3(continuous) {
title = "NodeX3";
kind-of-node = chance;
type-of-variable = continuous;
pos_x =181;
pos_y =138;
relevance = 7.0;
min = 0;
max = 1;
}

node X4(continuous) {
title = "NodeX4";
kind-of-node = chance;
type-of-variable = continuous;
pos_x =221;
pos_y =138;
relevance = 7.0;
min = 0;
max = 1;
}

node X5(continuous) {
title = "NodeX5";
kind-of-node = chance;
type-of-variable = continuous;
pos_x =201;
pos_y =148;
relevance = 7.0;
min = 0;
max = 1;
}

// links of the associated graph:

link X1 X2;
link X2 X3;
link X2 X4;
link X3 X5;
link X4 X5;


// Relations;

relation X1 {
kind-of-relation = potential;
values= continuous-tree (
case X1(0.0,0.5,1.0) {
0=0.9 + 1.0*exp(1.0*X1);
1=0.9+ 1.0*exp(1.0*X1);
}
);
}

relation X2 X1 {
kind-of-relation = potential;
values= continuous-tree (
case X1(0.0,0.5,1.0) {
0=case X2(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X2);
1=0.9+1.0*exp(1.0*X2);
}
1=case X2(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X2);
1=0.9+1.0*exp(1.0*X2);
}
}
);
}

relation X3 X2 {
kind-of-relation = potential;
values= continuous-tree (
case X2(0.0,0.5,1.0) {
0=case X3(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X3);
1=0.9+1.0*exp(1.0*X3);
}
1=case X3(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X3);
1=0.9+1.0*exp(1.0*X3);
}
}
);
}

relation X4 X2 {
kind-of-relation = potential;
values= continuous-tree (
case X2(0.0,0.5,1.0) {
0=case X4(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X4);
1=0.9+1.0*exp(1.0*X4);
}
1=case X4(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X4);
1=0.9+1.0*exp(1.0*X4);
}
}
);
}

relation X5 X3 X4 {
kind-of-relation = potential;
values= continuous-tree (
case X3(0.0,0.5,1.0) {
0=case X4(0.0,0.5,1.0) {
0=case X5(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X5);
1=0.9+1.0*exp(1.0*X5);
}
1=case X5(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X5);
1=0.9+1.0*exp(1.0*X5);
}
}
1=case X4(0.0,0.5,1.0) {
0=case X5(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X5);
1=0.9+1.0*exp(1.0*X5);
}
1=case X5(0.0,0.5,1.0) {
0=0.9+1.0*exp(1.0*X5);
1=0.9+1.0*exp(1.0*X5);
}
}
}
);
}

}