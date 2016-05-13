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

link X11 X2;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 V0;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X2;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18048292742969205 0.509586234179106 0.8195170725703079 0.490413765820894 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9610056862656025 0.03899431373439749 );
}

relation X13 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.43405994289255656 0.3188466667138161 0.5659400571074433 0.6811533332861839 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45648539386541187 0.4366912796024823 0.543514606134588 0.5633087203975177 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.38294405471489623 0.527817228405903 0.6170559452851038 0.4721827715940971 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8831914365750804 0.11680856342491963 );
}

relation X7 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4729303409119507 0.9620854057799377 0.40882295348353936 0.6905978079693423 0.5270696590880493 0.03791459422006231 0.5911770465164606 0.30940219203065766 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.49068482103571925 0.1867387571133444 0.5093151789642808 0.8132612428866556 );
}

relation X9 X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6641650563135145 0.41246639390731327 0.4269867227842422 0.6548004624537248 0.33583494368648537 0.5875336060926867 0.5730132772157577 0.34519953754627525 );
}

relation X10 D1 X1 X14 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20371103483380615 0.8020751456787292 0.6747649929227917 0.6627167723902826 0.39680008823538765 0.5672052221710621 0.6371934825250539 0.8514344334056325 0.23747000801782528 0.09372851085336983 0.8440811551578408 0.23164996521597095 0.7523758225716524 0.4960037648016213 0.20329125058835731 0.08049905644087535 0.7962889651661939 0.19792485432127083 0.32523500707720837 0.3372832276097174 0.6031999117646124 0.432794777828938 0.36280651747494613 0.1485655665943674 0.7625299919821747 0.9062714891466301 0.1559188448421592 0.7683500347840291 0.24762417742834766 0.5039962351983787 0.7967087494116427 0.9195009435591246 );
}

relation X1 D1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.43992115106508684 0.6843555268547932 0.5883472181233201 0.45103080665577366 0.5600788489349131 0.3156444731452068 0.41165278187668003 0.5489691933442263 );
}

relation X2 X11 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1866793951919866 0.8288391908905504 0.4424361022332131 0.4026176261751252 0.8133206048080134 0.17116080910944959 0.5575638977667868 0.5973823738248747 );
}

relation X3 D1 D2 X2 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3394483616533519 0.4051874170419857 0.44528010751761227 0.5346023841101596 0.5086630135038865 0.6820432767440147 0.8108800543540742 0.15240295746438073 0.663331327277337 0.7245735297447684 0.7335342271944764 0.5376144860107674 0.9388974915309953 0.4591024559917422 0.5350055854989061 0.6234493996434727 0.6605516383466481 0.5948125829580143 0.5547198924823877 0.46539761588984035 0.4913369864961135 0.31795672325598545 0.18911994564592574 0.8475970425356193 0.336668672722663 0.2754264702552316 0.2664657728055236 0.4623855139892325 0.061102508469004696 0.5408975440082577 0.4649944145010939 0.3765506003565272 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2704410111936531 0.8711943123086409 0.6292308186238575 0.6747454891413697 0.7295589888063468 0.12880568769135917 0.3707691813761425 0.3252545108586304 );
}

relation V0 D1 D2 X2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (184.01230226394532 170.80454020508907 144.076786789529 130.86902473067278 214.48985732679193 201.2820952679357 174.55434185237564 161.3465797935194 );
}

}
