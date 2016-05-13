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
values= table (1.0 0.0 0.45622005278504263 0.5872488109943443 0.0 0.0 0.5437799472149574 0.4127511890056557 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9592556975578141 0.04074430244218585 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3498882754736152 0.6581205018830267 1.0 1.0 0.9404880500177241 0.0013090957375888447 0.900264508733099 0.08590166613808982 0.9042096633954443 0.6501117245263849 0.3418794981169733 0.0 0.0 0.059511949982275894 0.9986909042624111 0.09973549126690105 0.9140983338619102 0.09579033660455578 );
}

relation X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8941900730372999 0.29402174293473227 0.0011088131898064105 0.3491335488132935 0.1047011137728938 0.35684470825197434 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.2440986715297459 0.23592750865023301 0.5287000793514777 0.0 0.0 1.0 0.7559013284702542 0.764072491349767 0.4712999206485223 1.0 1.0 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8695101337210457 0.0 0.0014268643992728196 0.8502899363839072 0.02539817568570638 0.5463435441867299 0.9985731356007272 0.0 0.10509169059324809 0.45365645581327 0.0 0.14971006361609288 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.434196173895731 1.0 0.0 0.9465687383597653 0.8359678474301587 1.0 0.565803826104269 0.0 0.0 0.05343126164023471 0.16403215256984133 0.0 );
}

relation X3 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9836995870979756 0.1597400879163659 0.8693734832287541 0.0 0.3642507649137163 0.5143597536481116 0.0 0.0 0.9161265864968083 0.016300412902024507 0.8402599120836342 0.13062651677124595 1.0 0.6357492350862838 0.4856402463518885 1.0 0.0 0.08387341350319169 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (95.4125733261107 30.635049579171483 90.64288185543643 25.865358108497194 0.0 99.79414075819797 );
}

}
