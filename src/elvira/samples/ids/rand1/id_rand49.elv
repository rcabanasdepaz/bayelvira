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
values= table (0.07422287422679663 0.5632565230541584 0.9257771257732034 0.4367434769458416 );
}

relation X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5061163327160322 0.4896136381345602 0.4938836672839678 0.5103863618654398 );
}

relation X6 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.31517132438257345 0.23986146897248822 0.6848286756174266 0.7601385310275117 );
}

relation X7 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.28861660274883777 0.688923351944984 0.7113833972511622 0.311076648055016 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6562592458798683 0.3437407541201317 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5332402588024191 0.2842177889567985 0.4667597411975808 0.7157822110432015 );
}

relation X10 X7 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.378522908490255 0.8873777306307935 0.47388684159323297 0.3994263762885735 0.6214770915097451 0.11262226936920647 0.526113158406767 0.6005736237114264 );
}

relation X11 D1 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6052225418054019 0.38940101749357253 0.5824138797932383 0.00876570097023849 0.3947774581945981 0.6105989825064274 0.4175861202067616 0.9912342990297615 );
}

relation X1 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.23747893749812493 0.9243408104391149 0.6702811337666423 0.37673595614496386 0.762521062501875 0.0756591895608852 0.32971886623335767 0.6232640438550362 );
}

relation X2 D1 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5263929820149409 0.4867529405346897 0.04290967509012169 0.31892604368204946 0.4736070179850592 0.5132470594653102 0.9570903249098783 0.6810739563179505 );
}

relation X3 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22338499775433773 0.470988800363084 0.6586675638397966 0.29936746263802366 0.7766150022456623 0.529011199636916 0.3413324361602034 0.7006325373619764 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
