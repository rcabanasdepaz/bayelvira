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

link D1 X10;

link D1 X2;

link D1 X8;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X1 X2;

link X1 X3;

link X10 D2;

link X4 D1;

link X4 X3;

link X5 D1;

link X5 X1;

link X6 D1;

link X6 X4;

link X7 D2;

link X7 X4;

link X8 D2;

link X8 X10;

link X9 D2;

link X9 X10;

link X9 X4;

//Network Relationships: 

relation X4 X7 X9 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.22991910187722744 0.0 0.06445188896099664 0.5738936692322205 0.42919292528707614 0.21955092966722972 1.0 1.0 0.7700808981227726 0.0 0.9355481110390034 0.4261063307677795 0.5708070747129238 0.7804490703327703 0.0 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20399434624914986 0.7708760893948737 0.7960056537508502 0.22912391060512638 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6829874275885386 0.31701257241146147 );
}

relation X10 X8 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8082716311688674 1.0 1.0 0.8091674847656858 1.0 0.42302102231719263 0.5129769147775906 0.46141828132920826 0.19172836883113262 0.0 0.0 0.1908325152343142 0.0 0.5769789776828074 0.4870230852224095 0.5385817186707917 );
}

relation X1 X5 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 1.0 0.03847068431239993 0.0 1.0 0.0 0.9615293156876001 );
}

relation X2 D2 X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8516273997171587 1.0 0.0 0.009455414806403047 1.0 0.0 0.0 0.8812198008479831 0.14837260028284116 0.0 0.0 0.9905445851935969 0.0 1.0 1.0 0.11878019915201689 );
}

relation X3 X1 X4 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24218228540486633 1.0 0.0 1.0 0.11464796171291479 0.9236183655183713 0.0 0.3184577474795914 0.7578177145951337 0.0 1.0 0.0 0.8853520382870852 0.07638163448162878 1.0 0.6815422525204086 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (144.2589409505312 70.26580001404831 94.34143863864898 0.0 );
}

}
