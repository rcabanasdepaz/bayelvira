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

relation X9 X12 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22642401782958482 0.32656344075015226 0.3783206294853032 0.5824473217942269 0.7735759821704152 0.6734365592498478 0.6216793705146968 0.41755267820577313 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5083140476314465 0.49168595236855356 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3374918273330653 0.4763762783625176 0.6625081726669347 0.5236237216374824 );
}

relation X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3673150934017739 0.3537533146744302 0.6326849065982262 0.6462466853255697 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4958907414519964 0.5041092585480036 );
}

relation X6 D1 X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6549578943628123 0.3033658580908442 0.12862267674550903 0.1861459743441275 0.436360702989596 0.704391416678265 0.86921304428411 0.7600455349641494 0.3450421056371878 0.696634141909156 0.8713773232544909 0.8138540256558725 0.5636392970104039 0.2956085833217351 0.13078695571589005 0.23995446503585055 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.002490927487636568 0.27727013845654547 0.9975090725123634 0.7227298615434545 );
}

relation X8 X11 X6 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3455389073648384 0.5505407944037344 0.3863081498024566 0.37337356153222845 0.5265791750186009 0.2473362502844261 0.1404233261722606 0.5022514757902984 0.6544610926351616 0.44945920559626557 0.6136918501975434 0.6266264384677716 0.47342082498139915 0.7526637497155739 0.8595766738277394 0.49774852420970167 );
}

relation X1 D1 D2 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4406709921540429 0.6524193285108089 0.2776147010785952 0.2491775224406398 0.5558235268359436 0.6343607132146274 0.5577090855770056 0.6930787206712458 0.5593290078459571 0.3475806714891911 0.7223852989214049 0.7508224775593602 0.4441764731640564 0.3656392867853726 0.44229091442299445 0.3069212793287543 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8817130398248866 0.5134056019080769 0.1182869601751134 0.48659439809192306 );
}

relation X3 D1 X4 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4438981477084426 0.5690840256242363 0.9788736283104047 0.13873556423190722 0.23681796334065353 0.3397836515447317 0.6107110259892259 0.45563654851823804 0.5561018522915574 0.4309159743757636 0.02112637168959533 0.8612644357680928 0.7631820366593465 0.6602163484552683 0.38928897401077417 0.544363451481762 );
}

relation X4 X2 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.17885290903143905 0.5670214483441474 0.8818151635947147 0.6999427713582472 0.821147090968561 0.43297855165585264 0.11818483640528543 0.30005722864175277 );
}

relation V0 D1 D2 X3 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (184.01230226394532 170.80454020508907 144.076786789529 130.86902473067278 214.48985732679193 201.2820952679357 174.55434185237564 161.3465797935194 );
}

}
