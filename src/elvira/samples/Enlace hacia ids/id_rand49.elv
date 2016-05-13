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

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
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

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
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
pos_x =6;
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

link D1 X11;

link D1 X2;

link D1 X9;

link D2 V0;

link D2 X1;

link D2 X3;

link X1 X2;

link X1 X3;

link X10 D2;

link X10 X11;

link X11 D2;

link X4 D1;

link X4 X5;

link X5 D1;

link X5 X6;

link X6 D1;

link X6 X7;

link X7 D1;

link X7 X10;

link X8 D2;

link X8 X4;

link X9 D2;

link X9 X10;

//Network Relationships: 

relation X4 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5344927308677445 0.05073404537125244 0.46550726913225554 0.9492659546287476 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.43302716791089846 0.41570389919846745 0.5669728320891015 0.5842961008015325 );
}

relation X6 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.7389844543890891 0.0 0.26101554561091084 );
}

relation X7 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4038846867693738 0.0 0.5961153132306263 1.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6084307958400806 0.3915692041599193 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.3307153151537614 1.0 0.6692846848462387 );
}

relation X10 X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6452498157227743 0.35821834722601364 1.0 0.2589543651558316 0.35475018427722566 0.6417816527739864 0.0 0.7410456348441683 );
}

relation X11 X10 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.4845450103453982 0.0 1.0 0.0 0.5154549896546018 1.0 );
}

relation X1 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.41814560987114163 0.5563385512814534 0.5863455670650194 0.4477731946885827 0.5818543901288583 0.44366144871854657 0.4136544329349806 0.5522268053114173 );
}

relation X2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.33706805026643566 0.6482830302861998 0.5411526289409934 0.4051239151848334 0.6629319497335644 0.3517169697138002 0.45884737105900675 0.5948760848151666 );
}

relation X3 X1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.9360158255195441 1.0 0.0 0.0 0.06398417448045592 0.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (126.61411397221849 110.70471913865515 129.60769677171908 113.69830193815574 );
}

}
