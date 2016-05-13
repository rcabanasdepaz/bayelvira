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
num-states = 3;
states = (x8_1 x8_2 x8_3);
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
num-states = 4;
states = (d1_1 d1_2 d1_3 d1_4);
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
num-states = 4;
states = (d2_1 d2_2 d2_3 d2_4);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x1_1 x1_2 x1_3 x1_4);
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
num-states = 3;
states = (x3_1 x3_2 x3_3);
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

link X1 X3;

link X2 X1;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X5;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.3065392670989729 1.0 0.0 1.0 1.0 0.0 0.6934607329010272 0.0 1.0 0.0 0.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7571152350495978 0.23397648369257124 0.008908281257831099 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.937892921216147 0.062107078783853124 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4179745842461488 0.28533994779142996 0.4384234988087218 0.768917807776137 0.6080247720148921 0.0563009378961822 1.0 0.0 0.4202449997182259 0.6144502385747136 0.4340444473911016 0.9856504921757799 0.5820254157538511 0.7146600522085701 0.5615765011912782 0.23108219222386292 0.39197522798510787 0.9436990621038178 0.0 1.0 0.5797550002817742 0.38554976142528646 0.5659555526088985 0.014349507824219995 );
}

relation X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9751492620290451 0.06244219407204807 0.024850737970954967 0.8864037879013315 0.0 0.05115401802662058 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9025930566419581 1.0 0.7291397442787976 0.0 0.054720666042720155 0.9811361172266168 0.9779620320750123 1.0 0.0974069433580419 0.0 0.27086025572120237 1.0 0.9452793339572798 0.018863882773383214 0.02203796792498772 0.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0895657849466543 0.5701341025385551 0.558813540866895 0.28905668278870095 0.580061521201953 0.0 0.14008529661812602 0.0 0.33037269385139273 0.4070759100477542 0.29276199577925194 0.6750649321271587 0.0 0.022789987413690732 0.008339166735726978 0.03587838508414032 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5663658601730865 0.5863673781820484 0.6445603355590502 0.0 0.0 0.8789310436373071 0.0 0.9395519487003036 0.8587537181468763 0.16650208430288818 0.4823476957187631 0.0 0.0 0.8113757168292265 0.24564334500103754 0.4915833266289962 0.43363413982691335 0.41363262181795146 0.3554396644409498 1.0 0.0 0.12106895636269281 1.0 0.06044805129969644 0.14124628185312368 0.8334979156971118 0.5176523042812369 0.0 1.0 0.18862428317077345 0.7543566549989623 0.5084166733710038 );
}

relation X3 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.916662456879474 0.5792011171390139 0.09331501779690214 0.6740115724621275 0.9984870415954872 0.0 0.0 0.0 0.0 0.7724590591713789 0.8353571438988214 0.001348576695283842 0.49809697947722 0.0 1.0 1.0 0.08333754312052603 0.09389956031642491 0.9066849822030978 0.0 0.0015129584045127584 0.869066458672852 0.0 1.0 1.0 0.22754094082862109 0.16464285610117863 0.998651423304716 0.05919923722944077 1.0 0.0 0.0 0.0 0.3268993225445611 0.0 0.32598842753787255 0.0 0.13093354132714805 0.0 0.0 0.0 0.0 0.0 0.0 0.44270378329333915 0.0 0.0 0.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 106.46320728178482 75.99462298879608 52.901783942687416 87.13011625100569 110.84017125263746 80.37158695964872 57.27874791354006 0.0 144.23819909989274 0.0 90.67677576079535 76.5081768380814 0.0 69.74964754672442 46.65680850061576 );
}

}
