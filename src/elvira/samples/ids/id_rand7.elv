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
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
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
pos_x =4;
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

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link X1 X5;

link X10 D1;

link X10 X4;

link X11 D1;

link X11 X12;

link X11 X9;

link X12 D1;

link X12 X9;

link X2 X5;

link X4 X2;

link X5 D2;

link X5 X7;

link X6 D2;

link X6 X11;

link X6 X12;

link X7 D2;

link X8 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X10;

//Network Relationships: 

relation X9 X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5151651521093246 0.0 0.09197659062891848 0.0 0.623568356726293 0.0 0.17787770702341893 0.39093226288716965 0.899102179034977 1.0 0.13232840109292549 0.5642430444993595 0.3069571408672566 0.6090677371128302 0.008921230336104534 0.0 0.24410324218078153 0.4357569555006406 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8526007215269438 0.36808574044949693 0.6131223290503306 0.14739927847305617 0.6319142595505031 0.3868776709496695 );
}

relation X11 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.995824479118766 0.0 0.004175520881233941 0.6241925715287054 0.0 0.3758074284712946 );
}

relation X12 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.553749521386256 0.6832337835479497 1.0 0.74417727262396 0.33769560288759687 0.9638479718518125 0.4462504786137441 0.3167662164520503 0.0 0.2558227273760401 0.6623043971124031 0.0361520281481875 );
}

relation X5 X2 X8 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.026685917468593345 0.6200664181757718 0.0 0.0 0.10240980428127436 0.9949893152141608 1.0 0.0 0.1023602140715049 1.0 1.0 0.4919683593078966 0.9733140825314066 0.3799335818242282 1.0 1.0 0.8975901957187257 0.0050106847858391226 0.0 1.0 0.8976397859284951 0.0 0.0 0.5080316406921033 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5947109953061067 0.40528900469389334 );
}

relation X7 X5 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3627772729604179 0.14557197465732527 0.9474228794441324 1.0 0.5742501508241138 1.0 1.0 1.0 0.31184862101707445 0.0 0.8479615994767044 1.0 0.637222727039582 0.8544280253426748 0.05257712055586764 0.0 0.42574984917588626 0.0 0.0 0.0 0.6881513789829256 1.0 0.15203840052329567 0.0 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9723601221079983 0.0 0.0 0.02763987789200182 1.0 0.0 );
}

relation X1 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.07412509609122779 0.5150439383863129 0.45782288481378025 0.06383258803939337 0.4849560616136872 0.4334088054344721 0.8620423158693789 0.0 0.10876830975174766 );
}

relation X2 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4211636959350855 0.3311176440047697 0.0 0.19633631993853629 0.5424995815145998 0.0 0.6272510353476617 0.0 0.0 0.5788363040649145 0.6688823559952304 1.0 0.8036636800614637 0.4575004184854003 1.0 0.37274896465233837 1.0 1.0 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3301202394317576 0.9901020863034201 0.6698797605682424 0.009897913696579802 );
}

relation X4 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4320847909291617 0.2761891649429959 0.15154040839959684 0.5113431554270307 0.41637480067124144 0.21246767962997343 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (15.231929368280161 7.391916344684891 74.46321456892576 66.6232015453305 0.0 64.2317121069271 );
}

}
