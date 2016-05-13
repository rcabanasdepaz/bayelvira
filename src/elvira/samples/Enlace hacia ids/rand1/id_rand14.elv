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
values= table (0.0723673464949693 0.9276326535050307 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2623662159192026 0.5283719369238392 0.7376337840807973 0.4716280630761609 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1288039198304014 0.8711960801695986 );
}

relation X7 X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7614732749527713 0.6730011185503537 0.568061874059576 0.3312445245630522 0.23852672504722872 0.3269988814496462 0.43193812594042397 0.6687554754369478 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1730912362378711 0.6780686886029279 0.8269087637621289 0.321931311397072 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5732610107480302 0.7078513636968348 0.42673898925196985 0.2921486363031653 );
}

relation X1 D2 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.35770437154421475 0.2674542135238467 0.601428364351725 0.3672164121963785 0.6422956284557853 0.7325457864761534 0.3985716356482751 0.6327835878036213 );
}

relation X2 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5677745772287659 0.7145919775671751 0.7478262193318305 0.41224835965028245 0.5606013084252864 0.4928056009131113 0.7134822231345895 0.4067691434057622 0.43222542277123416 0.28540802243282504 0.2521737806681695 0.5877516403497176 0.4393986915747136 0.5071943990868887 0.28651777686541047 0.5932308565942379 );
}

relation X3 X1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5395949115215081 0.6484486081300083 0.7021665922515061 0.6970950010383944 0.46040508847849176 0.3515513918699917 0.2978334077484939 0.30290499896160566 );
}

relation V0 D1 D2 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (184.01230226394532 170.80454020508907 144.076786789529 130.86902473067278 214.48985732679193 201.2820952679357 174.55434185237564 161.3465797935194 );
}

}
