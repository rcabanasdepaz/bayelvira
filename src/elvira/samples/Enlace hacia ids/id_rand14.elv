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
values= table (0.7019215558610385 0.2980784441389615 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4414693275829396 1.0 0.5585306724170604 0.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X7 X8 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5726525680987646 0.0 0.682007540472969 0.045594919394133224 0.42734743190123525 0.0 0.31799245952703104 0.9544050806058668 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.47024503256327477 1.0 0.5297549674367252 0.0 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.0 0.0 );
}

relation X1 X5 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.6443672772622067 1.0 1.0 1.0 0.35563272273779334 0.0 );
}

relation X2 D2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.3721107495378162 0.0 1.0 1.0 0.6759560953658619 0.2295940998011784 0.0 0.0 0.6278892504621838 0.0 0.0 0.0 0.324043904634138 0.7704059001988216 1.0 );
}

relation X3 X4 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8676705940264924 1.0 0.0 0.09338824772494123 0.13232940597350762 0.0 1.0 0.9066117522750587 );
}

relation V0 D1 D2 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (81.88468265304681 0.0 139.2977049086217 0.0 120.50933684603639 161.08350628182598 177.92235910161128 218.49652853740088 );
}

}
