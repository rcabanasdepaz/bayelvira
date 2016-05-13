// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
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

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
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

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X7;

link X10 X5;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X5;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X9;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

//Network Relationships: 

relation X11 X6 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6255548446673065 0.0 0.0 0.5521682508508445 0.3744451553326935 1.0 1.0 0.44783174914915547 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9544439746277645 0.045556025372235485 );
}

relation X13 X2 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.27962648019600767 0.0 0.7545637548249512 1.0 0.7203735198039923 1.0 0.24543624517504875 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.39062537714591467 0.9111947564449362 0.6093746228540854 0.08880524355506385 );
}

relation X5 X14 X8 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.9474646580407159 0.2225532709545169 1.0 0.525477671654347 0.4051676309571038 0.0 1.0 0.0 0.05253534195928413 0.7774467290454831 0.0 0.47452232834565294 0.5948323690428962 1.0 0.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5090980415481705 0.4909019584518294 );
}

relation X7 X1 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20161317912661064 0.6078845814912311 0.6553276212293847 0.7042782216768461 0.47868511116563517 0.353850828361419 1.0 0.0 0.7983868208733894 0.39211541850876874 0.34467237877061524 0.295721778323154 0.5213148888343648 0.646149171638581 0.0 1.0 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.8848230857171419 1.0 0.11517691428285812 );
}

relation X9 X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.45517808434332696 0.0 0.0 1.0 0.544821915656673 1.0 );
}

relation X10 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3524511562597621 0.7475288113200358 0.6475488437402379 0.2524711886799642 );
}

relation X1 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9924321631210306 0.0 0.6479020946204236 0.17512064699702642 0.007567836878969421 1.0 0.35209790537957647 0.8248793530029737 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5609703667056152 0.4390296332943849 );
}

relation X3 D2 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7506890609331054 0.09360250977157163 0.0 0.990030619850809 0.8679448801022034 1.0 0.3064378175290361 0.0 0.2493109390668947 0.9063974902284284 1.0 0.009969380149190972 0.13205511989779664 0.0 0.693562182470964 1.0 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 0.0 1.0 1.0 0.0 0.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 0.0 139.04352750621365 0.0 );
}

}
