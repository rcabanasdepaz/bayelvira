// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
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

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
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
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
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

link D1 X2;

link D1 X8;

link D2 V0;

link D2 X1;

link D2 X2;

link X1 X2;

link X1 X3;

link X4 D1;

link X4 X3;

link X4 X5;

link X5 D1;

link X5 X1;

link X6 D1;

link X6 X7;

link X7 D2;

link X7 V0;

link X7 X9;

link X8 D2;

link X8 X7;

link X9 D2;

//Network Relationships: 

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7305801013287311 0.11525628099679977 0.2694198986712689 0.8847437190032003 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X7 X8 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.0 1.0 0.0 0.0 1.0 0.0 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5507555279352779 1.0 0.44924447206472207 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.21519515484955498 0.5429009681984174 0.7848048451504449 0.4570990318015827 );
}

relation X1 X5 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7664137696865122 1.0 0.0 1.0 0.2335862303134879 0.0 1.0 );
}

relation X2 D2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2166081885372142 0.0 0.6343506653446208 0.39647550684302496 0.0 0.5310755643452323 0.39028400644998873 0.0 0.7833918114627858 0.0 0.3656493346553792 0.603524493156975 0.0 0.4689244356547677 0.6097159935500113 1.0 );
}

relation X3 X4 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.7676979464523283 0.0 0.0 1.0 0.23230205354767167 1.0 );
}

relation V0 D1 D2 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (140.63356186909138 163.89229928903376 224.90190926881075 248.16064668875313 161.10144480647438 184.36018222641678 245.36979220619378 268.6285296261361 );
}

}
