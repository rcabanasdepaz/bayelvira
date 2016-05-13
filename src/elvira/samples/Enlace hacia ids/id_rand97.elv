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

link X1 X10;

link X10 D2;

link X10 X9;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X6 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8694123984297439 1.0 0.3178535607080256 0.0 0.1305876015702561 0.0 0.6821464392919744 1.0 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5359262844856344 0.46407371551436566 );
}

relation X13 X2 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.006697838930996991 0.0 0.24856726382390373 0.0 0.993302161069003 1.0 0.7514327361760963 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7914943909545115 0.0 0.20850560904548854 1.0 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.505584085183141 0.7683245839973452 0.49441591481685904 0.2316754160026549 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X7 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6038891922497767 0.1306609791544649 0.40473756472017763 0.8408769659689815 0.3961108077502233 0.869339020845535 0.5952624352798224 0.15912303403101838 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18892054053708832 0.5350103903487404 0.8110794594629117 0.4649896096512595 );
}

relation X9 X5 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4176543339612739 0.23478598100744244 1.0 0.0 0.5823456660387262 0.7652140189925576 0.0 1.0 );
}

relation X10 X1 X14 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 0.7623298239051238 0.457537001538365 0.0 1.0 0.0 0.7249934895621658 0.617850036265589 0.6124804448248613 0.5327148222053455 1.0 0.7867537972181428 0.0 0.5835755097317025 1.0 0.0 0.0 0.23767017609487615 0.542462998461635 1.0 0.0 1.0 0.27500651043783414 0.38214996373441107 0.38751955517513875 0.46728517779465445 0.0 0.21324620278185716 1.0 0.41642449026829764 );
}

relation X1 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.4037782408626721 0.491152742905951 0.0 0.0 0.596221759137328 0.5088472570940489 1.0 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5149682881891987 0.4850317118108013 );
}

relation X3 D2 X2 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.2862290931551062 1.0 0.4938412728630462 0.0 0.23360580927310773 0.0 1.0 0.37357658567242885 0.9045154697547753 0.7914529606122158 0.0 0.22818512630159496 1.0 0.0 0.8830703122920989 1.0 0.7137709068448939 0.0 0.5061587271369539 1.0 0.7663941907268922 1.0 0.0 0.6264234143275712 0.09548453024522474 0.2085470393877843 1.0 0.771814873698405 0.0 0.0 0.11692968770790123 );
}

relation X4 X2 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.8711380015151057 0.9696232966459565 0.0 0.0 0.1288619984848942 0.030376703354043518 1.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (133.13478811776312 163.82242414574554 146.15730176959343 176.84493779757585 );
}

}
