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
pos_x =7;
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

link D1 X2;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X12;

link X11 X8;

link X12 D1;

link X4 X3;

link X5 D2;

link X5 X6;

link X5 X7;

link X6 D2;

link X7 D2;

link X7 X8;

link X7 X9;

link X8 X12;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7427346779587796 0.7375104058967112 0.2572653220412204 0.2624895941032888 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45795326379788653 0.4561024006750162 0.5420467362021135 0.5438975993249838 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3394920537147137 0.30073749949991047 0.6605079462852863 0.6992625005000894 );
}

relation X12 X11 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2836824117195378 0.11642033142514902 0.6558497648696144 0.7275668646171541 0.7163175882804621 0.8835796685748509 0.34415023513038556 0.2724331353828459 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45050094982781724 0.5494990501721828 );
}

relation X6 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3391364258917396 0.23446125283151037 0.4558356960367651 0.27126403693770584 0.6608635741082604 0.7655387471684896 0.5441643039632348 0.7287359630622943 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5274942508383689 0.4430728185180765 0.47250574916163113 0.5569271814819234 );
}

relation X8 X11 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5637482006976994 0.4205442014227496 0.3748912726178332 0.2782745915169386 0.43625179930230074 0.5794557985772504 0.6251087273821667 0.7217254084830613 );
}

relation X1 D1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6984319710471513 0.8172626435579824 0.5576621391884002 0.2286247951496934 0.30156802895284873 0.18273735644201747 0.44233786081159965 0.7713752048503066 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24850961904081328 0.5759701611556443 0.7514903809591866 0.42402983884435574 );
}

relation X3 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5639586345594944 0.5778758265987696 0.7137561433055868 0.7390055825701547 0.43604136544050554 0.42212417340123054 0.28624385669441327 0.2609944174298452 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.07582049907059514 0.9241795009294048 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
