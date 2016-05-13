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
values= table (0.3115773259329417 0.6884226740670584 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3924383925855901 0.7644329609826411 0.60756160741441 0.23556703901735887 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6016914256241269 0.398308574375873 );
}

relation X7 X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4790621642688175 0.9828891911620471 0.277439588834783 0.4573084296158314 0.5209378357311826 0.017110808837952865 0.722560411165217 0.5426915703841687 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1813639970816053 0.6670821999762793 0.8186360029183947 0.3329178000237206 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.26143864557619634 0.6705938061393245 0.7385613544238036 0.32940619386067543 );
}

relation X1 D2 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.47797554764761774 0.937195375622343 0.41125643398104483 0.13794790386347605 0.5220244523523823 0.06280462437765708 0.5887435660189551 0.8620520961365239 );
}

relation X2 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8341072996808806 0.5819935913477405 0.2804462150879462 0.2999740761547218 0.5493095456706809 0.45384315311626866 0.3889489278330173 0.5445940174134177 0.16589270031911948 0.4180064086522595 0.7195537849120538 0.7000259238452782 0.4506904543293191 0.5461568468837313 0.6110510721669826 0.4554059825865823 );
}

relation X3 X1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8721511649971007 0.20837398889763006 0.5980345458033913 0.7063591388348401 0.1278488350028993 0.79162601110237 0.40196545419660884 0.29364086116515975 );
}

relation V0 D1 D2 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (184.01230226394532 170.80454020508907 144.076786789529 130.86902473067278 214.48985732679193 201.2820952679357 174.55434185237564 161.3465797935194 );
}

}
