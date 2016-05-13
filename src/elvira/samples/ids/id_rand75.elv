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
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X2 X1;

link X3 X2;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X4;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9732108632769465 0.0 0.3714093504354385 0.34603750905196956 0.02678913672305353 1.0 0.6285906495645616 0.6539624909480305 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.12652491062320814 0.8734750893767919 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7829910330238311 0.21700896697616884 );
}

relation X4 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.3510122390557154 0.9345743324543553 0.11993601488773753 0.0 0.47388432794331053 0.0 0.7098767635521241 0.0 0.3418274944124884 0.50579660779433 0.0 0.0 0.0 0.0 0.16119938557980285 1.0 0.5041708020370037 0.0 0.6489877609442846 0.06542566754564472 0.8800639851122625 1.0 0.5261156720566895 1.0 0.2901232364478759 1.0 0.6581725055875115 0.49420339220567 0.0 0.0 1.0 1.0 0.8388006144201972 0.0 0.4958291979629963 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.0 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3025014384415502 0.28847312868276914 0.0 1.0 1.0 0.8715129815041129 0.6974985615584497 0.7115268713172309 1.0 0.0 0.0 0.128487018495887 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7830694391395955 0.08586275011948871 0.4248102866564686 0.8855639782918683 0.0 0.06415573554492093 0.5051881399500312 0.0 0.21693056086040458 0.8499815143355903 0.07000157339350012 0.11443602170813175 );
}

relation X2 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.09698867421123424 1.0 0.9201204517281882 0.0 1.0 0.0 0.6083450118511804 0.3590694251880065 0.2964396414297522 1.0 0.5306678689278387 0.23493278521582145 0.9030113257887658 0.0 0.07987954827181172 1.0 0.0 1.0 0.3916549881488197 0.6409305748119934 0.7035603585702478 0.0 0.4693321310721614 0.7650672147841785 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.4838835500417843 0.0 0.0 0.5161164499582157 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (76.6295710556177 67.54649392942798 95.69167247209396 86.60859534590423 32.072624347026604 22.98954722083688 );
}

}
