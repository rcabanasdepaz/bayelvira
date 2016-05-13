// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
}

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
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

link D1 X3;

link D1 X4;

link D1 X5;

link D1 X7;

link D1 X8;

link D1 X9;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X10 X3;

link X11 D1;

link X11 X1;

link X12 D1;

link X12 X10;

link X13 D1;

link X13 X3;

link X14 D1;

link X14 X10;

link X15 D1;

link X15 X10;

link X16 D1;

link X16 X10;

link X2 X5;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X7;

link X5 X8;

link X6 D2;

link X6 X12;

link X6 X13;

link X7 D2;

link X7 X8;

link X8 D2;

link X9 D2;

link X9 X7;

link X9 X8;

//Network Relationships: 

relation X10 X14 X15 X16 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.30215332433217407 0.8531490643484673 0.46016048762588835 0.35053307701960906 0.0 0.6501758804420406 0.0 0.030020781611523644 1.0 0.0 0.22954981634003385 0.57401191351178 0.6608868876323866 0.21997135280370061 0.4339204829075373 0.0 0.5392379496663453 0.038810729000601646 1.0 0.0 0.7248104877741504 0.3159339722438516 0.4005855142812776 0.42075114305196837 0.6978466756678259 0.14685093565153276 0.5398395123741117 0.649466922980391 1.0 0.34982411955795945 1.0 0.9699792183884764 0.0 1.0 0.7704501836599661 0.4259880864882199 0.3391131123676134 0.7800286471962994 0.5660795170924627 0.0 0.46076205033365475 0.9611892709993984 0.0 1.0 0.2751895122258496 0.6840660277561484 0.5994144857187225 0.5792488569480316 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.70182571241071 1.0 0.29817428758929 );
}

relation X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.6211400140377024 0.3111777806786539 0.3788599859622976 0.6888222193213461 );
}

relation X13 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.061314967011413216 0.7439297412847712 0.26909350058351544 0.25607025871522887 0.6695915324050714 0.0 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6838876013247108 0.31611239867528923 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5684740595012884 0.43152594049871157 );
}

relation X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8381741376348766 0.16182586236512347 );
}

relation X5 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5917299404021185 0.33498753986516805 0.7060953128853301 0.32524706446127 0.19236832768310796 0.8308640494358224 0.4082700595978815 0.6650124601348318 0.2939046871146699 0.6747529355387301 0.8076316723168919 0.16913595056417755 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X7 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.7853354234219163 1.0 0.6042954635063547 0.12008053646205713 0.48284219807977163 0.263052684549868 0.0 0.6197176480994859 1.0 1.0 0.8378720263718563 0.0 0.2146645765780837 0.0 0.39570453649364523 0.8799194635379428 0.5171578019202283 0.7369473154501319 0.0 0.38028235190051407 0.0 0.0 0.1621279736281436 );
}

relation X8 X5 X7 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.703322761626972 0.7590602629881733 0.0 1.0 0.37222287227673834 0.0 0.3574871985191221 0.8799481423567275 0.5302215243809918 0.26702080306090503 0.7076975972605131 1.0 0.0 0.0 0.44997479388244715 0.6144726554869815 0.34133837065708766 1.0 0.4520539700408115 0.9976639354032849 0.7133619221723522 0.0 0.0 1.0 0.2966772383730279 0.24093973701182658 1.0 0.0 0.6277771277232617 1.0 0.6425128014808779 0.12005185764327253 0.46977847561900826 0.732979196939095 0.2923024027394868 0.0 1.0 1.0 0.5500252061175528 0.3855273445130184 0.6586616293429123 0.0 0.5479460299591885 0.0023360645967150794 0.2866380778276477 1.0 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4692951351859579 0.5691395564592614 1.0 0.530704864814042 0.43086044354073866 0.0 );
}

relation X1 D2 X11 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.2817351801252272 0.0 0.0 0.010294825573452331 0.33790581114646767 0.0 0.9380910579592286 0.0764238607680828 0.0 0.6497638388936369 0.0 1.0 0.9523227487668979 0.17138189861076092 0.0 0.0 0.538023289074601 0.581291055720043 0.29996922073828286 0.01783407228392276 0.0 0.27481964060452135 0.528010412423881 0.6245002229317804 0.0 0.0 0.03055701992759631 0.0 0.9754980391442135 0.09626736148869144 1.0 1.0 0.24579104623752296 0.0 0.33804382293874996 0.0 0.0 1.0 0.2110668777984619 0.1505095523134503 0.5823791957550104 0.5566054447982639 0.0 0.0 0.0 0.027617100746333348 0.03330924796896734 0.0 0.04767725123310199 0.8286181013892391 0.6134282038614538 0.3459385046422681 0.02804307808977914 0.09010757091849166 0.5156934851548748 0.9680487663029146 0.0 0.17713342749478403 0.1928171791760696 0.0 1.0 0.3287513865218635 0.8117012050064436 0.0 0.024501960855786424 0.33820237223955424 0.0 0.0 0.7542089537624771 0.0 0.6451079353256942 0.0 0.7182648198747729 0.0 0.7889331222015381 0.8391956221130974 0.07971499309852194 0.44339455520173615 0.061908942040771305 0.9235761392319172 1.0 0.3226190603600298 0.9666907520310327 0.0 0.0 0.0 0.38657179613854614 0.6540614953577318 0.43393363283561986 0.3286013733614653 0.18433729410684238 0.014117161413162658 0.0 0.5480469319006945 0.2791724084000493 0.3754997770682195 0.0 0.6712486134781366 0.1577417750659601 1.0 0.0 0.5655302662717542 0.0 0.0 0.0 1.0 0.016848241735555963 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.68981950964177 1.0 1.0 0.31018049035823 0.0 0.0 );
}

relation X3 X10 X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 1.0 0.0 0.7473194655640872 0.041237616596372595 1.0 0.9199193512781528 1.0 0.0 0.0 0.8971850452924229 0.9674329101134176 0.6934406992913528 1.0 0.8627966812612525 0.3588962515640945 0.5659304429575249 0.0 0.0 0.0 1.0 0.2526805344359127 0.9587623834036274 0.0 0.0800806487218471 0.0 1.0 1.0 0.1028149547075771 0.032567089886582426 0.3065593007086473 0.0 0.13720331873874747 0.6411037484359056 0.4340695570424751 );
}

relation X4 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.06794819235066779 0.0752079451807832 0.0 0.28530963585686075 0.8035004990427889 0.0 0.6985079289096079 0.5815764087379601 0.9475416721103787 0.02607636398565167 0.07748986369837683 1.0 0.23354387873972415 0.34321564608125665 0.052458327889621396 0.6886140001574876 0.1190096372588343 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 145.32897774418308 0.0 157.25291178677355 183.39181928009327 184.81005327073083 );
}

}
