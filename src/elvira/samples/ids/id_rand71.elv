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

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
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

link D1 X5;

link D1 X6;

link D1 X7;

link D2 V0;

link D2 X3;

link D2 X4;

link X10 D1;

link X10 V0;

link X11 D1;

link X11 X10;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 V0;

link X13 X5;

link X13 X7;

link X13 X8;

link X2 X4;

link X3 X1;

link X5 D2;

link X5 V0;

link X6 D2;

link X6 X5;

link X7 D2;

link X7 V0;

link X8 D2;

link X8 X5;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8557153981944313 0.14428460180556868 );
}

relation X10 X9 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.342584788217578 0.6919397656755876 0.4357635416493342 0.0 0.657415211782422 0.30806023432441243 0.5642364583506658 1.0 );
}

relation X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7576990395334414 0.0 0.24230096046655847 1.0 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2754064299364996 0.7245935700635003 );
}

relation X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X5 X13 X6 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.5075512825824363 0.0026418249586244377 1.0 0.2041374378318934 1.0 0.0 0.0 1.0 0.0 0.8093517542734923 0.0 0.0 1.0 0.67627618341078 0.0 1.0 0.4924487174175637 0.9973581750413755 0.0 0.7958625621681066 0.0 0.0 1.0 0.0 1.0 0.19064824572650776 1.0 1.0 0.0 0.3237238165892201 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.9013733075785617 1.0 0.09862669242143823 );
}

relation X7 X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7847110583407215 0.0 0.8748427089300949 1.0 0.21528894165927848 1.0 0.1251572910699051 );
}

relation X8 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 1.0 );
}

relation X1 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 0.5137881505308193 0.13585159315305415 0.0 0.0 0.4862118494691807 0.8641484068469459 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6152029659702974 0.41504791338774816 0.3847970340297026 0.5849520866122518 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4493130900404619 1.0 0.4895155351537268 1.0 0.5506869099595381 0.0 0.5104844648462732 0.0 );
}

relation V0 D1 D2 X10 X13 X5 X7 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 312.09464088649696 365.61471393445356 379.16242376077145 230.2662417561029 243.8139515824208 0.0 310.8817344566953 377.86193888383457 391.40964871015245 444.92972175810905 458.47743158442694 309.58124957975843 323.1289594060763 376.649032454033 0.0 310.72427618455947 324.27198601087736 0.0 391.33976888515184 0.0 0.0 309.5113697547579 0.0 390.039284008215 403.5869938345329 457.10706688248956 470.65477670880745 321.75859470413883 335.3063045304567 388.82637757841337 402.37408740473126 300.3438402283287 0.0 0.0 380.95933292892107 232.0631509242525 0.0 0.0 0.0 0.0 393.2065578783021 446.72663092625874 460.2743407525766 0.0 324.92586857422594 378.44594162218254 391.99365144850043 0.0 0.0 379.58896822698364 393.1366780533015 244.24049604863296 257.78820587495085 311.3082789229075 0.0 391.8361931763646 405.3839030026825 458.90397605063913 0.0 323.55550387228845 337.10321369860634 390.62328674656294 404.1709965728808 );
}

}
