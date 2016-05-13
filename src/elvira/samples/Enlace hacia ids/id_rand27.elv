// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x10_1 x10_2 x10_3);
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

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =1;
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

link D1 X4;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X10 D1;

link X10 X5;

link X3 X1;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 V0;

link X5 X4;

link X5 X6;

link X6 D2;

link X6 V0;

link X7 D1;

link X7 X8;

link X7 X9;

link X8 D1;

link X8 X5;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8122813391668244 0.1877186608331755 );
}

relation X8 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.31563426177179765 1.0 0.6843657382282023 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.93469577975687 0.4946082330628067 0.06530422024312994 0.5053917669371933 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42013287105845076 0.0 0.050710405869443136 0.0 0.529156723072106 0.0 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.013767056316227242 0.36053564779035085 0.42946883614963427 0.614765057039355 1.0 0.34650461578234354 0.9277142983024634 0.0 0.0 0.9862329436837728 0.6394643522096491 0.5705311638503657 0.385234942960645 0.0 0.6534953842176565 0.07228570169753656 1.0 1.0 );
}

relation X5 X8 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4203647338866676 0.0 0.09711932912199064 0.6343012997401396 0.0 9.507030025947222E-4 0.3643101456142458 0.499012513741546 0.08613720016891893 0.2941837223585126 0.0 0.4809095131047892 0.21532512049908664 0.500987486258454 0.8167434707090905 0.07151497790134799 1.0 0.5181397838926161 );
}

relation X6 X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.5018211282252283 0.4445502948664992 0.7435675921929883 0.0 0.0 0.0 0.4981788717747717 0.5554497051335008 0.2564324078070117 1.0 1.0 );
}

relation X1 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.5291170202342083 0.0 0.3073613545617071 0.6144724220326865 0.7853801700137457 0.5069768068154274 0.4843036277925842 0.7645526474772721 0.11617532747980949 0.4851136029873497 0.8162441552345512 0.0 0.01829814076624978 1.0 0.0 0.05570838233913299 0.05232528688670302 0.0 0.0 0.2354473525227278 0.01750262189360413 0.5132060984567403 0.18375584476544887 0.0 0.45258483899954194 0.0 0.6926386454382928 0.32981919562818046 0.16229454309955116 0.4930231931845726 0.5156963722074157 0.0 0.8663220506265865 0.0016802985559099975 0.0 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.5311992678997512 0.0 0.9921964368431396 0.0 0.0 0.0 0.4688007321002488 1.0 0.007803563156860353 1.0 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 1.0 );
}

relation V0 D1 D2 X5 X6 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (115.53756730288868 0.0 207.4199907783644 155.79259851917942 118.24820252323329 66.62081026404832 120.046947950788 68.41955569160301 211.9293714262637 0.0 122.75758317113261 71.13019091194762 134.30117510192076 82.67378284273579 226.18359857739648 174.5562063182115 137.01181032226538 85.3844180630804 138.81055574982005 87.18316349063508 230.69297922529577 179.0655869661108 141.5211909701647 89.89379871097971 0.0 76.91729289995634 0.0 0.0 131.25532037948594 79.62792812030096 133.05406580704064 81.42667354785567 0.0 173.3090970233314 135.76470102738526 84.13730876820028 );
}

}
