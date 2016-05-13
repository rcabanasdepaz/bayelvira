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
values= table (0.45051577894768646 0.5494842210523135 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6176837133473934 0.4472746254039183 0.38231628665260653 0.5527253745960816 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5010503571727232 0.4989496428272769 );
}

relation X7 X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7047723991094863 0.6029476719647416 0.5099010748994515 0.8640852814538742 0.2952276008905136 0.3970523280352584 0.4900989251005484 0.13591471854612575 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.29191922152846933 0.8901400796910042 0.7080807784715306 0.1098599203089958 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6113185218029791 0.1331809081505112 0.3886814781970208 0.8668190918494888 );
}

relation X1 D2 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4756104771368459 0.6006416076619718 0.7347334095015474 0.5586063478422596 0.5243895228631541 0.3993583923380282 0.2652665904984526 0.44139365215774035 );
}

relation X2 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.788668671123137 0.5341521916865428 0.37951653126359 0.5008704711558615 0.6143599449488939 0.6046591408143169 0.11428056399902149 0.969767310269538 0.211331328876863 0.4658478083134573 0.62048346873641 0.4991295288441384 0.3856400550511061 0.395340859185683 0.8857194360009785 0.030232689730462073 );
}

relation X3 X1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5810207354190271 0.43653927522889735 0.8585642995535085 0.2858744663604531 0.4189792645809729 0.5634607247711025 0.14143570044649156 0.7141255336395469 );
}

relation V0 D1 D2 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (184.01230226394532 170.80454020508907 144.076786789529 130.86902473067278 214.48985732679193 201.2820952679357 174.55434185237564 161.3465797935194 );
}

}
