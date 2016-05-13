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
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
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
num-states = 3;
states = (d1_1 d1_2 d1_3);
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
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
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
pos_x =6;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =5;
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

link D2 V0;

link D2 X3;

link X1 V0;

link X1 X7;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X9;

link X12 D1;

link X12 X10;

link X12 X13;

link X12 X2;

link X12 X9;

link X13 X11;

link X13 X3;

link X2 X10;

link X4 X2;

link X5 D2;

link X5 X6;

link X6 D2;

link X7 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X1;

//Network Relationships: 

relation X9 X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5815952560133683 0.0 0.2319773461231064 0.0 0.0 0.597786548222963 0.41840474398663174 0.7732625084046157 0.4679257812968224 0.11504163922328116 0.6697306082635923 0.37553266803868135 0.0 0.22673749159538434 0.30009687258007123 0.8849583607767189 0.3302693917364076 0.026680783738355644 );
}

relation X10 X2 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3236281258390624 0.6954231319443509 1.0 0.16296365903725113 0.6763718741609376 0.30457686805564904 0.0 0.8370363409627488 );
}

relation X11 X13 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.502091471174303 0.12895093322324866 1.0 0.0 0.2736777308157019 0.0 0.0 1.0 0.22423079800999518 0.8710490667767513 0.0 0.0 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X13 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.8102697307172676 1.0 0.18973026928273246 );
}

relation X5 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.924000845369743 1.0 0.5823844378390829 0.2645557607106923 0.0637360312080081 1.0 0.07599915463025701 0.0 0.41761556216091716 0.7354442392893076 0.9362639687919919 0.0 );
}

relation X6 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9382579082052723 0.0 0.5415481044250464 0.0 0.26864429145438995 0.0 0.061742091794727616 0.0 0.45845189557495364 1.0 0.73135570854561 0.0 );
}

relation X7 X8 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.07322388121952166 0.3076926804829579 0.0 0.9308565253240161 0.0 1.0 0.9267761187804784 0.6923073195170422 0.0 0.06914347467598388 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.791833207346202 0.20816679265379803 );
}

relation X1 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.34083827161381464 0.12144316430497643 0.7149504005379822 0.20951663020186667 0.8067885759229736 0.6530884127586112 0.29485372983865216 0.0 0.40912544909217247 0.12311649524134459 0.737421262083594 0.2850495994620178 0.4151962245134643 0.19321142407702638 0.3469115872413888 0.3151853884041957 0.40626677589333815 0.5662803598679786 0.5360452331448408 0.14113557361142956 0.0 0.3752871452846691 0.0 0.0 0.38996088175715204 0.5937332241066617 0.024594191039848827 );
}

relation X2 X4 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 0.0 1.0 1.0 0.0 0.0 0.0 1.0 0.0 0.0 );
}

relation X3 X13 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.39905604388141624 0.0 0.9034332358125227 0.0 0.6009439561185838 1.0 0.09656676418747735 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.13926647074720652 0.847884428651484 0.012849100601309473 );
}

relation V0 D1 D2 X1 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (45.47471162458996 46.84598223971314 50.213718916843305 12.150345414855423 13.5216160299786 16.889352707108774 134.51935029841542 135.89062091353858 139.25835759066877 101.19498408868087 102.56625470380405 105.93399138093422 130.68664006097356 0.0 0.0 97.36227385123904 0.0 102.10128114349239 );
}

}
